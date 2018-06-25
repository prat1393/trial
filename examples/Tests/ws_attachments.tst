<?xml version="1.0" ?>

<TestCase name="ws_attachments" version="5">

<meta><create version="5.0.23" buildNumber="5.0.23.179" author="brad" date="10/06/2010" host="legolas"  /><lastEdited version="0.0" buildNumber="0.0.0.0" author="bmm" date="10/15/2010" host="bmacbookpro-air"  /></meta>

<id>3007f3cf</id>
<Documentation>Tests our ability to send and receive inline base64 encoded data blobs and XOP/MTOM attachments.  Filters and assertions on steps verify the requests and responses look correct.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj0wLjAgKDAuMC4wLjApJm5vZGVzPS0zODc2MTczOTQ=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="LoadBinaryAttachment" log=""
          type="com.itko.lisa.test.FileNode" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="LoadBase64Attachment" > 

<Documentation>Read in our attachment for use later in assertions.  Stored in property binaryAttachment.</Documentation>
<Loc>{{LISA_PROJ_ROOT}}/Data/ws_attachment.jpg</Loc>
<PropKey>binaryAttachment</PropKey>
<binary>true</binary>
<onFail>abort</onFail>
    </Node>


    <Node name="LoadBase64Attachment" log=""
          type="com.itko.lisa.utils.Base64Node" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="SingleSendInlineReceiveInline" > 

<Documentation>Read in our attachment and base64 encode it.  This is used in assertions later and to send the attachment inline to the web service.</Documentation>
<Loc>{{LISA_PROJ_ROOT}}/Data/ws_attachment.jpg</Loc>
<PropKey>base64Attachment</PropKey>
<onFail>abort</onFail>
    </Node>


    <Node name="SingleSendInlineReceiveInline" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="SingleSendMTOMReceiveInline" > 

<Documentation>Send a single inline base64 data blob, receive a single inline response.  Verify the request went out inline, and the response echoed back matches what was sent.</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.SingleSendInlineReceiveInline.request.rsp</valueToFilterKey>
<prop>base64request</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/:DataBase64Binary/text()</xpathq>
<nsMap0>=http://example.org/mtom/data</nsMap0>
<nsMap1>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap1>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.SingleSendInlineReceiveInline.rsp</valueToFilterKey>
<prop>base64response</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/ns2:DataBase64Binary/text()</xpathq>
<nsMap0>ns2=http://example.org/mtom/data</nsMap0>
<nsMap1>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap1>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert request base64 equals base64attachment" type="com.itko.lisa.test.AssertPropsEqual">
<log>Request base64 does not match base64 attachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop1>base64Attachment</prop1>
        <prop2>base64request</prop2>
</CheckResult>

<CheckResult assertTrue="false" name="Assert response base64 equals base64attachment" type="com.itko.lisa.test.AssertPropsEqual">
<log>Response base64 does not match base64 attachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop1>base64Attachment</prop1>
        <prop2>base64response</prop2>
</CheckResult>

<wsdl>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc?wsdl</wsdl>
<endpoint>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc</endpoint>
<targetNamespace>http://soapinterop.org/mtom-cr-test</targetNamespace>
<service>EchoTestSvc</service>
<port>EchoTestPort</port>
<operation>EchoTestSingleAsBase64Binary</operation>
<onError>abort</onError>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3d3dy53My5vcmcvMjAwMy8wNS9zb2FwLWVudmVsb3BlIiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiPgogIDxzb2FwZW52OkJvZHk+CiAgICA8RGF0YUJhc2U2NEJpbmFyeSB4bWxucz0iaHR0cDovL2V4YW1wbGUub3JnL210b20vZGF0YSIgeG1sbnM6bnMxPSJodHRwOi8vd3d3LnczLm9yZy8yMDA1LzA1L3htbG1pbWUiIG5zMTpjb250ZW50VHlwZT0iaW1hZ2UvanBlZyI+PCEtLWF0dHJpYnV0ZSBjb250ZW50VHlwZSBpcyBvcHRpb25hbC0tPnt7YmFzZTY0QXR0YWNobWVudH19PC9EYXRhQmFzZTY0QmluYXJ5PgogIDwvc29hcGVudjpCb2R5Pgo8L3NvYXBlbnY6RW52ZWxvcGU+</request>
<style>document</style>
<use>literal</use>
<soapAction></soapAction>
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
<soapVersionUri>1.2</soapVersionUri>
<httpVersionUri>1.1</httpVersionUri>
<webMethodUri>POST</webMethodUri>
<mtom>false</mtom>
<dontSendRequest>false</dontSendRequest>
<fault-as-error>true</fault-as-error>
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
<attachments>
<attachment-type>MIME</attachment-type>
</attachments>
<mtom-paths>
</mtom-paths>
    </Node>


    <Node name="SingleSendMTOMReceiveInline" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="SingleSendInlineReceiveMTOM" > 

<Documentation>Send a single MTOM attachment, receive the attachment inline base64 encoded.</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.SingleSendMTOMReceiveInline.request.rsp</valueToFilterKey>
<prop>mtomRequest</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/:DataBase64Binary/xop:Include</xpathq>
<nsMap0>xop=http://www.w3.org/2004/08/xop/include</nsMap0>
<nsMap1>=http://example.org/mtom/data</nsMap1>
<nsMap2>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap2>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.SingleSendMTOMReceiveInline.rsp</valueToFilterKey>
<prop>base64response</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/ns2:DataBase64Binary/text()</xpathq>
<nsMap0>ns2=http://example.org/mtom/data</nsMap0>
<nsMap1>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap1>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert request has MTOM attachment" type="com.itko.lisa.test.CheckResultPropRegEx">
<log>Did not find MTOM attachment in request</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop>mtomRequest</prop>
        <param>xop:Include</param>
