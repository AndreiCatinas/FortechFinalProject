<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Employee</title>
</head>
<body>

	<div>
		<form:form commandName="employee">
			<form:textarea path="firstname" rows="4" columns="30" />
			<form:textarea path="lastname" rows="4" columns="30" />
			<form:textarea path="department" rows="4" columns="30" />
			<form:textarea path="phone" rows="4" columns="30" />
			<form:textarea path="email" rows="4" columns="30" />
		</form:form>
	</div>
	<input type="submit" name = "submit" value = "Submit" />

</body>
</html>