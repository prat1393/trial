<?xml version="1.0" ?>

<TestCase name="tutorial7" version="5">

<meta>
   <create version="8.4.0" buildNumber="8.4.0.227" author="admin" date="02/16/2018" host="HVDIVD18MIS1939" />
   <lastEdited version="8.4.0" buildNumber="8.4.0.227" author="admin" date="02/16/2018" host="HVDIVD18MIS1939" />
</meta>

<id>3742A07F127F11E8B757EE3620524153</id>
<Documentation>Put documentation of the Test Case here.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9LTEmbGlzYXY9OC40LjAgKDguNC4wLjIyNykmbm9kZXM9LTIwNzM2MzY4MTU=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

    <Node name="EJB" log=""
          type="com.itko.lisa.ejb.EJBNode" 
          version="1" 
          uid="76FBA549127F11E8B757EE3620524153" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="end" > 


      <!-- Assertions -->
<CheckResult assertTrue="true" name="Any Exception Then Fail" type="com.itko.lisa.dynexec.CheckInvocationEx">
<log>Assertion name: Any Exception Then Fail checks for: true is of type: Assert on Invocation Exception.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>.*</param>
</CheckResult>

<jndiFactory>org.jnp.interfaces.NamingContextFactory</jndiFactory>
<jndiPkgPrefixes></jndiPkgPrefixes>
<jndiServerURL>jnp://{{EJBSERVER}}:{{EJBPORT1}}/</jndiServerURL>
<jndiDataSourceName>EJB3UserControlBean/remote</jndiDataSourceName>
<user></user>
<password_enc>f5504e2d23a7888253a27e8ef52607d8</password_enc>
<keepEJBHomeRef>false</keepEJBHomeRef>
<keepEJBRef>true</keepEJBRef>
<homeCall>
<Call>
<methodName>lookup</methodName>
<retClassName>java.lang.Object</retClassName>
<actParamClassName>java.lang.String</actParamClassName>
<paramClassName>java.lang.String</paramClassName>
<exClassName>javax.naming.NamingException</exClassName>
<param>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>4</serialNum>
<array>false</array>
<className>java.lang.String</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>false</useprop>
<simpleVal>EJB3UserControlBean/remote</simpleVal>
</DynObject>
</param>
<assert></assert>
<assertTrue>true</assertTrue>
<assertThen></assertThen>
<isCallMade>true</isCallMade>
</Call>
</homeCall>
<ejbCallStream>
<DynObject>
<type>com.itko.lisa.dynexec.ComplexObj</type>
<serialNum>5</serialNum>
<array>false</array>
<className>com.sun.proxy.$Proxy30</className>
<complex>true</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>false</simple>
<useprop>false</useprop>
<Setters>
</Setters>
<Call>
<methodName>addUser</methodName>
<retClassName>com.itko.examples.entity.User</retClassName>
<actParamClassName>java.lang.String</actParamClassName>
<actParamClassName>java.lang.String</actParamClassName>
<paramClassName>java.lang.String</paramClassName>
<paramClassName>java.lang.String</paramClassName>
<param>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>7</serialNum>
<array>false</array>
<className>java.lang.String</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>true</useprop>
<propKey></propKey>
</DynObject>
</param>
<param>
<DynObject>
<type>com.itko.lisa.dynexec.SimpleObj</type>
<serialNum>9</serialNum>
<array>false</array>
<className>java.lang.String</className>
<complex>false</complex>
<interface>false</interface>
<null>false</null>
<void>false</void>
<simple>true</simple>
<useprop>true</useprop>
<propKey>Password</propKey>
<simpleVal>Password</simpleVal>
</DynObject>
</param>
<result>
<DynObject>
<type>com.itko.lisa.dynexec.ComplexObj</type>
<serialNum>6</serialNum>
<array>false</array>
<className>com.itko.examples.entity.User</className>
<complex>true</complex>
<interface>false</interface>
<null>true</null>
<void>false</void>
<simple>false</simple>
<useprop>false</useprop>
<Setters>
</Setters>
</DynObject>
</result>
<assert>\ATrue\Z</assert>
<assertTrue>false</assertTrue>
<assertThen>fail</assertThen>
<isCallMade>true</isCallMade>
</Call>
</DynObject>
</ejbCallStream>
<isEJBLocalInterface>false</isEJBLocalInterface>
<isHomelessEjb>true</isHomelessEjb>
<onExNode>abort</onExNode>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="3742C795127F11E8B757EE3620524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="3742C793127F11E8B757EE3620524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="3742C791127F11E8B757EE3620524153" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


</TestCase>