</CheckResult>

<CheckResult assertTrue="false" name="Assert response base64 equals base64attachment" type="com.itko.lisa.test.AssertPropsEqual">
<log>Response base64 does not match base64attachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop1>base64Attachment</prop1>
        <prop2>base64response</prop2>
</CheckResult>

<wsdl>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc?wsdl</wsdl>
<endpoint>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc</endpoint>
<targetNamespace>http://soapinterop.org/mtom-cr-test</targetNamespace>
<service>EchoTestSvc</service>
<port>EchoTestPort</port>
<operation>EchoTestSingleAsBase64Binary</operation>
<onError>abort</onError>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3d3dy53My5vcmcvMjAwMy8wNS9zb2FwLWVudmVsb3BlIiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiPgogIDxzb2FwZW52OkJvZHk+CiAgICA8RGF0YUJhc2U2NEJpbmFyeSB4bWxucz0iaHR0cDovL2V4YW1wbGUub3JnL210b20vZGF0YSIgeG1sbnM6bnMxPSJodHRwOi8vd3d3LnczLm9yZy8yMDA1LzA1L3htbG1pbWUiIG5zMTpjb250ZW50VHlwZT0iaW1hZ2UvanBlZyI+PCEtLWF0dHJpYnV0ZSBjb250ZW50VHlwZSBpcyBvcHRpb25hbC0tPjx4b3A6SW5jbHVkZSBocmVmPSJjaWQ6RGF0YUJhc2U2NEJpbmFyeT1BODM4MjQwNkE3QkZDMUY4NUQ5MUE0MEEzOUNDQzkyMSIgeG1sbnM6eG9wPSJodHRwOi8vd3d3LnczLm9yZy8yMDA0LzA4L3hvcC9pbmNsdWRlIi8+PC9EYXRhQmFzZTY0QmluYXJ5PgogIDwvc29hcGVudjpCb2R5Pgo8L3NvYXBlbnY6RW52ZWxvcGU+</request>
<style>document</style>
<use>literal</use>
<soapAction></soapAction>
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
<soapVersionUri>1.2</soapVersionUri>
<httpVersionUri>1.1</httpVersionUri>
<webMethodUri>POST</webMethodUri>
<mtom>false</mtom>
<dontSendRequest>false</dontSendRequest>
<fault-as-error>true</fault-as-error>
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
<attachments>
<attachment-type>MTOM</attachment-type>
<attachment>
<type>url-bin</type>
<data>{{LISA_TC_URL}}/../Data/ws_attachment.jpg</data>
<contentType>application/octet-stream</contentType>
<cid>DataBase64Binary=A8382406A7BFC1F85D91A40A39CCC921</cid>
</attachment>
</attachments>
<mtom-paths>
</mtom-paths>
    </Node>


    <Node name="SingleSendInlineReceiveMTOM" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="SingleSendMTOMReceiveMTOM" > 

<Documentation>Send in a single base64 encoded data blob, receive an MTOM attachment back.</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.SingleSendInlineReceiveMTOM.request.rsp</valueToFilterKey>
<prop>base64request</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/:DataMTOM/text()</xpathq>
<nsMap0>=http://example.org/mtom/data</nsMap0>
<nsMap1>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap1>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.SingleSendInlineReceiveMTOM.rsp</valueToFilterKey>
<prop>attachmentID</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/ns2:DataMTOM/xop:Include/@href</xpathq>
<nsMap0>xop=http://www.w3.org/2004/08/xop/include</nsMap0>
<nsMap1>ns2=http://example.org/mtom/data</nsMap1>
<nsMap2>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap2>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert inline base64 equals base64attachment" type="com.itko.lisa.test.AssertPropsEqual">
<log>Inline base64 does not match base64 attachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop1>base64Attachment</prop1>
        <prop2>base64request</prop2>
</CheckResult>

<CheckResult assertTrue="false" name="Assert attachment equals binaryAttachment" type="com.itko.lisa.test.AssertByScript">
<log>Response attachment did not equal binaryAttachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <script>// This Beanshell script should return a boolean result indicating&#13;&#10;// the assertion is true or false&#13;&#10;&#13;&#10;// Get current step name&#13;&#10;String stepName = testExec.getCurrentNodeName();&#13;&#10;&#13;&#10;// Work around the fact that LISA doesn&apos;t know the current step name in edit mode&#13;&#10;if (stepName == null) return true;&#13;&#10;&#13;&#10;// Get the attachment id w/o the &quot;cid:&quot; in front&#13;&#10;if (attachmentID.startsWith(&quot;cid:&quot;)) {&#13;&#10;    attachmentID = attachmentID.substring(4, attachmentID.length());&#13;&#10;}&#13;&#10;&#13;&#10;// Build the LISA property name that will contain the binary attachment&#13;&#10;String pName = &quot;lisa.&quot; + stepName + &quot;.rsp.attachment.&quot; + attachmentID;&#13;&#10;&#13;&#10;// Retrieve the attachment&#13;&#10;byte[] respBytes = (byte[]) testExec.getStateObject(pName);&#13;&#10;&#13;&#10;// Test the byte arrays are equal&#13;&#10;if (binaryAttachment.length != respBytes.length) return false;&#13;&#10;for (int i = 0; i &lt; binaryAttachment.length; i++) {&#13;&#10;    if (binaryAttachment[i] != respBytes[i]) return false;&#13;&#10;}&#13;&#10;return true;</script>
</CheckResult>

