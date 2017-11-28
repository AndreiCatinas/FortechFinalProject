<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<html>
<head>
<title>Bookings</title>
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
	<c:url var="addAction" value="/bookings" />

	<div class="container-fluid">
		<div class="row content">
			<jsp:include page="../navBar.jsp" />



			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a class="btn btn-info" href="<c:url value='/bookings/all' />">All
					Bookings</a>
				<a class="btn btn-info" href="<c:url value='/bookings/active' />">Active
					Bookings</a>
				<a class="btn btn-info" href="<c:url value='/bookings/history' />">
					Booking History</a>
			</sec:authorize>

			<sec:authorize access="hasRole('ROLE_USER')">
				<a class="btn btn-info"
					href="<c:url value='/bookings/${user.username}/active' />">Active
					Bookings</a>
				<a class="btn btn-info"
					href="<c:url value='/bookings/${user.username}/history' />">Booking
					History</a>
			</sec:authorize>

			<div class="col-sm-10">
				<c:if test="${empty bookings}">
					<br>
					<div class="alert alert-info">
						<strong>Info!</strong> No bookings.
					</div>
				</c:if>

				<c:if test="${not empty bookings}">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>User</th>
								<th>Product</th>
								<th>Product Category</th>
								<th>Product Model</th>
								<th>Date From</th>
								<th>Expires</th>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<th>Expire</th>
								</sec:authorize>

								<sec:authorize access="hasRole('ROLE_USER')">
									<c:if test="${!hidden}">
										<th>Cancel</th>
									</c:if>
								</sec:authorize>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="booking" items="${bookings}">
								<tr>
									<td>${booking.user.username}</td>
									<td>${booking.product.name}</td>
									<td>${booking.product.productModel.productCategory.category}</td>
									<td>${booking.product.productModel.model}</td>
									<td>${booking.dateFrom}</td>
									<td>${booking.expires}</td>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<td><a
											href="<c:url value='/bookings/delete/${booking.id}' />">
												<span class="glyphicon glyphicon-trash city-label"></span>
										</a></td>
									</sec:authorize>

									<sec:authorize access="hasRole('ROLE_USER')">
										<c:if test="${!hidden}">
											<td><a
												href="<c:url value='/bookings/${user.username}/delete/${booking.id}' />">
													<span class="glyphicon glyphicon-trash city-label"></span>
											</a></td>
										</c:if>
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
