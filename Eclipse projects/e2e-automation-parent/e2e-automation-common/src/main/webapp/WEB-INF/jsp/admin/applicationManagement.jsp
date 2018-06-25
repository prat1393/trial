<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
	function showApplication() {
		var e = document.getElementById("releaseList");
		var release = e.options[e.selectedIndex].value;
		$.ajax({
			type : "POST",
			url : "/e2e/retrieveApplication",
			data : "release=" + release,
			success : function(response) {
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
				$('#applicationList').append(
						'<option value="app">Select Application</option>');
				for (i = 0; i < response.length; i++) {
					$('#applicationList').append(
							'<option value="' + response[i] + '">'
									+ response[i] + '</option>');
				}

			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function updateApplicationStatus() {
		alert("Hi");
		/* var TableData = new Array();
		$('#tableid').each(function(row, tr) {
			 TableData = TableData + $(tr).find('td:eq(0)').text() + ' ' // Task No.
					+ $(tr).find('td:eq(1)').text() + ' ' // Date
					+ '\n'; 
		});
		alert(TableData); */
		var table = document.getElementById('tableid');
	    for (var r = 0, n = table.rows.length; r < n; r++) {
	        for (var c = 0, m = table.rows[r].cells.length; c < m; c++) {
	            alert(table.rows[r].cells[c].innerHTML);
	        }
	    }
	}
</script>

<!-- <script src="/js/bootstrap-toggle.min.js"></script>
<link rel="stylesheet" href="/bootstrap-toggle.min.css"> -->
<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap2-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap2-toggle.min.js"></script>
</head>
<body>
	<!-- <div class="wrapper"> -->

	<%@include file="../template.jsp"%>

	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Admin Page <small>it all starts here</small>
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
								<label>Release</label> <select class="form-control select2"
									style="width: 50%;" id="releaseList"
									onchange="showApplication()">
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

			<div class="row">
				<div class="col-md-9">
					<div class="box box-primary">
						<div class="box-body">
							<div class="form-group">
								<label>Release</label>
								<table id="tableid" class="table table-bordered table-striped">
									<!-- <thead>
										<tr>
											<th>App Name</th>
											<th>Status</th>
										</tr>
									</thead>
									<tbody> -->
										<c:forEach items="${appMap}" var="map">
											<tr>
												<c:if test="${map.value == 'true'}">
													<td>${map.key}</td>
													<td><input type="checkbox" data-toggle="toggle"
														data-on="Enabled" data-off="Disabled" checked></td>
												</c:if>
												<c:if test="${map.value == 'false'}">
													<td>${map.key}</td>
													<td><input id="toggle-event" type="checkbox"
														data-toggle="toggle"></td>
												</c:if>
										</c:forEach>
									<!-- </tbody> -->
								</table>
								<button type="submit" class="btn btn-default" name="add"
									onclick="updateApplicationStatus()">Submit</button>
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
