#include <stdlib.h>
#include <string.h>

#include "libxml/parser.h"
#include "libxml/tree.h"

#define WF_STR_SIZE 1024

enum action {
    WF_ACTION_AUGMENT,
    WF_ACTION_INSPECT
};

struct entry {
    struct entry* children;
    char name[WF_STR_SIZE];
    char title[WF_STR_SIZE];
    struct entry* next;
};

void entry_add(struct entry* parent, struct entry* child) {
    if (parent->children==NULL) {
        parent->children=child;
    } else {
        struct entry* tmp=parent->children;
        while (tmp->next!=NULL) {
            tmp = tmp->next;
        }
        tmp->next = child;
    }
}

struct entry* entry_create(char* name, char* title) {
    struct entry* new;
    if ((new=malloc(sizeof(struct entry)))==NULL) {
        perror("malloc failed.");
        exit(1);
    }
    memset(new, 0, sizeof(struct entry));
    strncpy(new->name, name, WF_STR_SIZE-1);
    strncpy(new->title, title, WF_STR_SIZE-1);
    return new;
}

void entry_free(struct entry** entry) {
    if ((*entry)->children!=NULL) {
        entry_free(&(*entry)->children);
    }
    if ((*entry)->next!=NULL) {
        entry_free(&(*entry)->next);
    }
    free(*entry);
    *entry = NULL;
}

struct entry* entry_has(struct entry* entry, char* search_name) {
    struct entry* child = entry->children;
    if (strncmp(entry->name, search_name, WF_STR_SIZE)==0) {
        return entry;
    }
    while (child!=NULL) {
        struct entry* found = entry_has(child, search_name);
        if (found!=NULL) return found;
        child = child->next;
    }
    return NULL;
}

struct entry* entry_parse(xmlNodePtr element) {
    xmlChar* entry_name = xmlGetProp(element, BAD_CAST "name");
    xmlChar* entry_title= xmlGetProp(element, BAD_CAST "title");
    xmlNodePtr child = element->children;
    struct entry* new;

    if (entry_name==NULL) {
        printf("ERROR: parsing XML sitemap: missing entry name.\n");
        exit(1);
    }
    if (entry_title==NULL) {
        printf("ERROR: parsing XML sitemap: missing entry title.\n");
        exit(1);
    }
    new = entry_create((char*) entry_name, (char*) entry_title);
    xmlFree(entry_name);
    xmlFree(entry_title);

    while (child!=NULL) {
        if ( (child->type==XML_ELEMENT_NODE) && (strncmp((char*)child->name, "entry", WF_STR_SIZE)==0)) {
            struct entry* child_entry = entry_parse(child);
            entry_add(new, child_entry);
        }
        child = child->next;
    }
    return new;
}

void entry_print(struct entry* entry, int indent) {
    int x;

    for (x=0; x<indent; x++) {
        printf(" ");
    }
    printf("* %s\n", entry->title);
    if (entry->children!=NULL) {
        entry_print(entry->children, indent+4);
    }
    if (entry->next != NULL) {
        entry_print(entry->next, indent);
    }
}

void entry_store(xmlNodePtr element, struct entry* entry, int save_children, char* search_name) {
    xmlNodePtr entry_element = xmlNewChild(element, NULL, BAD_CAST "entry", NULL);
    struct entry* child = entry->children;

    xmlNewProp(entry_element, BAD_CAST "name", BAD_CAST entry->name);
    xmlNewProp(entry_element, BAD_CAST "title", BAD_CAST entry->title);

    if (search_name==NULL || entry_has(entry, search_name)) {
        while (save_children==1 && child!=NULL) {
            entry_store(entry_element, child, 1, search_name);
            child = child->next;
        }
    }
}

void usage() {
     printf("WF-Augment/Inspect\n2009 by thom\nUsage:\n");
     printf("\twf augment <sitemapfile> <pagefile>\n");
     printf("\twf inspect <sitemapfile>\n");
     exit(1);
}