<wsdl>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc?wsdl</wsdl>
<endpoint>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc</endpoint>
<targetNamespace>http://soapinterop.org/mtom-cr-test</targetNamespace>
<service>EchoTestSvc</service>
<port>EchoTestPort</port>
<operation>EchoTestSingleAsMTOM</operation>
<onError>abort</onError>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3d3dy53My5vcmcvMjAwMy8wNS9zb2FwLWVudmVsb3BlIiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiPgogIDxzb2FwZW52OkJvZHk+CiAgICA8RGF0YU1UT00geG1sbnM9Imh0dHA6Ly9leGFtcGxlLm9yZy9tdG9tL2RhdGEiIHhtbG5zOm5zMT0iaHR0cDovL3d3dy53My5vcmcvMjAwNS8wNS94bWxtaW1lIiBuczE6Y29udGVudFR5cGU9ImltYWdlL2pwZWciPjwhLS1hdHRyaWJ1dGUgY29udGVudFR5cGUgaXMgb3B0aW9uYWwtLT57e2Jhc2U2NEF0dGFjaG1lbnR9fTwvRGF0YU1UT00+CiAgPC9zb2FwZW52OkJvZHk+Cjwvc29hcGVudjpFbnZlbG9wZT4=</request>
<style>document</style>
<use>literal</use>
<soapAction></soapAction>
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
<soapVersionUri>1.2</soapVersionUri>
<httpVersionUri>1.1</httpVersionUri>
<webMethodUri>POST</webMethodUri>
<mtom>false</mtom>
<dontSendRequest>false</dontSendRequest>
<fault-as-error>true</fault-as-error>
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
<attachments>
<attachment-type>MIME</attachment-type>
</attachments>
<mtom-paths>
</mtom-paths>
    </Node>


    <Node name="SingleSendMTOMReceiveMTOM" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="MultipleSendInlineReceiveInline" > 

<Documentation>Send a single MTOM attachment in, receive an MTOM attachment back.</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.SingleSendMTOMReceiveMTOM.request.rsp</valueToFilterKey>
<prop>mtomRequest</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/:DataMTOM/xop:Include</xpathq>
<nsMap0>xop=http://www.w3.org/2004/08/xop/include</nsMap0>
<nsMap1>=http://example.org/mtom/data</nsMap1>
<nsMap2>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap2>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.SingleSendMTOMReceiveMTOM.rsp</valueToFilterKey>
<prop>attachmentID</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/ns2:DataMTOM/xop:Include/@href</xpathq>
<nsMap0>xop=http://www.w3.org/2004/08/xop/include</nsMap0>
<nsMap1>ns2=http://example.org/mtom/data</nsMap1>
<nsMap2>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap2>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert request has MTOM attachment" type="com.itko.lisa.test.CheckResultPropRegEx">
<log>Did not find MTOM attachment in request</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop>mtomRequest</prop>
        <param>xop:Include</param>
</CheckResult>

<CheckResult assertTrue="false" name="Assert attachment equals binaryAttachment" type="com.itko.lisa.test.AssertByScript">
<log>Response attachment did not equal binaryAttachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <script>// This Beanshell script should return a boolean result indicating&#13;&#10;// the assertion is true or false&#13;&#10;&#13;&#10;// Get current step name&#13;&#10;String stepName = testExec.getCurrentNodeName();&#13;&#10;&#13;&#10;// Work around the fact that LISA doesn&apos;t know the current step name in edit mode&#13;&#10;if (stepName == null) return true;&#13;&#10;&#13;&#10;// Get the attachment id w/o the &quot;cid:&quot; in front&#13;&#10;if (attachmentID.startsWith(&quot;cid:&quot;)) {&#13;&#10;    attachmentID = attachmentID.substring(4, attachmentID.length());&#13;&#10;}&#13;&#10;&#13;&#10;// Build the LISA property name that will contain the binary attachment&#13;&#10;String pName = &quot;lisa.&quot; + stepName + &quot;.rsp.attachment.&quot; + attachmentID;&#13;&#10;&#13;&#10;// Retrieve the attachment&#13;&#10;byte[] respBytes = (byte[]) testExec.getStateObject(pName);&#13;&#10;&#13;&#10;// Test the byte arrays are equal&#13;&#10;if (binaryAttachment.length != respBytes.length) return false;&#13;&#10;for (int i = 0; i &lt; binaryAttachment.length; i++) {&#13;&#10;    if (binaryAttachment[i] != respBytes[i]) return false;&#13;&#10;}&#13;&#10;return true;</script>
</CheckResult>

