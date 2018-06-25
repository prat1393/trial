<?xml version="1.0" ?>

<TestCase name="ws_security-xml" version="5">

<meta>
   <create version="6.1" buildNumber="6.1.0.96" author="joncaulfield" date="05/03/2012" host="localhost" />
   <lastEdited version="0.0" buildNumber="0.0.0.0" author="brogers" date="04/08/2013" host="gimli.local" />
</meta>

<id>a9882776</id>
<Documentation>This test model shows how to use signed and encrypted SOAP messages.  The first two steps should succeed and the last two steps should fail. (The calls are the same but the web service will not accept messages that are not encrypted or signed.)&#10;&#10;This test plan requires that your Java environment support unlimited strength encryption.  If either of the first two steps fail, a likely suspect is that your JCE jars need to be updated to enable unlimited strength encryption.  The JCE jars can be downloaded from www.oracle.com (search on the keywords &quot;JCE unlimited strength&quot; and pick the JCE library matching your Java version, such as JCE 7 for Java 7).  After you install the JCE jars, your LISA services will need to be restarted.&#10;&#10;You will notice in the audit document that two occurrences of Step Error are expected.  We audit for two Step Errors because we expect both of the last two steps here to raise Step Errors.  Thus, the audit document can also audit how many occurrences of an event are received.  If we add a third Step Error entry to the audit document, the auditor will fail this test since only two Step Error events are raised.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9MyZ0Y3Y9NSZsaXNhdj0wLjAgKDAuMC4wLjApJm5vZGVzPS0yOTcwOTI0NDk=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="WS Security: Test XML Signature" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          uid="a9882776-WS+Security%3A+Test+XML+Signature" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="WS Security: Test XML Encryption" > 

<Documentation>We sign the SOAP messages using a keystore - {{LISA_PROJ_ROOT}}/Data/keystores/keystore.jks;&#10;See the advanced tab of teh WS call.</Documentation>
<wsdl>http://{{WSSERVER}}:{{WSPORT}}/itko-examples/services/SignedCalculatorService?wsdl</wsdl>
<endpoint>http://{{WSSERVER}}:{{WSPORT}}/itko-examples/services/SignedCalculatorService</endpoint>
<targetNamespace>http://examples.itko.com/itko-examples/services/SignedCalculatorService</targetNamespace>
<service>CalculatorService</service>
<port>SignedCalculatorService</port>
<operation>add</operation>
<onError>abort</onError>
<maintainSession>true</maintainSession>
<clearSession>false</clearSession>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbnZlbG9wZS8iIHhtbG5zOnNvYXBlbmM9Imh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3NvYXAvZW5jb2RpbmcvIiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiIHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiIHhtbG5zOmVuYz0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbmNvZGluZy8iPgogIDxzb2FwZW52OkJvZHk+CiAgICA8bnMxOmFkZCB4bWxuczpuczE9Imh0dHA6Ly93ZWJzZXJ2aWNlcy5leGFtcGxlcy5pdGtvLmNvbSIgZW5jb2RpbmdTdHlsZT0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbmNvZGluZy8iPgogICAgICA8aTEgeHNpOnR5cGU9InhzOmludCI+MjwvaTE+CiAgICAgIDxpMiB4c2k6dHlwZT0ieHM6aW50Ij4zPC9pMj4KICAgIDwvbnMxOmFkZD4KICA8L3NvYXBlbnY6Qm9keT4KPC9zb2FwZW52OkVudmVsb3BlPg==</request>
<style>rpc</style>
<use>encoded</use>
<sslInfo>
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
<timestamp2>
<enabled>true</enabled>
<useMsTimestamp>true</useMsTimestamp>
<ttl>300</ttl>
</timestamp2>
<signature>
<enabled>true</enabled>
<file>{{LISA_PROJ_ROOT}}/Data/keystores/keystore.jks</file>
<password-encr>d32b9ba6faca78de7b32314e43c15b4f</password-encr>
<defaultalias>test</defaultalias>
<alias-pass-encr>d32b9ba6faca78de7b32314e43c15b4f</alias-pass-encr>
<keyId>IssuerSerial</keyId>
<algo>http://www.w3.org/2000/09/xmldsig#dsa-sha1</algo>
<digest_algo>http://www.w3.org/2000/09/xmldsig#sha1</digest_algo>
</signature>
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


    <Node name="WS Security: Test XML Encryption" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          uid="a9882776-WS+Security%3A+Test+XML+Encryption" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="WS Security: Fail: Document not encrypted" > 

