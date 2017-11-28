<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Employee Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.error {
	color: red;
	text-align
	=
	center;
}
</style>
</head>
<body>

	<div>
		<jsp:include page="../userNavbar.jsp"></jsp:include>
	</div>
	<c:url var="update" value="/employees/update/{id}" />
	<c:url var="goBack" value="/employees/all" />

	<div class="container-fluid">
		<div class="row content">
			<jsp:include page="../navBar.jsp" />

			<div class="col-sm-10">
				<div class="col-sm-6 col-sm-offset-2">
					<div class="form">
						<div style="text-align: right">
							<h3>Edit Employee</h3>
						</div>
						<form:form commandName="employee" action="${update}" method="POST"
							id="editEmployee">
							<div class="form-group">
								<label class="control-label">ID</label>
								<form:input readonly="true" type="text" path="id"
									class="form-control" required="required" />
							</div>

							<div class="form-group">
								<label class="control-label">First Name</label>
								<form:input type="text" path="firstname" class="form-control"
									required="required" />
								<form:errors path="firstname" cssClass="error" />
							</div>

							<div class="form-group">
								<label class="control-label">Last name</label>
								<form:input path="lastname" class="form-control"
									required="required" />
								<form:errors path="lastname" cssClass="error" />
							</div>

							<div class="form-group">
								<label class="control-label">Department</label>
								<form:input path="department" class="form-control"
									required="required" />
								<form:errors path="department" cssClass="error" />
							</div>

							<div class="form-group">
								<label class="control-label">Phone</label>
								<form:input path="phone" class="form-control"
									required="required" />
								<form:errors path="phone" cssClass="error" />
							</div>

							<div class="form-group">
								<label class="control-label">Email</label>
								<form:input path="email" class="form-control"
									required="required" />
								<form:errors path="email" cssClass="error" />
							</div>

							<div class="form-group">
								<label class="control-label">Active</label>
								<form:select path="active">
									<form:option value="true" label="Active" />
									<form:option value="false" label="Inactive" />
								</form:select>
							</div>

							<input type="submit" value="Save employee"
								class="btn btn-success" />
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