<wsdl>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc?wsdl</wsdl>
<endpoint>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc</endpoint>
<targetNamespace>http://soapinterop.org/mtom-cr-test</targetNamespace>
<service>EchoTestSvc</service>
<port>EchoTestPort</port>
<operation>EchoTestSingleAsMTOM</operation>
<onError>abort</onError>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3d3dy53My5vcmcvMjAwMy8wNS9zb2FwLWVudmVsb3BlIiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiPgogIDxzb2FwZW52OkJvZHk+CiAgICA8RGF0YU1UT00geG1sbnM9Imh0dHA6Ly9leGFtcGxlLm9yZy9tdG9tL2RhdGEiIHhtbG5zOm5zMT0iaHR0cDovL3d3dy53My5vcmcvMjAwNS8wNS94bWxtaW1lIiBuczE6Y29udGVudFR5cGU9ImltYWdlL2pwZWciPjwhLS1hdHRyaWJ1dGUgY29udGVudFR5cGUgaXMgb3B0aW9uYWwtLT48eG9wOkluY2x1ZGUgaHJlZj0iY2lkOkRhdGFNVE9NPTkxOTRCQTc5NjNBRjBBQUVBNTg4RDgzQUI4NUU1RUM5IiB4bWxuczp4b3A9Imh0dHA6Ly93d3cudzMub3JnLzIwMDQvMDgveG9wL2luY2x1ZGUiLz48L0RhdGFNVE9NPgogIDwvc29hcGVudjpCb2R5Pgo8L3NvYXBlbnY6RW52ZWxvcGU+</request>
<style>document</style>
<use>literal</use>
<soapAction></soapAction>
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
<soapVersionUri>1.2</soapVersionUri>
<httpVersionUri>1.1</httpVersionUri>
<webMethodUri>POST</webMethodUri>
<mtom>false</mtom>
<dontSendRequest>false</dontSendRequest>
<fault-as-error>true</fault-as-error>
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
<attachments>
<attachment-type>MTOM</attachment-type>
<attachment>
<type>url-bin</type>
<data>{{LISA_TC_URL}}/../Data/ws_attachment.jpg</data>
<contentType>application/octet-stream</contentType>
<cid>DataMTOM=9194BA7963AF0AAEA588D83AB85E5EC9</cid>
</attachment>
</attachments>
<mtom-paths>
</mtom-paths>
    </Node>


    <Node name="MultipleSendInlineReceiveInline" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="MultipleSendMTOMReceiveInline" > 

<Documentation>Send two inline base64 data blobs in, receive the same back.</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendInlineReceiveInline.request.rsp</valueToFilterKey>
<prop>base64request1</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/:WrapperBase64Binary/:Data[1]/text()</xpathq>
<nsMap0>=http://example.org/mtom/data</nsMap0>
<nsMap1>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap1>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendInlineReceiveInline.request.rsp</valueToFilterKey>
<prop>base64request2</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/:WrapperBase64Binary/:Data[2]/text()</xpathq>
<nsMap0>=http://example.org/mtom/data</nsMap0>
<nsMap1>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap1>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendInlineReceiveInline.rsp</valueToFilterKey>
<prop>base64response1</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/ns2:WrapperBase64Binary/ns2:Data[1]/text()</xpathq>
<nsMap0>ns2=http://example.org/mtom/data</nsMap0>
<nsMap1>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap1>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendInlineReceiveInline.rsp</valueToFilterKey>
<prop>base64response2</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/ns2:WrapperBase64Binary/ns2:Data[2]/text()</xpathq>
<nsMap0>ns2=http://example.org/mtom/data</nsMap0>
<nsMap1>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap1>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert first inline base64 equals base64attachment" type="com.itko.lisa.test.AssertPropsEqual">
<log>Inline base64 does not match base64 attachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop1>base64Attachment</prop1>
        <prop2>base64request1</prop2>
</CheckResult>

<CheckResult assertTrue="false" name="Assert second inline base64 equals base64attachment" type="com.itko.lisa.test.AssertPropsEqual">
<log>Inline base64 does not match base64 attachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop1>base64Attachment</prop1>
        <prop2>base64request2</prop2>
</CheckResult>

<CheckResult assertTrue="false" name="Assert first response base64 equals base64attachment" type="com.itko.lisa.test.AssertPropsEqual">
<log>First response base64 does not match base64 attachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop1>base64Attachment</prop1>
        <prop2>base64response1</prop2>
</CheckResult>

<CheckResult assertTrue="false" name="Assert second response base64 equals base64attachment" type="com.itko.lisa.test.AssertPropsEqual">
<log>Second response base64 does not match base64 attachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop1>base64Attachment</prop1>
        <prop2>base64response2</prop2>
</CheckResult>

<wsdl>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc?wsdl</wsdl>
<endpoint>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc</endpoint>
<targetNamespace>http://soapinterop.org/mtom-cr-test</targetNamespace>
<service>EchoTestSvc</service>
<port>EchoTestPort</port>
<operation>EchoTestMultipleAsBase64Binary</operation>
<onError>abort</onError>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3d3dy53My5vcmcvMjAwMy8wNS9zb2FwLWVudmVsb3BlIiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiPgogIDxzb2FwZW52OkJvZHk+CiAgICA8V3JhcHBlckJhc2U2NEJpbmFyeSB4bWxucz0iaHR0cDovL2V4YW1wbGUub3JnL210b20vZGF0YSI+CiAgICAgIDwhLS1EYXRhIGlzIG9wdGlvbmFsLCBidXQgY2FuIGhhdmUgYW4gdW5saW1pdGVkIG51bWJlciBvZiBpbnN0YW5jZXMtLT4KICAgICAgPERhdGEgeG1sbnM6bnMxPSJodHRwOi8vd3d3LnczLm9yZy8yMDA1LzA1L3htbG1pbWUiIG5zMTpjb250ZW50VHlwZT0iaW1hZ2UvanBlZyI+PCEtLWF0dHJpYnV0ZSBjb250ZW50VHlwZSBpcyBvcHRpb25hbC0tPnt7YmFzZTY0QXR0YWNobWVudH19PC9EYXRhPgogICAgICA8RGF0YSB4bWxuczpuczE9Imh0dHA6Ly93d3cudzMub3JnLzIwMDUvMDUveG1sbWltZSIgbnMxOmNvbnRlbnRUeXBlPSJpbWFnZS9qcGVnIj48IS0tYXR0cmlidXRlIGNvbnRlbnRUeXBlIGlzIG9wdGlvbmFsLS0+e3tiYXNlNjRBdHRhY2htZW50fX08L0RhdGE+CiAgICA8L1dyYXBwZXJCYXNlNjRCaW5hcnk+CiAgPC9zb2FwZW52OkJvZHk+Cjwvc29hcGVudjpFbnZlbG9wZT4=</request>
<style>document</style>
<use>literal</use>
<soapAction></soapAction>
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
<soapVersionUri>1.2</soapVersionUri>
<httpVersionUri>1.1</httpVersionUri>
<webMethodUri>POST</webMethodUri>
<mtom>false</mtom>
<dontSendRequest>false</dontSendRequest>
<fault-as-error>true</fault-as-error>
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
<attachments>
<attachment-type>MIME</attachment-type>
</attachments>
<mtom-paths>
</mtom-paths>
    </Node>


    <Node name="MultipleSendMTOMReceiveInline" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="MultipleSendInlineReceiveMTOM" > 

