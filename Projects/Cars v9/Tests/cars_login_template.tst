<?xml version="1.0" ?>

<TestCase name="car_demo_template" version="5">

<meta>
   <create version="9.5.0" buildNumber="9.5.0.371" author="admin" date="04/07/2016" host="CAIDEMO004" source="pathfinder" />
   <lastEdited version="9.5.0" buildNumber="9.5.0.660" author="admin" date="04/28/2016" host="linch0512501" />
</meta>

<id>97659FAFFCE811E5AFBC2CBE20524153</id>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj05LjUuMCAoOS41LjAuNjYwKSZub2Rlcz0xOTYyMjAyNTI1</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

      <Companion type="com.itko.lisa.test.FailTestCaseCompanion" >
      </Companion>

      <Companion type="com.ca.lisa.apptest.json.IgnoredJsonNodesCompanion" >
    <ignoredJsonPathForName>&lt;string-array&gt;&#10;  &lt;string&gt;$.access_token&lt;/string&gt;&#10;&lt;/string-array&gt;</ignoredJsonPathForName>
    <ignoredRegExForValue></ignoredRegExForValue>
      </Companion>

      <Filter type="com.itko.lisa.ws.HTTPInjectHeaderFilter">
              <headerKey>Authorization</headerKey>
      <headerValue>{{token_type}} {{access_token}}</headerValue>
      <skipStep>CAI Template Place Holder</skipStep>
      </Filter>

    <Node name="/cars-app/api/login" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="3" 
          uid="9769704BFCE811E5AFBC2CBE20524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="CAI Template Place Holder" > 


      <!-- Filters -->
      <Filter type="com.ca.lisa.apptest.json.FilterJSONGet">
        <valueToFilterKey>lisa./cars-app/api/login.rsp</valueToFilterKey>
      <jsonPath>$.access_token</jsonPath>
      <valueProp>access_token</valueProp>
      <lengthProp></lengthProp>
      </Filter>

      <Filter type="com.ca.lisa.apptest.json.FilterJSONGet">
        <valueToFilterKey>lisa./cars-app/api/login.rsp</valueToFilterKey>
      <jsonPath>$.token_type</jsonPath>
      <valueProp>token_type</valueProp>
      <lengthProp></lengthProp>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert Response Code Equals" type="com.itko.lisa.test.CheckResultHTTPResponseCode">
<log>Assertion name: Assert Response Code Equals checks for: false is of type: HTTP Response Code.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>200</param>
</CheckResult>

<CheckResult assertTrue="false" name="Assert Response Equals" type="com.ca.lisa.apptest.json.AssertJSONEquals2">
<log>Actual response does not match expected response.</log>
<then>continue</then>
<valueToAssertKey></valueToAssertKey>
        <jsonPath>$</jsonPath>
        <expectedValue>{&#10;    &quot;access_token&quot;: &quot;eyJhbGciOiJIUzI1NiJ9.eyJwcmluY2lwYWwiOiJINHNJQUFBQUFBQUFBSlZTUDBcL2JRQlJcL1RvTUFVUldvQkJJRFhhQmJkWkhhTVZOQmFTVmtRVVdhaFVwVUZcL3ZoSHB6dnpOMFprcVhLQkFNRHFBVUpxVitCYjFJV1BnQnFoNjdNWFhsbkNBNHNpSnZzZHpcL1wvXC9qMmZYY0dRTmZBdU1WeEl5ektaSjBJeG14bWhFb3RSYm9UcnN0eWlpZEVWaUk4RnNFVVR1RGxCQllJUUtpSjI4RExjNUR1OEpybEthaXZ0VFl4Y3ZXUGdyVGJKTGVPRzRTbnVhclBGN3JnamJmQ2VRRWtkSEZWZ2VBMG1lUlRwWExsbHJScWRUQmlNMTJDaW5JVTYydktqcVlodVVEbkJwUjJFRHFQaWJZbHhDR004ZDk4MHFRcTBEc1p2ek9aT3lGb1RYVDJFa1l4YlMrNGVKR2s2YjkzZmU1dUtFbXpEZDZoMnNvQU9kZmZhUTVubllZdGFTa290dExMekxaWHFXR3dJTDA3OHZka2ZGNGVcL2VxMEtBSFh5NXZGdnl2bk1BdlIrclwvOVwvVlJRZFJBNm1CNnlYc0hvbkl6ZVRKZk5uZzE3NTh2VFR6NU9yXC9TXC9QU05ralBqeDlIXC9QdmI1dnJMdW8wNDRZN1BiQWpvdDJ0K21jaVgzaWN2TCtGTG11S05KTklmNVJ5R045SmxNUVV0MnEwN1BmdFlIUjFKV3g4YlRVYnFcLzd0dVJTV00wc2tsc296OEtLSTduZkdRazBiT1wvaDNkSDQ0OTRkNGxtQm9oOHNjcWZtSkVyU2NwMjAwZTJjbnMyUEhmdytLSFAxXC8raHBPQ09jWUZ3TUFBQT09Iiwic3ViIjoibGlzYS5zaW1wc29uIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTQ2MDM5MTc1NSwiaWF0IjoxNDYwMzg4MTU1fQ.zqMzBhNOoosFvr4CwxvFb2C-S117W52hWA0iY2QG2ko&quot;,&#10;    &quot;firstname&quot;: &quot;Lisa&quot;,&#10;    &quot;roles&quot;: [&quot;ROLE_USER&quot;],&#10;    &quot;token_type&quot;: &quot;Bearer&quot;,&#10;    &quot;lastname&quot;: &quot;Simpson&quot;,&#10;    &quot;username&quot;: &quot;lisa.simpson&quot;&#10;}</expectedValue>
        <ignoreArrayOrder>true</ignoreArrayOrder>
</CheckResult>

<url>http://{{SERVER}}:{{PORT}}/cars-app/api/login</url>
<content>{&quot;username&quot;:&quot;lisa.simpson&quot;,&quot;password&quot;:&quot;golisa&quot;}</content>
<content-type>application/json;charset=UTF-8</content-type>
<data-type>text</data-type>
      <header field="accept-language" value="en-US,en;q=0.8" />
      <header field="connection" value="keep-alive" />
      <header field="content-type" value="application/json;charset=UTF-8" />
      <header field="accept-encoding" value="gzip, deflate" />
      <header field="accept" value="application/json, text/plain, */*" />
      <header field="user-agent" value="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36" />
<httpMethod>POST</httpMethod>
<onError>abort</onError>
    </Node>


    <Node name="CAI Template Place Holder" log=""
          type="com.itko.lisa.test.NoTransNode" 
          version="1" 
          uid="271EEE9BFCFA11E5A9B02CBE20524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="Output Log Message" > 

    </Node>


    <Node name="Output Log Message" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          uid="586278EDFCFA11E5A9B02CBE20524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="end" > 

<log>Provide logging info here.  &#13;&#10;Token Information = {{token_type}} {{access_token}}</log>
    </Node>


    <Node name="abort" log="The test was aborted"
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="97659FB5FCE811E5AFBC2CBE20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="97659FB3FCE811E5AFBC2CBE20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="97659FB1FCE811E5AFBC2CBE20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


</TestCase>
