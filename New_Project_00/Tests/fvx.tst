<?xml version="1.0" ?>

<TestCase name="fvx" version="5">

<meta>
   <create version="8.4.0" buildNumber="8.4.0.227" author="admin" date="02/21/2018" host="HVDIVD18MIS1939" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="02/21/2018" host="HVDIVD18MIS1939" />
</meta>

<id>8E68BB116F711E8BC9E688A20524153</id>
<Documentation>Put documentation of the Test Case here.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9LTEmbGlzYXY9OC40LjAgKDguNC4wLjIyNykmbm9kZXM9MTk5MDYyNjMzNw==</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<CheckResult assertTrue="true" name="" type="com.itko.lisa.test.SimpleWebAssertion">
<then>abort</then>
        <onErrorNode>fail</onErrorNode>
</CheckResult>

    <Node name="Lisa Bank" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="7307A9F016F911E8BC9E688A20524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Lisa Bank2" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<then>fail</then>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/lisabank</path>
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


    <Node name="Lisa Bank2" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="7310AAA516F911E8BC9E688A20524153" 
          think="500-1s" 
          useFilters="true" 
          quiet="false" 
          next="Lisa Bank3" > 


      <!-- Filters -->
      <Filter type="com.itko.lisa.test.FilterGetAttributeAndParseArg">
              <tag>A</tag>
      <tagn>7</tagn>
      <attrib>href</attrib>
      <isurl>true</isurl>
      <argkey>accountid</argkey>
      <prop>accountidLisa_Bank3_KEY</prop>
      <urlencode>false</urlencode>
      <default>152810304556</default>
      </Filter>

      <Filter type="com.itko.lisa.test.FilterGetAttributeAndParseArg">
              <tag>A</tag>
      <tagn>7</tagn>
      <attrib>href</attrib>
      <isurl>true</isurl>
      <argkey>userid</argkey>
      <prop>useridLisa_Bank3_KEY</prop>
      <urlencode>false</urlencode>
      <default>tups2905</default>
      </Filter>

      <Filter type="com.itko.lisa.test.FilterGetAttributeAndParseArg">
              <tag>A</tag>
      <tagn>7</tagn>
      <attrib>href</attrib>
      <isurl>true</isurl>
      <argkey>accountid</argkey>
      <prop>accountidLisa_Bank4_KEY</prop>
      <urlencode>false</urlencode>
      <default>152810304556</default>
      </Filter>

      <Filter type="com.itko.lisa.test.FilterGetAttributeAndParseArg">
              <tag>A</tag>
      <tagn>8</tagn>
      <attrib>href</attrib>
      <isurl>true</isurl>
      <argkey>accountid</argkey>
      <prop>accountidLisa_Bank5_KEY</prop>
      <urlencode>false</urlencode>
      <default>2295050569862</default>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<then>fail</then>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/lisabank/login.do</path>
</url>
<images>false</images>
<postList>
    <Parameter>
    <key>username</key>
    <value>tups2905</value>
    </Parameter>
    <Parameter>
    <key>password</key>
    <value>tups</value>
    </Parameter>
</postList>
<postEnc>application/x-www-form-urlencoded</postEnc>
<sslInfo>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="Lisa Bank3" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="7310AAA716F911E8BC9E688A20524153" 
          think="500-1s" 
          useFilters="true" 
          quiet="false" 
          next="Lisa Bank4" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<then>fail</then>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/lisabank/viewtransactions.do</path>
<query>accountid={{accountidLisa_Bank3_KEY}}&amp;userid={{useridLisa_Bank3_KEY}}</query>
</url>
<images>false</images>
<sslInfo>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="Lisa Bank4" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="73111FD916F911E8BC9E688A20524153" 
          think="500-1s" 
          useFilters="true" 
          quiet="false" 
          next="Lisa Bank5" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<then>fail</then>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/lisabank/viewtransactions.do</path>
<query>accountid={{accountidLisa_Bank4_KEY}}&amp;userid={{useridLisa_Bank3_KEY}}</query>
</url>
<images>false</images>
<sslInfo>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="Lisa Bank5" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="73111FDB16F911E8BC9E688A20524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Lisa Bank6" > 


      <!-- Filters -->
      <Filter type="com.itko.lisa.test.FilterGetAttributeAndParseArg">
              <tag>A</tag>
      <tagn>6</tagn>
      <attrib>href</attrib>
      <isurl>true</isurl>
      <argkey>accountid</argkey>
      <prop>accountidLisa_Bank6_KEY</prop>
      <urlencode>false</urlencode>
      <default>2295050569862</default>
      </Filter>


      <!-- Data Sets -->
