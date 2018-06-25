<?xml version="1.0" ?>

<TestCase name="main_all_should_pass" version="5">

<meta><create version="0.0" buildNumber="0.0.0.0" author="cam" date="10/21/2009" host="sloth"  /><lastEdited version="0.0" buildNumber="0.0.0.0" author="cam" date="12/10/2009" host="sloth"  /></meta>

<id>162b6417</id>
<Documentation>This test calls a subprocess to insert a unique username into the demo server USERS table. &#10;&#10;The dataset is interesting in that it relies on a datasheet to draw values from a unique code generator. &#10;&#10;The same thing could have been done with a unique code generator in conjunction with a &apos;counter&apos; data set but this exaple demonstrates how one dataset can influence another. &#10;&#10;Datasets are evaluated in the order they are specified, that is each time the step is executed, the UniqueUser property is assigned a new value and the data sheet refers to {{UniqueUser}} 4 times so we get five unique values.&#10;&#10;If any of the steps fail the test fails immediately.&#10;&#10;Compare this test to &apos;main_all_should_fail&apos; which is a similar test where we expect each step to fail and we fail the test if anything passes (this is known as negative testing)</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9MyZ0Y3Y9NSZsaXNhdj0wLjAgKDAuMC4wLjAgdW5zdXBwb3J0ZWQgZGV2IHZlcnNpb24pJm5vZGVzPS0xMjM0NTI3MTk3</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>


      <Filter type="com.itko.lisaint.webservice.WebserviceFilter">
              <buildTimeMax>0</buildTimeMax>
      <onErrorNode>fail</onErrorNode>
      <onWarning>continue</onWarning>
      </Filter>

    <Node name="subprocess" log=""
          type="com.itko.lisa.utils.ExecSubProcessNode" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="subprocess" > 

<Documentation>Calls the subprocess which is actually a SOAP/WebService call to add a new user.</Documentation>

      <!-- Data Sets -->
<readrec>UniqueUser</readrec>
<readrec>username</readrec>
<Subprocess>{{LISA_PROJ_ROOT}}/Subprocesses/ws-sec-sub.tst</Subprocess>
<getEvents>0</getEvents>
<sendConfig>true</sendConfig>
<fullyParseProps>true</fullyParseProps>
<sendCommonState>false</sendCommonState>
<getCommonState>false</getCommonState>
<onFail>abort</onFail>
<Parameters>
    <Parameter>
    <key>username_param</key>
    <value>{{username}}</value>
    <name>Name</name>
    </Parameter>
</Parameters>
<SaveProps>
</SaveProps>
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
          next="" > 

    </Node>


    <DataSet type="com.itko.lisa.test.DataSetIDGenerator" name="UniqueUser" atend="" local="true" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAAKVW5pcXVlVXNlcnQAI3VzZXJBMDgwMDdBMDAwMDAxMTQ4RkFBRkREQTU0MTAxMkUweA==</sample>
    <type>Alphanumeric</type>
    <prefix>user</prefix>
    </DataSet>

    <DataSet type="com.itko.lisa.test.DataSheet" name="username" atend="end" local="true" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAACdAAIdXNlcm5hbWV0ACN1c2VyQTA4MDA3QTAwMDAwMTE0OEZBQUZEREE1NDEwMTJFMHQAD3VzZXJuYW1lX1Jvd051bXQAATF4</sample>
<table>
<col>username</col>
<tr>
<td>{{UniqueUser}}</td>
</tr>
<tr>
<td>{{UniqueUser}}</td>
</tr>
<tr>
<td>{{UniqueUser}}</td>
</tr>
<tr>
<td>{{UniqueUser}}</td>
</tr>
</table>
    </DataSet>

</TestCase>
