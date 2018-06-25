<?xml version="1.0" ?>

<TestCase name="scripting" version="5">

<meta>
   <create version="0.0" buildNumber="0.0.0.0" author="cam" date="07/30/2014" host="gunna.local.ca.com" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="03/12/2018" host="HVDIVD18MIS1939" />
</meta>

<id>89FD4FB3178B11E499D910DDB1E3EF62</id>
<Documentation>LISA can now take advantage of JSR-223 scripting engines, allowing you to use a large variety of scriptin engines in script steps, assertions, data protocol handlers, match scripts and just about anywhere using {{=%language% }} syntax</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj04LjQuMCAoOC40LjAuMjI3KSZub2Rlcz0tNDYwODc5MjEy</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="Embedded expressions" log=""
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          uid="4261582E179111E494B010DDB1E3EF62" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="JavaScript" > 

<Documentation>You can embedd scripts just about anywhere with {{expressions}}.</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Good JavaScript" type="com.itko.lisa.test.CheckResultContains">
<log>Assertion name: Good JavaScript checks for: false  is of type: Result as String Contains Given String.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>JavaScript (result should be &apos;HELLO HELLO&apos;): HELLO HELLO</param>
</CheckResult>

<CheckResult assertTrue="false" name="Good Groovy" type="com.itko.lisa.test.CheckResultContains">
<log>Assertion name: Good Groovy checks for: false  is of type: Result as String Contains Given String.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>Groovy (result should be &apos;blue&apos;): blue</param>
</CheckResult>

<CheckResult assertTrue="false" name="Good beanshell" type="com.itko.lisa.test.CheckResultContains">
<log>Assertion name: Good beanshell checks for: false  is of type: Result as String Contains Given String.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>Beanshell (result should be &apos;UPPERCASE&apos;): UPPERCASE</param>
</CheckResult>

<log>Anywhere you use {{expressions}} you can specify a scripting language&#13;&#10;{{=%velocity%    &#13;&#10;Hello from Velocity. The host name is $LISA_HOST&#13;&#10;}}&#13;&#10;&#13;&#10;Beanshell (result should be &apos;UPPERCASE&apos;): {{=%beanshell%  &#13;&#10;    return new String(&quot;uppercase&quot;).toUpperCase();&#13;&#10;}}&#13;&#10;&#13;&#10;Groovy (result should be &apos;blue&apos;): {{=%groovy% &#13;&#10;  class AGroovyBean {&#13;&#10;    String color&#13;&#10;  }&#13;&#10; &#13;&#10;  def myGroovyBean = new AGroovyBean()&#13;&#10;  myGroovyBean.setColor(&apos;blue&apos;)&#13;&#10;  myGroovyBean.getColor()&#13;&#10;}} &#13;&#10;&#13;&#10;JavaScript (result should be &apos;HELLO HELLO&apos;): {{=%javascript%&#13;&#10;    var doubleUpper = function(s) {&#13;&#10;    var upper = s.toUpperCase()&#13;&#10;    return upper + &quot; &quot; + upper&#13;&#10;}&#13;&#10;doubleUpper(&quot;hello&quot;)&#13;&#10;}}&#13;&#10;&#13;&#10;The default language is now {{=com.itko.lisa.test.ScriptExecHandler.getDefaultLanguage()}}&#13;&#10;&#13;&#10;You can change the default language by setting lisa.scripting.default.language in local.properties&#13;&#10;or in the testExec or config, for example lisa.scripting.default.language=groovy&#13;&#10;&#13;&#10;LISA ships with JavaScript, Groovy, Beanshell and Velocity engines on all platforms. &#13;&#10;&#13;&#10;To install a new engine simply drop the jar(s) into LISA_HOME/lib or LISA_HOME/bin&#13;&#10;&#13;&#10;Script engines exist for the following languages:&#13;&#10;    &#183; ABCL&#13;&#10;    &#183; AppleScript&#13;&#10;    &#183; AWK&#13;&#10;    &#183; BeanShell&#13;&#10;    &#183; Bex&#13;&#10;    &#183; CajuScript&#13;&#10;    &#183; ejs&#13;&#10;    &#183; FreeMarker&#13;&#10;    &#183; Groovy&#13;&#10;    &#183; Jaskell&#13;&#10;    &#183; Java&#13;&#10;    &#183; JavaFX&#13;&#10;    &#183; JavaScript&#13;&#10;    &#183; Jelly&#13;&#10;    &#183; JEP&#13;&#10;    &#183; Jexl&#13;&#10;    &#183; jst&#13;&#10;    &#183; JudoScript&#13;&#10;    &#183; JUEL&#13;&#10;    &#183; MathEclipse&#13;&#10;    &#183; OCaml&#13;&#10;    &#183; OGNL&#13;&#10;    &#183; PHP&#13;&#10;    &#183; Pnuts&#13;&#10;    &#183; Python&#13;&#10;    &#183; Ruby&#13;&#10;    &#183; Scheme&#13;&#10;    &#183; Sleep &#13;&#10;    &#183; Smalltalk&#13;&#10;    &#183; Tcl&#13;&#10;    &#183; Velocity&#13;&#10;    &#183; XPath&#13;&#10;    &#183; XSLT&#13;&#10;&#13;&#10;</log>
    </Node>


    <Node name="JavaScript" log=""
          type="com.itko.lisa.test.UserScriptNode" 
          version="1" 
          uid="9CCE0DAA178B11E499D910DDB1E3EF62" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Groovy" > 

