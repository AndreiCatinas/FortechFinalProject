<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>New Employee</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
.error {
	color: red;
	text-align: center;
}
</style>
<body>

	<c:url var="save" value="/employees/add" />
	<c:url var="back" value="/employees/all" />

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
								<strong>New Employee</strong>
							</h3>
						</div>

						<form:form id="saveEmployee" commandName="employee"
							action="${save}" method="POST">
							<div class="form-group">
								<form:label path="firstname">Firstname:</form:label>
								<form:input required="required" type="text" path="firstname"
									class="form-control " />
								<form:errors path="firstname" cssClass="error" />
							</div>

							<div class="form-group">
								<form:label path="lastname">Lastname:</form:label>
								<form:input path="lastname" required="required"
									class="form-control" rows="1" />
								<form:errors path="lastname" cssClass="error" />
							</div>

							<div class="form-group">
								<form:label path="department">Department:</form:label>
								<form:input path="department" required="required"
									class="form-control" rows="1" />
								<form:errors path="department" cssClass="error" />
							</div>

							<div class="form-group">
								<form:label path="phone">Phone:</form:label>
								<form:input path="phone" class="form-control" rows="1" />
								<form:errors path="phone" cssClass="error" />
							</div>

							<div class="form-group">
								<form:label path="email">Email:</form:label>
								<form:input path="email" required="required"
									class="form-control" rows="1" />
								<form:errors path="email" cssClass="error" />
							</div>

							<input type="submit" value="Save" class="btn btn-success" />
						</form:form>

						<a href="${back}" class="btn btn-info">Back</a>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
