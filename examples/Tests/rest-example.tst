<?xml version="1.0" ?>

<TestCase name="REST Example" version="5">

<meta><create version="0.0" buildNumber="0.0.0.0" author="dmcnair" date="08/10/2010" host="dmcnair-macbook.local"  /><lastEdited version="5.0.23" buildNumber="5.0.23.112" author="brad" date="09/16/2010" host="legolas"  /></meta>

<id>b7dcf7ce</id>
<Documentation>The test demonstrates how to execute RESTful services.  The demo  server contains a JAX-RS example, and each step in this test shows how to interact with that service using both XML and JSON.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9MyZ0Y3Y9NSZsaXNhdj01LjAuMjMgKDUuMC4yMy4xMTIpJm5vZGVzPTEyNTQ1NzY2Mzk=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="List Users - XML" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="2" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="List Users - JSON" > 

<Documentation>load the user list as XML</Documentation>
<url>
<proto>http</proto>
<host>{{WSSERVER}}</host>
<port>{{WSPORT}}</port>
<path>/rest-example/control/users</path>
<parameterUrl>http://{{WSSERVER}}:{{WSPORT}}/rest-example/control/users</parameterUrl>
</url>
<content></content>
      <header field="accept" value="application/xml" />
<httpMethod>GET</httpMethod>
    </Node>


    <Node name="List Users - JSON" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="2" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Get User - XML" > 

<Documentation>load the user list as JSON</Documentation>
<url>
<proto>http</proto>
<host>{{WSSERVER}}</host>
<port>{{WSPORT}}</port>
<path>/rest-example/control/users</path>
<parameterUrl>http://{{WSSERVER}}:{{WSPORT}}/rest-example/control/users</parameterUrl>
</url>
<content></content>
      <header field="accept" value="application/json" />
<httpMethod>GET</httpMethod>
    </Node>


    <Node name="Get User - XML" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="2" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Create User - XML" > 

<Documentation>grab a specific user</Documentation>
<url>
<proto>http</proto>
<host>{{WSSERVER}}</host>
<port>{{WSPORT}}</port>
<path>/rest-example/control/users/lisa_simpson</path>
<parameterUrl>http://{{WSSERVER}}:{{WSPORT}}/rest-example/control/users/lisa_simpson</parameterUrl>
</url>
<content></content>
      <header field="accept" value="application/xml" />
<httpMethod>GET</httpMethod>
    </Node>


    <Node name="Create User - XML" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="2" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="end" > 

<Documentation>create a new user</Documentation>
<url>
<proto>http</proto>
<host>{{WSSERVER}}</host>
<port>{{WSPORT}}</port>
<path>/rest-example/control/users/save</path>
<parameterUrl>http://{{WSSERVER}}:{{WSPORT}}/rest-example/control/users/save</parameterUrl>
</url>
<content>&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;&#13;&#10;&lt;user&gt;&#13;&#10;  &lt;emailAddress&gt;test@test.com&lt;/emailAddress&gt;&#13;&#10;  &lt;firstName&gt;first-9&lt;/firstName&gt;&#13;&#10;  &lt;lastName&gt;last-9&lt;/lastName&gt;&#13;&#10;  &lt;password&gt;aaaaaaaa&lt;/password&gt;&#13;&#10;  &lt;username&gt;dmxxx-009&lt;/username&gt;&#13;&#10;&lt;/user&gt;&#13;&#10;</content>
      <header field="accept" value="application/xml" />
      <header field="content-type" value="application/xml" />
<httpMethod>PUT</httpMethod>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


</TestCase>
