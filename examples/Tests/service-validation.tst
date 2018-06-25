<?xml version="1.0" ?>

<TestCase name="service-validation" version="5">

<meta><create version="0.0" buildNumber="0.0.0.0" author="cam" date="11/12/2009" host="sloth"  /><lastEdited version="0.0" buildNumber="0.0.0.0" author="cam" date="11/20/2009" host="sloth"  /></meta>

<id>104d3051</id>
<Documentation>This is a simple example of service validation. We call 2 services (one web service, one EJB service) and validate that they do what they claim to do by inspecting the underlying database with SQL.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9MyZ0Y3Y9NSZsaXNhdj0wLjAgKDAuMC4wLjAgdW5zdXBwb3J0ZWQgZGV2IHZlcnNpb24pJm5vZGVzPS03MjU2NDgxNjg=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>


    <Node name="Make sure user doesn&apos;t exist" log=""
          type="com.itko.lisa.jdbc.JDBCNode" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="AddUser WS" > 

<Documentation>Checks the database to ensure the unique user doesn&apos;t already exist. Uses the resultset rowcount assertion to ensure zero rows are returned. &#10;&#10;We haven&apos;t called the service to add the user yet but how do we know that the user doesn&apos;t already exist? So let&apos;s validate our test case assumptions...</Documentation>

      <!-- Data Sets -->
<readrec>counter</readrec>
<readrec>UniqueUser</readrec>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="must be zero rows returned" type="com.itko.lisa.jdbc.CheckResultJDBCResultSet">
<log>Assert [must be zero rows returned] fired false of type JDBC Result Set General</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <hasWarnings>false</hasWarnings>
        <rowsAtLeast>0</rowsAtLeast>
        <rowsNoMore>0</rowsNoMore>
</CheckResult>

<driver>{{DBDRIVER}}</driver>
<dataSourceConnect>false</dataSourceConnect>
<jndiFactory></jndiFactory>
<jndiServerURL></jndiServerURL>
<jndiDataSourceName></jndiDataSourceName>
<connect>{{DBCONNURL}}</connect>
<user>{{DBUSER}}</user>
<password>{{DBPASSWORD}}</password>
<onSQLError>abort</onSQLError>
<resultSet>true</resultSet>
<maxRows>-1</maxRows>
<keepOpen>true</keepOpen>
<sql>select login from USERS where login = &apos;{{UniqueUser}}&apos;</sql>
<IsStoredProc>false</IsStoredProc>
    </Node>


    <Node name="AddUser WS" log=""
          type="com.itko.lisa.dynexec.axis.WebServiceNode" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="ValidateUserInDatabase" > 

<Documentation>Calls the web service to add a new user. This could cause many complicated transactions on the back end but we get a simple SOAP response. We need to validate that the new user actually made it into the database (see next step)</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="Any Exception Then Fail" type="com.itko.lisa.dynexec.CheckInvocationEx">
<log>Assert [Any Exception Then Fail] fired true of type Assert on Invocation Exception</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>.*</param>
</CheckResult>

