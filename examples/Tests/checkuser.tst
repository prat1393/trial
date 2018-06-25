<?xml version="1.0" ?>

<TestCase name="checkuser" version="5">

<meta>
   <create version="8.4.0" buildNumber="8.4.0.227" author="admin" date="03/10/2018" host="HVDIVD18MIS1939" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="03/10/2018" host="HVDIVD18MIS1939" />
</meta>

<id>1DC6E2B523BB11E8A38B265520524153</id>
<Documentation>Put documentation of the Test Case here.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9LTEmbGlzYXY9OC40LjAgKDguNC4wLjIyNykmbm9kZXM9MTAzMzMyNDc1MQ==</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

    <Node name="JMS queue/AccountCommand publish" log=""
          type="com.itko.lisa.esb.jndi.GeneralJndiJmsStep" 
          version="1" 
          uid="31D37CA423BB11E8A38B265520524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="end" > 

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
<pubDestination>queue/AccountCommand</pubDestination>
<subDestType>Queue</subDestType>
<subDestination>queue/B</subDestination>
<subTimeout>30</subTimeout>
<repDestination>queue/B</repDestination>
<repDestType>Queue</repDestType>
<durableSessionKey></durableSessionKey>
<sendMsgType>Text</sendMsgType>
<sendMessage>&#13;&#10;&lt;com.itko.examples.command.getUser&gt;&#13;&#10;&#13;&#10;  &lt;username&gt;tups2905&lt;/username&gt;&#13;&#10;&lt;/com.itko.examples.command.getUser&gt;</sendMessage>
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
          uid="1DC6E2BB23BB11E8A38B265520524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="1DC6E2B923BB11E8A38B265520524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="1DC6E2B723BB11E8A38B265520524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


</TestCase>
