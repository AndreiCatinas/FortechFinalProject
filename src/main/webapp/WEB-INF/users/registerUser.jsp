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
	margin-top: auto;
	margin-bottom: auto;
	text-align: center;
}

.error {
	margin: 0 auto;
	background-color: #fff;
	border: 1px solid rgba(0, 0, 0, 0.1);
	color: red;
}

.form-signin {
	max-width: 400px;
	padding: 15px 35px 45px;
	margin: 0 auto;
	background-color: #fff;
	border: 1px solid rgba(0, 0, 0, 0.1);
}

.form-signin-heading, .checkbox {
	margin-bottom: 30px;
}

.form-control {
	font-size: 16px;
	height: auto;
	padding: 10px;
	margin-bottom: 20px;
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
<title>Register</title>
</head>
<body>

	<div>
		<jsp:include page="../userNavbar.jsp"></jsp:include>
	</div>


	<div class="wrapper">
		<div>

			<form:form class="form-signin" commandName="user" action="/register"
				method="POST">


				<h2 class="form-signin-heading">Register Form</h2>


				<form:input type="text" class="form-control" placeholder="Username"
					path="username" />

				<%-- <form:errors path="username" cssClass="error" /> --%>
				<form:input type="password" class="form-control"
					placeholder="Password" path="plainPassword" />
				<%-- <form:errors path="plainPassword" cssClass="error" /> --%>
				<form:input type="password" class="form-control"
					placeholder="Retype Password" path="repeatPassword" />

				<form:button class="btn btn-lg btn-primary btn-block" type="submit">Register</form:button>

			</form:form>

		</div>
		
	</div>
	<div style="align: center; text-align: center; ">
			<form:errors path="user.*" cssClass="error" />
		</div>
</body>
<script>
	
</script>
</html>
