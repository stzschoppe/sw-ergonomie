<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<!-- match all the document -->
<xsl:template match="/">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<style type="text/css">
body {
background-color: #E0E0E0;
font-family: Arial, Helvetica, sans-serif;
font-size: 11pt;
}
#contentPane {
padding: 5px;
border: 1px solid black; background-color: #FFFFFF;
}
#descriptionPane {
padding: 5px;
padding-top: 0px;
font-style: italic;
}
#titlePane {
padding: 5px;
width: 150px;
border: 1px solid black;
background-color: #FFFFFF;
font-weight: bold;
}
</style>
<title>Test</title>
</head>
<body>
<table style="margin: 20">
<tr><td width="200" valign="top" id="descriptionPane"> 
<p id="titlePane"><xsl:value-of select="page/title"/></p>
<xsl:value-of select="page/description"/>
</td>
<td width="800" height="600" id="contentPane" valign="top">
<p>Topics: | 
<xsl:for-each select="page/topics/entry">
<xsl:element name="a">
    <xsl:attribute name="href">
    <xsl:value-of select="concat(substring-before(@name, '.xml'), '.html')" />
    </xsl:attribute>
    <xsl:value-of select="@title" />
  </xsl:element> |
</xsl:for-each>
</p>
<table width="100%">
<tr><td width="40%">
<p>Navigation:</p>
<xsl:apply-templates select="page/navigation" />
</td><td>
<xsl:value-of select="page/content"/>
</td></tr></table>
</td></tr></table>
</body>
</html>
</xsl:template>
<xsl:template match="navigation">
<ul><xsl:apply-templates select="entry" /></ul>
</xsl:template>

<xsl:template match="entry">
<li>
  <xsl:element name="a">
    <xsl:attribute name="href">
    <xsl:value-of select="concat(substring-before(@name, '.xml'), '.html')" />
    </xsl:attribute>
    <xsl:value-of select="@title" />
  </xsl:element>
  <xsl:if test="count(./entry)">
  <ul>
  <xsl:apply-templates select="entry" />
  </ul>
  </xsl:if>
</li>
</xsl:template>
</xsl:stylesheet>