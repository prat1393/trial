<?xml version="1.0" ?>

<TestCase name="AccountControlMDB" version="5">

<meta>
   <create version="0.0" buildNumber="0.0.0.0" author="cam" date="11/12/2009" host="sloth" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="03/09/2018" host="HVDIVD18MIS1939" />
</meta>

<id>5ec2fa43</id>
<Documentation>A simple JMS test that adds a new user with an account to LisaBank. We expect and assert on patterns in the responses from the two steps.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj04LjQuMCAoOC40LjAuMjI3KSZub2Rlcz0tMTY5MzEyNDAzNg==</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="AddAccountCommand" log=""
          type="com.itko.lisa.esb.jndi.GeneralJndiJmsStep" 
          version="1" 
          uid="5ec2fa43-AddAccountCommand" 
          think="1000-10000" 
          useFilters="true" 
          quiet="false" 
          next="DeleteAccountCommand" > 

<Documentation>Build an account command as XML and send the message. We set a &apos;replay-to&apos; queue and await the response, then set the payload of the response object (the contained text) to be the overall response for this step as opposed to the java object representing the entire message.&#10;&#10;We can then assert on the string (xml) response payload and ensure it structurally matches what we would expect. We use the &apos;assert xml is identical&apos; assertion type which is perfect for this type of check.</Documentation>

      <!-- Data Sets -->
<readrec>account</readrec>
<readrec>accountId</readrec>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="accountId in response" type="com.itko.lisa.xml.AssertXMLDiff">
<log>Assert [accountId in response] fired true of type XML Diff Assertion</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
<control>PHN0cmluZz57e2FjY291bnRJZH19PC9zdHJpbmc+Cgo=</control>
<assertType>Identical</assertType>
<testContent></testContent>
<hideIgnoredDiffs>true</hideIgnoredDiffs>
<ignoreElmAttrOrder>false</ignoreElmAttrOrder>
<useRegEx>false</useRegEx>
<ignoreElementsByXPath>false</ignoreElementsByXPath>
</CheckResult>

<msgVersion>2</msgVersion>
<autoExtractPayLoad>true</autoExtractPayLoad>
<pubEnabledType>true</pubEnabledType>
<subEnabledType>true</subEnabledType>
<repEnabledType>false</repEnabledType>
<repTempQ>true</repTempQ>
<pubSessionMode>AUTO</pubSessionMode>
<subSessionMode>AUTO</subSessionMode>
<keepConnection>false</keepConnection>
<sharePublisher>false</sharePublisher>
<connectionType>JNDI</connectionType>
<lisa.prefill.jndiFactories>org.jnp.interfaces.NamingContextFactory</lisa.prefill.jndiFactories>
<lisa.prefill.jndiServerURLs>jnp://{{SERVER}}:{{JNDIPORT}}</lisa.prefill.jndiServerURLs>
<jndiDataSourceName>QueueConnectionFactory</jndiDataSourceName>
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
<wmBrokerHost>localhost</wmBrokerHost>
<wmBrokerPort>6849</wmBrokerPort>
<wmBrokerClientAppName>LISA</wmBrokerClientAppName>
<wmBrokerDeliverEnabled>false</wmBrokerDeliverEnabled>
<wmBrokerEventConvertRsp>false</wmBrokerEventConvertRsp>
<wmBrokerEventConvertRspXML>false</wmBrokerEventConvertRspXML>
<sonicBrokerHost>localhost</sonicBrokerHost>
<sonicBrokerPort>2506</sonicBrokerPort>
<pubDestType>Queue</pubDestType>
<pubDestination>queue/AccountCommand</pubDestination>
<subDestType>Queue</subDestType>
<subDestination>queue/B</subDestination>
<subTimeout>30</subTimeout>
<repDestination>queue/B</repDestination>
<repDestType>Queue</repDestType>
<sendMsgType>Text</sendMsgType>
<sendMessage>&lt;com.itko.examples.command.AddAccountCommand&gt;&#10;  &lt;account&gt;&#10;    &lt;balance&gt;{{balance}}&lt;/balance&gt;&#10;    &lt;type&gt;{{type}}&lt;/type&gt;&#10;    &lt;id&gt;{{accountId}}&lt;/id&gt;&#10;    &lt;name&gt;{{name}}&lt;/name&gt;&#10;    &lt;version&gt;0&lt;/version&gt;&#10;    &lt;transactions class=&quot;list&quot;/&gt;&#10;  &lt;/account&gt;&#10;  &lt;username&gt;itko&lt;/username&gt;&#10;&lt;/com.itko.examples.command.AddAccountCommand&gt;</sendMessage>
<jmsMessageObject>
</jmsMessageObject>
<onExNode>abort</onExNode>
<customProperties>
</customProperties>
<customConnProperties>
</customConnProperties>
    </Node>


    <Node name="DeleteAccountCommand" log=""
          type="com.itko.lisa.esb.jndi.GeneralJndiJmsStep" 
          version="1" 
          uid="5ec2fa43-DeleteAccountCommand" 
          think="1000-10000" 
          useFilters="true" 
          quiet="false" 
          next="end" > 

