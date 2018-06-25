<?xml version="1.0" ?>

<TestCase name="webservices" version="5">

<meta><create version="5.0.23" buildNumber="5.0.23.206" author="ckraus" date="12/01/2010" host="Chris-Krauss-MacBook-Pro.local"  /><lastEdited version="5.1" buildNumber="5.1.0.180" author="ckraus" date="12/01/2010" host="Chris-Krauss-MacBook-Pro.local"  /></meta>

<id>de9077a5</id>
<Documentation>This test case will add, get, delete a user from the EJB3 web services.  A unique code generator is used to create a number prefixed by a value {{user}} from the config file.  The password is hard coded in the config file.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj01LjEgKDUuMS4wLjE4MCkmbm9kZXM9LTE4NDY5NTM5MDU=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="Log Test Data" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="Add User" > 

<Documentation>prerfix {{user}} from config file and random number to create unique_user</Documentation>

      <!-- Data Sets -->
<readrec>unique_user</readrec>
<log>add a user to LISA Bank via EJB3 web services&#10;data used for &#10;login ==&gt;{{unique_user}}&lt;&#10;password ==&gt;{{password}}&lt; </log>
    </Node>


    <Node name="Add User" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Get User" > 

<Documentation>Add user to LISA Bank</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.Add User.rsp</valueToFilterKey>
<prop>fl_login</prop>
<xpathq>/env:Envelope/env:Body/ns2:addUserResponse/return/login/text()</xpathq>
<nsMap0>env=http://schemas.xmlsoap.org/soap/envelope/</nsMap0>
<nsMap1>ns2=http://ejb3.examples.itko.com/</nsMap1>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Check Login" type="com.itko.lisa.test.CheckResultPropRegEx">
<log>looking for login used to add user</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop>fl_login</prop>
        <param>{{unique_user}}</param>
</CheckResult>

<CheckResult assertTrue="false" name="Verify default role" type="com.itko.lisa.test.CheckResultContains">
<log>Did not find default role of REGULAR when adding user</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>&gt;REGULAR&lt;</param>
</CheckResult>

<wsdl>http://{{WSSERVER}}:{{WSPORT}}/itkoExamples/EJB3UserControlBean?wsdl</wsdl>
<endpoint>{{ENDPOINT1}}</endpoint>
<targetNamespace>http://ejb3.examples.itko.com/</targetNamespace>
<service>EJB3UserControlBeanService</service>
<port>EJB3UserControlBeanPort</port>
<operation>addUser</operation>
<onError>abort</onError>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbnZlbG9wZS8iIHhtbG5zOnhzPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYSI+CiAgPHNvYXBlbnY6Qm9keT4KICAgIDxhZGRVc2VyIHhtbG5zPSJodHRwOi8vZWpiMy5leGFtcGxlcy5pdGtvLmNvbS8iPgogICAgICA8IS0tdXNlcm5hbWUgaXMgb3B0aW9uYWwtLT4KICAgICAgPHVzZXJuYW1lIHhtbG5zPSIiPnt7dW5pcXVlX3VzZXJ9fTwvdXNlcm5hbWU+CiAgICAgIDwhLS1wYXNzd29yZCBpcyBvcHRpb25hbC0tPgogICAgICA8cGFzc3dvcmQgeG1sbnM9IiI+e3twYXNzd29yZH19PC9wYXNzd29yZD4KICAgIDwvYWRkVXNlcj4KICA8L3NvYXBlbnY6Qm9keT4KPC9zb2FwZW52OkVudmVsb3BlPg==</request>
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


    <Node name="Get User" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Delete User" > 

<Documentation>Get the User back from the LISA Bank and make sure that the login matches from input to returned user</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.Get User.rsp</valueToFilterKey>
<prop>fl_login_get</prop>
<xpathq>/env:Envelope/env:Body/ns2:getUserResponse/return/login/text()</xpathq>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Check Login" type="com.itko.lisa.test.CheckResultPropRegEx">
<log>login returned from get did not match</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop>fl_login_get</prop>
        <param>{{unique_user}}</param>
</CheckResult>

<wsdl>http://{{WSSERVER1}}:{{WSPORT}}/itkoExamples/EJB3UserControlBean?wsdl</wsdl>
<endpoint>{{ENDPOINT1}}</endpoint>
<targetNamespace>http://ejb3.examples.itko.com/</targetNamespace>
<service>EJB3UserControlBeanService</service>
<port>(unsupported) (unsupported) EJB3UserControlBeanPort</port>
<operation>getUser</operation>
<onError>abort</onError>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbnZlbG9wZS8iIHhtbG5zOnhzPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYSI+CiAgPHNvYXBlbnY6Qm9keT4KICAgIDxnZXRVc2VyIHhtbG5zPSJodHRwOi8vZWpiMy5leGFtcGxlcy5pdGtvLmNvbS8iPgogICAgICA8IS0tdXNlcm5hbWUgaXMgb3B0aW9uYWwtLT4KICAgICAgPHVzZXJuYW1lIHhtbG5zPSIiPnt7dW5pcXVlX3VzZXJ9fTwvdXNlcm5hbWU+CiAgICA8L2dldFVzZXI+CiAgPC9zb2FwZW52OkJvZHk+Cjwvc29hcGVudjpFbnZlbG9wZT4=</request>
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


    <Node name="Delete User" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="end" > 

<Documentation>Delete the user to clean up the test case</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Check for True" type="com.itko.lisa.test.CheckResultContains">
<log>delete did not return true</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>&lt;return&gt;true&lt;/return&gt;</param>
</CheckResult>

<wsdl>http://{{WSSERVER1}}:{{WSPORT}}/itkoExamples/EJB3UserControlBean?wsdl</wsdl>
<endpoint>{{ENDPOINT1}}</endpoint>
<targetNamespace>http://ejb3.examples.itko.com/</targetNamespace>
<service>EJB3UserControlBeanService</service>
<port>(unsupported) EJB3UserControlBeanPort</port>
<operation>deleteUser</operation>
<onError>abort</onError>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbnZlbG9wZS8iIHhtbG5zOnhzPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYSI+CiAgPHNvYXBlbnY6Qm9keT4KICAgIDxkZWxldGVVc2VyIHhtbG5zPSJodHRwOi8vZWpiMy5leGFtcGxlcy5pdGtvLmNvbS8iPgogICAgICA8IS0tdXNlcm5hbWUgaXMgb3B0aW9uYWwtLT4KICAgICAgPHVzZXJuYW1lIHhtbG5zPSIiPnt7dW5pcXVlX3VzZXJ9fTwvdXNlcm5hbWU+CiAgICA8L2RlbGV0ZVVzZXI+CiAgPC9zb2FwZW52OkJvZHk+Cjwvc29hcGVudjpFbnZlbG9wZT4=</request>
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


    <DataSet type="com.itko.lisa.test.DataSetIDGenerator" name="unique_user" atend="" local="false" random="false" maxItemsToFetch="100" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAALdW5pcXVlX3VzZXJ0ABF3ZWJhcHAtMTgwOTY5NzA3NHg=</sample>
    <type>Number</type>
    <prefix>{{user}}-</prefix>
    </DataSet>

</TestCase>