<Documentation>An example of the built-in Rhino (javascript) engine</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="Any Exception Then Fail" type="com.itko.lisa.dynexec.CheckInvocationEx">
<log>Assertion name: Any Exception Then Fail checks for: true  is of type: Assert on Invocation Exception.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>.*</param>
</CheckResult>

<CheckResult assertTrue="false" name="JavaScript Assertion" type="com.itko.lisa.test.AssertByScript">
<log>Assertion name: JavaScript Assertion checks for: false  is of type: Assert by Script Execution.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <script>// This script should return a boolean result indicating the assertion is true or false&#10;_logger.info(&quot;JavaScript assertion&quot;)&#10;&#10;&#10;&quot;6&quot;.equals(LASTRESPONSE)&#10;</script>
        <language>ECMAScript</language>
        <copyprops>TestExecProps</copyprops>
</CheckResult>

<onerror>abort</onerror>
<language>ECMAScript</language>
<copyProps>TestExecProps</copyProps>
<script>// built-in logger object&#13;&#10;&#13;&#10;// will log to the log file as well as the test event stream&#13;&#10;_logger.info(&quot;Hello from JavaScript at &quot; + LISA_PROJ_NAME)&#13;&#10;_logger.warn(&quot;this is a test warning that can be ignored&quot;);&#13;&#10;&#13;&#10;// interact directly with testExec&#13;&#10;testExec.setStateObject(&quot;Hello&quot;, &quot;JavaScript World&quot;)&#13;&#10;&#13;&#10;// look Ma, no semicolons!&#13;&#10;&#13;&#10;var sum = function() {&#13;&#10;    var i, x = 0&#13;&#10;    for (i = 0; i &lt; arguments.length; ++i) {&#13;&#10;        x += arguments[i]&#13;&#10;    }&#13;&#10;    return x&#13;&#10;}&#13;&#10;var mySum = sum(1, 2, 3) // returns 6&#13;&#10;&#13;&#10;// implicit return value back to LISA&#13;&#10;// the last expression evaluated is our return value&#13;&#10;mySum.toString() </script>
    </Node>


    <Node name="Groovy" log=""
          type="com.itko.lisa.test.UserScriptNode" 
          version="1" 
          uid="852840DC178E11E494B010DDB1E3EF62" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Beanshell" > 

