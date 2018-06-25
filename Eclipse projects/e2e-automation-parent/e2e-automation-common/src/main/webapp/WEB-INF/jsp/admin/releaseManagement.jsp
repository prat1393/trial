<%@ page import ="java.util.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

function showApplication() {
	var e = document.getElementById("releaseList");
	var release = e.options[e.selectedIndex].value;
	$.ajax({
	type: "POST",
	url: "/e2e/retrieveApplication",
	data: "release=" + release,
	success: function(response){
	  $('#applicationdiv').show();
	  $('#backenddiv').hide();
	  $('#backendpropertiesdiv').hide();
	  $('#addreleasediv').hide();
	  $('#addedreleasediv').hide();
	  $('#deletereleasediv').hide();
	  $('#deletedreleasediv').hide();	  
	  $('#applicationPropertiesKeyDiv').hide();
	  $('#applicationPropertiesValueDiv').hide();
	  $('#applicationList').empty();
	  $('#applicationList').append('<option value="app">Select Application</option>');
	 for (i=0; i<response.length; i++) {
        $('#applicationList').append('<option value="' + response[i] + '">' + response[i] + '</option>');
      }
                
	},
 	error: function(e){
	alert('Error: ' + e);
 	}
 	});
}
function showApplicationBackends() {
	var e = document.getElementById("applicationList");
	var app = e.options[e.selectedIndex].value;
	var e = document.getElementById("releaseList");
	var release = e.options[e.selectedIndex].value;
	if(app=="DB_CONFIGURATION"){		
		$.ajax({
		type: "POST",
		url: "/e2e/retrieveDBBackends",
		data: "release=" + release,
		success: function(response){
		  $('#backenddiv').show();
		  $('#backendpropertiesdiv').hide();
		  $('#addreleasediv').hide();
		  $('#addedreleasediv').hide();
		  $('#applicationPropertiesKeyDiv').hide();
		  $('#applicationPropertiesValueDiv').hide();
		  
		  $('#backendList').empty();
		  $('#backendList').append('<option value="db">Select DB</option>');
		 for (i=0; i<response.length; i++) {
	        $('#backendList').append('<option value="' + response[i] + '">' + response[i] + '</option>');
	      }
	                
		},
	 	error: function(e){
		alert('Error: ' + e);
	 	}
	 	});
	}
	else if(app=="UVERSE") {
		$.ajax({
		type: "POST",
		url: "/e2e/retrieveUverseProperties",
		data: "release=" + release,
		success: function(response){
		$('#applicationPropertiesKeyDiv').show();	
		  $('#backenddiv').hide();
		  $('#backendpropertiesdiv').hide();
		  $('#addreleasediv').hide();
		  $('#addedreleasediv').hide();
		  $('#applicationPropertiesValueDiv').hide();
		  $('#applicationPropertiesKeyList').empty();
		  $('#applicationPropertiesKeyList').append('<option value="uverse">Select Uverse Properties</option>');
		 for (i=0; i<response.length; i++) {
	        $('#applicationPropertiesKeyList').append('<option value="' + response[i] + '">' + response[i] + '</option>');
	      }
	                
		},
	 	error: function(e){
		alert('Error: ' + e);
	 	}
	 	});
	 } 
	else if(app=="CAMP"){
		$.ajax({
			type: "POST",
			url: "/e2e/retrieveCAMPProperties",
			data: "release=" + release,
			success: function(response){
			  $('#applicationPropertiesKeyDiv').show();
			  $('#backenddiv').hide();
			  $('#backendpropertiesdiv').hide();
			  $('#addreleasediv').hide();
			  $('#addedreleasediv').hide();
			  $('#applicationPropertiesValueDiv').hide();
			  $('#applicationPropertiesKeyList').empty();
			  $('#applicationPropertiesKeyList').append('<option value="camp">Select CAMP Properties</option>');
			 for (i=0; i<response.length; i++) {
		        $('#applicationPropertiesKeyList').append('<option value="' + response[i] + '">' + response[i] + '</option>');
		      }
		                
			},
		 	error: function(e){
			alert('Error: ' + e);
		 	}
		 	});		
		}	
	else{
		alert("Fail");
	}
	}
	function showDBProperties(){
		var e = document.getElementById("releaseList");
		var release = e.options[e.selectedIndex].value;
		var e1 = document.getElementById("backendList");
		var backend = e1.options[e1.selectedIndex].value;
		$.ajax({
		type: "POST",
		url: "/e2e/ajaxshowproperties",
		/* data: "release=" + release +"&backend"+backend, */
		data: "release=" + release +"&backend="+backend,
		success: function(response){
		  $('#backendpropertiesdiv').show();	
		  $('#addreleasediv').hide();
		  $('#addedreleasediv').hide();
		  $('#backendpropertiesList').empty();
		 for (i=0; i<response.length; i++) {
	        $('#backendpropertiesList').append('<td><input style="border:none;" value="' + response[i] + '"/></td>');
	      } 
	                
		},
	 	error: function(e){
		alert('Error: ' + e);
	 	}
	 	});
	}
	
	function retrieveApplicationProperties(){
		var e = document.getElementById("releaseList");
		var release = e.options[e.selectedIndex].value;
		var e1 = document.getElementById("applicationPropertiesKeyList");
		var backend = e1.options[e1.selectedIndex].value;
		var e2 = document.getElementById("applicationList");
		var app = e2.options[e2.selectedIndex].value;
		$.ajax({
		type: "POST",
		url: "/e2e/retrieveApplicationProperties",
		data: "release=" + release +"&backend="+backend+"&app="+app,
		success: function(response){
		  $('#applicationPropertiesValueDiv').show();	
		  $('#addreleasediv').hide();
		  $('#addedreleasediv').hide();
		  $('#applicationPropertiesValue').empty();
		 $('#applicationPropertiesValue').append('<input style="border:none;" value="' + response + '"/>');
	                
		},
	 	error: function(e){
		alert('Error: ' + e);
	 	}
	 	});
	}
	
	function showAddRelease() {
		  $('#addreleasediv').show();
		  $('#newRelease').val('');
		  $('#applicationdiv').hide();
		  $('#backendpropertiesdiv').hide();
		  $('#backenddiv').hide();
		  $('#addedreleasediv').hide();
		  $('#deletereleasediv').hide();
		  $('#deletedreleasediv').hide();
		  $('#applicationPropertiesValueDiv').hide();
		  $('#applicationPropertiesKeyDiv').hide();
	}
	
	function addRelease() {
		var e = document.getElementById("presentReleaseList");
		var presentRelease = e.options[e.selectedIndex].value;
		var newRelease=document.getElementById("newRelease").value;
		$.ajax({
		type: "POST",
		url: "/e2e/addRelease",
		data: "newRelease=" + newRelease +"&presentRelease="+presentRelease,
		success: function(response){
		  $('#addedreleasediv').show();
		  $('#backendpropertiesdiv').hide();
		  $('#addreleasediv').hide();	  
	                
		},
	 	error: function(e){
		alert('Error: ' + e);
	 	}
	 	});
	}
	
	function showDeleteRelease() {
		  $('#deletereleasediv').show();
		  $('#newRelease').val('');
		  $('#applicationdiv').hide();
		  $('#backendpropertiesdiv').hide();
		  $('#backenddiv').hide();
		  $('#addedreleasediv').hide();
		  $('#addreleasediv').hide();
		  $('#applicationPropertiesValueDiv').hide();
		  $('#applicationPropertiesKeyDiv').hide();
	}
	
	function deleteRelease() {
		var e = document.getElementById("deleteReleaseList");
		var release = e.options[e.selectedIndex].value;
		$.ajax({
		type: "POST",
		url: "/e2e/deleteRelease",
		data: "release=" + release,
		success: function(response){
		  $('#deletedreleasediv').show();
		  $('#backendpropertiesdiv').hide();
		  $('#addreleasediv').hide();	  
	                
		},
	 	error: function(e){
		alert('Error: ' + e);
	 	}
	 	});
	}
	
	function addProperty() {
		var e = document.getElementById("releaseList");
		var release = e.options[e.selectedIndex].value;
		var e1 = document.getElementById("applicationList");
		var application = e1.options[e1.selectedIndex].value;
		var propertyKey=document.getElementById("propertyKey").value;
		var propertyValue=document.getElementById("propertyValue").value;
		$.ajax({
		type: "POST",
		url: "/e2e/addProperty",
		data: "release="+release+"&application=" + application +"&propertyKey="+propertyKey+"&propertyValue="+propertyValue,
		success: function(response){
		  $('#addedpropertydiv').show();  
	                
		},
	 	error: function(e){
		alert('Error: ' + e);
	 	}
	 	});
	}
	</script>
 </head>
