<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>User Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background: #eee !important;">


	<div>
		<jsp:include page="../userNavbar.jsp"></jsp:include>
	</div>
	<c:url var="update" value="/users/update/{id}" />
	<c:url var="goBack" value="/users/all" />

	<div class="container-fluid">
		<div class="row content">
			<jsp:include page="../navBar.jsp" />

			<div class="col-sm-10">
				<div class="col-sm-6 col-sm-offset-2">
					<div class="form">
						<div style="text-align: right">
							<h3>Edit User</h3>
						</div>
						<form:form commandName="user" action="${update}" method="POST"
							id="editUser">
							<div class="form-group">
								<label class="control-label">ID</label>
								<form:input readonly="true" type="text" path="id"
									class="form-control" required="required" />
							</div>

							<div class="form-group">
								<label class="control-label">Date Created</label>
								<form:input readonly="true" type="text" path="createDate"
									class="form-control" required="required" />
							</div>

							<div class="form-group">
								<label class="control-label">Username</label>
								<form:input type="text" path="username" class="form-control"
									required="required" />
							</div>

							<div class="form-group">
								<label class="control-label">Role</label>
								<form:select path="role" class="form-control"
									required="required">
									<form:option value="ROLE_USER" label="USER" />
									<form:option value="ROLE_ADMIN" label="ADMIN" />
									<form:option value="ROLE_INACTIVE" label="INACTIVE" />
								</form:select>
							</div>

							<input type="submit" value="Save User" class="btn btn-success" />
							<a href="${goBack}" class="btn btn-info" role="button">Go
								back</a>

						</form:form>

					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>