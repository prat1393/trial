<?xml version="1.0" ?>

<TestCase name="async-consumer-jms" version="5">

<meta><create version="0.0" buildNumber="0.0.0.0" author="cam" date="11/11/2009" host="sloth"  /><lastEdited version="0.0" buildNumber="0.0.0.0" author="cam" date="12/10/2009" host="sloth"  /></meta>

<id>6beaf91d</id>
<Documentation>An examples of an &apos;async consumer&apos; queue where the test case continually accepts messages from a response queue/topic and makes them available to the test case in the order that the messages came in.  &#10;The first step creates the queue (internal to the LISA test). &#10;&#10;The second step fires 3 messages to a JMS queue on the demo server, which should cause three messages to be received on the async queue. &#10;&#10;The third step validates that three messages were received by the async queue.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9MyZ0Y3Y9NSZsaXNhdj0wLjAgKDAuMC4wLjAgdW5zdXBwb3J0ZWQgZGV2IHZlcnNpb24pJm5vZGVzPTMxNTIwODc4Nw==</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>


    <Node name="create-consumer" log=""
          type="com.itko.lisa.jms.JMSNode" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="send-message" > 

<Documentation>Creates the async consumer queue. This sets up a queue/top listener and will receive messages from the target no matter what step is the &apos;current&apos; step of the test. The messages received are copied into an internal LISA queue.</Documentation>
<msgVersion>2</msgVersion>
<autoExtractPayLoad>false</autoExtractPayLoad>
<pubEnabledType>false</pubEnabledType>
<subEnabledType>true</subEnabledType>
<repEnabledType>false</repEnabledType>
<repTempQ>false</repTempQ>
<pubWithTrans>false</pubWithTrans>
<subWithTrans>false</subWithTrans>
<keepConnection>false</keepConnection>
<sharePublisher>false</sharePublisher>
<connectionType>JNDI</connectionType>
<lisa.prefill.jndiFactories>{{JNDIFACTORY}}</lisa.prefill.jndiFactories>
<lisa.prefill.jndiServerURLs>{{JNDIPROTOCOL}}://{{SERVER}}:{{JNDIPORT}}</lisa.prefill.jndiServerURLs>
<jndiDataSourceName>{{JMSCONNECTIONFACTORY}}</jndiDataSourceName>
<lisa.prefill.jndiServerUsers></lisa.prefill.jndiServerUsers>
<lisa.prefill.jndiServerPWD_enc>f5504e2d23a7888253a27e8ef52607d8</lisa.prefill.jndiServerPWD_enc>
<mqHost></mqHost>
<mqPort>1414</mqPort>
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
<tibRvService></tibRvService>
<tibRvNetwork></tibRvNetwork>
<tibRvDaemon></tibRvDaemon>
<tibRvConnMode>Native Client</tibRvConnMode>
<tibRvSendFieldName></tibRvSendFieldName>
<tibRvUseCMsg>false</tibRvUseCMsg>
<tibRvInBoxType>false</tibRvInBoxType>
<tibRvInBoxReplyMode>false</tibRvInBoxReplyMode>
<secondLevelAuthEnabled>false</secondLevelAuthEnabled>
<wmBrokerHost></wmBrokerHost>
<wmBrokerPort></wmBrokerPort>
<wmBrokerName></wmBrokerName>
<wmBrokerCID></wmBrokerCID>
<wmBrokerClientGroup></wmBrokerClientGroup>
<wmBrokerClientAppName></wmBrokerClientAppName>
<wmBrokerDeliverEnabled>false</wmBrokerDeliverEnabled>
<wmBrokerEventConvertRsp>false</wmBrokerEventConvertRsp>
<wmBrokerEventConvertRspXML>false</wmBrokerEventConvertRspXML>
<sonicBrokerHost></sonicBrokerHost>
<sonicBrokerPort></sonicBrokerPort>
<sonicBrokerUser></sonicBrokerUser>
<sonicBrokerPwd></sonicBrokerPwd>
<jcapsHost></jcapsHost>
<jcapsPort></jcapsPort>
<pubDestType>Queue</pubDestType>
<pubDestination></pubDestination>
<subDestType>Topic - ASYNC</subDestType>
<subDestination>topic/testTopic</subDestination>
<subTimeout>30</subTimeout>
<repDestType>Queue</repDestType>
<durableSessionKey></durableSessionKey>
<sendMsgType>Empty</sendMsgType>
<sendMessage></sendMessage>
<jmsMessageObject>
</jmsMessageObject>
<onExNode>abort</onExNode>
<customProperties>
</customProperties>
<customConnProperties>
</customConnProperties>
<asyncPropKey>EXAMPLE-ASYNC-WRAPPER</asyncPropKey>
    </Node>


    <Node name="send-message" log=""
          type="com.itko.lisa.jms.JMSNode" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="send-message" > 

<Documentation>Simply sends three messages to the target app server queue. Each message contains a unique, verifiable text body.</Documentation>

      <!-- Data Sets -->