<Documentation>Send two MTOM attachments in, receive the data back inline base64 encoded.</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendMTOMReceiveInline.request.rsp</valueToFilterKey>
<prop>mtomRequest1</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/:WrapperBase64Binary/:Data[1]/xop:Include</xpathq>
<nsMap0>xop=http://www.w3.org/2004/08/xop/include</nsMap0>
<nsMap1>=http://example.org/mtom/data</nsMap1>
<nsMap2>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap2>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendMTOMReceiveInline.request.rsp</valueToFilterKey>
<prop>mtomRequest2</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/:WrapperBase64Binary/:Data[2]/xop:Include</xpathq>
<nsMap0>xop=http://www.w3.org/2004/08/xop/include</nsMap0>
<nsMap1>=http://example.org/mtom/data</nsMap1>
<nsMap2>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap2>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendMTOMReceiveInline.rsp</valueToFilterKey>
<prop>base64response1</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/ns2:WrapperBase64Binary/ns2:Data[1]/text()</xpathq>
<nsMap0>ns2=http://example.org/mtom/data</nsMap0>
<nsMap1>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap1>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendMTOMReceiveInline.rsp</valueToFilterKey>
<prop>base64response2</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/ns2:WrapperBase64Binary/ns2:Data[2]/text()</xpathq>
<nsMap0>ns2=http://example.org/mtom/data</nsMap0>
<nsMap1>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap1>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert request has first MTOM attachment" type="com.itko.lisa.test.CheckResultPropRegEx">
<log>Did not find first MTOM attachment in request</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop>mtomRequest1</prop>
        <param>xop:Include</param>
</CheckResult>

<CheckResult assertTrue="false" name="Assert request has second MTOM attachment" type="com.itko.lisa.test.CheckResultPropRegEx">
<log>Did not find second MTOM attachment in request</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop>mtomRequest2</prop>
        <param>xop:Include</param>
</CheckResult>

<CheckResult assertTrue="false" name="Assert first response base64 equals base64attachment" type="com.itko.lisa.test.AssertPropsEqual">
<log>First response base64 does not match base64 attachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop1>base64Attachment</prop1>
        <prop2>base64response1</prop2>
</CheckResult>

<CheckResult assertTrue="false" name="Assert second response base64 equals base64attachment" type="com.itko.lisa.test.AssertPropsEqual">
<log>Second response base64 does not match base64 attachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop1>base64Attachment</prop1>
        <prop2>base64response2</prop2>
</CheckResult>

