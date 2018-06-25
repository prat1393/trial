<?xml version="1.0" ?>

<TestCase name="afa" version="5">

<meta>
   <create version="8.4.0" buildNumber="8.4.0.227" author="admin" date="01/04/2018" host="HVDIVD18MIS1939" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="03/09/2018" host="HVDIVD18MIS1939" />
</meta>

<id>571F02E6F14F11E78CDCC2DE20524153</id>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj04LjQuMCAoOC40LjAuMjI3KSZub2Rlcz0xNjM4NDI0Njkw</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

<CheckResult assertTrue="true" name="" type="com.itko.lisa.test.SimpleWebAssertion">
<then>abort</then>
        <onErrorNode>fail</onErrorNode>
</CheckResult>

    <Node name="Deposit Money" log="Deposit money directly using Message Queues behind the ATM Machines"
          type="com.itko.lisa.esb.jndi.GeneralJndiJmsStep" 
          version="1" 
          uid="456306a0-Deposit+Money" 
          think="1000-10000" 
          useFilters="true" 
          quiet="false" 
          next="end" > 

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
<lisa.prefill.jndiServerUsers></lisa.prefill.jndiServerUsers>
<lisa.prefill.jndiServerPWD_enc>f5504e2d23a7888253a27e8ef52607d8</lisa.prefill.jndiServerPWD_enc>
<mqHost></mqHost>
<mqPort></mqPort>
<mqChannel></mqChannel>
<mqQManager></mqQManager>
<mqCCID></mqCCID>
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
<mqSubscribeExtMsgProperties>
</mqSubscribeExtMsgProperties>
<tibRvService>7522:7524</tibRvService>
<tibRvNetwork></tibRvNetwork>
<tibRvDaemon></tibRvDaemon>
<tibRvConnMode>Native Client</tibRvConnMode>
<tibRvSendFieldName></tibRvSendFieldName>
<tibRvUseCMsg>false</tibRvUseCMsg>
<tibRvInBoxType>false</tibRvInBoxType>
<tibRvInBoxReplyMode>false</tibRvInBoxReplyMode>
<tibRvCmRequestOld>true</tibRvCmRequestOld>
<tibRvCmLedgerSync>false</tibRvCmLedgerSync>
<secondLevelAuthEnabled>false</secondLevelAuthEnabled>
<secondLevelAuthUser></secondLevelAuthUser>
<secondLevelAuthPwd_enc>f5504e2d23a7888253a27e8ef52607d8</secondLevelAuthPwd_enc>
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
<sonicBrokerPwd_enc>f5504e2d23a7888253a27e8ef52607d8</sonicBrokerPwd_enc>
<jcapsHost></jcapsHost>
<jcapsPort></jcapsPort>
<pubDestType>Queue</pubDestType>
<pubDestination>queue/AccountCommand</pubDestination>
<subDestType>Queue</subDestType>
<subDestination>queue/B</subDestination>
<subTimeout>30</subTimeout>
<subSelector>JMSCorrelationID LIKE &apos;{{lisa.jms.correlation.id}}%&apos;</subSelector>
<repDestination>queue/B</repDestination>
<repDestType>Queue</repDestType>
<durableSessionKey></durableSessionKey>
<sendMsgType>Text</sendMsgType>
<sendMessage>&lt;com.itko.examples.command.DepositMoneyCommand&gt;&#13;&#10;  &lt;accountId&gt;2212642529184&lt;/accountId&gt;&#13;&#10;  &lt;amount&gt;1200.00&lt;/amount&gt;&#13;&#10;  &lt;desc&gt;Salary Check&lt;/desc&gt;&#13;&#10;  &lt;username&gt;tups2905&lt;/username&gt;&#13;&#10;&lt;/com.itko.examples.command.DepositMoneyCommand&gt;</sendMessage>
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
<asyncPropKey></asyncPropKey>
    </Node>


    <Node name="abort" log="The test was aborted"
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="571F02ECF14F11E78CDCC2DE20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="571F02EAF14F11E78CDCC2DE20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="571F02E8F14F11E78CDCC2DE20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <DataSet type="com.itko.lisa.test.DataSetIDGenerator" name="lisa.jms.correlation.id" atend="" local="true" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAB3CAAAAAEAAAABdAAXbGlzYS5qbXMuY29ycmVsYXRpb24uaWR0AChsaXNhLWptcy1BMDgwMDhFMDAwMDAxMTlGRkE3ODc5MkJENTJEMkMzeA==</sample>
    <type>Alphanumeric</type>
    <prefix>lisa-jms-</prefix>
    </DataSet>

</TestCase>