<Documentation>We encrypt the SOAP request using AES 128-bit encryption</Documentation>
<wsdl>http://{{WSSERVER}}:{{WSPORT}}/itko-examples/services/EncryptedCalculatorService?wsdl</wsdl>
<endpoint>http://{{WSSERVER}}:{{WSPORT}}/itko-examples/services/EncryptedCalculatorService</endpoint>
<targetNamespace>http://examples.itko.com/itko-examples/services/EncryptedCalculatorService</targetNamespace>
<service>CalculatorService</service>
<port>EncryptedCalculatorService</port>
<operation>add</operation>
<onError>abort</onError>
<maintainSession>true</maintainSession>
<clearSession>false</clearSession>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbnZlbG9wZS8iIHhtbG5zOnNvYXBlbmM9Imh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3NvYXAvZW5jb2RpbmcvIiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiIHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiIHhtbG5zOmVuYz0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbmNvZGluZy8iPgogIDxzb2FwZW52OkJvZHk+CiAgICA8bnMxOmFkZCB4bWxuczpuczE9Imh0dHA6Ly93ZWJzZXJ2aWNlcy5leGFtcGxlcy5pdGtvLmNvbSIgZW5jb2RpbmdTdHlsZT0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbmNvZGluZy8iPgogICAgICA8aTEgeHNpOnR5cGU9InhzOmludCI+NjwvaTE+CiAgICAgIDxpMiB4c2k6dHlwZT0ieHM6aW50Ij43PC9pMj4KICAgIDwvbnMxOmFkZD4KICA8L3NvYXBlbnY6Qm9keT4KPC9zb2FwZW52OkVudmVsb3BlPg==</request>
<style>rpc</style>
<use>encoded</use>
<sslInfo>
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
<fault-as-error>true</fault-as-error>
<callTimeout>600000</callTimeout>
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
<timestamp2>
<enabled>true</enabled>
<useMsTimestamp>true</useMsTimestamp>
<ttl>300</ttl>
</timestamp2>
<usernameToken>
<enabled>false</enabled>
<type>PasswordText</type>
<signed>false</signed>
<nonce>true</nonce>
<craeted>true</craeted>
<useMsTimestamp>true</useMsTimestamp>
</usernameToken>
<signature>
<enabled>false</enabled>
<password-encr>f5504e2d23a7888253a27e8ef52607d8</password-encr>
<alias-pass-encr>f5504e2d23a7888253a27e8ef52607d8</alias-pass-encr>
<keyId>IssuerSerial</keyId>
<algo>http://www.w3.org/2000/09/xmldsig#rsa-sha1</algo>
<digest_algo>http://www.w3.org/2000/09/xmldsig#sha1</digest_algo>
</signature>
<encryption>
<enabled>true</enabled>
<file>{{LISA_PROJ_ROOT}}/Data/keystores/myout.p12</file>
<password-encr>9839f65d0802b8cf48da5c1b095a0daa</password-encr>
<defaultalias>16c73ab6-b892-458f-abf5-2f875f74882e</defaultalias>
<alias-pass-encr>f5504e2d23a7888253a27e8ef52607d8</alias-pass-encr>
<keyId>IssuerSerial</keyId>
<algo>http://www.w3.org/2001/04/xmlenc#aes128-cbc</algo>
<transport>http://www.w3.org/2001/04/xmlenc#rsa-1_5</transport>
</encryption>
</wssecurity>
<wssecurity>
<isSend>false</isSend>
<must>false</must>
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


    <Node name="WS Security: Fail: Document not encrypted" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          uid="a9882776-WS+Security%3A+Fail%3A+Document+not+encrypted" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="WS Security: Fail: Missing Signature" > 

