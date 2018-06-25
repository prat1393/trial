<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="/js/myapp.js"></script>
<style>
.example-modal .modal {
	position: relative;
	top: auto;
	bottom: auto;
	right: auto;
	left: auto;
	display: block;
	z-index: 1;
}

.example-modal .modal {
	background: transparent !important;
}
</style>
</head>
<body>

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
						<div class="box-footer">
							<button type="submit" class="btn btn-default" name="add"
								onclick="showAddRelease()">Add Release</button>
							<button type="submit" class="btn btn-danger" name="delete"
								onclick="showDeleteRelease()">Delete Release</button>
						</div>
					</div>
				</div>
			</div>

			<div class="row" id="applicationdiv" hidden="true">
				<div class="col-md-9">
					<div class="box box-primary">
						<div class="box-body">
							<div class="form-group">
								<label>Application</label> <select class="form-control select2"
									style="width: 50%;" id="applicationList"
									onchange="showApplicationBackends()">
								</select>
							</div>
						</div>
						<div class="box-footer">
							<button type="button" class="btn btn-default" data-toggle="modal"
								data-target="#modal-addApplication">Add Application</button>
							<button type="button" class="btn btn-danger" data-toggle="modal"
								data-target="#modal-deleteApplication">Delete Application</button>
						</div>
					</div>
				</div>
			</div>


			<div class="row" id="addreleasediv" hidden="true">
				<div class="col-md-9">
					<div class="box box-primary">
						<div class="box-body">
							<div class="form-group">
								<label>Enter New Release</label> <input id="newRelease"
									type="number" min="1800" max="2050" class="form-control"
									placeholder="Enter release" style="width: 50%;" /> <label>Copy
									Properties From Release</label> <select class="form-control select2"
									style="width: 50%;" id="presentReleaseList">
									<c:forEach items="${releaseList}" var="list">
										<option value="${list}">${list}</option>
									</c:forEach>
								</select>
							</div>

							<div class="box-footer">
								<button type="submit" class="btn btn-default" name="submit"
									onclick="addRelease()">Submit</button>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-9" id="addedreleasediv" hidden="true">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<h4>
						<i class="icon fa fa-check"></i> Success!
					</h4>
					Added Release Successfully...
				</div>
			</div>
			
			<div class="row" id="deletereleasediv" hidden="true">
				<div class="col-md-9">
					<div class="box box-primary">
						<div class="box-body">
							<div class="form-group">
								<label>Select Release to delete</label> <select
									class="form-control select2" style="width: 50%;"
									id="deleteReleaseList">
									<c:forEach items="${releaseList}" var="list">
										<option value="${list}">${list}</option>
									</c:forEach>
								</select>
							</div>

							<div class="box-footer">
								<button type="submit" class="btn btn-default" name="submit"
									onclick="deleteRelease()">Submit</button>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-9" id="deletedreleasediv" hidden="true">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<h4>
						<i class="icon fa fa-check"></i> Success!
					</h4>
					Deleted Release Successfully...
				</div>
			</div>

			<div class="row" id="backenddiv" hidden="true">
				<div class="col-md-9">
					<div class="box box-primary">
						<div class="box-body">
							<div class="form-group">
								<label>DB Configuration Properties</label> <select
									class="form-control select2" style="width: 50%;"
									id="backendList" onchange="retrieveDBProperties()">
								</select>
							</div>
						</div>
					</div>
				</div>
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
							<button type="submit" class="btn btn-primary"
								name="testconnection">Test Connection</button>
							<button type="submit" class="btn btn-primary"
								name="updateproperties">Update Properties</button>
						</div>
					</div>
				</div>
			</div>

			<div class="row" id="applicationPropertiesKeyDiv" hidden="true">
				<div class="col-md-9">
					<div class="box box-primary">
						<div class="box-body">
							<div class="form-group">
								<label>Application Properties</label> <select
									class="form-control select2" style="width: 50%;"
									id="applicationPropertiesKeyList"
									onchange="retrieveApplicationProperties()">
								</select>
							</div>
						</div>
						<div class="box-footer">
							<button type="button" class="btn btn-default" data-toggle="modal"
								data-target="#modal-addProperty" onmouseover="hidesuccess()">Add
								Property</button>
							<button type="button" class="btn btn-danger" data-toggle="modal"
								data-target="#modal-deleteProperty">Delete Property</button>
						</div>
					</div>
				</div>
			</div>

			<div class="row" id="applicationPropertiesValueDiv" hidden="true">
				<div class="col-md-9">
					<div class="box box-primary">
						<div class="box-body">
							<div id="applicationPropertiesValue"></div>
						</div>
						<div class="box-footer">
							<button type="submit" class="btn btn-primary"
								name="updateproperties">Update Properties</button>
						</div>
					</div>
				</div>
			</div>
		
			<div class="modal fade" id="modal-addProperty">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Add Property</h4>
				</div>
				<div class="modal-body">
					Property Name <input id="propertyKey" style="color: black;"
						placeholder="Key" /> Example: ABCXYZ<br>
					<br> Property Value <input id="propertyValue"
						style="color: black;" placeholder="Value" />
					<div id="successAddProperty" hidden="true">Added Property Successfully!!!!!!!!</div>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						onclick="addProperty()">Save changes</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<div class="modal modal-danger fade" id="modal-deleteProperty">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Danger Modal</h4>
				</div>
				<div class="modal-body">
					<p>One fine body&hellip;</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-outline pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-outline">Save changes</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

<div class="modal fade" id="modal-addApplication">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Add Application</h4>
				</div>
				<div class="modal-body">
					Application Name <input id="applicationKey" style="color: black;"
						placeholder="applicationName" /> Example: UVERSE, WIRELESS<br>
						<div id="successAddApplication" hidden="true">Added Application Successfully!!!!!!!!</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						onclick="addApplication()">Save changes</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<div class="modal modal-danger fade" id="modal-deleteApplication">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Danger Modal</h4>
				</div>
				<div class="modal-body">
					<p>One fine body&hellip;</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-outline pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-outline">Save changes</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
		</section>
	</div>

	

</body>
</html>
