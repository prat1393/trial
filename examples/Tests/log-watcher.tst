<?xml version="1.0" ?>

<TestCase name="log-watcher" version="5">

<meta>
   <create version="0.0" buildNumber="0.0.0.0" author="cam" date="12/17/2009" host="sloth" />
   <lastEdited version="6.0.6" buildNumber="6.0.6.95" author="brogers" date="02/10/2012" host="gimli.local" />
</meta>

<id>d6b07450</id>
<Documentation>This example shows how to fail a test by watching a log file for ERROR or WARNing messages. &#10;&#10;It uses a data set to feed the example AntiPattern bean two numbers to divide. About halfway&#10;through the data set we give 0 as the operand. This, of course, causes a divide-by-zero exception&#10;to occur on the server. The AntiPattern bean logs the exception and returns -1 as a result.&#10;&#10;This is a common anti-pattern - internal errors occuring but external parties have no idea that&#10;the result is incorrect. It *looks* believable but it&apos;s wrong. What should happen is that the&#10;EJB propagates the exception back to the caller.&#10;&#10;If we were using Pathfinder this test case would fail because the fact that an exception was logged will be recorded by the agent and LISA would figure out that something is wrong. &#10;&#10;An alternative to using Pathfinder/LISA Agent is to set up a Global Assertion to watch the server log file. We define what to look for as a regular&#10;expression, in this case simply the test &apos;ERROR&apos;. The regular expressions can be as simple or as complicated as you like.  &#10;&#10;Normally you would set the assertion to fail the test immediately. In this case we step to the &apos;Error detected in log file&apos; step and end the test normally.&#10;&#10;Applications under test should never pass if they are logging errors or warnings. Consider using&#10;an equivalent companion in your test cases by default!</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9MyZ0Y3Y9NSZsaXNhdj02LjAuNiAoNi4wLjYuOTUpJm5vZGVzPS0xODMxMjM5NDE=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

<CheckResult assertTrue="true" name="TailFileAssertion" type="com.itko.lisa.utils.TailFileAssertion">
<log>Assert [TailFileAssertion] fired true of type Tail File for Expression</log>
<then>Error Detected in log file</then>
<valueToAssertKey></valueToAssertKey>
        <delaytime>5</delaytime>
        <timebetween>2</timebetween>
        <timeout>120</timeout>
        <filetowatch>{{LISA_HOME}}/DemoServer/lisa-demo-server/jboss/server/default/log/server.log</filetowatch>
        <filenotfound>Error Detected in log file</filenotfound>
        <expression>ERROR</expression>
        <ignoreExistingContent>true</ignoreExistingContent>
</CheckResult>

    <Node name="Check for localhost" log=""
          type="com.itko.lisa.test.NoTransNode" 
          version="1" 
          think="50-100" 
          useFilters="true" 
          quiet="true" 
          next="Divide EJB" > 

<Documentation>Do-Nothing step with an assertion to see if we are running against localhost or not.  If we are, proceed normally.  If we are not, the Tail Log File assertion will not work, so we skip the test.</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="AssertSERVER" type="com.itko.lisa.test.CheckResultPropRegEx">
<log>Skipping test, not running on localhost</log>
<then>end</then>
<valueToAssertKey></valueToAssertKey>
        <prop>SERVER</prop>
        <param>localhost</param>
</CheckResult>

    </Node>


    <Node name="Divide EJB" log=""
          type="com.itko.lisa.ejb.EJBNode" 
          version="1" 
          think="1000-10000" 
          useFilters="true" 
          quiet="false" 
          next="Divide EJB" > 

<Documentation>This EJB call loops over a data set. One of the data includes a dicision by zero error which raises an error on the server but not an ejb exception - we actually get back an incorrect answer (-1)&#10;&#10;However, we have a tail file global assertion and this should pick up the error in the log and change the next step to &apos;Error detected&apos; thus ending our test as we want to - in a succesfull state. Normally you would leave the next step&apos; setting on the tail file assertion to &apos;fail the test&apos; if you are searching for errors in log files.</Documentation>

      <!-- Data Sets -->
<readrec>NumbersToDivide</readrec>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="Any Exception Then Fail" type="com.itko.lisa.dynexec.CheckInvocationEx">
<log>Assert [Any Exception Then Fail] fired true of type Assert on Invocation Exception</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>.*</param>
</CheckResult>

<jndiFactory>org.jnp.interfaces.NamingContextFactory</jndiFactory>
<jndiServerURL>jnp://{{EJBSERVER}}:{{EJBPORT}}</jndiServerURL>
<jndiDataSourceName>AntiPatternBean/remote</jndiDataSourceName>
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
<serialNum>7</serialNum>
<array>false</array>
<className>java.lang.String</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>false</useprop>
<simpleVal>AntiPatternBean/remote</simpleVal>
</DynObject>
</param>
<isCallMade>false</isCallMade>
</Call>
</homeCall>
<ejbCallStream>
<DynObject>
<type>com.itko.lisa.dynexec.ComplexObj</type>
<serialNum>8</serialNum>
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
<methodName>tryCatchLogBury_DivideByZero</methodName>
<retClassName>java.lang.Integer</retClassName>
<actParamClassName>int</actParamClassName>
<actParamClassName>int</actParamClassName>
<paramClassName>java.lang.Integer</paramClassName>
<paramClassName>java.lang.Integer</paramClassName>
<param>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>10</serialNum>
<array>false</array>
<className>java.lang.Integer</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>true</useprop>
<propKey>param1</propKey>
<simpleVal>1</simpleVal>
</DynObject>
</param>
<param>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>11</serialNum>
<array>false</array>
<className>java.lang.Integer</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>true</useprop>
<propKey>param2</propKey>
<simpleVal>0</simpleVal>
</DynObject>
</param>
<result>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>9</serialNum>
<array>false</array>
<className>java.lang.Integer</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>false</useprop>
<simpleVal>1</simpleVal>
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


    <Node name="Error Detected in log file" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="end" > 

<Documentation>This step is executed when the global assertion for the &apos;tail file&apos; fires.  We actually expect this to happen and want it to happen, so the next thing we do from here is end the test. Normally you would fail the test if your gobal assertion found an error in the log!</Documentation>
<log>We detected an error in the jboss log, that&apos;s a Good Thing in this test case.&#10;Normally we would set the TailFail assertion to fail the test if an error&#10;is detected.</log>
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


    <DataSet type="com.itko.lisa.test.DataSheet" name="NumbersToDivide" atend="end" local="false" random="false" maxItemsToFetch="100" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAADdAAGcGFyYW0xdAABMXQABnBhcmFtMnQAATF0ABZOdW1iZXJzVG9EaXZpZGVfUm93TnVtdAABMXg=</sample>
<table>
<col>param1</col>
<col>param2</col>
<tr>
<td>1</td>
<td>1</td>
</tr>
<tr>
<td>4</td>
<td>2</td>
</tr>
<tr>
<td>1037</td>
<td>-3</td>
</tr>
<tr>
<td>42</td>
<td>0</td>
</tr>
<tr>
<td>10</td>
<td>5</td>
</tr>
<tr>
<td>6</td>
<td>3</td>
</tr>
<tr>
<td>56</td>
<td>1231</td>
</tr>
<tr>
<td>52</td>
<td>6</td>
</tr>
</table>
    </DataSet>

</TestCase>
