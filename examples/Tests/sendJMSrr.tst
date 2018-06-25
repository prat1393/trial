<?xml version="1.0" ?>

<TestCase name="sendJMSrr" version="5">

<meta>
   <create version="7.5" buildNumber="7.5.0.642" author="admin" date="02/10/2014" host="KRACH03-demo1" />
   <lastEdited version="7.5" buildNumber="7.5.0.642" author="admin" date="02/10/2014" host="KRACH03-demo1" />
</meta>

<id>4C811690928911E3BBC3000C29570CA6</id>
<Documentation>Tests VSEasys ability to create a JMS service from Request Response pairs.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9LTEmbGlzYXY9Ny41ICg3LjUuMC42NDIpJm5vZGVzPS0yMDczNjMxOTAw</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

    <Node name="JMS" log=""
          type="com.itko.lisa.esb.jndi.GeneralJndiJmsStep" 
          version="1" 
          uid="52048745928911E3BBC3000C29570CA6" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="end" > 

<Documentation>Create a JMS service from Request Response pairs.</Documentation>
<msgVersion>2</msgVersion>
<autoExtractPayLoad>true</autoExtractPayLoad>
<pubEnabledType>true</pubEnabledType>
<subEnabledType>true</subEnabledType>
<repEnabledType>false</repEnabledType>
<repTempQ>false</repTempQ>
<pubSessionMode>AUTO</pubSessionMode>
<subSessionMode>AUTO</subSessionMode>
<keepConnection>false</keepConnection>
<sharePublisher>false</sharePublisher>
<connectionType>JNDI</connectionType>
<lisa.prefill.jndiFactories>org.jnp.interfaces.NamingContextFactory</lisa.prefill.jndiFactories>
<lisa.prefill.jndiServerURLs>jnp://localhost:1099</lisa.prefill.jndiServerURLs>
<jndiDataSourceName>ConnectionFactory</jndiDataSourceName>
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
<tibRvCmConfirmAdvisorySubject>_RV.INFO.RVCM.DELIVERY.CONFIRM.&gt;</tibRvCmConfirmAdvisorySubject>
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
<pubDestination>queue/TEST.REQUEST</pubDestination>
<subDestType>Queue</subDestType>
<subDestination>queue/TEST.RESPONSE</subDestination>
<subTimeout>30</subTimeout>
<repDestType>Queue</repDestType>
<durableSessionKey></durableSessionKey>
<sendMsgType>Text</sendMsgType>
<sendMessage>&lt;order&gt;&#13;&#10;&#9;&lt;id&gt;1-11&lt;/id&gt;&#13;&#10;&#9;&lt;name&gt;Nut&lt;/name&gt;&#13;&#10;&#9;&lt;product&gt;2mm nut&lt;/product&gt;&#13;&#10;&lt;/order&gt;</sendMessage>
<jmsMessageObject>
</jmsMessageObject>
<onExNode>abort</onExNode>
<customProperties>
</customProperties>
<customConnProperties>
</customConnProperties>
<asyncPropKey></asyncPropKey>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="4C811696928911E3BBC3000C29570CA6" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="4C811694928911E3BBC3000C29570CA6" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="4C811692928911E3BBC3000C29570CA6" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


</TestCase>
