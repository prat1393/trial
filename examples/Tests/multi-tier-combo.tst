<?xml version="1.0" ?>

<TestCase name="multi-tier-combo" version="5">

<meta>
   <create version="0.0" buildNumber="0.0.0.0" author="cam" date="11/12/2009" host="sloth" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="03/28/2018" host="HVDIVD18MIS1939" />
</meta>

<id>456306a0</id>
<Documentation>The MultiTierCombo test uses a variety of service endpoints to validate the LisaBank example. We test SOAP, EJB, JMS and web transactions and validate these transactions in a variety of ways including directly validating the demo server database.&#10;&#10;The test also demonstrates how you can build complex SOAP objects from spreadsheets. The &apos;User&apos; dataset on the first step is backed by a spreadsheet named &apos;multi-tier-users.xls&apos; in the &apos;Data&apos; folder of the project.&#10;&#10;If you run this test in the Interactive Test Run window (ITR) it will create single user from the first row of the spreadsheet and then the test will finish.&#10;&#10;If you stage the test with the example &apos;1User0Think_RunContinuously&apos; staging document, the test will be restarted until the end of the data set is reached. This is the preferred way to repeatedly iterate over a large data set. You could introduce a &apos;loop&apos; in the test case but it&apos;s nowhere near as flexible.&#10;&#10; If you let the staging document control the dataset ending the test then you can spread the test over many virtual users (if you want to) or control the pacing of the test with think times, etc.&#10;&#10;Note that the staging document &apos;end the continuous test run&apos; behaviour is only affected by global data sets that are set on the FIRST step in the test. If the data set is local to the test or declared elsewhere in the test, the &apos;run continuously&apos; behaviour really does mean &apos;run forever&apos;.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj04LjQuMCAoOC40LjAuMjI3KSZub2Rlcz0xOTcwNTk3Mjc=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

<CheckResult assertTrue="true" name="SimpleWebAssertion1" type="com.itko.lisa.test.SimpleWebAssertion">
<log>Assert [SimpleWebAssertion1] fired true of type Simple Web Assertion</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

    <Node name="Add User Object XML" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          uid="456306a0-Add+User+Object+XML" 
          think="1S-10S" 
          useFilters="true" 
          quiet="false" 
          next="Get User" > 

