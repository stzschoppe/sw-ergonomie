
test:
	./wf inspect test-sitemap.xml
	./wf augment test-sitemap.xml test-start.xml
	./wf augment test-sitemap.xml test-another.xml
	./wf augment test-sitemap.xml test-bnother.xml
	./wf augment test-sitemap.xml test-bnother-1st.xml
	./wf augment test-sitemap.xml test-bnother-2nd.xml
	./xslt-transform test.xsl test-start.xml test-start.html
	./xslt-transform test.xsl test-another.xml test-another.html
	./xslt-transform test.xsl test-bnother.xml test-bnother.html
	./xslt-transform test.xsl test-bnother-1st.xml test-bnother-1st.html
	./xslt-transform test.xsl test-bnother-2nd.xml test-bnother-2nd.html