int main(int argc, char **argv) {
    char* actionstr;
    char* sitemapfile;
    char* pagefile;
    xmlDocPtr sitemap_doc;
    xmlNodePtr sitemap_doc_root;
    struct entry* sitemap_root;
    enum action action;

    if (argc<2) {
        usage();
    }
    actionstr = argv[1];

    if (strncmp(actionstr, "augment", WF_STR_SIZE)==0) {
        action = WF_ACTION_AUGMENT;
        if (argc!=4) usage();
        sitemapfile = argv[2];
        pagefile = argv[3];
    } else if (strncmp(actionstr, "inspect", WF_STR_SIZE)==0) {
        action = WF_ACTION_INSPECT;
        if (argc!=3) usage();
        sitemapfile = argv[2];
    } else {
        usage();
    }

    /* read sitemap: */
    sitemap_doc = xmlReadFile(sitemapfile, "ISO-8859-1", 0);
    if (sitemap_doc==NULL) {
         printf("ERROR: reading XML sitemap.\n");
         exit(1);
     }
    sitemap_doc_root = xmlDocGetRootElement(sitemap_doc);
    sitemap_root = entry_parse(sitemap_doc_root);

    if (action==WF_ACTION_INSPECT) {
        entry_print(sitemap_root, 4);
    }
    if (action==WF_ACTION_AUGMENT) {
        xmlDocPtr  page_doc;
        xmlNodePtr page_doc_root, child, topics, navigation, title;
        struct entry* sitemap_child = sitemap_root->children;
        struct entry* sitemap_entry = entry_has(sitemap_root, pagefile);

        printf("use \"%s\" to augment \"%s\"...\n", sitemapfile, pagefile);
        if (sitemap_entry==NULL) {
            printf("\tERROR: page not found in sitemap.\n");
            exit(1);
        }

        /* read page: */
        page_doc = xmlReadFile(pagefile, "ISO-8859-1", XML_PARSE_NOBLANKS);
        if (page_doc==NULL) {
             printf("ERROR: reading XML page.\n");
             exit(1);
        }
        xmlThrDefIndentTreeOutput(1);
        page_doc_root = xmlDocGetRootElement(page_doc);

        /* update title: */
        child = page_doc_root->children;
        title = NULL;
        while (child != NULL) {
            if (child->type == XML_ELEMENT_NODE && strncmp((char*) child->name,
                    "title", WF_STR_SIZE) == 0) {
                title = child;
            }
            child = child->next;
        }
        if (title != NULL) {
            xmlUnlinkNode(title);
            xmlFreeNode(title);
        }
        xmlNewChild(page_doc_root, NULL, BAD_CAST "title", BAD_CAST sitemap_entry->title);

        /* update topics (top sitemap entries): */
        child = page_doc_root->children;
        topics = NULL;
        while (child!=NULL) {
            if (child->type == XML_ELEMENT_NODE && strncmp((char*)child->name, "topics", WF_STR_SIZE)==0) {
                topics = child;
            }
            child = child->next;
        }
        if (topics!=NULL) {
            xmlUnlinkNode(topics);
            xmlFreeNode(topics);
        }
        topics = xmlNewChild(page_doc_root, NULL, BAD_CAST "topics", NULL);
        sitemap_child = sitemap_root->children;
        while (sitemap_child!=NULL) {
            entry_store(topics, sitemap_child, 0, NULL);
            sitemap_child = sitemap_child->next;
        }

        /* update navigation tree: */
        child = page_doc_root->children;
        navigation = NULL;
        while (child!=NULL) {
            if (child->type == XML_ELEMENT_NODE && strncmp((char*)child->name, "navigation", WF_STR_SIZE)==0) {
                navigation = child;
            }
            child = child->next;
        }
        if (navigation!=NULL) {
             xmlUnlinkNode(navigation);
             xmlFreeNode(navigation);
        }
        navigation = xmlNewChild(page_doc_root, NULL, BAD_CAST "navigation", NULL);
        sitemap_child = sitemap_root->children;
        while (sitemap_child!=NULL) {
            if (entry_has(sitemap_child, pagefile)!=NULL && sitemap_child->children!=NULL) {
                entry_store(navigation, sitemap_child, 1, pagefile);
            }
            sitemap_child = sitemap_child->next;
        }



        if (xmlSaveFormatFileEnc(pagefile, page_doc, "ISO-8859-1", 1)==-1) {
                fprintf(stderr, "ERROR: Could not write to XML page.\n");
                exit(1);
        }
        xmlFreeDoc(page_doc);
        printf("\tDone.\n");

    }



    exit(0);
}