<Documentation>This step is designed to fail because we send an unencrypted SOAP request</Documentation>
<wsdl>http://{{WSSERVER}}:{{WSPORT}}/itko-examples/services/SignedCalculatorService?wsdl</wsdl>
<endpoint>http://{{WSSERVER}}:{{WSPORT}}/itko-examples/services/SignedCalculatorService</endpoint>
<targetNamespace>http://examples.itko.com/itko-examples/services/SignedCalculatorService</targetNamespace>
<service>CalculatorService</service>
<port>SignedCalculatorService</port>
<operation>add</operation>
<onError>WS Security: Fail: Missing Signature</onError>
<maintainSession>true</maintainSession>
<clearSession>false</clearSession>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbnZlbG9wZS8iIHhtbG5zOnNvYXBlbmM9Imh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3NvYXAvZW5jb2RpbmcvIiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiIHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiIHhtbG5zOmVuYz0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbmNvZGluZy8iPgogIDxzb2FwZW52OkJvZHk+CiAgICA8bnMxOmFkZCB4bWxuczpuczE9Imh0dHA6Ly93ZWJzZXJ2aWNlcy5leGFtcGxlcy5pdGtvLmNvbSIgZW5jb2RpbmdTdHlsZT0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbmNvZGluZy8iPgogICAgICA8aTEgeHNpOnR5cGU9InhzOmludCI+MjwvaTE+CiAgICAgIDxpMiB4c2k6dHlwZT0ieHM6aW50Ij4zPC9pMj4KICAgIDwvbnMxOmFkZD4KICA8L3NvYXBlbnY6Qm9keT4KPC9zb2FwZW52OkVudmVsb3BlPg==</request>
<style>rpc</style>
<use>encoded</use>
<sslInfo>
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
<timestamp2>
<enabled>false</enabled>
<useMsTimestamp>true</useMsTimestamp>
<ttl>300</ttl>
</timestamp2>
<usernameToken>
<enabled>false</enabled>
<type>PasswordText</type>
<signed>false</signed>
<nonce>true</nonce>
<craeted>true</craeted>
<useMsTimestamp>true</useMsTimestamp>
</usernameToken>
<signature>
<enabled>false</enabled>
<password-encr>f5504e2d23a7888253a27e8ef52607d8</password-encr>
<alias-pass-encr>f5504e2d23a7888253a27e8ef52607d8</alias-pass-encr>
<keyId>IssuerSerial</keyId>
<algo>http://www.w3.org/2000/09/xmldsig#rsa-sha1</algo>
<digest_algo>http://www.w3.org/2000/09/xmldsig#sha1</digest_algo>
</signature>
<encryption>
<enabled>false</enabled>
<file>{{LISA_PROJ_ROOT}}/Data/keystores/myout.p12</file>
<password-encr>9839f65d0802b8cf48da5c1b095a0daa</password-encr>
<defaultalias>16c73ab6-b892-458f-abf5-2f875f74882e</defaultalias>
<alias-pass-encr>f5504e2d23a7888253a27e8ef52607d8</alias-pass-encr>
<keyId>IssuerSerial</keyId>
<algo>http://www.w3.org/2001/04/xmlenc#aes128-cbc</algo>
<transport>http://www.w3.org/2001/04/xmlenc#rsa-1_5</transport>
</encryption>
</wssecurity>
<wssecurity>
<isSend>false</isSend>
<must>false</must>
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


    <Node name="WS Security: Fail: Missing Signature" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          uid="a9882776-WS+Security%3A+Fail%3A+Missing+Signature" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="end" > 

<Documentation>This step is designed to fail because we send an unsigned SOAP request</Documentation>
<wsdl>http://{{WSSERVER}}:{{WSPORT}}/itko-examples/services/EncryptedCalculatorService?wsdl</wsdl>
<endpoint>http://{{WSSERVER}}:{{WSPORT}}/itko-examples/services/EncryptedCalculatorService</endpoint>
<targetNamespace>http://examples.itko.com/itko-examples/services/EncryptedCalculatorService</targetNamespace>
<service>CalculatorService</service>
<port>EncryptedCalculatorService</port>
<operation>add</operation>
<onError>end</onError>
<maintainSession>true</maintainSession>
<clearSession>false</clearSession>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbnZlbG9wZS8iIHhtbG5zOnNvYXBlbmM9Imh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3NvYXAvZW5jb2RpbmcvIiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiIHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiIHhtbG5zOmVuYz0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbmNvZGluZy8iPgogIDxzb2FwZW52OkJvZHk+CiAgICA8bnMxOmFkZCB4bWxuczpuczE9Imh0dHA6Ly93ZWJzZXJ2aWNlcy5leGFtcGxlcy5pdGtvLmNvbSIgZW5jb2RpbmdTdHlsZT0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbmNvZGluZy8iPgogICAgICA8aTEgeHNpOnR5cGU9InhzOmludCI+NjwvaTE+CiAgICAgIDxpMiB4c2k6dHlwZT0ieHM6aW50Ij43PC9pMj4KICAgIDwvbnMxOmFkZD4KICA8L3NvYXBlbnY6Qm9keT4KPC9zb2FwZW52OkVudmVsb3BlPg==</request>
<style>rpc</style>
<use>encoded</use>
<sslInfo>
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
<fault-as-error>true</fault-as-error>
<callTimeout>600000</callTimeout>
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
<timestamp2>
<enabled>true</enabled>
<useMsTimestamp>true</useMsTimestamp>
<ttl>300</ttl>
</timestamp2>
<encryption>
<enabled>false</enabled>
<file>{{LISA_PROJ_ROOT}}/Data/keystores/myout.p12</file>
<password-encr>9839f65d0802b8cf48da5c1b095a0daa</password-encr>
<defaultalias>16c73ab6-b892-458f-abf5-2f875f74882e</defaultalias>
<alias-pass-encr>f5504e2d23a7888253a27e8ef52607d8</alias-pass-encr>
<keyId>IssuerSerial</keyId>
<algo>http://www.w3.org/2001/04/xmlenc#aes128-cbc</algo>
<transport>http://www.w3.org/2001/04/xmlenc#rsa-1_5</transport>
</encryption>
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


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="a9882776-end" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="a9882776-fail" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="a9882776-abort" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


</TestCase>
