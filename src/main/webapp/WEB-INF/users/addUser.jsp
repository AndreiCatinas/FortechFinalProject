<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>New User</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<c:url var="save" value="/users/add/${employee}" />
	<c:url var="back" value="/users/active" />

	<div>
		<jsp:include page="../userNavbar.jsp"></jsp:include>
	</div>

	<div class="container-fluid">
		<div class="row content">
			<jsp:include page="../navBar.jsp" />

			<div class="col-sm-10">
				<div class="col-sm-6 col-sm-offset-2">
					<div class="form">
						<div style="text-align: right">
							<h3>
								<strong>New User</strong>
							</h3>
						</div>


						<form:form id="saveUser" commandName="user" action="${save}"
							method="POST">

							<div class="form-group">
								<form:label path="username">Username:</form:label>
								<form:input required="required" type="text" path="username"
									class="form-control " />
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

							<input type="submit" value="Save" class="btn btn-success" />
							<a href="${back}" class="btn btn-info">Back</a>
						</form:form>


					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
