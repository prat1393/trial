<%@ page import ="java.util.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<!DOCTYPE html>

<html>
<head>
 </head>
<body>
<!-- <div class="wrapper"> -->

 <%@include file="../template.jsp" %> 

<div class="content-wrapper"> 
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Blank page
        <small>it all starts here</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Examples</a></li>
        <li class="active">Blank page</li>
      </ol>
    </section>
    
    <section class="content">
     <form:form method="POST" modelAttribute="banlist" >
      <div class="row">
      
        <div class="col-md-9">
          <div class="box box-primary">            
            <%-- <form:form method="POST" modelAttribute="banlist"> --%>
              <div class="box-body">
              	<div class="form-group">              	         	
                	<label>Select Release</label>
                	<form:select path="release" class="form-control select2" style="width: 50%;">
                  		<form:options items="${release}" />
                	</form:select>                
              	</div>
              	<div class="form-group">              	         	
                	<label>Select Backends</label>
                	<form:select path="backends" class="form-control select2" style="width: 50%;">
                  		<form:options items="${backends}" />
                	</form:select>                
              	</div>            
			 </div>
		
			<div class="box-footer">
                <button type="submit" class="btn btn-primary" name="submit">Submit</button>
              </div> 	 
          </div>
          </div>
          </div>
          
      <c:if test="${not empty resultList}">	   
     	<div class="row">
        <!-- <div class="col-xs-6"> --> 
        <div class="col-md-9">
          <div class="box box-primary">   
            <%-- <form:form method="POST" modelAttribute="banlist" action="updateProperties">   --%>      
            <div class="box-body">
            <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Driver</th>
                  <th>Username</th>
                  <th>Password</th>
                </tr>
                </thead>
                <tbody>               
					 <tr>
					 <td><form:input style="border:none;width:500px; " path="driveclass" value="${resultList.get(0)}"/></td>
					 <td><form:input style="border:none" path="username" value="${resultList.get(1)}"/></td>
					 <td><form:input style="border:none" path="password" value="${resultList.get(2)}"/></td>
					 </tr>
                </tbody>
              </table>
            </div>
            <div class="box-footer">
                <button type="submit" class="btn btn-primary" name="testconnection">Test Connection</button>
                <button type="submit" class="btn btn-primary" name="updateproperties">Update Properties</button>
             </div> 
            </div>
          </div>
      </div>
    	
	</c:if>
	</form:form>
	</section>
    
    
    
</div> 


<!-- </div> -->


</body>
</html>