<wsdl>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc?wsdl</wsdl>
<endpoint>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc</endpoint>
<targetNamespace>http://soapinterop.org/mtom-cr-test</targetNamespace>
<service>EchoTestSvc</service>
<port>EchoTestPort</port>
<operation>EchoTestMultipleAsBase64Binary</operation>
<onError>abort</onError>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3d3dy53My5vcmcvMjAwMy8wNS9zb2FwLWVudmVsb3BlIiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiPgogIDxzb2FwZW52OkJvZHk+CiAgICA8V3JhcHBlckJhc2U2NEJpbmFyeSB4bWxucz0iaHR0cDovL2V4YW1wbGUub3JnL210b20vZGF0YSI+CiAgICAgIDwhLS1EYXRhIGlzIG9wdGlvbmFsLCBidXQgY2FuIGhhdmUgYW4gdW5saW1pdGVkIG51bWJlciBvZiBpbnN0YW5jZXMtLT4KICAgICAgPERhdGEgeG1sbnM6bnMxPSJodHRwOi8vd3d3LnczLm9yZy8yMDA1LzA1L3htbG1pbWUiIG5zMTpjb250ZW50VHlwZT0iaW1hZ2UvanBlZyI+PCEtLWF0dHJpYnV0ZSBjb250ZW50VHlwZSBpcyBvcHRpb25hbC0tPjx4b3A6SW5jbHVkZSBocmVmPSJjaWQ6RGF0YT01NDhERDg3NjJCMzEyRUEyQTc5ODc3RjI5MTk4RjJFMyIgeG1sbnM6eG9wPSJodHRwOi8vd3d3LnczLm9yZy8yMDA0LzA4L3hvcC9pbmNsdWRlIi8+PC9EYXRhPgogICAgICA8RGF0YSB4bWxuczpuczE9Imh0dHA6Ly93d3cudzMub3JnLzIwMDUvMDUveG1sbWltZSIgbnMxOmNvbnRlbnRUeXBlPSJpbWFnZS9qcGVnIj48IS0tYXR0cmlidXRlIGNvbnRlbnRUeXBlIGlzIG9wdGlvbmFsLS0+PHhvcDpJbmNsdWRlIGhyZWY9ImNpZDpEYXRhPTU0OEREODc2MkIzMTJFQTJBNzk4NzdGMjkxOThGMkU0IiB4bWxuczp4b3A9Imh0dHA6Ly93d3cudzMub3JnLzIwMDQvMDgveG9wL2luY2x1ZGUiLz48L0RhdGE+CiAgICA8L1dyYXBwZXJCYXNlNjRCaW5hcnk+CiAgPC9zb2FwZW52OkJvZHk+Cjwvc29hcGVudjpFbnZlbG9wZT4=</request>
<style>document</style>
<use>literal</use>
<soapAction></soapAction>
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
<soapVersionUri>1.2</soapVersionUri>
<httpVersionUri>1.1</httpVersionUri>
<webMethodUri>POST</webMethodUri>
<mtom>false</mtom>
<dontSendRequest>false</dontSendRequest>
<fault-as-error>true</fault-as-error>
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
<attachments>
<attachment-type>MTOM</attachment-type>
<attachment>
<type>url-bin</type>
<data>{{LISA_TC_URL}}/../Data/ws_attachment.jpg</data>
<contentType>application/octet-stream</contentType>
<cid>Data=548DD8762B312EA2A79877F29198F2E3</cid>
</attachment>
<attachment>
<type>url-bin</type>
<data>{{LISA_TC_URL}}/../Data/ws_attachment.jpg</data>
<contentType>application/octet-stream</contentType>
<cid>Data=548DD8762B312EA2A79877F29198F2E4</cid>
</attachment>
</attachments>
<mtom-paths>
</mtom-paths>
    </Node>


    <Node name="MultipleSendInlineReceiveMTOM" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="MultipleSendMTOMReceiveMTOM" > 

<Documentation>Send two inline base64 data blobs in, receive two MTOM attachments back.</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendInlineReceiveMTOM.request.rsp</valueToFilterKey>
<prop>base64request1</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/:WrapperMTOM/:Data[1]/text()</xpathq>
<nsMap0>=http://example.org/mtom/data</nsMap0>
<nsMap1>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap1>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendInlineReceiveMTOM.request.rsp</valueToFilterKey>
<prop>base64request2</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/:WrapperMTOM/:Data[2]/text()</xpathq>
<nsMap0>=http://example.org/mtom/data</nsMap0>
<nsMap1>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap1>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendInlineReceiveMTOM.rsp</valueToFilterKey>
<prop>attachmentID1</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/ns2:WrapperMTOM/ns2:Data[1]/xop:Include/@href</xpathq>
<nsMap0>xop=http://www.w3.org/2004/08/xop/include</nsMap0>
<nsMap1>ns2=http://example.org/mtom/data</nsMap1>
<nsMap2>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap2>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendInlineReceiveMTOM.rsp</valueToFilterKey>
<prop>attachmentID2</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/ns2:WrapperMTOM/ns2:Data[2]/xop:Include/@href</xpathq>
<nsMap0>xop=http://www.w3.org/2004/08/xop/include</nsMap0>
<nsMap1>ns2=http://example.org/mtom/data</nsMap1>
<nsMap2>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap2>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert first inline base64 equals base64attachment" type="com.itko.lisa.test.AssertPropsEqual">
<log>Inline base64 does not match base64 attachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop1>base64Attachment</prop1>
        <prop2>base64request1</prop2>
</CheckResult>

<CheckResult assertTrue="false" name="Assert second inline base64 equals base64attachment" type="com.itko.lisa.test.AssertPropsEqual">
<log>Inline base64 does not match base64 attachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop1>base64Attachment</prop1>
        <prop2>base64request2</prop2>
</CheckResult>

<CheckResult assertTrue="false" name="Assert attachments equal binaryAttachment" type="com.itko.lisa.test.AssertByScript">
<log>Response attachments did not equal binaryAttachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <script>// This Beanshell script should return a boolean result indicating&#13;&#10;// the assertion is true or false&#13;&#10;&#13;&#10;// Get current step name&#13;&#10;String stepName = testExec.getCurrentNodeName();&#13;&#10;&#13;&#10;// Work around the fact that LISA doesn&apos;t know the current step name in edit mode&#13;&#10;if (stepName == null) return true;&#13;&#10;&#13;&#10;// Get attachment IDs&#13;&#10;List attachmentIDs = new ArrayList();&#13;&#10;attachmentIDs.add(attachmentID1);&#13;&#10;attachmentIDs.add(attachmentID2);&#13;&#10;&#13;&#10;for (String aid : attachmentIDs) {&#13;&#10;&#13;&#10;    // Strip off the leading &quot;cid:&quot;&#13;&#10;    if (aid.startsWith(&quot;cid:&quot;)) aid = aid.substring(4, aid.length());&#13;&#10;&#13;&#10;    // Build the LISA property name that will contain the binary attachment&#13;&#10;    pName = &quot;lisa.&quot; + stepName + &quot;.rsp.attachment.&quot; + aid;&#13;&#10;&#13;&#10;    // Retrieve the attachment&#13;&#10;    byte[] respBytes = (byte[]) testExec.getStateObject(pName);&#13;&#10;&#13;&#10;    // Test the byte arrays are equal&#13;&#10;    if (binaryAttachment.length != respBytes.length) return false;&#13;&#10;    for (int i = 0; i &lt; binaryAttachment.length; i++) {&#13;&#10;        if (binaryAttachment[i] != respBytes[i]) return false;&#13;&#10;    }&#13;&#10;}&#13;&#10;return true;</script>
</CheckResult>

