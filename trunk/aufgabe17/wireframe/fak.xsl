<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<!-- match all the document -->
<xsl:template match="/">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<style type="text/css">
body {
background-color: #E0E0E0;
font-family: Verdana, Helvetica, sans-serif;
font-size: 10pt;
}
#contentPane {
padding: 5px;
padding-top: 80px;
border: 1px solid black; background-color: #FFFFFF;
background-image: url(./img/top.png);
background-repeat: no-repeat;
width: 790px;
height: 600px;

}
#descriptionPane {
padding: 5px;
padding-top: 0px;
font-style: italic;
}
#titlePane {
padding: 5px;
width: 170px;
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
<td id="contentPane" valign="top">
<p><a href="fak-start.html">Home</a> | 
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
<tr><td width="40%" valign="top">
<p>Navigation:</p>
<xsl:apply-templates select="page/navigation" />
</td><td valign="top">
<xsl:apply-templates select="page/content" />
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
<xsl:template match="content">
  <xsl:apply-templates select="*|text()"/>
</xsl:template>
<xsl:template match="p">
<p><xsl:apply-templates select="*|text()"/></p>
</xsl:template>
<xsl:template match="ul">
<ul><xsl:apply-templates select="*|text()"/></ul>
</xsl:template>
<xsl:template match="li">
<li><xsl:apply-templates select="*|text()"/></li>
</xsl:template>
<xsl:template match="text()">
<xsl:value-of select="." />
</xsl:template>
</xsl:stylesheet>
