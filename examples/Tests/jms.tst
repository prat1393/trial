<?xml version="1.0" ?>

<TestCase name="JMS" version="5">

<meta><create version="0.0" buildNumber="0.0.0.0" author="cam" date="11/11/2009" host="sloth"  /><lastEdited version="0.0" buildNumber="0.0.0.0" author="cam" date="12/22/2009" host="sloth"  /></meta>

<id>288cfd61</id>
<Documentation>Simple JMS example showing how to send XML/text messages as well as objects in native java format</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9MyZ0Y3Y9NSZsaXNhdj0wLjAgKDAuMC4wLjAgdW5zdXBwb3J0ZWQgZGV2IHZlcnNpb24pJm5vZGVzPTgxMjIxNzgxNg==</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

    <Node name="start" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="true" 
          next="jms-1" > 

<Documentation>Just a simple marker step</Documentation>

      <!-- Data Sets -->
<readrec>order_data</readrec>
<log>start JMS test example.</log>
    </Node>


    <Node name="jms-1" log=""
          type="com.itko.lisa.esb.jndi.GeneralJndiJmsStep" 
          version="1" 
          think="1000-10000" 
          useFilters="true" 
          quiet="false" 
          next="check-order" > 

<Documentation>This step uses builds an XML document using data from the order_data data set. We place the order text on queue A and we expect a reply on queue B. &#10;&#10;We use the special property lisa.jms.correlation.id to set a well known message property value and the use that in the selector for the response. &#10;&#10;This ensures that we get &apos;our&apos; response even when there are many simultanous users doing the same thing.</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.test.FilterSaveResponse">
        <valueToFilterKey>lisa.jms-1.rsp</valueToFilterKey>
      <prop>message</prop>
      </Filter>


      <!-- Data Sets -->
<readrec>lisa.jms.correlation.id</readrec>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="Any Exception Then Fail" type="com.itko.lisa.dynexec.CheckInvocationEx">
<log>Assert [Any Exception Then Fail] fired true of type Assert on Invocation Exception</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>.*</param>
</CheckResult>

<msgVersion>2</msgVersion>
<autoExtractPayLoad>false</autoExtractPayLoad>
<pubEnabledType>true</pubEnabledType>
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
<lisa.prefill.jndiServerPWD_enc>f5504e2d23a7888253a27e8ef52607d8</lisa.prefill.jndiServerPWD_enc>
<mqPort></mqPort>
<mqCCID>-1</mqCCID>
<mqConnMode>JMS</mqConnMode>
<mqOverrideQMan></mqOverrideQMan>
<mqUseCorrelationIDOnSubscribe>false</mqUseCorrelationIDOnSubscribe>
<mqCreatePublishDestination>false</mqCreatePublishDestination>
<mqCreateSubscribeDestination>false</mqCreateSubscribeDestination>
<mqEnvProperties>
</mqEnvProperties>
<mqExtMsgProperties>
</mqExtMsgProperties>
<tibRvService>7522:7524</tibRvService>
<tibRvConnMode>Native Client</tibRvConnMode>
<tibRvUseCMsg>false</tibRvUseCMsg>
<tibRvInBoxType>false</tibRvInBoxType>
<tibRvInBoxReplyMode>false</tibRvInBoxReplyMode>
<secondLevelAuthEnabled>false</secondLevelAuthEnabled>
<wmBrokerHost>localhost</wmBrokerHost>
<wmBrokerPort>6849</wmBrokerPort>
<wmBrokerClientAppName>LISA</wmBrokerClientAppName>
<wmBrokerDeliverEnabled>false</wmBrokerDeliverEnabled>
<wmBrokerEventConvertRsp>false</wmBrokerEventConvertRsp>
<wmBrokerEventConvertRspXML>false</wmBrokerEventConvertRspXML>
<sonicBrokerHost>localhost</sonicBrokerHost>
<sonicBrokerPort>2506</sonicBrokerPort>
<pubDestType>Queue</pubDestType>
<pubDestination>queue/A</pubDestination>
<subDestType>Queue</subDestType>
<subDestination>queue/B</subDestination>
<subTimeout>30</subTimeout>
<subSelector>JMSCorrelationID LIKE &apos;{{lisa.jms.correlation.id}}%&apos;</subSelector>
<repDestType>Queue</repDestType>
<sendMsgType>Text</sendMsgType>
<sendMessage>&lt;order&gt;&#10;&#9;&lt;id&gt;{{order_id}}&lt;/id&gt;&#10;&#9;&lt;name&gt;{{order_user}}&lt;/name&gt;&#10;&#9;&lt;product&gt;{{order_product}}&lt;/product&gt;&#10;&lt;/order&gt;</sendMessage>
<jmsMessageObject>
<DynObject>
<type>com.itko.lisa.dynexec.ComplexObj</type>
<serialNum>171</serialNum>
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
<serialNum>232</serialNum>
<array>false</array>
<className>java.lang.String</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>false</useprop>
<simpleVal>&lt;order&gt;&#10;&#9;&lt;id&gt;1-11&lt;/id&gt;&#10;&#9;&lt;name&gt;ouid-1&lt;/name&gt;&#10;&#9;&lt;product&gt;prod100&lt;/product&gt;&#10;&lt;/order&gt;</simpleVal>
</DynObject>
</result>
<retPropKey>order</retPropKey>
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


    <Node name="check-order" log=""
          type="com.itko.lisa.dynexec.NodeConvertPropToLastResponse" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="true" 
          next="send-msg-post-update" > 

