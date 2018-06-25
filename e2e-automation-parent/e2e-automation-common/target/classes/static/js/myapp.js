
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


function showApplicationBackends() {
	var e = document.getElementById("applicationList");
	var app = e.options[e.selectedIndex].value;
	var e = document.getElementById("releaseList");
	var release = e.options[e.selectedIndex].value;		
		$.ajax({
		type: "POST",
		url: "/e2e/retrieveApplicationBackends",
		data: "release=" + release+"&app="+app,
		success: function(response){  
			  $('#backendpropertiesdiv').hide();
			  $('#addreleasediv').hide();
			  $('#addedreleasediv').hide();		  
			  $('#applicationPropertiesValueDiv').hide();
		  if(app=="DB_CONFIGURATION"){
			  $('#backenddiv').show();
			  $('#applicationPropertiesKeyDiv').hide();
			  $('#backendList').empty();
			  $('#backendList').append('<option value="db">Select DB</option>');
			  for (i=0; i<response.length; i++) {
			        $('#backendList').append('<option value="' + response[i] + '">' + response[i] + '</option>');
			  }
		  }
		  else {
			  $('#applicationPropertiesKeyDiv').show();	
			  $('#backenddiv').hide();
			  $('#applicationPropertiesKeyList').empty();
			  $('#applicationPropertiesKeyList').append('<option value="uverse">Select Properties</option>');
			  for (i=0; i<response.length; i++) {
			        $('#applicationPropertiesKeyList').append('<option value="' + response[i] + '">' + response[i] + '</option>');
			      }
		  }
		  
	    },
	 	error: function(e){
		alert('Error: ' + e);
	 	}
	 	});	
	}
	function retrieveDBProperties(){
		var e = document.getElementById("releaseList");
		var release = e.options[e.selectedIndex].value;
		var e1 = document.getElementById("backendList");
		var backend = e1.options[e1.selectedIndex].value;
		$.ajax({
		type: "POST",
		url: "/e2e/retrieveDbProperties",
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
		 $('#applicationPropertiesValue').append('<b>Value: </b><input value="' + response + '"/>');
	                
		},
	 	error: function(e){
		alert('Error: ' + e);
	 	}
	 	});
	}
	
	function addApplication() {
		var e = document.getElementById("releaseList");
		var release = e.options[e.selectedIndex].value;
		var applicationKey=document.getElementById("applicationKey").value;
		$.ajax({
		type: "POST",
		url: "/e2e/addApplication",
		data: "release="+release+"&applicationKey=" + applicationKey,
		success: function(response){
			$('#applicationKey').val('');
			$('#successAddApplication').show();	 
		},
	 	error: function(e){
		alert('Error: ' + e);
	 	}
	 	});
	}
	
	function abc() {
		$('#successAddProperty').hide();
		
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
			$('#propertyKey').val('');
			$('#propertyValue').val('');
			$('#successAddProperty').show();
		},
	 	error: function(e){
		alert('Error: ' + e);
	 	}
	 	});
	}
	function hidesuccess() {
		$('#successAddProperty').hide();
		
	}