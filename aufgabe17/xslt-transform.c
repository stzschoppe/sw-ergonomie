#include <stdlib.h>


#include <libxml/tree.h>
#include <libxslt/xslt.h>
#include <libxslt/xsltInternals.h> /*xsltStylesheetPtr */
#include <libxslt/transform.h> /* xsltApplyStylesheet() */
#include <libxslt/xsltutils.h> /* xsltSaveResultToFilename */


int main(int argc, char **argv) {
     xsltStylesheetPtr stylesheet = NULL;
     xmlDocPtr doc, res;
     char* stylesheetfile;
     char* infile;
     char *outfile;

     if (argc!=4) {
         printf("XSLT Transform\n2009 by thom using libxml2\nUsage:\n\txslt-transform <stylesheet> <infile> <outfile>\n");
         exit(1);
     }

     stylesheetfile = argv[1];
     infile = argv[2];
     outfile = argv[3];


     xmlSubstituteEntitiesDefault(1);
     xmlLoadExtDtdDefaultValue = 1;
     stylesheet = xsltParseStylesheetFile(BAD_CAST stylesheetfile);
     doc = xmlParseFile(infile);
     res = xsltApplyStylesheet(stylesheet, doc, 0);
     printf("use \"%s\" to transform \"%s\" to \"%s\" ...\n", stylesheetfile, infile, outfile);
     xsltSaveResultToFilename(outfile, res, stylesheet, 0);
     xsltFreeStylesheet(stylesheet);
     xmlFreeDoc(res);
     xmlFreeDoc(doc);
     xsltCleanupGlobals();
     xmlCleanupParser();
     printf("\tDone.\n");
     exit(0);
}
