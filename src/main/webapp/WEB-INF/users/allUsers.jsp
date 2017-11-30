<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<html>
<head>
<title>Users</title>
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
	<c:url var="addAction" value="/users/add/" />

	<div class="container-fluid">
		<div class="row content">
			<jsp:include page="../navBar.jsp" />

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a class="btn btn-info" href="<c:url value='/users/all' />">List
					all Users</a>
				<a class="btn btn-info" href="<c:url value='/users/active' />">List
					all active Users</a>
				<a class="btn btn-info" href="<c:url value='/users/inactive' />">List
					all inactive Users</a>
			</sec:authorize>

			<div class="col-sm-10">
				<c:if test="${empty users}">
					<br>
					<div class="alert alert-info">
						<strong>Info!</strong> No Users.
					</div>
				</c:if>

				<br> <a href="${addAction}" class="btn btn-primary">New
					User</a>

				<c:if test="${not empty users}">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Id</th>
								<th>Username</th>
								<th>Create Date</th>
								<th>Role</th>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<th>Edit</th>
									<th>Delete</th>
								</sec:authorize>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="user" items="${users}">
								<tr>
									<td>${user.id}</td>
									<td>${user.username}</td>
									<td>${user.createDate}</td>
									<td>${user.role}</td>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<td><a href="<c:url value='/users/update/${user.id}' />">
												<span class="glyphicon glyphicon-pencil"></span>
										</a></td>

										<td><a href="<c:url value='/users/delete/${user.id}' />">
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
