<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Update Product</title>
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
	<c:url var="update" value="/products/update/{id}" />
	<c:url var="goBack" value="/products/all" />

	<div class="container-fluid">
		<div class="row content">
			<jsp:include page="../navBar.jsp" />

			<div class="col-sm-10">
				<div class="col-sm-6 col-sm-offset-2">
					<div class="form">
						<div style="text-align: right">
							<h3>Edit Product</h3>
						</div>
						<form:form commandName="productDto" action="${update}"
							method="POST" id="editProduct">
							<div class="form-group">
								<label class="control-label">ID</label>
								<form:input readonly="true" type="text" path="id"
									class="form-control" required="required" />
							</div>

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
								<label class="control-label">Name</label>
								<form:input path="name" class="form-control" required="required" />
							</div>

							<div class="form-group">
								<label class="control-label">Serial Number</label>
								<form:input path="serialNumber" class="form-control"
									required="required" />
							</div>

							<div class="form-group">
								<form:label path="aquisitionDate">Aquisition Date:</form:label>
								<form:input type="date" path="aquisitionDate"
									class="form-control" />
								<form:errors path="aquisitionDate" cssClass="error" />
							</div>

							<div class="form-group">
								<label class="control-label">Description</label>
								<form:input path="description" class="form-control"
									required="required" />
							</div>

							<input type="submit" value="Save" class="btn btn-success" />
							<a href="${goBack}" class="btn btn-info" role="button">Go
								back</a>

						</form:form>
					</div>
				</div>
			</div>
		</div>


	</div>

	<script type="text/javascript">
		var category = JSON
				.parse('<c:out value="${category}" escapeXml="false" />');
		var model = JSON.parse('<c:out value="${model}" escapeXml="false" />');
		var selMod = '<c:out value = "${selMod}"/>';
		var selCat = '<c:out value = "${selCat}"/>';

		$(document)
				.ready(
						function() {
							var options = "";
							var selected = "";
							for (var i = 0; i < category.length; i++) {
								if (category[i].category == selCat) {
									selected = "selected";
								}
								options += '<option ' + selected + ' value ="' + category[i].category + '">'
										+ category[i].category + '</option>';
								selected = "";
							}
							$("#c").append(options);
							var mmm = '<option selected value="' + selMod + '">'
									+ selMod + '</option>';
							$("#m").append(mmm);
						});

		function changeCat(value) {
			if (value.length == 0)
				document.getElementById("m").innerHTML = "<option>---Select---</option>";
			else {
				var catOptions = "";
				for (var i = 0; i < model.length; i++) {
					if (model[i].productCategory.category == value) {
						catOptions += '<option ' + ' value="' + model[i].model + '">'
								+ model[i].model + '</option>';
					}
				}
			}
			document.getElementById("m").innerHTML = catOptions;
		}
	</script>
</body>
</html>