<Documentation>Parse the result (the message we got back) and check that the order_id is consistent.</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="assert order id correct" type="com.itko.lisa.test.AssertDiffEngine">
<log>Assert [assert order id correct] fired false of type Highlight Content for Comparison</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
<param></param>
<param>&lt;id&gt;</param>
<param>{{order_id}}</param>
<param>&lt;/id&gt;</param>
<param></param>
</CheckResult>

<Property>order</Property>
    </Node>


    <Node name="send-msg-post-update" log=""
          type="com.itko.lisa.esb.jndi.GeneralJndiJmsStep" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="start" > 

<Documentation>An example of sending a java message object. The message object is actually just the reply we got in the jms-1 step (there is a filter on that step to save the result as a property named &apos;message&apos;).  You could use a javascript step to modify the object before sending.  &#10;&#10;This step also demonstrates the use of temporary reply queues, basically we send a message to queue/C and a new queue is dynamically created for the response. Once the JMS connection is closed the temporary queue is destroyed.</Documentation>
<msgVersion>2</msgVersion>
<autoExtractPayLoad>true</autoExtractPayLoad>
<pubEnabledType>true</pubEnabledType>
<subEnabledType>true</subEnabledType>
<repEnabledType>false</repEnabledType>
<repTempQ>true</repTempQ>
<pubWithTrans>false</pubWithTrans>
<subWithTrans>false</subWithTrans>
<keepConnection>false</keepConnection>
<sharePublisher>false</sharePublisher>
<connectionType>JNDI</connectionType>
<lisa.prefill.jndiFactories>{{JNDIFACTORY}}</lisa.prefill.jndiFactories>
<lisa.prefill.jndiServerURLs>{{JNDIPROTOCOL}}://{{SERVER}}:{{JNDIPORT}}</lisa.prefill.jndiServerURLs>
<jndiDataSourceName>{{JMSCONNECTIONFACTORY}}</jndiDataSourceName>
<lisa.prefill.jndiServerPWD_enc>f5504e2d23a7888253a27e8ef52607d8</lisa.prefill.jndiServerPWD_enc>
<mqPort></mqPort>
<mqCCID>-1</mqCCID>
<mqConnMode>JMS</mqConnMode>
<mqOverrideQMan></mqOverrideQMan>
<mqUseCorrelationIDOnSubscribe>false</mqUseCorrelationIDOnSubscribe>
<mqCreatePublishDestination>false</mqCreatePublishDestination>
<mqCreateSubscribeDestination>false</mqCreateSubscribeDestination>
<mqEnvProperties>
</mqEnvProperties>
<mqExtMsgProperties>
</mqExtMsgProperties>
<tibRvService>7522:7524</tibRvService>
<tibRvConnMode>Native Client</tibRvConnMode>
<tibRvUseCMsg>false</tibRvUseCMsg>
<tibRvInBoxType>false</tibRvInBoxType>
<tibRvInBoxReplyMode>false</tibRvInBoxReplyMode>
<tibRvCmConfirmAdvisorySubject>_RV.INFO.RVCM.DELIVERY.CONFIRM.&gt;</tibRvCmConfirmAdvisorySubject>
<secondLevelAuthEnabled>false</secondLevelAuthEnabled>
<wmBrokerHost>localhost</wmBrokerHost>
<wmBrokerPort>6849</wmBrokerPort>
<wmBrokerClientAppName>LISA</wmBrokerClientAppName>
<wmBrokerDeliverEnabled>false</wmBrokerDeliverEnabled>
<wmBrokerEventConvertRsp>false</wmBrokerEventConvertRsp>
<wmBrokerEventConvertRspXML>false</wmBrokerEventConvertRspXML>
<sonicBrokerHost>localhost</sonicBrokerHost>
<sonicBrokerPort>2506</sonicBrokerPort>
<pubDestType>Queue</pubDestType>
<pubDestination>{{order.step.2.queue}}</pubDestination>
<subDestType>Queue</subDestType>
<subTimeout>30</subTimeout>
<repDestType>Queue</repDestType>
<sendMsgType>Message</sendMsgType>
<sendMessage>message</sendMessage>
<jmsMessageObject>
</jmsMessageObject>
<onExNode>abort</onExNode>
<customProperties>
</customProperties>
<customConnProperties>
</customConnProperties>
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
          next="end" > 

    </Node>


    <DataSet type="com.itko.lisa.test.DataSheet" name="order_data" atend="end" local="false" random="false" maxItemsToFetch="100" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAFdAARb3JkZXJfZGF0YV9Sb3dOdW10AAExdAAJb3JkZXJfcXR5dAADMTAwdAAKb3JkZXJfdXNlcnQABm91aWQtMXQACG9yZGVyX2lkdAAEMS0xMXQADW9yZGVyX3Byb2R1Y3R0AAdwcm9kMTAweA==</sample>
<table>
<col>order_id</col>
<col>order_user</col>
<col>order_product</col>
<col>order_qty</col>
<tr>
<td>1-11</td>
<td>ouid-1</td>
<td>prod100</td>
<td>100</td>
</tr>
<tr>
<td>2-22</td>
<td>ouid-2</td>
<td>prod200</td>
<td>200</td>
</tr>
<tr>
<td>3-33</td>
<td>ouid-3</td>
<td>prod300</td>
<td>300</td>
</tr>
</table>
    </DataSet>

    <DataSet type="com.itko.lisa.test.DataSetIDGenerator" name="lisa.jms.correlation.id" atend="" local="false" random="false" maxItemsToFetch="100" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAAXbGlzYS5qbXMuY29ycmVsYXRpb24uaWR0AChsaXNhLWptcy1BMDgwMDdBMDAwMDAxMTRCNDYxQ0U5N0EyNDRCRjdFeA==</sample>
    <type>Alphanumeric</type>
    <prefix>lisa-jms-</prefix>
    </DataSet>

</TestCase>
