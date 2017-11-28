<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<html>
<head>
<title>Products</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<sec:authentication var="user" property="principal" />

	<div>
		<jsp:include page="../userNavbar.jsp"></jsp:include>
	</div>

	<c:url var="addAction" value="/products" />

	<div class="container-fluid">
		<div class="row content">
			<jsp:include page="../navBar.jsp" />

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a class="btn btn-info" href="<c:url value='/products/all' />">All
					Products</a>
				<a class="btn btn-info" href="<c:url value='/products/booked' />">Booked
					Products</a>
				<a class="btn btn-info" href="<c:url value='/products/available' />">Available
					Products</a>
			</sec:authorize>
			
			<div class="col-sm-10">
				<c:if test="${empty products}">
					<br>
					<div class="alert alert-info">
						<strong>Info!</strong> No products.
					</div>
				</c:if>

				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<br>
					<a href="${addAction}" class="btn btn-primary">Add Product</a>
				</sec:authorize>

				<c:if test="${not empty products}">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Id</th>
								<th>Category</th>
								<th>Model</th>
								<th>Name</th>
								<th>Serial number</th>
								<th>Aquisition Date</th>
								<th>Description</th>
								<sec:authorize access="hasRole('ROLE_USER')">
									<th>Book It</th>
								</sec:authorize>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<th>Edit</th>
									<th>Delete</th>
								</sec:authorize>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="product" items="${products}">
								<tr>
									<td>${product.id}</td>
									<td>${product.productModel.productCategory}</td>
									<td>${product.productModel}</td>
									<td>${product.name}</td>
									<td>${product.serialNumber}</td>
									<td>${product.aquisitionDate}</td>
									<td>${product.description}</td>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<td><a
											href="<c:url value='/products/update/${product.id}' />">
												<span class="glyphicon glyphicon-pencil"></span>
										</a></td>

										<td><a
											href="<c:url value='/products/delete/${product.id}' />">
												<span class="glyphicon glyphicon-trash city-label"></span>
										</a></td>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_USER')">
										<td><a
											href="<c:url value='/bookings/${user.username}/products/${product.id}' />">
												<span class="glyphicon glyphicon-star"></span>
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
