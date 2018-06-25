<?xml version="1.0" ?>

<TestCase name="load data from a csv file" version="5">

<meta><create version="0.0" buildNumber="0.0.0.0" author="cam" date="11/13/2009" host="sloth"  /><lastEdited version="0.0" buildNumber="0.0.0.0" author="cam" date="12/21/2009" host="sloth"  /></meta>

<id>fccf826e</id>
<Documentation>This test model uses a comma separated values (CSV) file as a data set to test a web application. The demo web app that ships with LISA allows us to add and remove users from the database.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9MyZ0Y3Y9NSZsaXNhdj0wLjAgKDAuMC4wLjAgdW5zdXBwb3J0ZWQgZGV2IHZlcnNpb24pJm5vZGVzPTEwMjg5MTI0MjQ=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

      <Companion type="com.itko.lisa.test.StartStopNodeCompanion" >
    <final>cleanup</final>
      </Companion>

      <Filter type="com.itko.lisa.test.FilterLisaProps">
              </Filter>

<CheckResult assertTrue="true" name="SimpleWebAssertion1" type="com.itko.lisa.test.SimpleWebAssertion">
<log>Assert [SimpleWebAssertion1] fired true of type Simple Web Assertion</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

    <Node name="start" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="true" 
          next="new user" > 

<Documentation>Starts test and initializes a unique user ID from &#10;the unique_user dataset.</Documentation>

      <!-- Data Sets -->
<readrec>unique_user</readrec>
<log>Starting LISA example test case.  </log>
    </Node>


    <Node name="new user" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="add user" > 

<Documentation>Navigate to the &apos;new user&apos; page.</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert any Non-Empty response" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert any Non-Empty response] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=new</query>
<parameterUrl></parameterUrl>
</url>
<images>false</images>
    </Node>


    <Node name="add user" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="list users" > 

<Documentation>Add a new user to the system by filling in the web for and submitting. The username is a combination of the unique_user data set valu we generated at the start of the test and the text value stored in the VCSV file.&#10;&#10;When we run out of rows in the CSV file, the data set has it&apos;s &apos;next&apos; step set to the delete_user step of the test.</Documentation>

      <!-- Data Sets -->
<readrec>users.csv</readrec>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert any Non-Empty response" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert any Non-Empty response] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=add&amp;user={{user}}&amp;pwd={{password}}</query>
<parameterUrl></parameterUrl>
</url>
<images>false</images>
    </Node>


    <Node name="list users" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="add user" > 

<Documentation>Ask the app for a list of users and use an assertion to verify that the user we just added is in the list.</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert any Non-Empty response" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert any Non-Empty response] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<CheckResult assertTrue="false" name="Assert user is now in list" type="com.itko.lisa.test.CheckResultContains">
<log>Assert [Assert user is now in list] fired false of type Result as String Contains Given String</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>{{user}}</param>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=list</query>
<parameterUrl></parameterUrl>
</url>
<images>false</images>
    </Node>


    <Node name="delete user" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="remove user" > 

<Documentation>Now we start deleting the users we created in then preovious steps.</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert any Non-Empty response" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert any Non-Empty response] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=delete</query>
<parameterUrl></parameterUrl>
</url>
<images>false</images>
    </Node>


    <Node name="remove user" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="list users2" > 

<Documentation>Cycle through the CSV file again but this time we remove the user instead of adding the user.</Documentation>

      <!-- Data Sets -->
<readrec>delete.users.csv</readrec>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert any Non-Empty response" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert any Non-Empty response] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=remove&amp;user={{user}}</query>
<parameterUrl></parameterUrl>
</url>
<images>false</images>
    </Node>


    <Node name="list users2" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="remove user" > 

<Documentation>Ask the app to list all users and assert that the user we just removed is NOT in the list. When the data set ends we end the test. This test also has a &apos;final step&apos; companion to clean up so that willo be called whenever the test ends, either noramlly or because of a failure.</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert any Non-Empty response" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert any Non-Empty response] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<CheckResult assertTrue="true" name="Assert user was removed" type="com.itko.lisa.test.CheckResultContains">
<log>Assert [Assert user was removed] fired true of type Result as String Contains Given String</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>{{user}}</param>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=list</query>
<parameterUrl></parameterUrl>
</url>
<images>false</images>
    </Node>


    <Node name="cleanup" log=""
          type="com.itko.lisa.jdbc.JDBCNode" 
          version="1" 
          think="1-10" 
          useFilters="true" 
          quiet="false" 
          next="end" > 

<Documentation>This node removes the user id/s created by this &#10;test case.  It is an example of SQL Database &#10;node.&#10;&#10;Note:  This node is always fired as it is &#10;defined in a special &quot;Companion&quot;.  This companion &#10;is used to ensure the first and last node fired &#10;in the test case.  This is special in that even &#10;at a test fail event this node gets called since &#10;it is defined in the companion.</Documentation>
<driver>{{DBDRIVER}}</driver>
<dataSourceConnect>false</dataSourceConnect>
<jndiFactory></jndiFactory>
<jndiServerURL></jndiServerURL>
<jndiDataSourceName></jndiDataSourceName>
<connect>{{DBCONNURL}}</connect>
<user>{{DBUSER}}</user>
<password>{{DBPASSWORD}}</password>
<onSQLError>start</onSQLError>
<resultSet>false</resultSet>
<maxRows>-1</maxRows>
<keepOpen>false</keepOpen>
<sql>DELETE FROM users WHERE login LIKE &apos;%{{unique_user}}%&apos;</sql>
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


    <DataSet type="com.itko.lisa.test.DataFile" name="users.csv" atend="delete user" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAACdAAIcGFzc3dvcmR0AAoxLXBhc3N3b3JkdAAEdXNlcnQAETEte3t1bmlxdWVfdXNlcn19eA==</sample>
    <location>{{LISA_PROJ_ROOT}}/Data/users.csv</location>
    <delim>,</delim>
    <bundle>false</bundle>
    </DataSet>

    <DataSet type="com.itko.lisa.test.DataFile" name="delete.users.csv" atend="end" local="false" random="false" maxItemsToFetch="1" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAACdAAIcGFzc3dvcmR0AAoxLXBhc3N3b3JkdAAEdXNlcnQAETEte3t1bmlxdWVfdXNlcn19eA==</sample>
    <location>{{LISA_PROJ_ROOT}}/Data/users.csv</location>
    <delim>,</delim>
    <bundle>false</bundle>
    </DataSet>

    <DataSet type="com.itko.lisa.test.DataSetIDGenerator" name="unique_user" atend="" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAALdW5pcXVlX3VzZXJ0ABZjc3ZfdXNlcnRlc3QtODQ0ODA5MjE3eA==</sample>
    <type>Number</type>
    <prefix>{{user_prefix}}-</prefix>
    </DataSet>

</TestCase>
