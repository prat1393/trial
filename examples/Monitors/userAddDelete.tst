<?xml version="1.0" ?>

<TestCase name="userAddDelete" version="5">

<meta><create version="0.0" buildNumber="0.0.0.0" author="rvickers" date="01/06/2010" host="Dogma.local"  /><lastEdited version="0.0" buildNumber="0.0.0.0" author="rvickers" date="01/06/2010" host="Dogma.local"  /></meta>

<id>447074a2</id>
<Documentation>This test is used in the monitor setup for CVS demos.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9LTEmbGlzYXY9MC4wICgwLjAuMC4wKSZub2Rlcz04ODU4OTk4NDA=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

    <Node name="add" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="delete" > 


      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <prop>addResult</prop>
<xpathq>string(//multiRef[@id=&apos;id0&apos;])</xpathq>
      </Filter>


      <!-- Data Sets -->
<readrec>uid</readrec>
<readrec>countDatSset</readrec>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert add success" type="com.itko.lisa.test.CheckResultPropRegEx">
<log>The add service returned false for the add function</log>
<then>fail</then>
        <prop>addResult</prop>
        <param>true</param>
</CheckResult>

<wsdl>http://{{WSSERVER}}:{{WSPORT}}/itko-examples/services/UserControlService?wsdl</wsdl>
<endpoint>http://{{WSSERVER}}:{{WSPORT}}/itko-examples/services/UserControlService</endpoint>
<targetNamespace>urn:UserControlService</targetNamespace>
<service>UserControlServiceService</service>
<port>UserControlService</port>
<operation>addUser</operation>
<onError>abort</onError>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUg
eG1sbnM6ZW5jPSJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy9zb2FwL2VuY29kaW5nLyIgeG1s
bnM6c29hcGVuYz0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbmNvZGluZy8iIHht
bG5zOnNvYXBlbnY9Imh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3NvYXAvZW52ZWxvcGUvIiB4
bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiIHhtbG5zOnhzaT0iaHR0
cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiPgogIDxzb2FwZW52OkJvZHk+
CiAgICA8bnMxOmFkZFVzZXIgZW5jb2RpbmdTdHlsZT0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5v
cmcvc29hcC9lbmNvZGluZy8iIHhtbG5zOm5zMT0iaHR0cDovL3dlYnNlcnZpY2VzLmV4YW1wbGVz
Lml0a28uY29tIj4KICAgICAgPGxvZ2luIHhzaTp0eXBlPSJzb2FwZW5jOnN0cmluZyI+e3t1aWR9
fTwvbG9naW4+CiAgICAgIDxjbGVhcnRleHRQYXNzd29yZCB4c2k6dHlwZT0ic29hcGVuYzpzdHJp
bmciPnBhc3N7e2NvdW50fX08L2NsZWFydGV4dFBhc3N3b3JkPgogICAgPC9uczE6YWRkVXNlcj4K
ICA8L3NvYXBlbnY6Qm9keT4KPC9zb2FwZW52OkVudmVsb3BlPg==</request>
<sslInfo>
<ssl-keystore-file></ssl-keystore-file>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
</sslInfo>
<wsiInfo>
<wsi-check-wsdl>false</wsi-check-wsdl>
<wsi-check-msg>false</wsi-check-msg>
<wsi-display-type>onlyFailed</wsi-display-type>
<wsi-on-fail>continue</wsi-on-fail>
</wsiInfo>
<transport>
<soapVersionUri>1.1</soapVersionUri>
<httpVersionUri>1.1</httpVersionUri>
<webMethodUri>POST</webMethodUri>
<mtom>false</mtom>
<dontSendRequest>false</dontSendRequest>
<allowNonNillable>false</allowNonNillable>
<callTimeout>30000</callTimeout>
</transport>
<uddiActive>false</uddiActive>
<uddi-result>
<uddi-selection>
</uddi-selection>
</uddi-result>
<wss4jInfo>
<version>5</version>
<wssecurity>
<isSend>true</isSend>
<must>false</must>
<role></role>
</wssecurity>
<wsi-bsp>false</wsi-bsp>
</wss4jInfo>
<wsaInfo>
<useAddressing>false</useAddressing>
<must>false</must>
<to></to>
<toOverride>false</toOverride>
<toDefault>true</toDefault>
<from></from>
<fromOverride>false</fromOverride>
<fromDefault>true</fromDefault>
<action></action>
<actionOverride>false</actionOverride>
<actionDefault>true</actionDefault>
<msgid></msgid>
<msgidOverride>false</msgidOverride>
<msgidDefault>true</msgidDefault>
<replyTo></replyTo>
<replyToOverride>false</replyToOverride>
<faultTo></faultTo>
<faulttToOverride>false</faulttToOverride>
<addressingVersion>http://www.w3.org/2005/08/addressing</addressingVersion>
</wsaInfo>
<customHTTPHeaderInfo>
</customHTTPHeaderInfo>
    </Node>


    <Node name="delete" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="add" > 


      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <prop>deleteResult</prop>
<xpathq>string(//multiRef[@id=&apos;id0&apos;])</xpathq>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="true" name="Assert Stop at 10 users" type="com.itko.lisa.test.CheckResultPropRegEx">
<log></log>
<then>end</then>
        <prop>count</prop>
        <param>10</param>
</CheckResult>

<CheckResult assertTrue="false" name="Assert delete function" type="com.itko.lisa.test.CheckResultPropRegEx">
<log>The Delete service retuned false</log>
<then>fail</then>
        <prop>deleteResult</prop>
        <param>true</param>
</CheckResult>

<wsdl>http://{{WSSERVER}}:{{WSPORT}}/itko-examples/services/UserControlService?wsdl</wsdl>
<endpoint>http://{{WSSERVER}}:{{WSPORT}}/itko-examples/services/UserControlService</endpoint>
<targetNamespace>urn:UserControlService</targetNamespace>
<service>UserControlServiceService</service>
<port>UserControlService</port>
<operation>deleteUser</operation>
<onError>abort</onError>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUg
eG1sbnM6ZW5jPSJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy9zb2FwL2VuY29kaW5nLyIgeG1s
bnM6c29hcGVuYz0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbmNvZGluZy8iIHht
bG5zOnNvYXBlbnY9Imh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3NvYXAvZW52ZWxvcGUvIiB4
bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiIHhtbG5zOnhzaT0iaHR0
cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiPgogIDxzb2FwZW52OkJvZHk+
CiAgICA8bnMxOmRlbGV0ZVVzZXIgZW5jb2RpbmdTdHlsZT0iaHR0cDovL3NjaGVtYXMueG1sc29h
cC5vcmcvc29hcC9lbmNvZGluZy8iIHhtbG5zOm5zMT0iaHR0cDovL3dlYnNlcnZpY2VzLmV4YW1w
bGVzLml0a28uY29tIj4KICAgICAgPHVzZXIgeHNpOnR5cGU9InNvYXBlbmM6c3RyaW5nIj57e3Vp
ZH19PC91c2VyPgogICAgPC9uczE6ZGVsZXRlVXNlcj4KICA8L3NvYXBlbnY6Qm9keT4KPC9zb2Fw
ZW52OkVudmVsb3BlPg==</request>
<sslInfo>
<ssl-keystore-file></ssl-keystore-file>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
</sslInfo>
<wsiInfo>
<wsi-check-wsdl>false</wsi-check-wsdl>
<wsi-check-msg>false</wsi-check-msg>
<wsi-display-type>onlyFailed</wsi-display-type>
<wsi-on-fail>continue</wsi-on-fail>
</wsiInfo>
<transport>
<soapVersionUri>1.1</soapVersionUri>
<httpVersionUri>1.1</httpVersionUri>
<webMethodUri>POST</webMethodUri>
<mtom>false</mtom>
<dontSendRequest>false</dontSendRequest>
<allowNonNillable>false</allowNonNillable>
<callTimeout>30000</callTimeout>
</transport>
<uddiActive>false</uddiActive>
<uddi-result>
<uddi-selection>
</uddi-selection>
</uddi-result>
<wss4jInfo>
<version>5</version>
<wssecurity>
<isSend>true</isSend>
<must>false</must>
<role></role>
</wssecurity>
<wsi-bsp>false</wsi-bsp>
</wss4jInfo>
<wsaInfo>
<useAddressing>false</useAddressing>
<must>false</must>
<to></to>
<toOverride>false</toOverride>
<toDefault>true</toDefault>
<from></from>
<fromOverride>false</fromOverride>
<fromDefault>true</fromDefault>
<action></action>
<actionOverride>false</actionOverride>
<actionDefault>true</actionDefault>
<msgid></msgid>
<msgidOverride>false</msgidOverride>
<msgidDefault>true</msgidDefault>
<replyTo></replyTo>
<replyToOverride>false</replyToOverride>
<faultTo></faultTo>
<faulttToOverride>false</faulttToOverride>
<addressingVersion>http://www.w3.org/2005/08/addressing</addressingVersion>
</wsaInfo>
<customHTTPHeaderInfo>
</customHTTPHeaderInfo>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <DataSet type="com.itko.lisa.test.DataSetIDGenerator" name="uid" atend="" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAADdWlkdAAUY3ZzTW9uaXRvci0zNTQwNTcyMzF4</sample>
    <type>Number</type>
    <prefix>cvsMonitor-</prefix>
    </DataSet>

    <DataSet type="com.itko.lisa.test.CounterDataSet" name="countDatSset" atend="" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAAFY291bnR0AAExeA==</sample>
    <propKey>count</propKey>
    <countFrom>1</countFrom>
    <countTo>10</countTo>
    <countIncrement>1</countIncrement>
    </DataSet>

</TestCase>