<wsdl>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc?wsdl</wsdl>
<endpoint>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc</endpoint>
<targetNamespace>http://soapinterop.org/mtom-cr-test</targetNamespace>
<service>EchoTestSvc</service>
<port>EchoTestPort</port>
<operation>EchoTestMultipleAsMTOM</operation>
<onError>abort</onError>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3d3dy53My5vcmcvMjAwMy8wNS9zb2FwLWVudmVsb3BlIiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiPgogIDxzb2FwZW52OkJvZHk+CiAgICA8V3JhcHBlck1UT00geG1sbnM9Imh0dHA6Ly9leGFtcGxlLm9yZy9tdG9tL2RhdGEiPgogICAgICA8IS0tRGF0YSBpcyBvcHRpb25hbCwgYnV0IGNhbiBoYXZlIGFuIHVubGltaXRlZCBudW1iZXIgb2YgaW5zdGFuY2VzLS0+CiAgICAgIDxEYXRhIHhtbG5zOm5zMT0iaHR0cDovL3d3dy53My5vcmcvMjAwNS8wNS94bWxtaW1lIiBuczE6Y29udGVudFR5cGU9ImltYWdlL2pwZWciPjwhLS1hdHRyaWJ1dGUgY29udGVudFR5cGUgaXMgb3B0aW9uYWwtLT57e2Jhc2U2NEF0dGFjaG1lbnR9fTwvRGF0YT4KICAgICAgPERhdGEgeG1sbnM6bnMxPSJodHRwOi8vd3d3LnczLm9yZy8yMDA1LzA1L3htbG1pbWUiIG5zMTpjb250ZW50VHlwZT0iaW1hZ2UvanBlZyI+PCEtLWF0dHJpYnV0ZSBjb250ZW50VHlwZSBpcyBvcHRpb25hbC0tPnt7YmFzZTY0QXR0YWNobWVudH19PC9EYXRhPgogICAgPC9XcmFwcGVyTVRPTT4KICA8L3NvYXBlbnY6Qm9keT4KPC9zb2FwZW52OkVudmVsb3BlPg==</request>
<style>document</style>
<use>literal</use>
<soapAction></soapAction>
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
<soapVersionUri>1.2</soapVersionUri>
<httpVersionUri>1.1</httpVersionUri>
<webMethodUri>POST</webMethodUri>
<mtom>false</mtom>
<dontSendRequest>false</dontSendRequest>
<fault-as-error>true</fault-as-error>
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
<attachments>
<attachment-type>MIME</attachment-type>
</attachments>
<mtom-paths>
</mtom-paths>
    </Node>


    <Node name="MultipleSendMTOMReceiveMTOM" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="end" > 

<Documentation>Send two MTOM attachments in, receive two MTOM attachments back.</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendMTOMReceiveMTOM.request.rsp</valueToFilterKey>
<prop>mtomRequest1</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/:WrapperMTOM/:Data[1]/xop:Include</xpathq>
<nsMap0>xop=http://www.w3.org/2004/08/xop/include</nsMap0>
<nsMap1>=http://example.org/mtom/data</nsMap1>
<nsMap2>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap2>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendMTOMReceiveMTOM.request.rsp</valueToFilterKey>
<prop>mtomRequest2</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/:WrapperMTOM/:Data[2]/xop:Include</xpathq>
<nsMap0>xop=http://www.w3.org/2004/08/xop/include</nsMap0>
<nsMap1>=http://example.org/mtom/data</nsMap1>
<nsMap2>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap2>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendMTOMReceiveMTOM.rsp</valueToFilterKey>
<prop>attachmentID1</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/ns2:WrapperMTOM/ns2:Data[1]/xop:Include/@href</xpathq>
<nsMap0>xop=http://www.w3.org/2004/08/xop/include</nsMap0>
<nsMap1>ns2=http://example.org/mtom/data</nsMap1>
<nsMap2>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap2>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.MultipleSendMTOMReceiveMTOM.rsp</valueToFilterKey>
<prop>attachmentID2</prop>
<xpathq>/soapenv:Envelope/soapenv:Body/ns2:WrapperMTOM/ns2:Data[2]/xop:Include/@href</xpathq>
<nsMap0>xop=http://www.w3.org/2004/08/xop/include</nsMap0>
<nsMap1>ns2=http://example.org/mtom/data</nsMap1>
<nsMap2>soapenv=http://www.w3.org/2003/05/soap-envelope</nsMap2>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert request has first MTOM attachment" type="com.itko.lisa.test.CheckResultPropRegEx">
<log>Did not find first MTOM attachment in request</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop>mtomRequest1</prop>
        <param>xop:Include</param>
</CheckResult>

<CheckResult assertTrue="false" name="Assert request has second MTOM attachment" type="com.itko.lisa.test.CheckResultPropRegEx">
<log>Did not find second MTOM attachment in request</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop>mtomRequest2</prop>
        <param>xop:Include</param>
</CheckResult>

