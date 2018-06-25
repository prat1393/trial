<?xml version="1.0" ?>


<!-- Test Case created by LISA Test Editor -->
<!-- On: Thu Jul 02 12:10:11 CDT 2009 -->


<TestCase name="web-app-proxy" version="5">
<id>2fdd8085</id>
<Documentation>This LISA test case is provided as a way to quickly see some functionality.  &#10;The server applications are hosted on an iTKO public server. If you are &#10;behind a firewall you may have difficulty with examples that use EJBs or &#10;database connections.  If you would like a local version of the server &#10;applications please email support@itko.com.</Documentation>
<sig>ZWQ9MyZ0Y3Y9MiZsaXNhdj0wLjAgKDAuMC4wLjApJm5vZGVzPTE4MDA4MTU5OTU=</sig>
<rootNode>start</rootNode>
<subprocess>false</subprocess>

<initState>
    <Parameter>
    <key>unique_user</key>
    <value>webapp-1652191067</value>
    </Parameter>
</initState>

<resultState>
    <Parameter>
    <key>WSPORT</key>
    <value>Property From Config</value>
    </Parameter>
    <Parameter>
    <key>user</key>
    <value>Property From Config</value>
    </Parameter>
    <Parameter>
    <key>password</key>
    <value>Property From Config</value>
    </Parameter>
    <Parameter>
    <key>DBPORT</key>
    <value>Property From Config</value>
    </Parameter>
    <Parameter>
    <key>DBNAME</key>
    <value>Property From Config</value>
    </Parameter>
    <Parameter>
    <key>PORT</key>
    <value>Property From Config</value>
    </Parameter>
    <Parameter>
    <key>SERVER</key>
    <value>Property From Config</value>
    </Parameter>
    <Parameter>
    <key>JNDIPORT</key>
    <value>Property From Config</value>
    </Parameter>
    <Parameter>
    <key>lisa.start.rsp</key>
    <value>Set in Step start</value>
    </Parameter>
    <Parameter>
    <key>lisa.start.rsp.time</key>
    <value>Set in Step start</value>
    </Parameter>
    <Parameter>
    <key>lisa.examples overview.rsp</key>
    <value>Set in Step examples overview</value>
    </Parameter>
    <Parameter>
    <key>lisa.examples overview.rsp.time</key>
    <value>Set in Step examples overview</value>
    </Parameter>
    <Parameter>
    <key>lisa.fail.rsp</key>
    <value>Set in Step fail</value>
    </Parameter>
    <Parameter>
    <key>lisa.fail.rsp.time</key>
    <value>Set in Step fail</value>
    </Parameter>
    <Parameter>
    <key>lisa.home users.rsp</key>
    <value>Set in Step home users</value>
    </Parameter>
    <Parameter>
    <key>lisa.home users.rsp.time</key>
    <value>Set in Step home users</value>
    </Parameter>
    <Parameter>
    <key>lisa.new user.rsp</key>
    <value>Set in Step new user</value>
    </Parameter>
    <Parameter>
    <key>lisa.new user.rsp.time</key>
    <value>Set in Step new user</value>
    </Parameter>
    <Parameter>
    <key>list_cmd_url</key>
    <value>Set in Step new user</value>
    </Parameter>
    <Parameter>
    <key>lisa.add user.rsp</key>
    <value>Set in Step add user</value>
    </Parameter>
    <Parameter>
    <key>lisa.add user.rsp.time</key>
    <value>Set in Step add user</value>
    </Parameter>
    <Parameter>
    <key>lisa.list users.rsp</key>
    <value>Set in Step list users</value>
    </Parameter>
    <Parameter>
    <key>lisa.list users.rsp.time</key>
    <value>Set in Step list users</value>
    </Parameter>
    <Parameter>
    <key>lisa.delete user.rsp</key>
    <value>Set in Step delete user</value>
    </Parameter>
    <Parameter>
    <key>lisa.delete user.rsp.time</key>
    <value>Set in Step delete user</value>
    </Parameter>
    <Parameter>
    <key>lisa.remove user.rsp</key>
    <value>Set in Step remove user</value>
    </Parameter>
    <Parameter>
    <key>lisa.remove user.rsp.time</key>
    <value>Set in Step remove user</value>
    </Parameter>
    <Parameter>
    <key>lisa.list users2.rsp</key>
    <value>Set in Step list users2</value>
    </Parameter>
    <Parameter>
    <key>lisa.list users2.rsp.time</key>
    <value>Set in Step list users2</value>
    </Parameter>
    <Parameter>
    <key>lisa.end.rsp</key>
    <value>Set in Step end</value>
    </Parameter>
    <Parameter>
    <key>lisa.end.rsp.time</key>
    <value>Set in Step end</value>
    </Parameter>
