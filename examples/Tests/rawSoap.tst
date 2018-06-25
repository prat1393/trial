<?xml version="1.0" encoding="UTF-8"?>
<TestCase name="rawSoap" version="2">
<id>78cc5962</id>
<Documentation>Raw Soap Step with Integration Filters.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9MyZ0Y3Y9MiZsaXNhdj00LjUgKDYwMTYpJm5vZGVzPTg5NTMwMzg4Ng==</sig>
<rootNode>listUsers</rootNode>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<Configurations>
<Configuration>
<name>default</name>
<external>false</external>
<props>
    <Parameter>
    <key>WSSERVER</key>
    <value>localhost</value>
    <name>Name</name>
    </Parameter>
    <Parameter>
    <key>WSPORT</key>
    <value>8080</value>
    <name>Name</name>
    </Parameter>
</props>
</Configuration>
</Configurations>
      <Filter type="com.itko.lisaint.FilterLisaInt">
              <sendHeader>true</sendHeader>
      <buildTimeMax>0</buildTimeMax>
      <onErrorNode>fail</onErrorNode>
      <onWarning>continue</onWarning>
      <compContentKey>true</compContentKey>
      <forceGC>false</forceGC>
      <allowExceptionToFail>true</allowExceptionToFail>
      <logLevel>INFO</logLevel>
      <logger/>
      <techWebService>true</techWebService>
      <techServlet>true</techServlet>
      <techJms>true</techJms>
      <techEjb>true</techEjb>
      <techJavaObject>true</techJavaObject>
      </Filter>

    <Node log="" name="listUsers" next="end" quiet="false" think="1-10" type="com.itko.lisa.ws.RawSOAPNode" useFilters="true" version="1"> 

<graphx>-1</graphx>
<graphy>-1</graphy>
<Documentation>Raw Soap Step</Documentation>
<url>http://{{WSSERVER}}:{{WSPORT}}/itkoExamples/EJB3UserControlBean</url>
<action/>
<soapRequest itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUg
eG1sbnM6c29hcGVudj0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbnZlbG9wZS8i
IHhtbG5zOnhzZD0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiIHhtbG5zOnhzaT0i
aHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiPiAgCiAgPHNvYXBlbnY6
Qm9keT4KICAgIDxsaXN0VXNlcnMgeG1sbnM9Imh0dHA6Ly9lamIzLmV4YW1wbGVzLml0a28uY29t
LyIgLz4KICA8L3NvYXBlbnY6Qm9keT4KPC9zb2FwZW52OkVudmVsb3BlPgoKCgo=</soapRequest>
<contentType>text/xml; charset=UTF-8</contentType>
<onError>continue (quiet)</onError>
<customHTTPHeaderInfo>
</customHTTPHeaderInfo>

      <!-- Result Elements -->
<CheckResult assertTrue="false" name="Assert response" type="com.itko.lisa.xml.AssertXMLXPath">
<log>Didn't get body as part of the soap response</log>
<then>fail</then>
<valueToAssertKey/>
<xpathq>/env:Envelope/env:Body</xpathq>
</CheckResult>

    </Node>


    <Node log="" name="end" next="fail" quiet="true" think="0-0" type="com.itko.lisa.test.NormalEnd" useFilters="true" version="1"> 

<graphx>-1</graphx>
<graphy>-1</graphy>
    </Node>


    <Node log="" name="fail" next="unknown" quiet="true" think="0-0" type="com.itko.lisa.test.Abend" useFilters="true" version="1"> 

<graphx>-1</graphx>
<graphy>-1</graphy>
    </Node>


</TestCase>
