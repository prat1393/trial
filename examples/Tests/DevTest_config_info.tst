<?xml version="1.0" ?>

<TestCase name="DevTest_config_info" version="5">

<meta>
   <create version="0.0" buildNumber="0.0.0.0" author="cam" date="12/15/2009" host="sloth" />
   <lastEdited version="8.0.1" buildNumber="8.0.1.260" author="admin" date="12/17/2014" host="komna031491" />
</meta>

<id>79f4b3db</id>
<Documentation>This test case fetches diagnostic information about the machine running DevTest.  The results can help CA support solve configuration issues.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj04LjAuMSAoOC4wLjEuMjYwKSZub2Rlcz0zOTE5ODYwMjY=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="DevTestInfo" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          uid="79f4b3db-LISAInfo" 
          think="1000" 
          useFilters="true" 
          quiet="true" 
          next="EnvironmentVariables" > 

<Documentation>Outputs the full version number of DevTest</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="assert version number was substituted correctly" type="com.itko.lisa.test.CheckResultContains">
<log>assert version number was substituted correctly</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>LISA Version: {{</param>
</CheckResult>

<CheckResult assertTrue="true" name="assert build number resolved" type="com.itko.lisa.test.CheckResultContains">
<log>assert build number resolved</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>build {{</param>
</CheckResult>

<log>LISA Version: {{lisa.version.number}} build {{lisa.build.number}}&#10;LISA Home: {{LISA_HOME}}&#10;OS: {{os.name}} {{os.version}}</log>
    </Node>


    <Node name="EnvironmentVariables" log=""
          type="com.itko.lisa.utils.CommandLineExecNode" 
          version="1" 
          uid="79f4b3db-EnvironmentVariables" 
          think="1000" 
          useFilters="true" 
          quiet="false" 
          next="JavaConfiguration" > 

<Documentation>dumps all environment variables</Documentation>
<cmd>set</cmd>
<basedir>{{LISA_HOME}}</basedir>
<toNode>abort</toNode>
<exceptionNode>abort</exceptionNode>
<timeOut>300</timeOut>
<killAtEnd>false</killAtEnd>
<wait>true</wait>
<addToEnv>false</addToEnv>
<spawn>false</spawn>
<execShell>true</execShell>
<charset>DEFAULT</charset>
<env>
</env>
<exitCodes>
</exitCodes>
    </Node>


    <Node name="JavaConfiguration" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          uid="79f4b3db-JavaConfiguration" 
          think="1000" 
          useFilters="true" 
          quiet="true" 
          next="JavaSystemProperties" > 

<Documentation>Uses java system properties and some beanshell calls to provide basic Java runtime info</Documentation>
<log>Java Home: {{java.home}}&#10;JVM Version: {{java.version}}&#10;Classpath: {{java.class.path}}&#10;Free Memory:&#9;{{=Runtime.getRuntime().freeMemory()}} bytes&#10;Total Memory:&#9;{{=Runtime.getRuntime().totalMemory()}} bytes&#10;Max Memory:&#9;{{=Runtime.getRuntime().maxMemory()}} bytes</log>
    </Node>


    <Node name="JavaSystemProperties" log=""
          type="com.itko.lisa.test.ScriptNode" 
          version="1" 
          uid="79f4b3db-JavaSystemProperties" 
          think="1000" 
          useFilters="true" 
          quiet="false" 
          next="BinFolder" > 

<Documentation>Dumps a sorted lisa of java system properties. The list will include all properties from the lisa.properties, local.properties and site.properties files in LISA_HOME</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="Any Exception Then Fail" type="com.itko.lisa.dynexec.CheckInvocationEx">
<log>Assert [Any Exception Then Fail] fired true of type Assert on Invocation Exception</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>.*</param>
</CheckResult>

<onerror>abort</onerror>
<script>TreeMap sortedProps = new TreeMap(System.getProperties());&#10;StringBuffer sb = new StringBuffer();&#10;String NL = System.getProperty(&quot;line.separator&quot;);&#10;&#10;for (Iterator i = sortedProps.keySet().iterator(); i.hasNext();) {&#10;    String key = (String) i.next();&#10;    sb.append(key + &quot; = &quot; + sortedProps.get(key) + NL);&#10;}&#10;&#10;return sb.toString();</script>
    </Node>


    <Node name="BinFolder" log=""
          type="com.itko.lisa.utils.FileSystemSnapshot" 
          version="1" 
          uid="79f4b3db-BinFolder" 
          think="1000" 
          useFilters="true" 
          quiet="false" 
          next="LibFolder" > 

<Documentation>Dumps the bin folder, which contains most of the &apos;core&apos; devtest jar files</Documentation>
<location>{{LISA_HOME}}/bin</location>
<recursive>true</recursive>
<size>true</size>
<date>true</date>
<onFail>abort</onFail>
    </Node>


    <Node name="LibFolder" log=""
          type="com.itko.lisa.utils.FileSystemSnapshot" 
          version="1" 
          uid="79f4b3db-LibFolder" 
          think="1000" 
          useFilters="true" 
          quiet="false" 
          next="HotDeployFolder" > 

<Documentation>Dump the lib folder, which contains third-party jars</Documentation>
<location>{{LISA_HOME}}/lib</location>
<recursive>true</recursive>
<size>true</size>
<date>true</date>
<onFail>abort</onFail>
    </Node>


    <Node name="HotDeployFolder" log=""
          type="com.itko.lisa.utils.FileSystemSnapshot" 
          version="1" 
          uid="79f4b3db-HotDeployFolder" 
          think="1000" 
          useFilters="true" 
          quiet="false" 
          next="WriteFile" > 

<Documentation>Dump the hotDeploy folder, which contains customer-specific jars and generated web service client stubs.</Documentation>
<location>{{=com.itko.lisa.test.Environment.getHotDeployPath();}}</location>
<recursive>true</recursive>
<size>true</size>
<date>true</date>
<onFail>abort</onFail>
    </Node>


    <Node name="WriteFile" log=""
          type="com.itko.lisa.utils.ParseTextContentNode" 
          version="1" 
          uid="79f4b3db-WriteFile" 
          think="1000" 
          useFilters="true" 
          quiet="false" 
          next="end" > 

<Documentation>Finally we create a response that includes the other step responses and then use a step filter to save this step result to a file</Documentation>

      <!-- Filters -->
      <Filter type="com.itko.lisa.test.FilterSavePropToFile">
        <valueToFilterKey>lisa.WriteFile.rsp</valueToFilterKey>
      <file>{{EXAMPLES_HOME}}/LisaConfiguration-{{MACHINE_NAME}}.txt</file>
      <append>false</append>
      </Filter>

<text>PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PQpNYWNoaW5lOiB7e01BQ0hJTkVfTkFNRT1qYXZhLm5ldC5JbmV0QWRkcmVzcy5nZXRMb2NhbEhvc3QoKS5nZXRIb3N0TmFtZSgpO319CkRhdGU6IHt7PW5ldyBEYXRlKCk7fX0KCkxJU0EgSW5mbzoKe3tsaXNhLkxJU0FJbmZvLnJzcH19CgpFbnZpcm9ubWVudCBWYXJpYWJsZXM6Cnt7bGlzYS5FbnZpcm9ubWVudFZhcmlhYmxlcy5yc3B9fQoKSmF2YSBDb25maWd1cmF0aW9uIEluZm86Cnt7bGlzYS5KYXZhQ29uZmlndXJhdGlvbi5yc3B9fQoKSmF2YSBTeXN0ZW0gUHJvcGVydGllczoKe3tsaXNhLkphdmFTeXN0ZW1Qcm9wZXJ0aWVzLnJzcH19CgpMSVNBL2JpbjoKe3tsaXNhLkJpbkZvbGRlci5yc3B9fQoKTElTQS9MaWI6Cnt7bGlzYS5MaWJGb2xkZXIucnNwfX0KCkxJU0EvaG90RGVwbG95Ogp7e2xpc2EuSG90RGVwbG95Rm9sZGVyLnJzcH19Cgo9PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09</text>
    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="79f4b3db-fail" 
          think="0-0" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="79f4b3db-end" 
          think="0-0" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="79f4b3db-abort" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="end" > 

    </Node>


</TestCase>