<body>
<!-- <div class="wrapper"> -->

 <%@include file="../template.jsp" %> 

<div class="content-wrapper"> 
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Admin Page
        <small>it all starts here</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Dashboard</a></li>
        <li class="active">Admin</li>
      </ol>
    </section>
    
    <section class="content">
      <div class="row">
        <div class="col-md-9">
          <div class="box box-primary">            
              <div class="box-body">
              	<div class="form-group">              	         	
                	<label>Release</label>
					<select class="form-control select2" style="width: 50%;" id="releaseList" onchange="showApplication()">    
						<option value="release">Select Release</option>
						<c:forEach items="${releaseList}" var="list">
        					<option value="${list}">${list}</option>
    					</c:forEach>
					</select>
              	</div>              	            
			 </div> 
          </div>
          </div>
       </div>     
     
     <div class="row" id="applicationdiv" hidden="true">      
        <div class="col-md-9">
          <div class="box box-primary">            
              <div class="box-body">
              	<div class="form-group">              	         	
                	<label>Application</label>
                	<select class="form-control select2" style="width: 50%;" id="applicationList" onchange="showApplicationBackends()">
                  	</select>               
              	</div>            
			 </div>
          </div>
          </div>
      </div>      
     
    <div class="row" id="backenddiv" hidden="true">      
        <div class="col-md-9">
          <div class="box box-primary">            
              <div class="box-body">
              	<div class="form-group">              	         	
                	<label>DB Configuration Properties</label>
                	<select class="form-control select2" style="width: 50%;" id="backendList" onchange="showDBProperties()">
                  	</select>  
                  	<br>       
                  	<button type="submit" class="btn btn-primary" name="updateproperties">Add DB</button>
                	<button type="submit" class="btn btn-primary" name="updateproperties">Delete DB</button>              
              	</div>   
              	 <div class="row" id="backendpropertiesdiv" hidden="true">
        			<div class="col-md-9">
          				<div class="box box-primary">      
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
					 					<tr id="backendpropertiesList">
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
			 </div> 
          </div>
        </div>
      </div>     
      
	 <div class="row" id="applicationPropertiesKeyDiv" hidden="true">      
        <div class="col-md-9">
          <div class="box box-primary">            
              <div class="box-body">
              	<div class="form-group">              	         	
                	<label>Application Properties</label>
                	<select class="form-control select2" style="width: 50%;" id="applicationPropertiesKeyList" onchange="retrieveApplicationProperties()">
                  	</select>   
                  	<br>       
                  	<button type="submit" class="btn btn-primary" name="updateproperties">Add Properties</button>
                	<button type="submit" class="btn btn-primary" name="updateproperties">Delete Properties</button>     
              	</div>   
              	 <div class="row" id="applicationPropertiesValueDiv" hidden="true">
        			<div class="col-md-9">
         				<div class="box box-primary">      
            				<div class="box-body">
            					<div id="applicationPropertiesValue">
            					</div>
           					 </div>
            				<div class="box-footer">
                				<button type="submit" class="btn btn-primary" name="updateproperties">Update Properties</button>
               				</div> 
            			</div>
          			</div>
     		 	</div>
			 </div> 
          </div>
        </div>
      </div>  
      
       
    
      
</section>
    
    
    
</div> 


<!-- </div> -->


</body>
</html>