<CheckResult assertTrue="false" name="Assert attachments equal binaryAttachment" type="com.itko.lisa.test.AssertByScript">
<log>Response attachments did not equal binaryAttachment</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <script>// This Beanshell script should return a boolean result indicating&#13;&#10;// the assertion is true or false&#13;&#10;&#13;&#10;// Get current step name&#13;&#10;String stepName = testExec.getCurrentNodeName();&#13;&#10;&#13;&#10;// Work around the fact that LISA doesn&apos;t know the current step name in edit mode&#13;&#10;if (stepName == null) return true;&#13;&#10;&#13;&#10;// Get attachment IDs&#13;&#10;List attachmentIDs = new ArrayList();&#13;&#10;attachmentIDs.add(attachmentID1);&#13;&#10;attachmentIDs.add(attachmentID2);&#13;&#10;&#13;&#10;for (String aid : attachmentIDs) {&#13;&#10;&#13;&#10;    // Strip off the leading &quot;cid:&quot;&#13;&#10;    if (aid.startsWith(&quot;cid:&quot;)) aid = aid.substring(4, aid.length());&#13;&#10;&#13;&#10;    // Build the LISA property name that will contain the binary attachment&#13;&#10;    pName = &quot;lisa.&quot; + stepName + &quot;.rsp.attachment.&quot; + aid;&#13;&#10;&#13;&#10;    // Retrieve the attachment&#13;&#10;    byte[] respBytes = (byte[]) testExec.getStateObject(pName);&#13;&#10;&#13;&#10;    // Test the byte arrays are equal&#13;&#10;    if (binaryAttachment.length != respBytes.length) return false;&#13;&#10;    for (int i = 0; i &lt; binaryAttachment.length; i++) {&#13;&#10;        if (binaryAttachment[i] != respBytes[i]) return false;&#13;&#10;    }&#13;&#10;}&#13;&#10;return true;</script>
</CheckResult>

<wsdl>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc?wsdl</wsdl>
<endpoint>http://{{WSSERVER}}:{{WSPORT}}/nxws/services/EchoTestSvc</endpoint>
<targetNamespace>http://soapinterop.org/mtom-cr-test</targetNamespace>
<service>EchoTestSvc</service>
<port>EchoTestPort</port>
<operation>EchoTestMultipleAsMTOM</operation>
<onError>abort</onError>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3d3dy53My5vcmcvMjAwMy8wNS9zb2FwLWVudmVsb3BlIiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiPgogIDxzb2FwZW52OkJvZHk+CiAgICA8V3JhcHBlck1UT00geG1sbnM9Imh0dHA6Ly9leGFtcGxlLm9yZy9tdG9tL2RhdGEiPgogICAgICA8IS0tRGF0YSBpcyBvcHRpb25hbCwgYnV0IGNhbiBoYXZlIGFuIHVubGltaXRlZCBudW1iZXIgb2YgaW5zdGFuY2VzLS0+CiAgICAgIDxEYXRhIHhtbG5zOm5zMT0iaHR0cDovL3d3dy53My5vcmcvMjAwNS8wNS94bWxtaW1lIiBuczE6Y29udGVudFR5cGU9ImltYWdlL2pwZWciPjwhLS1hdHRyaWJ1dGUgY29udGVudFR5cGUgaXMgb3B0aW9uYWwtLT48eG9wOkluY2x1ZGUgaHJlZj0iY2lkOkRhdGE9RUJGNTA1RTcwNUI0MEU0RkNFQjg5QkUyRDU3NkFFRDEiIHhtbG5zOnhvcD0iaHR0cDovL3d3dy53My5vcmcvMjAwNC8wOC94b3AvaW5jbHVkZSIvPjwvRGF0YT4KICAgICAgPERhdGEgeG1sbnM6bnMxPSJodHRwOi8vd3d3LnczLm9yZy8yMDA1LzA1L3htbG1pbWUiIG5zMTpjb250ZW50VHlwZT0iaW1hZ2UvanBlZyI+PCEtLWF0dHJpYnV0ZSBjb250ZW50VHlwZSBpcyBvcHRpb25hbC0tPjx4b3A6SW5jbHVkZSBocmVmPSJjaWQ6RGF0YT02NDhERDg3NjJCMzEyRUEyQTc5ODc3RjI5MTk4RjJFNCIgeG1sbnM6eG9wPSJodHRwOi8vd3d3LnczLm9yZy8yMDA0LzA4L3hvcC9pbmNsdWRlIi8+PC9EYXRhPgogICAgPC9XcmFwcGVyTVRPTT4KICA8L3NvYXBlbnY6Qm9keT4KPC9zb2FwZW52OkVudmVsb3BlPg==</request>
<style>document</style>
<use>literal</use>
<soapAction></soapAction>
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
<soapVersionUri>1.2</soapVersionUri>
<httpVersionUri>1.1</httpVersionUri>
<webMethodUri>POST</webMethodUri>
<mtom>false</mtom>
<dontSendRequest>false</dontSendRequest>
<fault-as-error>true</fault-as-error>
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
<attachments>
<attachment-type>MTOM</attachment-type>
<attachment>
<type>url-bin</type>
<data>{{LISA_TC_URL}}/../Data/ws_attachment.jpg</data>
<contentType>application/octet-stream</contentType>
<cid>Data=EBF505E705B40E4FCEB89BE2D576AED1</cid>
</attachment>
<attachment>
<type>url-bin</type>
<data>{{LISA_TC_URL}}/../Data/ws_attachment.jpg</data>
<contentType>application/octet-stream</contentType>
<cid>Data=648DD8762B312EA2A79877F29198F2E4</cid>
</attachment>
</attachments>
<mtom-paths>
</mtom-paths>
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


</TestCase>