<Documentation>- adds a new customer to the bank from a XML dataset&#10;- the customers&apos;s login is verified in the returned soap object&#10;- the customer input data is filtered and used in latter steps&#10;-- password&#10;-- first name&#10;- the soap response is filtered and used in latter steps&#10;-- checking account id</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.Add User Object XML.rsp</valueToFilterKey>
<prop>fl_login</prop>
<xpathq>string(/env:Envelope/env:Body/ns2:addUserObjectResponse/*[name()=&apos;return&apos;]/*[name()=&apos;login&apos;])</xpathq>
<nsMap0>env=http://schemas.xmlsoap.org/soap/envelope/</nsMap0>
<nsMap1>ns2=http://ejb3.examples.itko.com/</nsMap1>
      </Filter>

      <Filter type="com.itko.lisa.xml.FilterXMLXPath">
        <valueToFilterKey>lisa.Add User Object XML.rsp</valueToFilterKey>
<prop>fl_checking_account_id</prop>
<xpathq>/env:Envelope/env:Body/ns2:addUserObjectResponse/return/accounts[2]/id/text()</xpathq>
<nsMap0>=</nsMap0>
<nsMap1>ns2=http://ejb3.examples.itko.com/</nsMap1>
<nsMap2>env=http://schemas.xmlsoap.org/soap/envelope/</nsMap2>
      </Filter>

      <Filter type="com.itko.lisa.test.FilterXMLGetChildText">
        <valueToFilterKey>DataSet1</valueToFilterKey>
      <tag>pwd</tag>
      <tagn>1</tagn>
      <prop>fl_pwd</prop>
      </Filter>

      <Filter type="com.itko.lisa.test.FilterXMLGetChildText">
        <valueToFilterKey>lisa.Add User Object XML.rsp</valueToFilterKey>
      <tag>fname</tag>
      <tagn>1</tagn>
      <prop>fl_fname</prop>
      </Filter>


      <!-- Data Sets -->
<readrec>ds_login</readrec>
<readrec>DataSet1</readrec>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Check For Property" type="com.itko.lisa.test.CheckResultPropRegEx">
<log>Assert [Assert96] fired false of type Property Value Expression</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop>fl_login</prop>
        <param>{{ds_login}}</param>
</CheckResult>

<CheckResult assertTrue="false" name="Check login tag exists" type="com.itko.lisa.xml.AssertXMLXPath">
<log>the login tag was not return User not added</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
<xpathq>/env:Envelope/env:Body/ns2:addUserObjectResponse/*[name()=&apos;return&apos;]/*[name()=&apos;login&apos;]</xpathq>
<nsMap0>env=http://schemas.xmlsoap.org/soap/envelope/</nsMap0>
<nsMap1>ns2=http://ejb3.examples.itko.com/</nsMap1>
</CheckResult>

<wsdl>http://localhost:8080/itkoExamples/EJB3UserControlBean?wsdl</wsdl>
<endpoint>{{ENDPOINT1}}</endpoint>
<targetNamespace>http://ejb3.examples.itko.com/</targetNamespace>
<service>EJB3UserControlBeanService</service>
<port>EJB3UserControlBeanPort</port>
<operation>addUserObject</operation>
<onError>abort</onError>
<maintainSession>true</maintainSession>
<clearSession>false</clearSession>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxzb2FwZW52OkVudmVsb3BlIHhtbG5zOnNvYXBlbnY9Imh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3NvYXAvZW52ZWxvcGUvIiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiPg0KICA8c29hcGVudjpCb2R5Pg0KICAgIHt7RGF0YVNldDF9fQ0KICA8L3NvYXBlbnY6Qm9keT4NCjwvc29hcGVudjpFbnZlbG9wZT4=</request>
<style>document</style>
<use>literal</use>
<sslInfo>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
<ssl-key-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-key-password-enc>
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


    <Node name="Get User" log="Verify user addition by checking the state of the system"
          type="com.itko.lisa.ejb.EJBNode" 
          version="1" 
          uid="456306a0-Get+User" 
          think="1000-10000" 
          useFilters="true" 
          quiet="false" 
          next="Verify User Added" > 

<Documentation>- Get the user object directly using Enterprise Java Bean (EJB3UserControlBean/remote)&#10;- Verifies that the correct user is added</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="Any Exception Then Fail" type="com.itko.lisa.dynexec.CheckInvocationEx">
<log>Assert [Any Exception Then Fail] fired true of type Assert on Invocation Exception</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>.*</param>
</CheckResult>

<jndiFactory>org.jnp.interfaces.NamingContextFactory</jndiFactory>
<jndiServerURL>jnp://{{EJBSERVER}}:{{JNDIPORT}}/</jndiServerURL>
<jndiDataSourceName>EJB3UserControlBean/remote</jndiDataSourceName>
<password_enc>f5504e2d23a7888253a27e8ef52607d8</password_enc>
<keepEJBHomeRef>false</keepEJBHomeRef>
<keepEJBRef>true</keepEJBRef>
<homeCall>
<Call>
<methodName>lookup</methodName>
<retClassName>java.lang.Object</retClassName>
<actParamClassName>java.lang.String</actParamClassName>
<paramClassName>java.lang.String</paramClassName>
<exClassName>javax.naming.NamingException</exClassName>
<param>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>138</serialNum>
<array>false</array>
<className>java.lang.String</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>false</useprop>
<simpleVal>EJB3UserControlBean/remote</simpleVal>
</DynObject>
</param>
<isCallMade>false</isCallMade>
</Call>
</homeCall>
<ejbCallStream>
<DynObject>
<type>com.itko.lisa.dynexec.ComplexObj</type>
<serialNum>139</serialNum>
<array>false</array>
<className>$Proxy3</className>
<complex>true</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>false</simple>
<useprop>false</useprop>
<Setters>
</Setters>
<Call>
<methodName>getUser</methodName>
<retClassName>com.itko.examples.entity.User</retClassName>
<actParamClassName>java.lang.String</actParamClassName>
<paramClassName>java.lang.String</paramClassName>
<param>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>141</serialNum>
<array>false</array>
<className>java.lang.String</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>true</useprop>
<propKey>ds_login</propKey>
<simpleVal></simpleVal>
</DynObject>
</param>
<result>
<DynObject>
<type>com.itko.lisa.dynexec.ComplexObj</type>
<serialNum>140</serialNum>
<array>false</array>
<className>com.itko.examples.entity.User</className>
<complex>true</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>false</simple>
<useprop>false</useprop>
<Setters>
</Setters>
<Call>
<methodName>getLogin</methodName>
<retClassName>java.lang.String</retClassName>
<result>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>160</serialNum>
<array>false</array>
<className>java.lang.String</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>false</useprop>
<simpleVal>user-1410084599</simpleVal>
</DynObject>
</result>
<assert>{{ds_login}}</assert>
<assertTrue>false</assertTrue>
<assertThen>fail</assertThen>
<isCallMade>false</isCallMade>
</Call>
</DynObject>
</result>
<isCallMade>false</isCallMade>
</Call>
</DynObject>
</ejbCallStream>
<isEJBLocalInterface>false</isEJBLocalInterface>
<isHomelessEjb>true</isHomelessEjb>
<onExNode>abort</onExNode>
    </Node>


    <Node name="Verify User Added" log="Verifies the data source to make sure User is correctly added into the system"
          type="com.itko.lisa.jdbc.JDBCNode" 
          version="1" 
          uid="456306a0-Verify+User+Added" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="Deposit Money" > 

<Documentation>- Makes a SQL Query to the database and check appropriate table &#10;- Assert on the SQL response to make sure user is added successfully</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Check for ds_login" type="com.itko.lisa.resultset.AssertResultSetValue">
<log>Assert [Check for ds_login] fired false of type ResultSet Contents</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <column>login</column>
        <regEx>{{ds_login}}</regEx>
</CheckResult>

<driver>{{DBDRIVER}}</driver>
<dataSourceConnect>false</dataSourceConnect>
<jndiFactory></jndiFactory>
<jndiServerURL></jndiServerURL>
<jndiDataSourceName></jndiDataSourceName>
<connect>{{DBCONNURL}}</connect>
<user>{{DBUSER}}</user>
<password_enc>36d7b89b4c7c645d39a77c565d00c58f</password_enc>
<onSQLError>abort</onSQLError>
<resultSet>true</resultSet>
<maxRows>-1</maxRows>
<keepOpen>true</keepOpen>
<usePool>false</usePool>
<sql>select login from users </sql>
<IsStoredProc>false</IsStoredProc>
    </Node>


    <Node name="Deposit Money" log="Deposit money directly using Message Queues behind the ATM Machines"
          type="com.itko.lisa.esb.jndi.GeneralJndiJmsStep" 
          version="1" 
          uid="456306a0-Deposit+Money" 
          think="1000-10000" 
          useFilters="true" 
          quiet="false" 
          next="Login" > 

<Documentation>- Assumes that ATM deposit puts a message on the queue. &#10;- The money is deposited into the account via a message driven bean on the backend&#10;- The transaction id is retrurned in response&#10;- Uses lisa.jms.correlation.id to make sure the correct response is picked</Documentation>

      <!-- Data Sets -->
<readrec>lisa.jms.correlation.id</readrec>
<msgVersion>2</msgVersion>
<autoExtractPayLoad>false</autoExtractPayLoad>
<pubEnabledType>true</pubEnabledType>
<subEnabledType>true</subEnabledType>
<repEnabledType>true</repEnabledType>
<repTempQ>false</repTempQ>
<pubSessionMode>AUTO</pubSessionMode>
<subSessionMode>AUTO</subSessionMode>
<keepConnection>true</keepConnection>
<sharePublisher>true</sharePublisher>
<connectionType>JNDI</connectionType>
<lisa.prefill.jndiFactories>{{JNDIFACTORY}}</lisa.prefill.jndiFactories>
<lisa.prefill.jndiServerURLs>{{JNDIPROTOCOL}}://{{SERVER}}:{{JNDIPORT}}</lisa.prefill.jndiServerURLs>
<jndiDataSourceName>QueueConnectionFactory</jndiDataSourceName>
<lisa.prefill.jndiServerPWD_enc>f5504e2d23a7888253a27e8ef52607d8</lisa.prefill.jndiServerPWD_enc>
<mqPort></mqPort>
<mqCCID></mqCCID>
<mqConnMode>JMS</mqConnMode>
<mqOverrideQMan></mqOverrideQMan>
<mqUseCorrelationIDOnSubscribe>false</mqUseCorrelationIDOnSubscribe>
<mqCreatePublishDestination>false</mqCreatePublishDestination>
<mqCreateSubscribeDestination>false</mqCreateSubscribeDestination>
<mqEnvProperties>
</mqEnvProperties>
<mqExtMsgProperties>
</mqExtMsgProperties>
<mqSubscribeExtMsgProperties>
</mqSubscribeExtMsgProperties>
<tibRvService>7522:7524</tibRvService>
<tibRvConnMode>Native Client</tibRvConnMode>
<tibRvUseCMsg>false</tibRvUseCMsg>
<tibRvInBoxType>false</tibRvInBoxType>
<tibRvInBoxReplyMode>false</tibRvInBoxReplyMode>
<tibRvCmRequestOld>true</tibRvCmRequestOld>
<tibRvCmLedgerSync>false</tibRvCmLedgerSync>
<secondLevelAuthEnabled>false</secondLevelAuthEnabled>
<secondLevelAuthPwd_enc>f5504e2d23a7888253a27e8ef52607d8</secondLevelAuthPwd_enc>
<wmBrokerHost>localhost</wmBrokerHost>
<wmBrokerPort>6849</wmBrokerPort>
<wmBrokerClientAppName>LISA</wmBrokerClientAppName>
<wmBrokerDeliverEnabled>false</wmBrokerDeliverEnabled>
<wmBrokerEventConvertRsp>false</wmBrokerEventConvertRsp>
<wmBrokerEventConvertRspXML>false</wmBrokerEventConvertRspXML>
<sonicBrokerHost>localhost</sonicBrokerHost>
<sonicBrokerPort>2506</sonicBrokerPort>
<sonicBrokerPwd_enc>f5504e2d23a7888253a27e8ef52607d8</sonicBrokerPwd_enc>
<pubDestType>Queue</pubDestType>
<pubDestination>queue/AccountCommand</pubDestination>
<subDestType>Queue</subDestType>
<subDestination>queue/B</subDestination>
<subTimeout>30</subTimeout>
<subSelector>JMSCorrelationID LIKE &apos;{{lisa.jms.correlation.id}}%&apos;</subSelector>
<repDestination>queue/B</repDestination>
<repDestType>Queue</repDestType>
<sendMsgType>Text</sendMsgType>
<sendMessage>&lt;com.itko.examples.command.DepositMoneyCommand&gt;&#13;&#10;  &lt;accountId&gt;{{fl_checking_account_id}}&lt;/accountId&gt;&#13;&#10;  &lt;amount&gt;1200.00&lt;/amount&gt;&#13;&#10;  &lt;desc&gt;Salary Check&lt;/desc&gt;&#13;&#10;  &lt;username&gt;{{ds_login}}&lt;/username&gt;&#13;&#10;&lt;/com.itko.examples.command.DepositMoneyCommand&gt;</sendMessage>
<jmsMessageObject>
<DynObject>
<type>com.itko.lisa.dynexec.ComplexObj</type>
<serialNum>282</serialNum>
<array>false</array>
<className>org.jboss.mq.SpyTextMessage</className>
<complex>true</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>false</simple>
<useprop>false</useprop>
<Setters>
</Setters>
<Call>
<methodName>getText</methodName>
<retClassName>java.lang.String</retClassName>
<exClassName>javax.jms.JMSException</exClassName>
<result>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>295</serialNum>
<array>false</array>
<className>java.lang.String</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>false</useprop>
<simpleVal>&lt;string&gt;6ba3273e:1157db636c7:-7fa4&lt;/string&gt;</simpleVal>
</DynObject>
</result>
<isCallMade>false</isCallMade>
</Call>
</DynObject>
</jmsMessageObject>
<onExNode>abort</onExNode>
<customProperties>
</customProperties>
<customConnProperties>
</customConnProperties>
    </Node>


    <Node name="Login" log="Login to Lisabank Web UI"
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="456306a0-Login" 
          think="1000-10000" 
          useFilters="true" 
          quiet="false" 
          next="Account Activity" > 

<Documentation>- Login to the lisabank Web UI&#10;- Verifies that the login is successful and user actually login into his/her own account</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.test.FilterGetAttributeAndParseArg">
              <tag>A</tag>
      <tagn>8</tagn>
      <attrib>href</attrib>
      <isurl>true</isurl>
      <argkey>accountid</argkey>
      <prop>accountidLisaBank_-_Account_Activity_KEY</prop>
      <urlencode>false</urlencode>
      <default>-2d085326:1157caf4ae9:-7fff</default>
      </Filter>

      <Filter type="com.itko.lisa.test.FilterGetAttributeAndParseArg">
        <valueToFilterKey>lisa.Login.rsp</valueToFilterKey>
      <tag>A</tag>
      <tagn>7</tagn>
      <attrib>href</attrib>
      <isurl>true</isurl>
      <argkey>userid</argkey>
      <prop>useridLisaBank_-_Account_Activity_KEY</prop>
      <urlencode>false</urlencode>
      <default>user-1398721607</default>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert Non Empty Response to Login" type="com.itko.lisa.test.CheckResultAny">
<log>Login to Lisabank UI result in empty response</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<CheckResult assertTrue="false" name="Assert User Login" type="com.itko.lisa.test.CheckResultContains">
<log>Could not find User first name on login page</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>{{fl_fname}}</param>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/lisabank/login.do</path>
</url>
<images>false</images>
<postList>
    <Parameter>
    <key>username</key>
    <value>{{ds_login}}</value>
    </Parameter>
    <Parameter>
    <key>password</key>
    <value>{{fl_pwd}}</value>
    </Parameter>
</postList>
<postEnc>application/x-www-form-urlencoded</postEnc>
<sslInfo>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
<ssl-key-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-key-password-enc>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="Account Activity" log="Check Account Activity for account {{checking-account-id}}"
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="456306a0-Account+Activity" 
          think="1000-10000" 
          useFilters="true" 
          quiet="false" 
          next="Pay Bill Online" > 

<Documentation>- Checks account activity for the user</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert40" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert40] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/lisabank/buttonclick.do</path>
</url>
<images>false</images>
<postList>
    <Parameter>
    <key>accountid</key>
    <value>{{fl_checking_account_id}}</value>
    </Parameter>
    <Parameter>
    <key>userid</key>
    <value>{{fl_login}}</value>
    </Parameter>
    <Parameter>
    <key>action</key>
    <value>Withdraw</value>
    </Parameter>
</postList>
<postEnc>application/x-www-form-urlencoded</postEnc>
<sslInfo>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
<ssl-key-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-key-password-enc>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="Pay Bill Online" log="Withdraw money to pay online bill"
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="456306a0-Pay+Bill+Online" 
          think="2000-10000" 
          useFilters="true" 
          quiet="false" 
          next="Logout" > 

<Documentation>Withdraw money to pay online bill</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert41" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert41] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/lisabank/depositmoney.do</path>
</url>
<images>false</images>
<postList>
    <Parameter>
    <key>depositorwithdraw</key>
    <value>withdraw</value>
    </Parameter>
    <Parameter>
    <key>userid</key>
    <value>{{fl_login}}</value>
    </Parameter>
    <Parameter>
    <key>username</key>
    <value>{{fl_login}}</value>
    </Parameter>
    <Parameter>
    <key>accountId</key>
    <value>{{fl_checking_account_id}}</value>
    </Parameter>
    <Parameter>
    <key>password</key>
    <value>{{fl_pwd}}</value>
    </Parameter>
    <Parameter>
    <key>accountname</key>
    <value>Basic+Checking</value>
    </Parameter>
    <Parameter>
    <key>description</key>
    <value>Water+Bill</value>
    </Parameter>
    <Parameter>
    <key>amount</key>
    <value>69.30</value>
    </Parameter>
</postList>
<postEnc>application/x-www-form-urlencoded</postEnc>
<sslInfo>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>false</params-saved-as-unicode>
    </Node>


    <Node name="Logout" log="Logout Lisabank"
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="456306a0-Logout" 
          think="2000-10000" 
          useFilters="true" 
          quiet="false" 
          next="Get Transactions" > 

<Documentation>Logout to the portal</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert42" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert42] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/lisabank/logout.do</path>
</url>
<images>false</images>
<sslInfo>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>false</params-saved-as-unicode>
    </Node>


    <Node name="Get Transactions" log="Directly connect with EJB AccountControlBean to get transactions information"
          type="com.itko.lisa.ejb.EJBNode" 
          version="1" 
          uid="456306a0-Get+Transactions" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="Delete User XML" > 

<Documentation>Directly connect with EJB AccountControlBean to get transactions information</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="Any Exception Then Fail" type="com.itko.lisa.dynexec.CheckInvocationEx">
<log>Assert [Any Exception Then Fail] fired true of type Assert on Invocation Exception</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>.*</param>
</CheckResult>

<jndiFactory>org.jnp.interfaces.NamingContextFactory</jndiFactory>
<jndiServerURL>jnp://{{EJBSERVER}}:{{EJBPORT}}/</jndiServerURL>
<jndiDataSourceName>EJB3AccountControlBean/remote</jndiDataSourceName>
<password_enc>f5504e2d23a7888253a27e8ef52607d8</password_enc>
<keepEJBHomeRef>false</keepEJBHomeRef>
<keepEJBRef>true</keepEJBRef>
<homeCall>
<Call>
<methodName>lookup</methodName>
<retClassName>java.lang.Object</retClassName>
<actParamClassName>java.lang.String</actParamClassName>
<paramClassName>java.lang.String</paramClassName>
<exClassName>javax.naming.NamingException</exClassName>
<param>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>329</serialNum>
<array>false</array>
<className>java.lang.String</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>false</useprop>
<simpleVal>EJB3AccountControlBean/remote</simpleVal>
</DynObject>
</param>
<isCallMade>false</isCallMade>
</Call>
</homeCall>
<ejbCallStream>
<DynObject>
<type>com.itko.lisa.dynexec.ComplexObj</type>
<serialNum>330</serialNum>
<array>false</array>
<className>$Proxy5</className>
<complex>true</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>false</simple>
<useprop>false</useprop>
<Setters>
</Setters>
<Call>
<methodName>getTransactions</methodName>
<retClassName>[Lcom.itko.examples.entity.Transaction;</retClassName>
<actParamClassName>java.lang.String</actParamClassName>
<actParamClassName>java.util.Date</actParamClassName>
<actParamClassName>java.util.Date</actParamClassName>
<paramClassName>java.lang.String</paramClassName>
<paramClassName>java.util.Date</paramClassName>
<paramClassName>java.util.Date</paramClassName>
<param>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>332</serialNum>
<array>false</array>
<className>java.lang.String</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>true</useprop>
<propKey>fl_checking_account_id</propKey>
<simpleVal>checking-account-id</simpleVal>
</DynObject>
</param>
<param>
<DynObject>
<type>com.itko.lisa.dynexec.ComplexObj</type>
<serialNum>333</serialNum>
<array>false</array>
<className>java.util.Date</className>
<complex>true</complex>
<interface>false</interface>
<null>true</null>
<void>false</void>
<simple>false</simple>
<useprop>false</useprop>
<Setters>
</Setters>
</DynObject>
</param>
<param>
<DynObject>
<type>com.itko.lisa.dynexec.ComplexObj</type>
<serialNum>334</serialNum>
<array>false</array>
<className>java.util.Date</className>
<complex>true</complex>
<interface>false</interface>
<null>true</null>
<void>false</void>
<simple>false</simple>
<useprop>false</useprop>
<Setters>
</Setters>
</DynObject>
</param>
<result>
<DynObject>
<type>com.itko.lisa.dynexec.ArrayObj</type>
<serialNum>331</serialNum>
<array>true</array>
<className>[Lcom.itko.examples.entity.Transaction;</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>false</simple>
<useprop>false</useprop>
<Array>
<ArrayType>com.itko.examples.entity.Transaction</ArrayType>
<Iterator>
</Iterator>
</Array>
</DynObject>
</result>
<isCallMade>false</isCallMade>
</Call>
</DynObject>
</ejbCallStream>
<isEJBLocalInterface>false</isEJBLocalInterface>
<isHomelessEjb>true</isHomelessEjb>
<onExNode>abort</onExNode>
    </Node>


    <Node name="Delete User XML" log=""
          type="com.itko.lisa.ws.nx.NxWSStep" 
          version="1" 
          uid="456306a0-Delete+User+XML" 
          think="1S-10S" 
          useFilters="true" 
          quiet="false" 
          next="Verify User Deleted" > 

<Documentation>Delete the user from the bank via a web service.  The web service always returns true so you need to validate the user was deleted.</Documentation>
<wsdl>http://{{WSSERVER}}:{{WSPORT}}/itkoExamples/EJB3UserControlBean?wsdl</wsdl>
<endpoint>{{ENDPOINT1}}</endpoint>
<targetNamespace>http://ejb3.examples.itko.com/</targetNamespace>
<service>EJB3UserControlBeanService</service>
<port>EJB3UserControlBeanPort</port>
<operation>deleteUser</operation>
<onError>abort</onError>
<maintainSession>true</maintainSession>
<clearSession>false</clearSession>
<request itko_enc="base64">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbnZlbG9wZS8iIHhtbG5zOnhzPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYSI+CiAgPHNvYXBlbnY6Qm9keT4KICAgIDxkZWxldGVVc2VyIHhtbG5zPSJodHRwOi8vZWpiMy5leGFtcGxlcy5pdGtvLmNvbS8iPgogICAgICA8IS0tdXNlcm5hbWUgaXMgb3B0aW9uYWwtLT4KICAgICAgPHVzZXJuYW1lIHhtbG5zPSIiPnt7ZmxfbG9naW59fTwvdXNlcm5hbWU+CiAgICA8L2RlbGV0ZVVzZXI+CiAgPC9zb2FwZW52OkJvZHk+Cjwvc29hcGVudjpFbnZlbG9wZT4=</request>
<style>document</style>
<use>literal</use>
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


    <Node name="Verify User Deleted" log="Verifies the data source to make sure User is correctly deleted from the system"
          type="com.itko.lisa.jdbc.JDBCNode" 
          version="1" 
          uid="456306a0-Verify+User+Deleted" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="end" > 

<Documentation>- Makes a SQL Query to the database and check Users table &#10;&#10;- Assert on the SQL response to make sure user is deleted successfully</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="Assert User not in DB" type="com.itko.lisa.resultset.AssertResultSetValue">
<log>User: {{fl_login}} still exist in the database!!</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <column>login</column>
        <regEx>{{fl_login}}</regEx>
</CheckResult>

<driver>{{DBDRIVER}}</driver>
<dataSourceConnect>false</dataSourceConnect>
<jndiFactory></jndiFactory>
<jndiServerURL></jndiServerURL>
<jndiDataSourceName></jndiDataSourceName>
<connect>{{DBCONNURL}}</connect>
<user>{{DBUSER}}</user>
<password_enc>36d7b89b4c7c645d39a77c565d00c58f</password_enc>
<onSQLError>abort</onSQLError>
<resultSet>true</resultSet>
<maxRows>-1</maxRows>
<keepOpen>true</keepOpen>
<usePool>false</usePool>
<sql>select login from users where login = &apos;{{fl_login}}&apos;</sql>
<IsStoredProc>false</IsStoredProc>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="456306a0-end" 
          think="0-0" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="456306a0-fail" 
          think="0-0" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="456306a0-abort" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="end" > 

    </Node>


    <DataSet type="com.itko.lisa.test.DataSetIDGenerator" name="ds_login" atend="" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAB3CAAAAAEAAAABdAAIZHNfbG9naW50AA91c2VyLTE0MTAwODQ1OTl4</sample>
    <type>Number</type>
    <prefix>user-</prefix>
    </DataSet>

    <DataSet type="com.itko.lisa.xml.dataset.XMLDataSet" name="DataSet1" atend="" local="false" random="false" maxItemsToFetch="100" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAB3CAAAAAEAAAABdAAIRGF0YVNldDF0A+s8YWRkVXNlck9iamVjdCB4bWxucz0iaHR0cDovL2VqYjMuZXhhbXBsZXMuaXRrby5jb20vIj4KICAgICAgPHVzZXJPYmplY3QgeG1sbnM9IiI+CiAgICAgICAgPGFjY291bnRzPgogICAgICAgICAgPGJhbGFuY2U+MTAxLjAxPC9iYWxhbmNlPgogICAgICAgICAgPG5hbWU+QmFzaWMgQ2hlY2tpbmc8L25hbWU+CiAgICAgICAgICA8dHlwZT5DSEVDS0lORzwvdHlwZT4KICAgICAgICA8L2FjY291bnRzPgogICAgICAgIDxhY2NvdW50cz4KICAgICAgICAgIDxiYWxhbmNlPjEwMy4yMjwvYmFsYW5jZT4KICAgICAgICAgIDxuYW1lPk9yYW5nZSBTYXZpbmdzPC9uYW1lPgogICAgICAgICAgPHR5cGU+U0FWSU5HUzwvdHlwZT4KICAgICAgICA8L2FjY291bnRzPgogICAgICAgIDxhZGRyZXNzZXM+CiAgICAgICAgICA8Y2l0eT5TYW50YSBDbGFyYTwvY2l0eT4KICAgICAgICAgIDxsaW5lMT5VU1MgRW50ZXJwcmlzZSwgTkNDPC9saW5lMT4KICAgICAgICAgIDxzdGF0ZT5DYWxpZm9ybmlhPC9zdGF0ZT4KICAgICAgICAgIDx6aXA+OTUzNDM8L3ppcD4KICAgICAgICA8L2FkZHJlc3Nlcz4KICAgICAgICA8YWRkcmVzc2VzPgogICAgICAgICAgPGNpdHk+RGFsbGFzPC9jaXR5PgogICAgICAgICAgPGxpbmUxPkRlZXAgU3BhY2UgTmluZTwvbGluZTE+CiAgICAgICAgICA8c3RhdGU+VGV4YXM8L3N0YXRlPgogICAgICAgICAgPHppcD43NTIzNDwvemlwPgogICAgICAgIDwvYWRkcmVzc2VzPgogICAgICAgIDxlbWFpbD5KYW1lcy5LaXJrQFN0YXJUcmVrLmNvbTwvZW1haWw+CiAgICAgICAgPGZuYW1lPkphbWVzPC9mbmFtZT4KICAgICAgICA8bG5hbWU+S2lyazwvbG5hbWU+CiAgICAgICAgPGxvZ2luPnVzZXItMTQxMDA4NDU5OTwvbG9naW4+CiAgICAgICAgPHBob25lPjIzNC00MzUzPC9waG9uZT4KICAgICAgICA8cHdkPmtpcmsxMjM8L3B3ZD4KICAgICAgICA8cm9sZUtleT5SRUdVTEFSPC9yb2xlS2V5PgogICAgICA8L3VzZXJPYmplY3Q+CiAgICA8L2FkZFVzZXJPYmplY3Q+eA==</sample>
    <directoryPath>{{LISA_PROJ_ROOT}}/Data/datasets/xml/multi-tier-combo-XML</directoryPath>
    </DataSet>

    <DataSet type="com.itko.lisa.test.DataSetIDGenerator" name="lisa.jms.correlation.id" atend="" local="true" random="false" maxItemsToFetch="100" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAB3CAAAAAEAAAABdAAXbGlzYS5qbXMuY29ycmVsYXRpb24uaWR0AChsaXNhLWptcy1BMDgwMDhFMDAwMDAxMTlGRkE3ODc5MkJENTJEMkMzeA==</sample>
    <type>Alphanumeric</type>
    <prefix>lisa-jms-</prefix>
    </DataSet>

</TestCase>