</resultState>

      <Companion type="com.itko.lisa.test.WebProxyCompanion" >
<host>localhost</host>
<port>8001</port>
<sslHost>localhost</sslHost>
<sslPort>8001</sslPort>
<credential_enc>f5504e2d23a7888253a27e8ef52607d8</credential_enc>
<preemptiveAuthenticationType></preemptiveAuthenticationType>
<excludeSimpleHostNames>false</excludeSimpleHostNames>
      </Companion>

<Configurations>
<Configuration>
<name>localhost</name>
<external>false</external>
<props>
    <Parameter>
    <key>WSPORT</key>
    <value>8080</value>
    </Parameter>
    <Parameter>
    <key>user</key>
    <value>webapp</value>
    </Parameter>
    <Parameter>
    <key>password</key>
    <value>example-pwd</value>
    </Parameter>
    <Parameter>
    <key>DBPORT</key>
    <value>3306</value>
    </Parameter>
    <Parameter>
    <key>DBNAME</key>
    <value>itko_examples</value>
    </Parameter>
    <Parameter>
    <key>PORT</key>
    <value>8080</value>
    </Parameter>
    <Parameter>
    <key>SERVER</key>
    <value>localhost</value>
    </Parameter>
    <Parameter>
    <key>JNDIPORT</key>
    <value>1099</value>
    </Parameter>
</props>
</Configuration>
<Configuration>
<name>weblogic-localhost</name>
<external>false</external>
<props>
    <Parameter>
    <key>DBCONNURL</key>
    <value>jdbc:pointbase:server://localhost:9092/itko_examples</value>
    </Parameter>
    <Parameter>
    <key>DBDRIVER</key>
    <value>com.pointbase.jdbc.jdbcUniversalDriver</value>
    </Parameter>
    <Parameter>
    <key>DBNAME</key>
    <value>itko_examples</value>
    </Parameter>
    <Parameter>
    <key>DBPASSWORD</key>
    <value>examples</value>
    </Parameter>
    <Parameter>
    <key>DBPORT</key>
    <value>9092</value>
    </Parameter>
    <Parameter>
    <key>DBUSER</key>
    <value>itko_examples</value>
    </Parameter>
    <Parameter>
    <key>JNDIPORT</key>
    <value>7001</value>
    </Parameter>
    <Parameter>
    <key>PORT</key>
    <value>7001</value>
    </Parameter>
    <Parameter>
    <key>SERVER</key>
    <value>localhost</value>
    </Parameter>
    <Parameter>
    <key>WSPORT</key>
    <value>7001</value>
    </Parameter>
    <Parameter>
    <key>password</key>
    <value>example-pwd</value>
    </Parameter>
    <Parameter>
    <key>user</key>
    <value>webapp</value>
    </Parameter>
</props>
</Configuration>
</Configurations>
      <Filter type="com.itko.lisa.test.FilterLisaProps">
              </Filter>

      <Filter type="com.itko.lisaint.servlet.ServletFilter">
              <buildTimeMax>0</buildTimeMax>
      <onErrorNode>fail</onErrorNode>
      <onWarning>continue</onWarning>
      <sendHeader>true</sendHeader>
      <forceGC>false</forceGC>
      <allowExceptionToFail>true</allowExceptionToFail>
      <logLevel>DEBUG</logLevel>
      <logger>com.itko.examples.ejb</logger>
      </Filter>

