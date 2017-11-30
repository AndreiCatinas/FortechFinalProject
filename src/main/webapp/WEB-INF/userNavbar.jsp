<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Nav</title>
</head>

<body>
	<nav class="navbar navbar-inverse" style="background-color: #179096;">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/home">Home</a>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<sec:authorize access="!isAuthenticated()">
				<li><a href="/login"><span class="glyphicon glyphicon-user"></span>
						Sign In</a></li>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<li><a href=""><span class="glyphicon glyphicon-user"></span>
						<sec:authentication property="principal.username" /></a></li>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<li><a href="/logout"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			</sec:authorize>
		</ul>
	</div>
	</nav>
</body>
</html>