<readrec>Create a Numeric Counting Data Set</readrec>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<then>fail</then>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/lisabank/viewtransactions.do</path>
<query>accountid={{accountidLisa_Bank5_KEY}}&amp;userid={{useridLisa_Bank3_KEY}}</query>
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


    <Node name="Lisa Bank6" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="73111FDD16F911E8BC9E688A20524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Lisa Bank7" > 


      <!-- Filters -->
      <Filter type="com.itko.lisa.test.FilterTagByAttributeGetAttribute">
              <tag>INPUT</tag>
      <searchattrib>name</searchattrib>
      <searchvalue>depositorwithdraw</searchvalue>
      <tagn>1</tagn>
      <attrib>value</attrib>
      <prop>depositorwithdrawLisa_Bank7_KEY</prop>
      <default>deposit</default>
      <urlencode>false</urlencode>
      </Filter>

      <Filter type="com.itko.lisa.test.FilterTagByAttributeGetAttribute">
              <tag>INPUT</tag>
      <searchattrib>name</searchattrib>
      <searchvalue>username</searchvalue>
      <tagn>1</tagn>
      <attrib>value</attrib>
      <prop>usernameLisa_Bank7_KEY</prop>
      <default>tups2905</default>
      <urlencode>false</urlencode>
      </Filter>

      <Filter type="com.itko.lisa.test.FilterTagByAttributeGetAttribute">
              <tag>INPUT</tag>
      <searchattrib>name</searchattrib>
      <searchvalue>accountId</searchvalue>
      <tagn>1</tagn>
      <attrib>value</attrib>
      <prop>accountIdLisa_Bank7_KEY</prop>
      <default>2295050569862</default>
      <urlencode>false</urlencode>
      </Filter>

      <Filter type="com.itko.lisa.test.FilterTagByAttributeGetAttribute">
              <tag>INPUT</tag>
      <searchattrib>name</searchattrib>
      <searchvalue>accountname</searchvalue>
      <tagn>1</tagn>
      <attrib>value</attrib>
      <prop>accountnameLisa_Bank7_KEY</prop>
      <default>Trupti</default>
      <urlencode>false</urlencode>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<then>fail</then>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/lisabank/buttonclick.do</path>
</url>
<images>false</images>
<postList>
    <Parameter>
    <key>accountid</key>
    <value>{{accountidLisa_Bank6_KEY}}</value>
    </Parameter>
    <Parameter>
    <key>userid</key>
    <value>{{useridLisa_Bank3_KEY}}</value>
    </Parameter>
    <Parameter>
    <key>action</key>
    <value>Deposit</value>
    </Parameter>
</postList>
<postEnc>application/x-www-form-urlencoded</postEnc>
<sslInfo>
<ssl-keystore-file></ssl-keystore-file>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
<ssl-alias></ssl-alias>
<ssl-key-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-key-password-enc>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="Lisa Bank7" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="73111FDF16F911E8BC9E688A20524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Lisa Bank8" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<then>fail</then>
</CheckResult>

<CheckResult assertTrue="true" name="Scripted Assertion" type="com.itko.lisa.test.AssertByScript">
<log>Assertion name: Scripted Assertion checks for: true is of type: Assert by Script Execution.</log>
<then>Lisa Bank5</then>
<valueToAssertKey></valueToAssertKey>
        <script>// This script should return a boolean result indicating the assertion is true or false&#13;&#10;int i=testExec.getStateValue(&quot;counter&quot;).toInteger();&#13;&#10;testExec.setStateValue(&quot;asfasd&quot;,i);&#13;&#10;String f=&quot;false&quot;;&#13;&#10;if(i&lt;=110)&#13;&#10;{&#13;&#10;f=&quot;true&quot;;&#13;&#10;}&#13;&#10;retval= f;</script>
        <language>Groovy</language>
        <copyprops>TestExecPropsAndSystemProps</copyprops>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/lisabank/depositmoney.do</path>
</url>
<images>false</images>
<postList>
    <Parameter>
    <key>depositorwithdraw</key>
    <value>{{depositorwithdrawLisa_Bank7_KEY}}</value>
    </Parameter>
    <Parameter>
    <key>userid</key>
    <value>{{useridLisa_Bank3_KEY}}</value>
    </Parameter>
    <Parameter>
    <key>username</key>
    <value>{{usernameLisa_Bank7_KEY}}</value>
    </Parameter>
    <Parameter>
    <key>accountId</key>
    <value>{{accountIdLisa_Bank7_KEY}}</value>
    </Parameter>
    <Parameter>
    <key>password</key>
    <value>tups</value>
    </Parameter>
    <Parameter>
    <key>accountname</key>
    <value>{{accountnameLisa_Bank7_KEY}}</value>
    </Parameter>
    <Parameter>
    <key>description</key>
    <value>deposit {{counter}}</value>
    </Parameter>
    <Parameter>
    <key>amount</key>
    <value>{{counter}}</value>
    </Parameter>
</postList>
<postEnc>application/x-www-form-urlencoded</postEnc>
<sslInfo>
<ssl-keystore-file></ssl-keystore-file>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
<ssl-alias></ssl-alias>
<ssl-key-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-key-password-enc>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="Lisa Bank8" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="73111FE116F911E8BC9E688A20524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="end" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<then>fail</then>
</CheckResult>

<url>
<proto>http</proto>
<host>{{SERVER}}</host>
<port>{{PORT}}</port>
<path>/lisabank/buttonclick.do</path>
</url>
<images>false</images>
<postList>
    <Parameter>
    <key>action</key>
    <value>Log Out</value>
    </Parameter>
</postList>
<postEnc>application/x-www-form-urlencoded</postEnc>
<sslInfo>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="8E68BB716F711E8BC9E688A20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="8E68BB516F711E8BC9E688A20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="8E68BB316F711E8BC9E688A20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <DataSet type="com.itko.lisa.test.CounterDataSet" name="Create a Numeric Counting Data Set" atend="Lisa Bank8" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAAHY291bnRlcnQAAzEwMXg=</sample>
    <propKey>counter</propKey>
    <countFrom>101</countFrom>
    <countTo>110</countTo>
    <countIncrement>1</countIncrement>
    </DataSet>

</TestCase>
