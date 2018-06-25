<?xml version="1.0" ?>

<TestCase name="creditCheckValidate" version="5">

<meta><create version="0.0" buildNumber="0.0.0.0" author="rvickers" date="01/06/2010" host="Dogma.local"  /><lastEdited version="5.0.3" buildNumber="5.0.3.350" author="rvickers" date="01/07/2010" host="Dogma.local"  /></meta>

<id>390d3c2f</id>
<Documentation>Used for CVS monitor demos.  Fails randomly on a specific cid.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj01LjAuMyAoNS4wLjMuMzUwKSZub2Rlcz0xODkxNzYyNTYz</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

    <Node name="creditCheck" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="creditCheck" > 


      <!-- Data Sets -->
<readrec>cid number</readrec>
<readrec>counter</readrec>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="Assert CID Valid" type="com.itko.lisa.test.CheckResultPropRegEx">
<log>System return an invalid CID &gt;&gt; [{{cid}}]</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop>cid</prop>
        <param>30051</param>
</CheckResult>

<log>Ran credit check for ID: {{cid}}</log>
    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <DataSet type="com.itko.lisa.test.CounterDataSet" name="cid number" atend="" local="true" random="true" maxItemsToFetch="100" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAADY2lkdAAFMzAwMDB4</sample>
    <propKey>cid</propKey>
    <countFrom>30000</countFrom>
    <countTo>30100</countTo>
    <countIncrement>1</countIncrement>
    </DataSet>

    <DataSet type="com.itko.lisa.test.CounterDataSet" name="counter" atend="end" local="true" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAAeA==</sample>
    <propKey>count</propKey>
    <countFrom>1</countFrom>
    <countTo>6</countTo>
    <countIncrement>1</countIncrement>
    </DataSet>

</TestCase>
