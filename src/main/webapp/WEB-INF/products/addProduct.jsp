<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>New Product</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<style>
.error {
	color: red;
	text-align: center;
}
</style>
<body style="background: #eee !important;">

	<div>
		<jsp:include page="../userNavbar.jsp"></jsp:include>
	</div>


	<c:url var="save" value="/products" />
	<c:url var="back" value="/products/all" />

	<div class="container-fluid">
		<div class="row content">
			<jsp:include page="../navBar.jsp" />

			<div class="col-sm-10">
				<div class="col-sm-6 col-sm-offset-2">
					<div class="form">
						<div style="text-align: right">
							<h3>
								<strong>New Product</strong>
							</h3>
						</div>

						<form:form id="saveProduct" commandName="productDto"
							action="${save}" method="POST">

							<div class="form-group">
								<label class="control-label">Category</label>
								<form:select path="category" id="c"
									onChange="changeCat(this.value)"
									class="custom-select form-control">
									<form:option value="" label="--- Select ---" />
								</form:select>

								<label class="control-label">Model</label>
								<form:select path="model" id="m"
									class="custom-select form-control">
									<form:option value="" label="--- Select ---" />
								</form:select>
							</div>

							<div class="form-group">
								<form:label path="name">Name:</form:label>
								<form:input required="required" type="text" path="name"
									class="form-control " />
								<form:errors path="name" cssClass="error" />
							</div>

							<div class="form-group">
								<form:label path="serialNumber">Serial Number:</form:label>
								<form:input path="serialNumber" class="form-control" rows="1" />
								<form:errors path="serialNumber" cssClass="error" />
							</div>

							<div class="form-group">
								<form:label path="aquisitionDate">Aquisition Date:</form:label>
								<form:input required="required" type="date"
									path="aquisitionDate" class="form-control" />
								<form:errors path="aquisitionDate" cssClass="error" />
							</div>

							<div class="form-group">
								<form:label path="description">Description:</form:label>
								<form:textarea path="description" class="form-control" rows="1" />
							</div>

							<input type="submit" value="Save" class="btn btn-success" />
							<a href="${back}" class="btn btn-info">Back</a>
						</form:form>


					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			var category = JSON
					.parse('<c:out value="${category}" escapeXml="false" />');
			var model = JSON
					.parse('<c:out value="${model}" escapeXml="false" />');

			$(document)
					.ready(
							function() {
								var options = "";
								for (var i = 0; i < category.length; i++) {
									options += '<option value ="' + category[i].category + '">'
											+ category[i].category
											+ '</option>';
								}
								$("#c").append(options);

							});

			function changeCat(value) {
				var catOptions = "<option value=''>--- Select ---</option>";
				for (var i = 0; i < model.length; i++) {
					if (model[i].productCategory.category == value)
						catOptions += '<option value="' + model[i].model + '">'
								+ model[i].model + '</option>';
				}
				document.getElementById("m").innerHTML = catOptions;
			}
		</script>

	</div>
</body>
</html>