<Documentation>Once we have entered a new unique account it&apos;s a good idea to delete the data to keep the database clean and also we can test the delete functionality at the same time.</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="assert user deleted" type="com.itko.lisa.test.CheckResultContains">
<log>Expected &apos;true&apos; in the response</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>true</param>
</CheckResult>

<msgVersion>2</msgVersion>
<autoExtractPayLoad>true</autoExtractPayLoad>
<pubEnabledType>true</pubEnabledType>
<subEnabledType>true</subEnabledType>
<repEnabledType>false</repEnabledType>
<repTempQ>true</repTempQ>
<pubSessionMode>AUTO</pubSessionMode>
<subSessionMode>AUTO</subSessionMode>
<keepConnection>false</keepConnection>
<sharePublisher>false</sharePublisher>
<connectionType>JNDI</connectionType>
<lisa.prefill.jndiFactories>org.jnp.interfaces.NamingContextFactory</lisa.prefill.jndiFactories>
<lisa.prefill.jndiServerURLs>jnp://{{SERVER}}:{{JNDIPORT}}</lisa.prefill.jndiServerURLs>
<jndiDataSourceName>QueueConnectionFactory</jndiDataSourceName>
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
<wmBrokerHost>localhost</wmBrokerHost>
<wmBrokerPort>6849</wmBrokerPort>
<wmBrokerClientAppName>LISA</wmBrokerClientAppName>
<wmBrokerDeliverEnabled>false</wmBrokerDeliverEnabled>
<wmBrokerEventConvertRsp>false</wmBrokerEventConvertRsp>
<wmBrokerEventConvertRspXML>false</wmBrokerEventConvertRspXML>
<sonicBrokerHost>localhost</sonicBrokerHost>
<sonicBrokerPort>2506</sonicBrokerPort>
<pubDestType>Queue</pubDestType>
<pubDestination>queue/AccountCommand</pubDestination>
<subDestType>Queue</subDestType>
<subDestination>queue/B</subDestination>
<subTimeout>30</subTimeout>
<repDestination>queue/B</repDestination>
<repDestType>Queue</repDestType>
<sendMsgType>Text</sendMsgType>
<sendMessage>&lt;com.itko.examples.command.DeleteAccountCommand&gt;&#10;  &lt;accountId&gt;{{accountId}}&lt;/accountId&gt;&#10;  &lt;username&gt;itko&lt;/username&gt;&#10;&lt;/com.itko.examples.command.DeleteAccountCommand&gt;</sendMessage>
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
          uid="5ec2fa43-abort" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="end" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="5ec2fa43-fail" 
          think="0-0" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="5ec2fa43-end" 
          think="0-0" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <DataSet type="com.itko.lisa.test.DataSheet" name="account" atend="" local="false" random="false" maxItemsToFetch="100" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAZ3CAAAAAgAAAAEdAAHYmFsYW5jZXQABjEwMC4wMHQADmFjY291bnRfUm93TnVtdAABMXQABHR5cGV0AAhDSEVDS0lOR3QABG5hbWV0ABBDaGVja2luZyBBY2NvdW50eA==</sample>
<table>
<col>name</col>
<col>balance</col>
<col>type</col>
<tr>
<td>Checking Account</td>
<td>100.00</td>
<td>CHECKING</td>
</tr>
</table>
    </DataSet>

    <DataSet type="com.itko.lisa.test.DataSetIDGenerator" name="accountId" atend="" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAB3CAAAAAEAAAABdAAJYWNjb3VudElkdAAKMTY0MTEzMjY5M3g=</sample>
    <type>Number</type>
    <prefix></prefix>
    </DataSet>

</TestCase>