<CheckResult assertTrue="true" name="SimpleWebAssertion1" type="com.itko.lisa.test.SimpleWebAssertion">
<log>Assert [SimpleWebAssertion1] fired true of type Simple Web Assertion</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

    <Node name="start" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          think="1000-2000" 
 useFilters="true" 
 quiet="true" 
          next="examples overview" > 

<graphx>154</graphx>
<graphy>618</graphy>
<Documentation>Starts test and initializes a unique user ID from &#10;the unique_user dataset.</Documentation>
      <log>Starting LISA example test case web-app-proxy.  </log>

      <!-- Data Sets -->
<readrec>unique_user</readrec>
    </Node>


    <Node name="examples overview" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          think="1000-2000" 
 useFilters="true" 
 quiet="false" 
          next="home users" > 

<graphx>30</graphx>
<graphy>475</graphy>
<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/index.jsp</path>
</url>
<images>false</images>

      <!-- Result Elements -->
<CheckResult assertTrue="false" name="Assert any Non-Empty response" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert any Non-Empty response] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<CheckResult assertTrue="false" name="assert title" type="com.itko.lisa.web.WebHTMLComparisonAssert">
<log>Assert [assert title] fired false of type Highlight HTML Content for Comparison</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
<param></param>
<param>&lt;title&gt;examples overview&lt;/title&gt;</param>
<param></param>
</CheckResult>

    </Node>


    <Node name="home users" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          think="1000-2000" 
 useFilters="true" 
 quiet="false" 
          next="new user" > 

<graphx>3</graphx>
<graphy>288</graphy>
<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
</url>
<images>false</images>

      <!-- Result Elements -->
<CheckResult assertTrue="false" name="Assert any Non-Empty response" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert any Non-Empty response] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<CheckResult assertTrue="false" name="assert title" type="com.itko.lisa.web.WebHTMLComparisonAssert">
<log>Assert [assert title] fired false of type Highlight HTML Content for Comparison</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
<param></param>
<param>&lt;title&gt;home (users)&lt;/title&gt;</param>
<param></param>
</CheckResult>

    </Node>


    <Node name="new user" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          think="1000-2000" 
 useFilters="true" 
 quiet="false" 
          next="add user" > 

<graphx>82</graphx>
<graphy>115</graphy>
<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=new</query>
</url>
<images>false</images>

      <!-- Filters -->
      <Filter type="com.itko.lisa.web.FilterParseWebProps">
        <param0></param0>
<param1>&lt;td &gt;&#10;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&lt;a class=&quot;nav2i&quot; href=&quot;</param1>
<param2>{{list_cmd_url}}</param2>
<param3>&quot;&gt;List&lt;/a&gt;&#10;&#10;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&lt;/td&gt;</param3>
<param4></param4>
      </Filter>


      <!-- Result Elements -->
<CheckResult assertTrue="false" name="Assert any Non-Empty response" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert any Non-Empty response] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<CheckResult assertTrue="false" name="assert title" type="com.itko.lisa.web.WebHTMLComparisonAssert">
<log>Assert [assert title] fired false of type Highlight HTML Content for Comparison</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
<param></param>
<param>&lt;title&gt;new user &lt;/title&gt;</param>
<param></param>
</CheckResult>

    </Node>


    <Node name="add user" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          think="1000-2000" 
 useFilters="true" 
 quiet="false" 
          next="list users" > 

<graphx>241</graphx>
<graphy>13</graphy>
<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=add&amp;user={{unique_user}}&amp;pwd={{password}}</query>
</url>
<images>false</images>

      <!-- Result Elements -->
