<?xml version="1.0" ?>

<TestCase name="ip-spoofing" version="5">

<meta><create version="0.0" buildNumber="0.0.0.0" author="Chip Killmar" date="08/27/2010" host="killmar1"  /><lastEdited version="0.0" buildNumber="0.0.0.0" author="Chip Killmar" date="09/02/2010" host="killmar1"  /></meta>

<id>556a99e8</id>
<Documentation>***&#10;Note: this test case requires that your LISA examples server is running.&#10;***&#10;&#10;This example test case demonstrates IP spoofing support in LISA.&#10;&#10;It requests the URL &quot;http://localhost:8080/ip-spoof-test&quot; using a REST step, a web page that contains the IP address of the requesting client.&#10;&#10;It then makes a SOAP request to the URL http://localhost:8080/itko-examples/ip-spoof-test/webservice, a web service containing an operation that returns the IP address of the requesting client. &#10;&#10;It executes both requests in a loop for 10 times.&#10;&#10;You can stage this test in conjunction with the IP Spoofing Test staging document &quot;ip-spoofing.stg&quot;.&#10;&#10;With the correct network interface configuration, you should see different IP addresses used among virtual users as they make the HTTP and SOAP requests.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj0wLjAgKDAuMC4wLjApJm5vZGVzPS0xODgwNjQzNDA=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="Set IP_SPOOFING_TEST_SERVER" log=""
          type="com.itko.lisa.test.ScriptNode" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Log Value of IP_SPOOFING_TEST_SERVER" > 

<Documentation>Creates a new LISA property, IP_SPOOFING_TEST_SERVER, from the property SERVER defined in project.config.</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="Any Exception Then Fail" type="com.itko.lisa.dynexec.CheckInvocationEx">
<log>Assert [Any Exception Then Fail] fired true of type Assert on Invocation Exception</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>.*</param>
</CheckResult>

<onerror>abort</onerror>
<script>/*&#13;&#10; * The code below creates a new LISA property, &quot;IP_SPOOFING_TEST_SERVER&quot;, from&#13;&#10; * the property &quot;SERVER&quot; defined in project.config.&#13;&#10; *&#13;&#10; * This is done because if the value &quot;localhost&quot; is used to request the IP&#13;&#10; * spoofing test page URL in a later step, only the loopback IP address can be&#13;&#10; * used.&#13;&#10; *&#13;&#10; * If we detect that &quot;localhost&quot; is set for the property value, use the&#13;&#10; * current machine&apos;s hostname value contained in the property &quot;LISA_HOST&quot;.&#13;&#10; */&#13;&#10;String ipSpoofingTestServer = (String) testExec.getStateValue(&quot;SERVER&quot;);&#13;&#10;if (ipSpoofingTestServer != null &amp;&amp; &quot;localhost&quot;.equalsIgnoreCase(ipSpoofingTestServer)) {&#13;&#10;    ipSpoofingTestServer = testExec.getStateValue(&quot;LISA_HOST&quot;);&#13;&#10;}&#13;&#10;testExec.setStateValue(&quot;IP_SPOOFING_TEST_SERVER&quot;, ipSpoofingTestServer);</script>
    </Node>


    <Node name="Log Value of IP_SPOOFING_TEST_SERVER" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="Request IP Spoofing Test Page" > 

<Documentation>Logs the value of the property IP_SPOOFING_TEST_SERVER.</Documentation>
<log>IP_SPOOFING_TEST_SERVER={{IP_SPOOFING_TEST_SERVER}}&#13;&#10;&#13;&#10;To change this value, update the value of the &quot;SERVER&quot; key in your configuration.</log>
    </Node>


    <Node name="Request IP Spoofing Test Page" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="2" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Log Value of SPOOFED_IP_ADDRESS from Test Page" > 

<Documentation>Makes an HTTP GET request for the IP spoofing test web page URL hosted on the LISA demo server.</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.Request IP Spoofing Test Page.rsp</valueToFilterKey>
<prop>SPOOFED_IP_ADDRESS</prop>
<xpathq>string(//DIV[@id=&apos;mainContent&apos;]/P[1]/STRONG)</xpathq>
<nsMap0>=http://www.w3.org/1999/xhtml</nsMap0>
      </Filter>


      <!-- Data Sets -->
<readrec>Counter</readrec>
<url>
<proto>http</proto>
<host>{{IP_SPOOFING_TEST_SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/ip-spoof-test</path>
<parameterUrl>http://{{IP_SPOOFING_TEST_SERVER}}:{{PORT}}/itko-examples/ip-spoof-test</parameterUrl>
</url>
<content></content>
<httpMethod>GET</httpMethod>
    </Node>


    <Node name="Log Value of SPOOFED_IP_ADDRESS from Test Page" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="Request IP Spoofing Test Web Service" > 

<Documentation>Logs the value of the property SPOOFED_IP_ADDRESS obtained by parsing the response from the IP spoofing test web page.</Documentation>
<log>SPOOFED_IP_ADDRESS = {{SPOOFED_IP_ADDRESS}}</log>
    </Node>


    <Node name="Request IP Spoofing Test Web Service" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Log Value of SPOOFED_IP_ADDRESS from Test Web Service" > 

<Documentation>Sends a SOAP request via HTTP POST for the IP spoofing test web service hosted on the LISA demo server.</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.Request IP Spoofing Test Web Service.rsp</valueToFilterKey>
<prop>SPOOFED_IP_ADDRESS</prop>
<xpathq>string(/env:Envelope/env:Body/ns2:getIPAddressResponse/*[name()=&apos;return&apos;])</xpathq>
<nsMap0>env=http://schemas.xmlsoap.org/soap/envelope/</nsMap0>
<nsMap1>ns2=http://ipspooftest.lisa.itko.com/</nsMap1>
      </Filter>

<wsdl>http://{{IP_SPOOFING_TEST_SERVER}}:{{PORT}}/itko-examples/ip-spoof-test/webservice?wsdl</wsdl>
<endpoint>http://{{IP_SPOOFING_TEST_SERVER}}:{{PORT}}/itko-examples/ip-spoof-test/webservice</endpoint>
<targetNamespace>http://ipspooftest.lisa.itko.com/</targetNamespace>
<service>IPSpoofTestWebServiceImplService</service>
<port>IPSpoofTestWebServiceImplPort</port>
<operation>getIPAddress</operation>
<onError>abort</onError>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbnZlbG9wZS8iIHhtbG5zOnhzPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYSI+CiAgPHNvYXBlbnY6Qm9keT4KICAgIDxnZXRJUEFkZHJlc3MgeG1sbnM9Imh0dHA6Ly9pcHNwb29mdGVzdC5saXNhLml0a28uY29tLyIvPgogIDwvc29hcGVudjpCb2R5Pgo8L3NvYXBlbnY6RW52ZWxvcGU+</request>
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


    <Node name="Log Value of SPOOFED_IP_ADDRESS from Test Web Service" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="Request IP Spoofing Test Page" > 

<Documentation>Logs the value of the property SPOOFED_IP_ADDRESS obtained by parsing the SOAP response from the IP spoofing test web service.</Documentation>
<log>SPOOFED_IP_ADDRESS = {{SPOOFED_IP_ADDRESS}}</log>
    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <DataSet type="com.itko.lisa.test.CounterDataSet" name="Counter" atend="end" local="true" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAAeA==</sample>
    <propKey>DataSet1</propKey>
    <countFrom>1</countFrom>
    <countTo>10</countTo>
    <countIncrement>1</countIncrement>
    </DataSet>

</TestCase>
