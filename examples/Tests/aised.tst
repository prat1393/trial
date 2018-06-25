<?xml version="1.0" ?>

<TestCase name="aised" version="5">

<meta>
   <create version="8.4.0" buildNumber="8.4.0.227" author="admin" date="03/08/2018" host="HVDIVD18MIS1939" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="03/28/2018" host="HVDIVD18MIS1939" />
</meta>

<id>D0D9D94022DB11E8B94320E620524153</id>
<Documentation>Put documentation of the Test Case here.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj04LjQuMCAoOC40LjAuMjI3KSZub2Rlcz0tNjI1MTk2ODcy</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="http GET" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="DD8170D422DB11E8B94320E620524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="http GET~1" > 


      <!-- Filters -->
      <Filter type="com.itko.lisa.test.FilterGetAttributeAndParseArg">
        <valueToFilterKey>lisa.http GET.rsp</valueToFilterKey>
      <tag>a</tag>
      <tagn>1</tagn>
      <attrib>href</attrib>
      <isurl>true</isurl>
      <argkey>jsessionid</argkey>
      <prop>jsessionid</prop>
      <urlencode>false</urlencode>
      <default>dasdasdasmdkasnkndsanjkasdn</default>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Check HTTP Response Code" type="com.itko.lisa.test.CheckResultHTTPResponseCode">
<log>Assertion name: Check HTTP Response Code checks for: false is of type: HTTP Response Code.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>200</param>
</CheckResult>

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


    <Node name="http GET~1" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="C70DFED922DD11E8B94320E620524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="http GET~2" > 


      <!-- Data Sets -->
<readrec>Read Rows from Excel File</readrec>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="Ensure Result Contains String" type="com.itko.lisa.test.CheckResultContains">
<log>Assertion name: Ensure Result Contains String checks for: true is of type: Result as String Contains Given String.</log>
<then>Subprocess mysub</then>
<valueToAssertKey></valueToAssertKey>
        <param>Validation Error</param>
</CheckResult>

<url>
<proto>http</proto>
<host>135.66.27.108</host>
<port>8080</port>
<path>/lisabank/login.do;jsessionid={{jsessionid}}</path>
</url>
<images>false</images>
<postList>
    <Parameter>
    <key>username</key>
    <value>{{Username}}</value>
    <name>Name</name>
    </Parameter>
    <Parameter>
    <key>password</key>
    <value>{{Password}}</value>
    <name>Name</name>
    </Parameter>
</postList>
<postEnc>application/x-www-form-urlencoded</postEnc>
<sslInfo>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
<ssl-key-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-key-password-enc>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="http GET~2" log=""
          type="com.itko.lisa.test.URLTrans" 
          version="2" 
          uid="AF72AE6022DF11E8B94320E620524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="http GET" > 

<url>
<proto>http</proto>
<host>135.66.27.108</host>
<port>8080</port>
<path>/lisabank/logout.do</path>
</url>
<images>false</images>
<sslInfo>
<ssl-keystore-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-keystore-password-enc>
<ssl-key-password-enc>f5504e2d23a7888253a27e8ef52607d8</ssl-key-password-enc>
</sslInfo>
<encoding>UTF-8</encoding>
<params-saved-as-unicode>true</params-saved-as-unicode>
    </Node>


    <Node name="Subprocess mysub" log=""
          type="com.itko.lisa.utils.ExecSubProcessNode" 
          version="1" 
          uid="8D02B5E12F5911E890A61E4720524153" 
          think="0H" 
          useFilters="true" 
          quiet="true" 
          next="http GET" > 

<Subprocess>C:\lisa 8.4\DevTest\examples\Tests\mysub.tst</Subprocess>
<fullyParseProps>false</fullyParseProps>
<sendCommonState>false</sendCommonState>
<getCommonState>false</getCommonState>
<onAbort>abort</onAbort>
<Parameters>
    <Parameter>
    <key>id</key>
    <value>{{Username}}</value>
    <name>user id</name>
    </Parameter>
    <Parameter>
    <key>pass</key>
    <value>{{Password}}</value>
    <name>password</name>
    </Parameter>
</Parameters>
<SaveProps>
</SaveProps>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="D0D9D94622DB11E8B94320E620524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="D0D9D94422DB11E8B94320E620524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="D0D9D94222DB11E8B94320E620524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <DataSet type="com.itko.lisa.test.ExcelDataFile" name="Read Rows from Excel File" atend="end" local="false" random="false" maxItemsToFetch="100" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAN3CAAAAAQAAAADdAAgUmVhZCBSb3dzIGZyb20gRXhjZWwgRmlsZV9Sb3dOdW10AAExdAAIUGFzc3dvcmR0AAR0dXBzdAAIVXNlcm5hbWV0AAh0dXBzMjkwNXg=</sample>
    <location>H:/testdata.xlsx</location>
    <sheetname>Sheet1</sheetname>
    </DataSet>

</TestCase>
