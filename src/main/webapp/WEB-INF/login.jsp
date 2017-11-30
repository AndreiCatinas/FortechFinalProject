<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
body {
	background: #eee !important;
}

.wrapper {
	margin-top: 80px;
	margin-bottom: 80px;
}

.error {
	color: red;
	text-align: center;
}

.form-signin {
	display: block;
	justify-content: space-between;
	max-width: 380px;
	padding: 15px 35px 45px;
	margin: 0 auto;
	background-color: #fff;
	border: 1px solid rgba(0, 0, 0, 0.1);
	justify-content: space-between;
}

.form-signin-heading, .checkbox {
	margin-bottom: 30px;
}

.form-control {
	margin-bottom: 20px;
	position: relative;
	font-size: 16px;
	height: auto;
	padding: 30px;
}

}
input[type="text"] {
	margin-bottom: 20px;
	border-radius: 5;
}

input[type="password"] {
	margin-bottom: 20px;
	border-radius: 5;
}
}
</style>
<title>Login</title>
</head>
<body>
	<div>
		<jsp:include page="userNavbar.jsp"></jsp:include>
	</div>


	<div class="wrapper">

		<form:form class="form-signin" commandName="user" action="/login"
			method="POST">
			<div class="panel error">
				<c:if test="${param.error  != null}">
					<div>Incorrect username or password!</div>
				</c:if>
			</div>
			<h2 class="form-signin-heading">Please login</h2>
			<form:input type="text" class="form-control" name="username"
				placeholder="Email Address" path="username" />
			<form:input type="password" class="form-control" name="password"
				placeholder="Password" path="password" />
			<a href="/register" style="text-align: left;">Register</a>

			<a href="/recover" style="float: right; clear: both;">Forgot your
				password?</a>
			<form:button class="btn btn-lg btn-primary btn-block" type="submit">Login</form:button>
		</form:form>
	</div>
</body>
<script>
	
</script>
</html>
