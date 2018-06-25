<?xml version="1.0" ?>

<TestCase name="addaccount" version="5">

<meta>
   <create version="8.4.0" buildNumber="8.4.0.227" author="admin" date="03/13/2018" host="HVDIVD18MIS1939" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="03/13/2018" host="HVDIVD18MIS1939" />
</meta>

<id>733AD1D3262511E8A36346B120524153</id>
<Documentation>Put documentation of the Test Case here.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9LTEmbGlzYXY9OC40LjAgKDguNC4wLjIyNykmbm9kZXM9MjI4MzkwODg2</sig>
<subprocess>true</subprocess>

<initState>
    <Parameter>
    <key>sessionid</key>
    <value>&lt;&lt; default value &gt;&gt;</value>
    <name>transaction session inf</name>
    </Parameter>
</initState>

<resultState>
    <Parameter>
    <key>DBCONNURL</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>DBDRIVER</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>DBNAME</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>DBPASSWORD</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>DBPORT</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>DBUSER</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>EJBPORT</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>EJBSERVER</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>ENDPOINT</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>ENDPOINT1</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>JMSCONNECTIONFACTORY</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>JNDIFACTORY</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>JNDIPORT</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>JNDIPROTOCOL</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>LIVE_INVOCATION_PORT</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>LIVE_INVOCATION_SERVER</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>PORT</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>Random Code Generator</key>
    <value></value>
    <name>Set 1st in Raw SOAP Request</name>
    </Parameter>
    <Parameter>
    <key>SERVER</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>SERVER1</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>VSE_WSPORT</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>WSPORT</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>WSPORT1</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>WSSERVER</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>WSSERVER1</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>lisa.Raw SOAP Request.rsp</key>
    <value></value>
    <name>Set 1st in Raw SOAP Request</name>
    </Parameter>
    <Parameter>
    <key>lisa.Raw SOAP Request.rsp.time</key>
    <value></value>
    <name>Set 1st in Raw SOAP Request</name>
    </Parameter>
    <Parameter>
    <key>lisa.continue (quiet).rsp</key>
    <value></value>
    <name>Set 1st in continue (quiet)</name>
    </Parameter>
    <Parameter>
    <key>lisa.continue (quiet).rsp.time</key>
    <value></value>
    <name>Set 1st in continue (quiet)</name>
    </Parameter>
    <Parameter>
    <key>order.step.2.queue</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>password</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>user</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
    <Parameter>
    <key>user_prefix</key>
    <value></value>
    <name>Set in project.config configuration</name>
    </Parameter>
</resultState>

    <Node name="Raw SOAP Request" log=""
          type="com.itko.lisa.ws.RawSOAPNode" 
          version="1" 
          uid="A71C601D262511E8A36346B120524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="end" > 


      <!-- Data Sets -->
<readrec>Random Code Generator</readrec>
<url>{{ENDPOINT}}</url>
<action></action>
<soapRequest itko_enc="base64">PHNvYXBlbnY6RW52ZWxvcGUgeG1sbnM6c29hcGVudj0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbnZlbG9wZS8iIHhtbG5zOmVqYjM9Imh0dHA6Ly9lamIzLmV4YW1wbGVzLml0a28uY29tLyI+DQogICA8c29hcGVudjpIZWFkZXIvPg0KICAgPHNvYXBlbnY6Qm9keT4NCiAgICAgIDxlamIzOmFkZEFjY291bnQ+DQogICAgICAgICA8IS0tT3B0aW9uYWw6LS0+DQogICAgICAgICA8dXNlcm5hbWU+dHVwczI5MDU8L3VzZXJuYW1lPg0KICAgICAgICAgPCEtLU9wdGlvbmFsOi0tPg0KICAgICAgICAgPGFjY291bnRPYmo+DQogICAgICAgICAgICA8IS0tT3B0aW9uYWw6LS0+DQogICAgICAgICAgICA8YmFsYW5jZT4xMDA8L2JhbGFuY2U+DQogICAgICAgICAgICA8IS0tT3B0aW9uYWw6LS0+DQogICAgICAgICAgICA8aWQ+e3tSYW5kb20gQ29kZSBHZW5lcmF0b3J9fTwvaWQ+DQogICAgICAgICAgICA8IS0tT3B0aW9uYWw6LS0+DQogICAgICAgICAgICA8bmFtZT5TYXZpbmdzIEFjY291bnQ8L25hbWU+DQogICAgICAgICAgICA8IS0tT3B0aW9uYWw6LS0+DQogICAgICAgICAgICA8dHlwZT5TYXZpbmdzPC90eXBlPg0KICAgICAgICAgPC9hY2NvdW50T2JqPg0KICAgICAgPC9lamIzOmFkZEFjY291bnQ+DQogICA8L3NvYXBlbnY6Qm9keT4NCjwvc29hcGVudjpFbnZlbG9wZT4=</soapRequest>
<contentType>text/xml; charset=UTF-8</contentType>
<onError>abort</onError>
<discardResponse>false</discardResponse>
<customHTTPHeaderInfo>
    <Parameter>
    <key>Token</key>
    <value>{{sessionid}}</value>
    </Parameter>
</customHTTPHeaderInfo>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="733AF8E9262511E8A36346B120524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="733AF8E7262511E8A36346B120524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="733AF8E5262511E8A36346B120524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <DataSet type="com.itko.lisa.test.DataSetRandIDGenerator" name="Random Code Generator" atend="" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAAVUmFuZG9tIENvZGUgR2VuZXJhdG9ydAAKNzM3MjM3NzU1Nng=</sample>
<type>Number</type>
<length>10</length>
<prefix></prefix>
    </DataSet>

</TestCase>
