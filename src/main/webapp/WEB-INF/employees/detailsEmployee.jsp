
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<html>
<head>
<title>Employee</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body style="background: #eee !important;">
	>

	<div>
		<jsp:include page="../userNavbar.jsp"></jsp:include>
	</div>
	<c:url var="goBack" value="/employees/all" />

	<div class="container-fluid">
		<div class="row content">
			<jsp:include page="../navBar.jsp" />

			<div class="col-sm-10">
				<div class="col-sm-6 col-sm-offset-2">
					<div class="form">
						<h3>Employee details</h3>
						<table class="table table-striped table-hover">
							<tr>
								<td>ID</td>
								<td>${employee.id}</td>
							</tr>
							<tr>
								<td>First Name:</td>
								<td>${employee.firstname}</td>
							</tr>
							<tr>
								<td>Last Name:</td>
								<td>${employee.lastname}</td>
							</tr>
							<tr>
								<td>Department:</td>
								<td>${employee.department}</td>
							</tr>
							<tr>
								<td>Phone:</td>
								<td>${employee.phone}</td>
							</tr>
							<tr>
								<td>Email:</td>
								<td>${employee.email}</td>
							</tr>

							<tr>
								<td>Active</td>
								<c:if test="${employee.active = true}">
									<td>Active employee</td>
								</c:if>
								<c:if test="${employee.active = false}">
									<td>Inactive employee</td>
								</c:if>
							</tr>
						</table>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<h5>User details</h5>
							<table class="table table-striped table-hover">
								<c:if test="${empty user }">
									<tr>
										<td>No User</td>
										<td><a href="/users/add/${employee.id}">Create user</a></td>
									</tr>
								</c:if>
								<c:if test="${not empty user}">


									<tr>
										<td>Username:</td>
										<td>${user.username}</td>
									</tr>
									<tr>
										<td>Created:</td>
										<td>${user.createDate}</td>
									</tr>
									<tr>
										<td>User Role:</td>
										<td>${user.role}</td>
									</tr>

								</c:if>

							</table>
						</sec:authorize>
						<a href="${goBack}" class="btn btn-primary">Go Back</a>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