<Documentation>An example of the groovy engine that is shipped with LISA.</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="Any Exception Then Fail" type="com.itko.lisa.dynexec.CheckInvocationEx">
<log>Assertion name: Any Exception Then Fail checks for: true  is of type: Assert on Invocation Exception.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>.*</param>
</CheckResult>

<CheckResult assertTrue="false" name="Groovy Assertion" type="com.itko.lisa.test.AssertByScript">
<log>Assertion name: Groovy Assertion checks for: false  is of type: Assert by Script Execution.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <script>// you can do anything with Groovy here.&#10;// as a quick demo let&apos;s use the &apos;tr&apos; String function which is&#10;// exclusive to Groovy..&#10;&#10;_logger.info(&quot;groovy assertion&quot;)&#10;&#10;return LASTRESPONSE.tr(&quot;I&quot;, &quot;i&quot;).equals(&quot;i&apos;m flying!&quot;);</script>
        <language>Groovy</language>
        <copyprops>TestExecProps</copyprops>
</CheckResult>

<onerror>abort</onerror>
<language>Groovy</language>
<copyProps>TestExecProps</copyProps>
<script>// built-in logger object&#10;_logger.info(&quot;Hello from Groovy at &quot; + LISA_PROJ_NAME)&#10;&#10;// interact directly with testExec&#10;testExec.setStateObject(&quot;Hello&quot;, &quot;Groovy World&quot;)&#10;&#10;trait FlyingAbility { &#10;    String fly() { &quot;I&apos;m flying!&quot; } &#10;}&#10;&#10;class Bird implements FlyingAbility {} &#10;def bird = new Bird() &#10;&#10;return bird.fly() &#10;</script>
    </Node>


    <Node name="Beanshell" log=""
          type="com.itko.lisa.test.UserScriptNode" 
          version="1" 
          uid="36191377179011E494B010DDB1E3EF62" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="end" > 

<Documentation>Beanshell has been upgraded to version 2.1.8</Documentation>

      <!-- Assertions -->
<CheckResult assertTrue="true" name="Any Exception Then Fail" type="com.itko.lisa.dynexec.CheckInvocationEx">
<log>Assertion name: Any Exception Then Fail checks for: true  is of type: Assert on Invocation Exception.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>.*</param>
</CheckResult>

<CheckResult assertTrue="false" name="Beanshell Assertion" type="com.itko.lisa.test.AssertByScript">
<log>Assertion name: Beanshell Assertion checks for: false  is of type: Assert by Script Execution.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <script>// This script should return a boolean result indicating the assertion is true or false&#10;_logger.info(&quot;beanshell assertion&quot;);&#10;&#10;return &quot;Greetings from beanshell&quot;.equals(LASTRESPONSE);</script>
        <language>BeanShell</language>
        <copyprops>TestExecPropsAndSystemProps</copyprops>
</CheckResult>

<onerror>abort</onerror>
<language>BeanShell</language>
<copyProps>TestExecObject</copyProps>
<script>/* &#10; This is similar to the deprecated scripting step in that&#10; we are using beanshell, only this time we set the step to &#10; only inject two variables into scope - the testExec and &#10; the _logger.  This speeds things up a bit if you don&apos;t &#10; need direct access to the testExec variables (you can &#10; still get to them)&#10;*/&#10;&#10;// this goes to the LISA log file. Useful for debugging&#10;_logger.info(&quot;Hello from beanshell at &quot; + testExec.getStateObject(&quot;LISA_PROJ_NAME&quot;));&#10;&#10;testExec.setStateObject(&quot;OS_NAME&quot;, System.getProperty(&quot;os.name&quot;));&#10;&#10;return &quot;Greetings&quot; + &quot; from &quot; + &quot;beanshell&quot;;&#10;&#10;</script>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="89FD4FB9178B11E499D910DDB1E3EF62" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="89FD4FB7178B11E499D910DDB1E3EF62" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="89FD4FB5178B11E499D910DDB1E3EF62" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


</TestCase>
