<?xml version="1.0" ?>

<TestCase name="web-application" version="5">

<meta>
   <create version="0.0" buildNumber="0.0.0.0" author="cam" date="11/11/2009" host="sloth" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="03/08/2018" host="HVDIVD18MIS1939" />
</meta>

<id>2fdd8085</id>
<Documentation>This is a simple web test that was generated using the web recorder. It contains some basic assertions such as &apos;assert non-empty response&apos; which is automatically generated and some &apos;assert title&apos; assertions that were created by parsing the HTML responses for the &lt;title&gt; tag and ensuring the page we recorded is the same page when we playback the test.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj04LjQuMCAoOC40LjAuMjI3KSZub2Rlcz0xODAwODE1OTk1</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

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
          uid="2fdd8085-start" 
          think="1000-2000" 
          useFilters="true" 
          quiet="true" 
          next="examples overview" > 

<Documentation>Starts test and initializes a unique user ID from &#10;the unique_user dataset.</Documentation>

      <!-- Data Sets -->
<readrec>unique_user</readrec>
<log>Starting LISA example test case web-application.  </log>
    </Node>


    <Node name="examples overview" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          uid="2fdd8085-examples+overview" 
          think="1000-2000" 
          useFilters="true" 
          quiet="false" 
          next="home users" > 


      <!-- Assertions -->
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

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/index.jsp</path>
</url>
<images>false</images>
<sslInfo>
<ssl-keystore-file></ssl-keystore-file>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
<ssl-alias></ssl-alias>
<ssl-key-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-key-password-enc>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="home users" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          uid="2fdd8085-home+users" 
          think="1000-2000" 
          useFilters="true" 
          quiet="false" 
          next="new user" > 


      <!-- Assertions -->
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
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
</url>
<images>false</images>
<sslInfo>
<ssl-keystore-file></ssl-keystore-file>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
<ssl-alias></ssl-alias>
<ssl-key-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-key-password-enc>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="new user" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          uid="2fdd8085-new+user" 
          think="1000-2000" 
          useFilters="true" 
          quiet="false" 
          next="add user" > 


      <!-- Filters -->
      <Filter type="com.itko.lisa.web.FilterParseWebProps">
        <valueToFilterKey>lisa.new user.rsp</valueToFilterKey>
<param0></param0>
<param1>&lt;td &gt;&#10;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&lt;a class=&quot;nav2i&quot; href=&quot;</param1>
<param2>{{list_cmd_url}}</param2>
<param3>&quot;&gt;List&lt;/a&gt;&#10;&#10;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&lt;/td&gt;</param3>
<param4></param4>
      </Filter>


      <!-- Assertions -->
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

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=new</query>
</url>
<images>false</images>
<sslInfo>
<ssl-keystore-file></ssl-keystore-file>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
<ssl-alias></ssl-alias>
<ssl-key-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-key-password-enc>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="add user" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          uid="2fdd8085-add+user" 
          think="1000-2000" 
          useFilters="true" 
          quiet="false" 
          next="list users" > 


      <!-- Assertions -->
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

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=add&amp;user={{unique_user}}&amp;pwd={{password}}</query>
</url>
<images>false</images>
<sslInfo>
<ssl-keystore-file></ssl-keystore-file>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
<ssl-alias></ssl-alias>
<ssl-key-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-key-password-enc>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="list users" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          uid="2fdd8085-list+users" 
          think="1000-2000" 
          useFilters="true" 
          quiet="false" 
          next="delete user" > 


      <!-- Assertions -->
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

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=list</query>
</url>
<images>false</images>
<sslInfo>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>false</params-saved-as-unicode>
    </Node>


    <Node name="delete user" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          uid="2fdd8085-delete+user" 
          think="1000-2000" 
          useFilters="true" 
          quiet="false" 
          next="remove user" > 


      <!-- Assertions -->
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

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=delete</query>
</url>
<images>false</images>
<sslInfo>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>false</params-saved-as-unicode>
    </Node>


    <Node name="remove user" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          uid="2fdd8085-remove+user" 
          think="1000-2000" 
          useFilters="true" 
          quiet="false" 
          next="list users2" > 


      <!-- Assertions -->
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
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=remove&amp;user={{unique_user}}</query>
</url>
<images>false</images>
<sslInfo>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>false</params-saved-as-unicode>
    </Node>


    <Node name="list users2" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="1" 
          uid="2fdd8085-list+users2" 
          think="1000-2000" 
          useFilters="true" 
          quiet="false" 
          next="end" > 


      <!-- Assertions -->
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

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/itko-examples/user-manage.jsp</path>
<query>cmd=list</query>
</url>
<images>false</images>
<sslInfo>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>false</params-saved-as-unicode>
    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="2fdd8085-fail" 
          think="0-0" 
          useFilters="true" 
          quiet="true" 
          next="end" > 

    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="2fdd8085-end" 
          think="0-0" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="2fdd8085-abort" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="end" > 

    </Node>


    <DataSet type="com.itko.lisa.test.DataSetIDGenerator" name="unique_user" atend="" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAB3CAAAAAEAAAAAeA==</sample>
    <type>Number</type>
    <prefix>{{user}}-</prefix>
    </DataSet>

</TestCase>
