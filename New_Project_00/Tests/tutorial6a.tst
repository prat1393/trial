<?xml version="1.0" ?>

<TestCase name="tutorial6a" version="5">

<meta>
   <create version="8.4.0" buildNumber="8.4.0.227" author="admin" date="02/14/2018" host="HVDIVD18MIS1939" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="02/16/2018" host="HVDIVD18MIS1939" />
</meta>

<id>4B48612119C11E89CFDEE3620524153</id>
<Documentation>Put documentation of the Test Case here.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj04LjQuMCAoOC40LjAuMjI3KSZub2Rlcz04MjE2MjAzNDY=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

<CheckResult assertTrue="true" name="" type="com.itko.lisa.test.SimpleWebAssertion">
<log>Assertion name:  checks for: true is of type: Simple Web Assertion.</log>
<then>abort</then>
<valueToAssertKey></valueToAssertKey>
</CheckResult>

    <Node name="Lisa Bank" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="8965BAAE127111E8B757EE3620524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Lisa Bank2" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<log>Assertion name: Ensure Non-Empty Result checks for: false is of type: Any Non-Empty Result.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
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
          uid="896DF810127111E8B757EE3620524153" 
          think="500-1S" 
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
      <default>2295050569862</default>
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
      <default>2295050569862</default>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<log>Assertion name: Ensure Non-Empty Result checks for: false is of type: Any Non-Empty Result.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
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
<ssl-keystore-file></ssl-keystore-file>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
<ssl-alias></ssl-alias>
<ssl-key-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-key-password-enc>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="Lisa Bank3" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="896DF812127111E8B757EE3620524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Lisa Bank4" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<log>Assertion name: Ensure Non-Empty Result checks for: false is of type: Any Non-Empty Result.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
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
<ssl-keystore-file></ssl-keystore-file>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
<ssl-alias></ssl-alias>
<ssl-key-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-key-password-enc>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="Lisa Bank4" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="896DF814127111E8B757EE3620524153" 
          think="500-1s" 
          useFilters="true" 
          quiet="false" 
          next="Lisa Bank5" > 


      <!-- Filters -->
      <Filter type="com.itko.lisa.test.FilterGetAttributeAndParseArg">
              <tag>A</tag>
      <tagn>6</tagn>
      <attrib>href</attrib>
      <isurl>true</isurl>
      <argkey>accountid</argkey>
      <prop>accountidLisa_Bank5_KEY</prop>
      <urlencode>false</urlencode>
      <default>2295050569862</default>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<log>Assertion name: Ensure Non-Empty Result checks for: false is of type: Any Non-Empty Result.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
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
          uid="896E1F26127111E8B757EE3620524153" 
          think="500-1s" 
          useFilters="true" 
          quiet="false" 
          next="Lisa Bank6" > 


      <!-- Filters -->
      <Filter type="com.itko.lisa.test.FilterTagByAttributeGetAttribute">
              <tag>INPUT</tag>
      <searchattrib>name</searchattrib>
      <searchvalue>depositorwithdraw</searchvalue>
      <tagn>1</tagn>
      <attrib>value</attrib>
      <prop>depositorwithdrawLisa_Bank6_KEY</prop>
      <default>withdraw</default>
      <urlencode>false</urlencode>
      </Filter>

      <Filter type="com.itko.lisa.test.FilterTagByAttributeGetAttribute">
              <tag>INPUT</tag>
      <searchattrib>name</searchattrib>
      <searchvalue>username</searchvalue>
      <tagn>1</tagn>
      <attrib>value</attrib>
      <prop>usernameLisa_Bank6_KEY</prop>
      <default>tups2905</default>
      <urlencode>false</urlencode>
      </Filter>

      <Filter type="com.itko.lisa.test.FilterTagByAttributeGetAttribute">
              <tag>INPUT</tag>
      <searchattrib>name</searchattrib>
      <searchvalue>accountId</searchvalue>
      <tagn>1</tagn>
      <attrib>value</attrib>
      <prop>accountIdLisa_Bank6_KEY</prop>
      <default>2295050569862</default>
      <urlencode>false</urlencode>
      </Filter>

      <Filter type="com.itko.lisa.test.FilterTagByAttributeGetAttribute">
              <tag>INPUT</tag>
      <searchattrib>name</searchattrib>
      <searchvalue>accountname</searchvalue>
      <tagn>1</tagn>
      <attrib>value</attrib>
      <prop>accountnameLisa_Bank6_KEY</prop>
      <default>Trupti</default>
      <urlencode>false</urlencode>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<log>Assertion name: Ensure Non-Empty Result checks for: false is of type: Any Non-Empty Result.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
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
    <value>{{accountidLisa_Bank5_KEY}}</value>
    </Parameter>
    <Parameter>
    <key>userid</key>
    <value>{{useridLisa_Bank3_KEY}}</value>
    </Parameter>
    <Parameter>
    <key>action</key>
    <value>Withdraw</value>
    </Parameter>
</postList>
<postEnc>application/x-www-form-urlencoded</postEnc>
<sslInfo>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="Lisa Bank6" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="896E1F28127111E8B757EE3620524153" 
          think="500-1s" 
          useFilters="true" 
          quiet="false" 
          next="Lisa Bank7" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<log>Assertion name: Ensure Non-Empty Result checks for: false is of type: Any Non-Empty Result.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
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
    <value>{{depositorwithdrawLisa_Bank6_KEY}}</value>
    </Parameter>
    <Parameter>
    <key>userid</key>
    <value>{{useridLisa_Bank3_KEY}}</value>
    </Parameter>
    <Parameter>
    <key>username</key>
    <value>{{usernameLisa_Bank6_KEY}}</value>
    </Parameter>
    <Parameter>
    <key>accountId</key>
    <value>{{accountIdLisa_Bank6_KEY}}</value>
    </Parameter>
    <Parameter>
    <key>password</key>
    <value>tups</value>
    </Parameter>
    <Parameter>
    <key>accountname</key>
    <value>{{accountnameLisa_Bank6_KEY}}</value>
    </Parameter>
    <Parameter>
    <key>description</key>
    <value>afasf</value>
    </Parameter>
    <Parameter>
    <key>amount</key>
    <value>200</value>
    </Parameter>
</postList>
<postEnc>application/x-www-form-urlencoded</postEnc>
<sslInfo>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="Lisa Bank7" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="896E1F2A127111E8B757EE3620524153" 
          think="500-1s" 
          useFilters="true" 
          quiet="false" 
          next="end" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure Non-Empty Result" type="com.itko.lisa.test.CheckResultAny">
<log>Assertion name: Ensure Non-Empty Result checks for: false is of type: Any Non-Empty Result.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
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
          uid="4B52258119C11E89CFDEE3620524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="4B52256119C11E89CFDEE3620524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="4B4FB44119C11E89CFDEE3620524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


</TestCase>