<className>com.itko.lisa.wsgen.EJB3UserControlService.tns.EJB3UserControlBeanServiceLocator</className>
<onExNode>abort</onExNode>
<WSDL>http://{{WSSERVER}}:{{WSPORT}}/itkoExamples/EJB3UserControlBean?wsdl</WSDL>
<Package>com.itko.lisa.wsgen.EJB3UserControlService</Package>
<ClientJar>EJB3UserControlService.jar</ClientJar>
<SOAPBindAddr>http://{{WSSERVER}}:{{WSPORT}}/itkoExamples/EJB3UserControlBean</SOAPBindAddr>
<objStream>
<DynObject>
<type>com.itko.lisa.dynexec.ComplexObj</type>
<serialNum>0</serialNum>
<array>false</array>
<className>com.itko.lisa.wsgen.EJB3UserControlService.tns.EJB3UserControlBeanServiceLocator</className>
<complex>true</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>false</simple>
<useprop>false</useprop>
<Setters>
</Setters>
<Call>
<methodName>getEJB3UserControlBeanPort</methodName>
<retClassName>com.itko.lisa.wsgen.EJB3UserControlService.tns.EJB3UserControlBean</retClassName>
<actParamClassName>java.net.URL</actParamClassName>
<paramClassName>java.net.URL</paramClassName>
<exClassName>javax.xml.rpc.ServiceException</exClassName>
<param>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>3</serialNum>
<array>false</array>
<className>java.net.URL</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>true</useprop>
<propKey>itkolisa.WebServiceNode.hiddenBindAddrKey</propKey>
<simpleVal></simpleVal>
</DynObject>
</param>
<result>
<DynObject>
<type>com.itko.lisa.dynexec.ComplexObj</type>
<serialNum>4</serialNum>
<array>false</array>
<className>com.itko.lisa.wsgen.EJB3UserControlService.tns.EJB3UserControlBeanBindingStub</className>
<complex>true</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>false</simple>
<useprop>false</useprop>
<Setters>
</Setters>
<Call>
<methodName>addUser</methodName>
<retClassName>com.itko.lisa.wsgen.EJB3UserControlService.tns.User</retClassName>
<actParamClassName>java.lang.String</actParamClassName>
<actParamClassName>java.lang.String</actParamClassName>
<paramClassName>java.lang.String</paramClassName>
<paramClassName>java.lang.String</paramClassName>
<exClassName>java.rmi.RemoteException</exClassName>
<param>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>551</serialNum>
<array>false</array>
<className>java.lang.String</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>true</useprop>
<propKey>UniqueUser</propKey>
<simpleVal></simpleVal>
</DynObject>
</param>
<param>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>552</serialNum>
<array>false</array>
<className>java.lang.String</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>true</useprop>
<propKey>UniqueUser</propKey>
<simpleVal></simpleVal>
</DynObject>
</param>
<result>
<DynObject>
<type>com.itko.lisa.dynexec.ComplexObj</type>
<serialNum>550</serialNum>
<array>false</array>
<className>com.itko.lisa.wsgen.EJB3UserControlService.tns.User</className>
<complex>true</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>false</simple>
<useprop>false</useprop>
<Setters>
</Setters>
</DynObject>
</result>
<isCallMade>false</isCallMade>
</Call>
</DynObject>
</result>
<isCallMade>false</isCallMade>
</Call>
</DynObject>
</objStream>
<wsGlobalInfo>
<soapVersionUri>1.1</soapVersionUri>
<httpVersionUri>1.1</httpVersionUri>
<webMethodUri>POST</webMethodUri>
<mtom>false</mtom>
<dontSendRequest>false</dontSendRequest>
<dontDeserialze>false</dontDeserialze>
<allowNonNillable>false</allowNonNillable>
<callTimeout>30000</callTimeout>
<wsi-check-wsdl>false</wsi-check-wsdl>
<wsi-check-msg>false</wsi-check-msg>
<wsi-display-type>notPassed</wsi-display-type>
<wsi-on-fail>continue</wsi-on-fail>
<ssl-keystore-file></ssl-keystore-file>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
</wsGlobalInfo>
<wss4jInfo>
<version>5</version>
<wsi-bsp>false</wsi-bsp>
</wss4jInfo>
<GenericHeader>
<ns>
</ns>
<xml></xml>
</GenericHeader>
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
<wsNameSpaceMapping>
    <Parameter>
    <key>http://ejb3.examples.itko.com/</key>
    <value>com.itko.lisa.wsgen.EJB3UserControlService.tns</value>
    </Parameter>
    <Parameter>
    <key>http://www.w3.org/2001/XMLSchema</key>
    <value>com.itko.lisa.wsgen.EJB3UserControlService.xsd</value>
    </Parameter>
    <Parameter>
    <key>http://schemas.xmlsoap.org/wsdl/soap/</key>
    <value>com.itko.lisa.wsgen.EJB3UserControlService.soap</value>
    </Parameter>
    <Parameter>
    <key>http://schemas.xmlsoap.org/wsdl/</key>
    <value>com.itko.lisa.wsgen.EJB3UserControlService</value>
    </Parameter>
</wsNameSpaceMapping>
<useDefaultNamespace>true</useDefaultNamespace>
<forceNoWrap>false</forceNoWrap>
<uddi-lookup>false</uddi-lookup>
<uddi-result>
<uddi-selection>
</uddi-selection>
</uddi-result>
    </Node>


    <Node name="ValidateUserInDatabase" log=""
          type="com.itko.lisa.jdbc.JDBCNode" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="DeleteUserCommand" > 

