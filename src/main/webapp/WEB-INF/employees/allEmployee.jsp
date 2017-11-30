<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<html>
<head>
<title>Employees</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body style="background: #eee !important;">


	<div>
		<jsp:include page="../userNavbar.jsp"></jsp:include>
	</div>
	<c:url var="addAction" value="/employees/add" />

	<div class="container-fluid">
		<div class="row content">
			<jsp:include page="../navBar.jsp" />

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a class="btn btn-info" href="<c:url value='/employees/all' />">List
					all Employees</a>
				<a class="btn btn-info" href="<c:url value='/employees/active' />">List
					all active Employees</a>
				<a class="btn btn-info" href="<c:url value='/employees/inactive' />">List
					all inactive Employees</a>
			</sec:authorize>

			<div class="col-sm-10">
				<c:if test="${empty employees}">
					<br>
					<div class="alert alert-info">
						<strong>Info!</strong> No employees.
					</div>
				</c:if>

				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<br>
					<a href="${addAction}" class="btn btn-primary">New Employee</a>
				</sec:authorize>

				<c:if test="${not empty employees}">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Id</th>
								<th>Firstname</th>
								<th>Lastname</th>
								<th>Department</th>
								<th>Phone</th>
								<th>Email</th>
								<th>Details</th>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<th>Edit</th>
									<th>Delete</th>
								</sec:authorize>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="employee" items="${employees}">
								<tr>
									<td>${employee.id}</td>
									<td>${employee.firstname}</td>
									<td>${employee.lastname}</td>
									<td>${employee.department}</td>
									<td>${employee.phone}</td>
									<td>${employee.email}</td>
									<td><a
										href="<c:url value='/employees/details/${employee.id}' />">
											<span class="glyphicon glyphicon-user"></span>
									</a></td>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<td><a
											href="<c:url value='/employees/update/${employee.id}' />">
												<span class="glyphicon glyphicon-pencil"></span>
										</a></td>

										<td><a
											href="<c:url value='/employees/delete/${employee.id}' />">
												<span class="glyphicon glyphicon-trash city-label"></span>
										</a></td>
									</sec:authorize>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</c:if>
			</div>
		</div>

	</div>
</body>
</html>
