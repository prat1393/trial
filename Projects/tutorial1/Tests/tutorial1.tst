<?xml version="1.0" ?>

<TestCase name="tutorial1" version="5">

<meta>
   <create version="8.4.0" buildNumber="8.4.0.227" author="admin" date="12/06/2017" host="HVDIVD18MIS1939" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="12/06/2017" host="HVDIVD18MIS1939" />
</meta>

<id>DED1E1DDA7D11E793B5E6ED20524153</id>
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
          uid="5214EE78DA7D11E793B5E6ED20524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="Output Log Message~1" > 

<log>The LISA home directory is: {{LISA_HOME}}. LISA sets this property.&#13;&#10;&#13;&#10;The value of config_prop is: {{config_prop}}. We set this property in the configuration.&#13;&#10;&#13;&#10;The version of Java being used is: {{java.version}}. This is a system property.&#13;&#10;&#13;&#10;The new value of config_prop is: {{config_prop=21}}. We changed the value of config_prop here in log message itself.&#13;&#10;&#13;&#10;Adding 1 to config_prop gives: {{config_prop+1}} + 1. We did not change the value of config_prop.&#13;&#10;&#13;&#10;Create a new property named MyOutputLogMessage_step_prop: {{MyOutputLogMessage_step_prop=100}}.&#13;&#10;&#13;&#10;The MyOutputLogMessage_step_prop property has been assigned the value 100.&#13;&#10;&#13;&#10;</log>
    </Node>


    <Node name="Output Log Message~1" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          uid="F241B20DDA8011E793B5E6ED20524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="end" > 

<log>The current value of config_prop is: {{config_prop}}.&#13;&#10;&#13;&#10;The current value of MyOutputLogMessage_step_prop: {{MyOutputLogMessage_step_prop}}.&#13;&#10;&#13;&#10;</log>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="DED1E23DA7D11E793B5E6ED20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="DED1E21DA7D11E793B5E6ED20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="DED1E1FDA7D11E793B5E6ED20524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


</TestCase>
