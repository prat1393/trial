<?xml version="1.0" ?>

<TestCase name="scriptedDataSet" version="5">

<meta>
   <create version="0.0" buildNumber="0.0.0.0" author="cam" date="01/22/2015" host="gunna.local.ca.com" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="04/12/2018" host="HVDIVD18MIS1939" />
</meta>

<id>510073EFA1C911E48F9F10DDB1E3EF62</id>
<Documentation>Example of a custom data set that can be scripted.  These are meant only for non-performance production environments.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj04LjQuMCAoOC40LjAuMjI3KSZub2Rlcz0xOTA3ODE2NDE0</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="log myValue" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          uid="1AC70438A1CF11E4B6A510DDB1E3EF62" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="log myValue" > 

<Documentation>use a custom dataset written in the Groovy language.</Documentation>

      <!-- Data Sets -->
<readrec>groovy datascript Example</readrec>
<log>groovy returned {{myValue}}</log>
    </Node>


    <Node name="log javascript data" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          uid="AE997002A1D311E482A610DDB1E3EF62" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="log javascript data" > 

<Documentation>use a custom dataset written in the JavScript language.</Documentation>

      <!-- Data Sets -->
<readrec>javascript DataSet</readrec>
<log>javascript returned {{myValue}}</log>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="510073F5A1C911E48F9F10DDB1E3EF62" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="510073F3A1C911E48F9F10DDB1E3EF62" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="510073F1A1C911E48F9F10DDB1E3EF62" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <DataSet type="com.itko.lisa.test.ScriptedDataSet" name="groovy datascript Example" atend="log javascript data" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAB3CAAAAAEAAAABdAAHbXlWYWx1ZXQAATN4</sample>
    <language>Groovy</language>
    <script>// this is an example data set which is able to save state across&#13;&#10;// executions within the same test. This is a common use case for &#13;&#10;// custom data sets that read proprietary file formats, for example.&#13;&#10;&#13;&#10;// all it really does is count from 1 to 10 &#13;&#10;&#13;&#10;// you could save the current file position or cursor data &#13;&#10;// or the last primary key value used..&#13;&#10;&#13;&#10;&#13;&#10;import com.itko.lisa.vse.SharedModelMap&#13;&#10;import java.util.HashMap&#13;&#10;&#13;&#10;// we can save the &apos;state&apos; of the dataset across calls using SharedModelMap&#13;&#10;// or PersistentModelMap. Both of them save and retrieve String values&#13;&#10;// only so we need to do some data conversion...&#13;&#10;&#13;&#10;// is there alread a value from a previous call to the dataset?&#13;&#10;String currentValue = SharedModelMap.get(&quot;myNamespace&quot;, &quot;myValue&quot;)&#13;&#10;testExec.setStatevalue(&quot;apa&quot;,currentValue);&#13;&#10;if (currentValue == null) currentValue = &quot;0&quot;;&#13;&#10;&#13;&#10;// increment&#13;&#10;String newValue = String.valueOf(currentValue.toInteger() + 1)&#13;&#10;&#13;&#10;&#13;&#10;// save&#13;&#10;SharedModelMap.put(&quot;myNamespace&quot;, &quot;myValue&quot;, newValue)&#13;&#10;&#13;&#10;// return&#13;&#10;&#13;&#10;if (newValue.toInteger() &gt;= 10) {&#13;&#10;    _logger.info(&quot;Dataset has reached the end. clearing saved state and returning null&quot;)&#13;&#10;    SharedModelMap.remove(&quot;myNamespace&quot;, &quot;myValue&quot;)&#13;&#10;    return null // null means &apos;end of data set reached&apos;&#13;&#10;}&#13;&#10;&#13;&#10;map = new HashMap()&#13;&#10;map.put(&quot;myValue&quot;, newValue);&#13;&#10;return map;&#13;&#10;</script>
    <copyProps>TestExecObject</copyProps>
    </DataSet>

    <DataSet type="com.itko.lisa.test.ScriptedDataSet" name="javascript DataSet" atend="end" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAF3CAAAAAIAAAACdAAHbXlWYWx1ZXQAATF0AA5zb21lT3RoZXJWYWx1ZXQABmNoZWVzZXg=</sample>
    <language>ECMAScript</language>
    <script>// javascript data set&#13;&#10;&#13;&#10;// this is an example data set which is able to save state across&#13;&#10;// executions within the same test. This is a common use case for &#13;&#10;// custom data sets that read proprietary file formats, for example.&#13;&#10;&#13;&#10;// all it really does is count from 1 to 10 &#13;&#10;&#13;&#10;// you could save the current file position or cursor data &#13;&#10;// or the last primary key value used..&#13;&#10;&#13;&#10;&#13;&#10;// we can save the &apos;state&apos; of the dataset across calls using SharedModelMap&#13;&#10;// or PersistentModelMap. Both of them save and retrieve String values&#13;&#10;// only so we need to do some data conversion...&#13;&#10;&#13;&#10;// is there alread a value from a previous call to the dataset?&#13;&#10;&#13;&#10;var currentValue = com.itko.lisa.vse.SharedModelMap.get(&quot;myNamespace&quot;, &quot;myValue&quot;)&#13;&#10;if (currentValue === null) currentValue = &quot;0&quot;&#13;&#10;&#13;&#10;// increment&#13;&#10;var newValue = (Number(currentValue) + 1).toString()&#13;&#10;&#13;&#10;&#13;&#10;// save&#13;&#10;com.itko.lisa.vse.SharedModelMap.put(&quot;myNamespace&quot;, &quot;myValue&quot;, newValue)&#13;&#10;&#13;&#10;// return&#13;&#10;&#13;&#10;var map = null&#13;&#10;if (Number(newValue) &gt;= 10) {&#13;&#10;    _logger.info(&quot;Dataset has reached the end. clearing saved state and returning null&quot;)&#13;&#10;    com.itko.lisa.vse.SharedModelMap.remove(&quot;myNamespace&quot;, &quot;myValue&quot;)    &#13;&#10;} else {&#13;&#10;    map = new java.util.HashMap()&#13;&#10;    map.put(&quot;myValue&quot;, newValue)&#13;&#10;    map.put(&quot;someOtherValue&quot;, &quot;cheese&quot;)&#13;&#10;}&#13;&#10;&#13;&#10;// javascript doesn&apos;t have explicit return so implicitly &#13;&#10;// return the last evaluated expression, in this case &#13;&#10;// our data map which contains two values....&#13;&#10;&#13;&#10;map&#13;&#10;</script>
    <copyProps>TestExecObject</copyProps>
    </DataSet>

</TestCase>