<Documentation>Here we use the SQL step to verify that there is now a row in the USERS table with our specified username. We checked that this was NOT the case before the callto the web service, so we can reasonably assume that the service did its job...</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Check result set" type="com.itko.lisa.jdbc.CheckResultJDBCResultSet">
<log>Assert [Check result set] fired false of type JDBC Result Set General</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <hasWarnings>false</hasWarnings>
        <rowsAtLeast>1</rowsAtLeast>
        <rowsNoMore>1</rowsNoMore>
</CheckResult>

<CheckResult assertTrue="false" name="Assert db username is expected value" type="com.itko.lisa.jdbc.CheckResultJDBCValue">
<log>Assert db username is expected value</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <column>login</column>
        <regEx>{{UniqueUser}}</regEx>
</CheckResult>

<driver>{{DBDRIVER}}</driver>
<dataSourceConnect>false</dataSourceConnect>
<jndiFactory></jndiFactory>
<jndiServerURL></jndiServerURL>
<jndiDataSourceName></jndiDataSourceName>
<connect>{{DBCONNURL}}</connect>
<user>{{DBUSER}}</user>
<password>{{DBPASSWORD}}</password>
<onSQLError>abort</onSQLError>
<resultSet>true</resultSet>
<maxRows>-1</maxRows>
<keepOpen>true</keepOpen>
<sql>select login from USERS where login = &apos;{{UniqueUser}}&apos;</sql>
<IsStoredProc>false</IsStoredProc>
    </Node>


    <Node name="DeleteUserCommand" log=""
          type="com.itko.lisa.esb.jndi.GeneralJndiJmsStep" 
          version="1" 
          think="1000-10000" 
          useFilters="true" 
          quiet="false" 
          next="Validate user NOT in db" > 

<Documentation>Now call another service endpoint (this time an message queue).  We post a message and expect a reply on a temporary queue (this is a very common pattern). We then assert that the response contains the expected result. &#10;&#10;So we are testing that the *service* looks like it has deleted the user but we will check to see if this really is the case in the next step...</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="should see true in response" type="com.itko.lisa.test.CheckResultContains">
<log>Assert [should see true in response] fired false of type Result as String Contains Given String</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>&lt;boolean&gt;true&lt;/boolean&gt;</param>
</CheckResult>