<CheckResult assertTrue="false" name="Assert any Non-Empty response" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert any Non-Empty response] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<CheckResult assertTrue="false" name="assert title" type="com.itko.lisa.web.WebHTMLComparisonAssert">
<log>Assert [assert title] fired false of type Highlight HTML Content for Comparison</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
<param></param>
<param>&lt;title&gt;add user &lt;/title&gt;</param>
<param></param>
</CheckResult>

    </Node>


    <Node name="list users" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          think="1000-2000" 
 useFilters="true" 
 quiet="false" 
          next="delete user" > 

<graphx>430</graphx>
<graphy>13</graphy>
<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=list</query>
</url>
<images>false</images>

      <!-- Result Elements -->
<CheckResult assertTrue="false" name="Assert any Non-Empty response" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert any Non-Empty response] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<CheckResult assertTrue="false" name="assert title" type="com.itko.lisa.web.WebHTMLComparisonAssert">
<log>Assert [assert title] fired false of type Highlight HTML Content for Comparison</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
<param></param>
<param>&lt;title&gt;list users &lt;/title&gt;</param>
<param></param>
</CheckResult>

    </Node>


    <Node name="delete user" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          think="1000-2000" 
 useFilters="true" 
 quiet="false" 
          next="remove user" > 

<graphx>589</graphx>
<graphy>115</graphy>
<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=delete</query>
</url>
<images>false</images>

      <!-- Result Elements -->
<CheckResult assertTrue="false" name="Assert any Non-Empty response" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert any Non-Empty response] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<CheckResult assertTrue="false" name="assert title" type="com.itko.lisa.web.WebHTMLComparisonAssert">
<log>Assert [assert title] fired false of type Highlight HTML Content for Comparison</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
<param></param>
<param>&lt;title&gt;delete user &lt;/title&gt;</param>
<param></param>
</CheckResult>

    </Node>


    <Node name="remove user" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          think="1000-2000" 
 useFilters="true" 
 quiet="false" 
          next="list users2" > 

<graphx>668</graphx>
<graphy>288</graphy>
<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=remove&amp;user={{unique_user}}</query>
</url>
<images>false</images>

      <!-- Result Elements -->
<CheckResult assertTrue="false" name="Assert any Non-Empty response" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert any Non-Empty response] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<CheckResult assertTrue="false" name="assert title" type="com.itko.lisa.web.WebHTMLComparisonAssert">
<log>Assert [assert title] fired false of type Highlight HTML Content for Comparison</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
<param></param>
<param>&lt;title&gt;remove user &lt;/title&gt;</param>
<param></param>
</CheckResult>

    </Node>


    <Node name="list users2" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          think="1000-2000" 
 useFilters="true" 
 quiet="false" 
          next="end" > 

<graphx>641</graphx>
<graphy>475</graphy>
<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=list</query>
</url>
<images>false</images>

      <!-- Result Elements -->
<CheckResult assertTrue="false" name="Assert any Non-Empty response" type="com.itko.lisa.test.CheckResultAny">
<log>Assert [Assert any Non-Empty response] fired false of type Any Non-Empty Result</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

<CheckResult assertTrue="false" name="assert title" type="com.itko.lisa.web.WebHTMLComparisonAssert">
<log>Assert [assert title] fired false of type Highlight HTML Content for Comparison</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
<param></param>
<param>&lt;title&gt;list users &lt;/title&gt;</param>
<param></param>
</CheckResult>

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          think="0h" 
 useFilters="true" 
 quiet="true" 
          next="end" > 

<graphx>-1</graphx>
<graphy>-1</graphy>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          think="0-0" 
 useFilters="true" 
 quiet="true" 
          next="fail" > 

<graphx>336</graphx>
<graphy>672</graphy>
    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          think="0-0" 
 useFilters="true" 
 quiet="true" 
          next="end" > 

<graphx>250</graphx>
<graphy>250</graphy>
    </Node>


    <DataSet type="com.itko.lisa.test.DataSetIDGenerator" name="unique_user" atend="" local="false" random="false" maxItemsToFetch="1" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAAeA==</sample>
    <type>Number</type>
    <prefix>{{user}}-</prefix>
    </DataSet>

</TestCase>
