<?xml version="1.0" ?>

<TestCase name="main_all_should_fail" version="5">

<meta>
   <create version="0.0" buildNumber="0.0.0.0" author="cam" date="11/12/2009" host="sloth" />
   <lastEdited version="6.1" buildNumber="6.1.0.96" author="joncaulfield" date="05/07/2012" host="localhost" />
</meta>

<id>162b6417</id>
<Documentation>This test is an example of negative testing using an audit document.  When we expect test steps to fail, we configure an audit document to listen for the expected events.  If those events are detected, the verdict of the test is Success.  If those events are not detected, the verdict of the test is Failure.  The audit document determines the verdict.  &#10;&#10;An audit document is paired with a test plan by using a suite document.   In the AllTestsSuite, you will see this test plan paired with the main_all_should_fail audit document.&#10;&#10;In this particular test plan, the attempt to insert a new user fails and raises a &quot;Cycle failed&quot; event.  The audit document listens for that &quot;Cycle failed&quot; event.  If the &quot;Cycle failed&quot; event is detected, the audit document reports the verdict of the test as success, since our &quot;fail on purpose&quot; condition is met.&#10;&#10;If you run this test in the ITR, it will, of course, fail by design.  When this test is staged with an audit document, the failure is converted by the audit document into a success.  Please be aware that audit documents are applied to a staged test, not to a test running within the ITR.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9MyZ0Y3Y9NSZsaXNhdj02LjEgKDYuMS4wLjk2KSZub2Rlcz0tMjA4NzM0MTE5OQ==</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

      <Filter type="com.itko.lisaint.webservice.WebserviceFilter">
              <buildTimeMax>0</buildTimeMax>
      <onErrorNode>fail</onErrorNode>
      <onWarning>fail</onWarning>
      </Filter>

    <Node name="Subprocess: insert new user" log=""
          type="com.itko.lisa.utils.ExecSubProcessNode" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="true" 
          next="Subprocess: insert new user" > 

<Documentation>Call the web service to insert a new user. It should fail since we are asking for an existing user to be created.&#10;&#10;The getAllUsers dataset iterates over each user in the LISA examples database.&#10;&#10;The count dataset limits the number of iterations to 10, so if there are a large number of users, we don&apos;t take a very long time &#10;iterating over all of them.</Documentation>

      <!-- Data Sets -->
<readrec>count</readrec>
<readrec>getAllUsers</readrec>
<Subprocess>{{LISA_PROJ_ROOT}}/Subprocesses/ws-sec-sub.tst</Subprocess>
<fullyParseProps>true</fullyParseProps>
<sendCommonState>false</sendCommonState>
<getCommonState>false</getCommonState>
<onAbort>Subprocess: insert new user</onAbort>
<Parameters>
    <Parameter>
    <key>username_param</key>
    <value>{{LOGIN}}</value>
    <name>Name</name>
    </Parameter>
</Parameters>
<SaveProps>
</SaveProps>
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


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="end" > 

    </Node>


    <DataSet type="com.itko.lisa.test.CounterDataSet" name="count" atend="end" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAAIRGF0YVNldDF0AAExeA==</sample>
    <propKey>DataSet1</propKey>
    <countFrom>1</countFrom>
    <countTo>10</countTo>
    <countIncrement>1</countIncrement>
    </DataSet>

    <DataSet type="com.itko.lisa.test.JDBCDataSet" name="getAllUsers" atend="end" local="true" random="false" maxItemsToFetch="100" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAACdAAIVVNFUk5BTUV0AAVWYWx1ZXQAD3VzZXJuYW1lX1Jvd051bXQAATF4</sample>
    <driver>{{DBDRIVER}}</driver>
    <connect>{{DBCONNURL}}</connect>
    <userid>sa</userid>
    <sql>select LOGIN from USERS</sql>
    <password_enc>aa3dae49b425766818574ddff7698e56</password_enc>
    </DataSet>

</TestCase>
