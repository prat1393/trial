<?xml version="1.0" ?>

<TestCase name="lisabank" version="5">

<meta>
   <create version="8.4.0" buildNumber="8.4.0.227" author="admin" date="06/11/2018" host="HVDIVD18MIS1939" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="06/11/2018" host="HVDIVD18MIS1939" />
</meta>

<id>3A69D1136D7311E8997F68F020524153</id>
<Documentation>Put documentation of the Test Case here.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9LTEmbGlzYXY9OC40LjAgKDguNC4wLjIyNykmbm9kZXM9LTIwNzM3MDU0ODQ=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="3A6E3DE96D7311E8997F68F020524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="3A6D53876D7311E8997F68F020524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="3A6D53856D7311E8997F68F020524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


</TestCase>