<readrec>counterA</readrec>
<msgVersion>2</msgVersion>
<autoExtractPayLoad>false</autoExtractPayLoad>
<pubEnabledType>true</pubEnabledType>
<subEnabledType>false</subEnabledType>
<repEnabledType>false</repEnabledType>
<repTempQ>false</repTempQ>
<pubWithTrans>false</pubWithTrans>
<subWithTrans>false</subWithTrans>
<keepConnection>false</keepConnection>
<sharePublisher>false</sharePublisher>
<connectionType>JNDI</connectionType>
<lisa.prefill.jndiFactories>{{JNDIFACTORY}}</lisa.prefill.jndiFactories>
<lisa.prefill.jndiServerURLs>{{JNDIPROTOCOL}}://{{SERVER}}:{{JNDIPORT}}</lisa.prefill.jndiServerURLs>
<jndiDataSourceName>{{JMSCONNECTIONFACTORY}}</jndiDataSourceName>
<lisa.prefill.jndiServerUsers></lisa.prefill.jndiServerUsers>
<lisa.prefill.jndiServerPWD_enc>f5504e2d23a7888253a27e8ef52607d8</lisa.prefill.jndiServerPWD_enc>
<mqHost></mqHost>
<mqPort>1414</mqPort>
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
<tibRvService></tibRvService>
<tibRvNetwork></tibRvNetwork>
<tibRvDaemon></tibRvDaemon>
<tibRvConnMode>Native Client</tibRvConnMode>
<tibRvSendFieldName></tibRvSendFieldName>
<tibRvUseCMsg>false</tibRvUseCMsg>
<tibRvInBoxType>false</tibRvInBoxType>
<tibRvInBoxReplyMode>false</tibRvInBoxReplyMode>
<secondLevelAuthEnabled>false</secondLevelAuthEnabled>
<wmBrokerHost></wmBrokerHost>
<wmBrokerPort></wmBrokerPort>
<wmBrokerName></wmBrokerName>
<wmBrokerCID></wmBrokerCID>
<wmBrokerClientGroup></wmBrokerClientGroup>
<wmBrokerClientAppName></wmBrokerClientAppName>
<wmBrokerDeliverEnabled>false</wmBrokerDeliverEnabled>
<wmBrokerEventConvertRsp>false</wmBrokerEventConvertRsp>
<wmBrokerEventConvertRspXML>false</wmBrokerEventConvertRspXML>
<sonicBrokerHost></sonicBrokerHost>
<sonicBrokerPort></sonicBrokerPort>
<sonicBrokerUser></sonicBrokerUser>
<sonicBrokerPwd></sonicBrokerPwd>
<jcapsHost></jcapsHost>
<jcapsPort></jcapsPort>
<pubDestType>Queue</pubDestType>
<pubDestination>queue/C</pubDestination>
<subDestType>Queue</subDestType>
<subDestination></subDestination>
<subTimeout>30</subTimeout>
<repDestType>Queue</repDestType>
<durableSessionKey></durableSessionKey>
<sendMsgType>Text</sendMsgType>
<sendMessage>Test message for async lisener. id: {{message-id}}</sendMessage>
<jmsMessageObject>
</jmsMessageObject>
<onExNode>abort</onExNode>
<customProperties>
</customProperties>
<customConnProperties>
</customConnProperties>
<asyncPropKey></asyncPropKey>
    </Node>


    <Node name="consumer" log=""
          type="com.itko.lisa.jms.AsyncConsumerNode" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="consumer" > 

<Documentation>Check the async queue and validates the message received.</Documentation>

      <!-- Data Sets -->
<readrec>DataSetB</readrec>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="Any Exception Then Fail" type="com.itko.lisa.dynexec.CheckInvocationEx">
<log>Assert [Any Exception Then Fail] fired true of type Assert on Invocation Exception</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>.*</param>
</CheckResult>

<wrapperKeyName>EXAMPLE-ASYNC-WRAPPER</wrapperKeyName>
<waitTimeOut>10</waitTimeOut>
<messageDynObj>
<DynObject>
<type>com.itko.lisa.dynexec.ComplexObj</type>
<serialNum>637</serialNum>
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
<serialNum>638</serialNum>
<array>false</array>
<className>java.lang.String</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>false</useprop>
<simpleVal>Test message for async lisener. id: 100</simpleVal>
</DynObject>
</result>
<retPropKey>MESSAGE_ID</retPropKey>
<isCallMade>false</isCallMade>
</Call>
</DynObject>
</messageDynObj>
<onExNode>abort</onExNode>
<autoExtractPayLoad>false</autoExtractPayLoad>
    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="end" > 

    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          think="0-0" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          think="0-0" 
          useFilters="true" 
          quiet="true" 
          next="create-consumer" > 

    </Node>


    <DataSet type="com.itko.lisa.test.CounterDataSet" name="counterA" atend="consumer" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAAKbWVzc2FnZS1pZHQAAzEwMHg=</sample>
    <propKey>message-id</propKey>
    <countFrom>100</countFrom>
    <countTo>103</countTo>
    <countIncrement>1</countIncrement>
    </DataSet>

    <DataSet type="com.itko.lisa.test.CounterDataSet" name="DataSetB" atend="end" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAAIY2hlY2staWR0AAMxMDB4</sample>
    <propKey>check-id</propKey>
    <countFrom>100</countFrom>
    <countTo>103</countTo>
    <countIncrement>1</countIncrement>
    </DataSet>

</TestCase>
