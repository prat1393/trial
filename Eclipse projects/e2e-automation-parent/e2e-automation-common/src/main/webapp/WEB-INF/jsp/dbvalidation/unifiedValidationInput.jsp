<%@ page import ="java.util.*" %>
<%@ page import ="org.apache.poi.ss.usermodel.CellType" %>
<%@ page import ="org.apache.poi.ss.usermodel.Row" %>
<%@ page import ="org.apache.poi.xssf.usermodel.XSSFSheet" %>
<%@ page import ="org.apache.poi.xssf.usermodel.XSSFWorkbook" %>
<%@ page import ="java.io.FileInputStream" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<script>
	function show1(){
	  document.getElementById('divbulk').style.display ='none';
	  document.getElementById('divmanual').style.display ='block';
	}
	function show2(){
	  document.getElementById('divbulk').style.display = 'block';
	  document.getElementById('divmanual').style.display ='none';
	}
	function showUnifiedDBApplication() {
		var e = document.getElementById("releaseList");
		var release = e.options[e.selectedIndex].value;
		$.ajax({
		type: "POST",
		url: "/e2e/retrieveUnifiedDBApplication",
		data: "release=" + release,
		success: function(response){
			if (response.includes("true")) {
				$('#enable').show();
			}
			if (response.includes("false")) {
				$('#disable').show();
			}	  
	                
		},
	 	error: function(e){
		alert('Error: ' + e);
	 	}
	 	});
	}
</script>
 </head>
<body>
<div class="wrapper">

 <%@include file="../template.jsp" %> 

<div class="content-wrapper"> 
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       Unified
        <small>Validation</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Forms</a></li>
        <li class="active">General Elements</li>
      </ol>
    </section>
    
    <section class="content">
      <div class="row">
      
        <!-- left column -->
        <div class="col-md-6">
          <!-- general form elements -->
          <div class="box box-primary">
            <!-- <div class="box-header with-border">
              <h3 class="box-title">Quick Example</h3>
            </div> -->
            <form:form action="unifiedvalidation" method="POST" modelAttribute="banlist" enctype="multipart/form-data">
              <div class="box-body">
              	<div class="form-group">
              	         	
                	<label>Select Release</label>
                	<form:select path="release" class="form-control select2" style="width: 100%;" id="releaseList" onchange="showUnifiedDBApplication()">
                  		<form:options items="${release}" />
                	</form:select>
                
              </div>
              <div hidden="true" id="disable">
          	<h2>Admin Disable the application</h2>
          </div>
          <div hidden="true" id="enable">
              <div class="form-group">
				<label>Select Input Method for Validation</label><br><br>
				<input type="radio" name="inputMethod" value="manual" onclick="show1();" />Manual
				<input type="radio" name="inputMethod" value="bulk" onclick="show2();" />Bulk
				</div>
				
              <%-- <div class="form-group" id="divmanual" style="display:none;">
                <label>Enter BAN</label>
                <form:input path="ban" type="text" name="ban" class="form-control" placeholder="Enter BAN1"/> 
                <br> 
                <form:input path="ban" type="text" name="ban" class="form-control" placeholder="Enter BAN2"/>  
                <br>
                <form:input path="ban" type="text" name="ban" class="form-control" placeholder="Enter BAN3"/>  
                              
                </div> --%>
             <div class="box-body" id="divmanual" style="display:none;">
              <table class="table table-bordered">
                <tr>
                  <th style="width: 50px">#</th>
                  <th>Uverse BAN</th>
                  <th>Wireless BAN</th>
                  <th>DTV BAN</th>
                </tr>
                <tr>
                  <td><form:input style="border:none" path="ban" type="text" class="form-control" value="*"/></td>
                  <td><form:input style="border:none" path="ban" type="text" name="ban" class="form-control" placeholder="Enter BAN1"/></td>
                  <td><form:input style="border:none" path="ban" type="text" name="ban" class="form-control" placeholder="Enter BAN1"/></td>
                  <td><form:input style="border:none" path="ban" type="text" name="ban" class="form-control" placeholder="Enter BAN1"/> </td>
                </tr>
                <tr>
                  <td><form:input style="border:none" path="ban" type="text" class="form-control" value="*"/></td>
                  <td><form:input style="border:none" path="ban" type="text" name="ban" class="form-control" placeholder="Enter BAN1"/></td>
                  <td><form:input style="border:none" path="ban" type="text" name="ban" class="form-control" placeholder="Enter BAN1"/></td>
                  <td><form:input style="border:none" path="ban" type="text" name="ban" class="form-control" placeholder="Enter BAN1"/></td>
                </tr>
                <tr>
                  <td><form:input style="border:none" path="ban" type="text"  class="form-control" value="*"/></td>
                  <td><form:input style="border:none" path="ban" type="text" name="ban" class="form-control" placeholder="Enter BAN1"/></td>
                  <td><form:input style="border:none" path="ban" type="text" name="ban" class="form-control" placeholder="Enter BAN1"/></td>
                  <td><form:input style="border:none" path="ban" type="text" name="ban" class="form-control" placeholder="Enter BAN1"/></td>
                </tr>
              </table>
            </div>
            
            <div class="form-group" id="divbulk" style="display:none;">
                  <label for="exampleInputFile">File input</label>
                  <%-- //<form:input path="file" type="file" id="exampleInputFile"/>   --%>  
                   <input  type="file" name="file" accept=".xls,.xlsx" id="exampleInputFile"/>              
               </div> 
               
             
              <!-- /.box-body -->

              <div class="box-footer">
                <button type="submit" class="btn btn-primary" name="unifiedsubmit">Submit</button>
              </div> 
            <!-- </form> -->
            </div>
             </div>
            </form:form>
          </div>
          </div>
          </div>
           

    
          
          
          
          </section>
          
          
         
    
    
</div> 

<%-- <%@include file="footer.jsp" %>  --%>
</div>


</body>
</html>
