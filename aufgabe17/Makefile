FLAGS=-Wall -pedantic -L/usr/lib -I/usr/include -I/usr/include/libxml2 \
	-lxml2 -L/usr/lib -lxslt
CC=gcc

all:	wf xslt-transform

clean:
	-rm -rf *~
	-rm -rf xslt-transform wf

wf:	wf.c
	$(CC) $(FLAGS) wf.c -o wf

xslt-transform:	xslt-transform.c
				$(CC) $(FLAGS) xslt-transform.c -o xslt-transform
				