<msgVersion>2</msgVersion>
<autoExtractPayLoad>false</autoExtractPayLoad>
<pubEnabledType>true</pubEnabledType>
<subEnabledType>true</subEnabledType>
<repEnabledType>false</repEnabledType>
<repTempQ>true</repTempQ>
<pubWithTrans>false</pubWithTrans>
<subWithTrans>false</subWithTrans>
<keepConnection>false</keepConnection>
<sharePublisher>false</sharePublisher>
<connectionType>JNDI</connectionType>
<lisa.prefill.jndiFactories>org.jnp.interfaces.NamingContextFactory</lisa.prefill.jndiFactories>
<lisa.prefill.jndiServerURLs>jnp://{{SERVER}}:{{JNDIPORT}}</lisa.prefill.jndiServerURLs>
<jndiDataSourceName>QueueConnectionFactory</jndiDataSourceName>
<lisa.prefill.jndiServerUsers></lisa.prefill.jndiServerUsers>
<lisa.prefill.jndiServerPWD_enc>f5504e2d23a7888253a27e8ef52607d8</lisa.prefill.jndiServerPWD_enc>
<mqHost></mqHost>
<mqPort></mqPort>
<mqChannel></mqChannel>
<mqQManager></mqQManager>
<mqCCID>-1</mqCCID>
<mqSecurityExit></mqSecurityExit>
<mqSendExit></mqSendExit>
<mqReceiveExit></mqReceiveExit>
<mqConnMode>JMS</mqConnMode>
<mqReplyToQMgrName></mqReplyToQMgrName>
<mqTempQModel></mqTempQModel>
<mqOverrideQMan></mqOverrideQMan>
<mqUseCorrelationIDOnSubscribe>false</mqUseCorrelationIDOnSubscribe>
<mqCreatePublishDestination>false</mqCreatePublishDestination>
<mqCreateSubscribeDestination>false</mqCreateSubscribeDestination>
<mqEnvProperties>
</mqEnvProperties>
<mqExtMsgProperties>
</mqExtMsgProperties>
<tibRvService>7522:7524</tibRvService>
<tibRvNetwork></tibRvNetwork>
<tibRvDaemon></tibRvDaemon>
<tibRvConnMode>Native Client</tibRvConnMode>
<tibRvSendFieldName></tibRvSendFieldName>
<tibRvUseCMsg>false</tibRvUseCMsg>
<tibRvInBoxType>false</tibRvInBoxType>
<tibRvInBoxReplyMode>false</tibRvInBoxReplyMode>
<secondLevelAuthEnabled>false</secondLevelAuthEnabled>
<secondLevelAuthUser></secondLevelAuthUser>
<secondLevelAuthPwd></secondLevelAuthPwd>
<wmBrokerHost>localhost</wmBrokerHost>
<wmBrokerPort>6849</wmBrokerPort>
<wmBrokerName></wmBrokerName>
<wmBrokerCID></wmBrokerCID>
<wmBrokerClientGroup></wmBrokerClientGroup>
<wmBrokerClientAppName>LISA</wmBrokerClientAppName>
<wmBrokerDeliverEnabled>false</wmBrokerDeliverEnabled>
<wmBrokerEventConvertRsp>false</wmBrokerEventConvertRsp>
<wmBrokerEventConvertRspXML>false</wmBrokerEventConvertRspXML>
<sonicBrokerHost>localhost</sonicBrokerHost>
<sonicBrokerPort>2506</sonicBrokerPort>
<sonicBrokerUser></sonicBrokerUser>
<sonicBrokerPwd></sonicBrokerPwd>
<jcapsHost></jcapsHost>
<jcapsPort></jcapsPort>
<pubDestType>Queue</pubDestType>
<pubDestination>queue/UserCommand</pubDestination>
<subDestType>Queue</subDestType>
<subDestination>queue/B</subDestination>
<subTimeout>30</subTimeout>
<repDestination>queue/B</repDestination>
<repDestType>Queue</repDestType>
<durableSessionKey></durableSessionKey>
<sendMsgType>Text</sendMsgType>
<sendMessage>&lt;com.itko.examples.command.DeleteUserCommand&gt;&#10;  &lt;username&gt;{{UniqueUser}}&lt;/username&gt;&#10;&lt;/com.itko.examples.command.DeleteUserCommand&gt;</sendMessage>
<jmsMessageObject>
</jmsMessageObject>
<onExNode>abort</onExNode>
<customProperties>
</customProperties>
<customConnProperties>
</customConnProperties>
<asyncPropKey></asyncPropKey>
    </Node>


    <Node name="Validate user NOT in db" log=""
          type="com.itko.lisa.jdbc.JDBCNode" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="Make sure user doesn&apos;t exist" > 

<Documentation>Similar to the first step, we are checking that the user record no longer appears in the database, i.e. tat the DeleteUserCommand really did delete the user..</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="must be zero rows returned" type="com.itko.lisa.jdbc.CheckResultJDBCResultSet">
<log>Assert [must be zero rows returned] fired false of type JDBC Result Set General</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <hasWarnings>false</hasWarnings>
        <rowsAtLeast>0</rowsAtLeast>
        <rowsNoMore>0</rowsNoMore>
</CheckResult>

<driver>{{DBDRIVER}}</driver>
<dataSourceConnect>false</dataSourceConnect>
<jndiFactory></jndiFactory>
<jndiServerURL></jndiServerURL>
<jndiDataSourceName></jndiDataSourceName>
<connect>{{DBCONNURL}}</connect>
<user>{{DBUSER}}</user>
<password>{{DBPASSWORD}}</password>
<onSQLError>abort</onSQLError>
<resultSet>true</resultSet>
<maxRows>-1</maxRows>
<keepOpen>true</keepOpen>
<sql>select login from USERS where login = &apos;{{UniqueUser}}&apos;</sql>
<IsStoredProc>false</IsStoredProc>
    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="end" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          think="0-0" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          think="0-0" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <DataSet type="com.itko.lisa.test.CounterDataSet" name="counter" atend="end" local="true" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAAHY291bnRlcnQAATF4</sample>
    <propKey>counter</propKey>
    <countFrom>1</countFrom>
    <countTo>3</countTo>
    <countIncrement>1</countIncrement>
    </DataSet>

    <DataSet type="com.itko.lisa.test.DataSetIDGenerator" name="UniqueUser" atend="" local="true" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAAKVW5pcXVlVXNlcnQAI2VqYjNBMDgwMDdBMDAwMDAxMTQzRTJFRUIzMTlDRjIzREMxeA==</sample>
    <type>Alphanumeric</type>
    <prefix>ejb3</prefix>
    </DataSet>

</TestCase>
