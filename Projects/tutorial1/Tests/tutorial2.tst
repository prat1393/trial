<?xml version="1.0" ?>

<TestCase name="tutorial2" version="5">

<meta>
   <create version="8.4.0" buildNumber="8.4.0.227" author="admin" date="12/06/2017" host="HVDIVD18MIS1939" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="12/06/2017" host="HVDIVD18MIS1939" />
</meta>

<id>FBE74520DA8611E793B5E6ED20524153</id>
<Documentation>Put documentation of the Test Case here.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9LTEmbGlzYXY9OC40LjAgKDguNC4wLjIyNykmbm9kZXM9LTg0ODYzMDQ2NQ==</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

    <Node name="Output Log Message" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          uid="784E3273DA8711E793B5E6ED20524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="Output Log Message~1" > 


      <!-- Data Sets -->
<readrec>Read Rows from a Delimited Data File</readrec>
<log>Date is: {{month}}/{{day}}/{{year}}&#13;&#10;&#13;&#10;</log>
    </Node>


    <Node name="Output Log Message~1" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          uid="865A8A0DDA8711E793B5E6ED20524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="end" > 

<log>{{month}}/{{day}}/{{year}}</log>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="FBE74526DA8611E793B5E6ED20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="FBE74524DA8611E793B5E6ED20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="FBE74522DA8611E793B5E6ED20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <DataSet type="com.itko.lisa.test.DataFile" name="Read Rows from a Delimited Data File" atend="end" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAEdAArUmVhZCBSb3dzIGZyb20gYSBEZWxpbWl0ZWQgRGF0YSBGaWxlX1Jvd051bXQAATF0AAVtb250aHQAAHQABHllYXJxAH4ABXQAA2RheXEAfgAFeA==</sample>
    <location>{{LISA_RELATIVE_PROJ_ROOT}}/Data/devtestdata.txt</location>
    <charset>DEFAULT</charset>
    <delim>,</delim>
    </DataSet>

</TestCase>
