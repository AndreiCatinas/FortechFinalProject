<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<head>
<title>Nav</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<sec:authentication var="user" property="principal" />

<div class="col-sm-2 sidenav">

	<ul class="nav nav-pills nav-stacked">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li><a class="btn-info" href="/employees/active">Employees</a></li>
			<li><a class="btn-info" href="/products/available">Products</a></li>
			<li><a class="btn-info" href="/bookings/all">Bookings</a></li>
			<li><a class="btn-info" href="/users/active">Users</a></li>
		</sec:authorize>

		<sec:authorize access="hasRole('ROLE_USER')">
			<li><a class="btn-info" href="/employees/active">Employees</a></li>
			<li><a class="btn-info" href="/products/available">Products</a></li>
			<li><a class="btn-info" href="/bookings/${user.username}/active">Bookings</a></li>
		</sec:authorize>


	</ul>
	<br>
</div>