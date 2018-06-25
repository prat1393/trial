<?xml version="1.0" ?>

<TestCase name="trial" version="5">

<meta>
   <create version="8.4.0" buildNumber="8.4.0.227" author="admin" date="03/13/2018" host="HVDIVD18MIS1939" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="03/13/2018" host="HVDIVD18MIS1939" />
</meta>

<id>91FFC75C262311E8A36346B120524153</id>
<Documentation>Put documentation of the Test Case here.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9LTEmbGlzYXY9OC40LjAgKDguNC4wLjIyNykmbm9kZXM9LTE0OTQxODA4MTM=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

    <Node name="login" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="9ABA9556262311E8A36346B120524153" 
          think="0-1S" 
          useFilters="true" 
          quiet="false" 
          next="overview page" > 


      <!-- Filters -->
      <Filter type="com.itko.lisa.test.FilterGetAttributeAndParseArg">
        <valueToFilterKey>lisa.login.rsp</valueToFilterKey>
      <tag>a</tag>
      <tagn>1</tagn>
      <attrib>href</attrib>
      <isurl>true</isurl>
      <argkey>jsessionid</argkey>
      <prop>session</prop>
      <urlencode>false</urlencode>
      <default></default>
      </Filter>

<url>
<proto>http</proto>
<host>135.66.27.108</host>
<port>8080</port>
<path>/lisabank/home.do</path>
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


    <Node name="overview page" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="ADD3FEFB262411E8A36346B120524153" 
          think="0-1S" 
          useFilters="true" 
          quiet="false" 
          next="end" > 

<url>
<proto>http</proto>
<host>135.66.27.108</host>
<port>8080</port>
<path>/lisabank/login.do;jsessionid={{session}}</path>
</url>
<images>false</images>
<postList>
    <Parameter>
    <key>username</key>
    <value>tups2905</value>
    <name>Name</name>
    </Parameter>
    <Parameter>
    <key>password</key>
    <value>tups</value>
    <name>Name</name>
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


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="91FFC762262311E8A36346B120524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="91FFC760262311E8A36346B120524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="91FFC75E262311E8A36346B120524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


</TestCase>
