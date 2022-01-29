<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="ua.boretskyi.webtask.dao.entity.*, java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ride confirmation</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
		<div class="py-3">
			<div class="card">
				<div class="card-header ">
					Ride info <a type="button" class="btn btn-warning btn-sm"
						href="profile?order=true">Edit</a> <a type="button"
						class="btn btn-warning btn-sm" href="cancel-ride">Cancel the
						ride</a>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><b>From </b>
						${rideInfo.getPlaceFrom()} <b>to </b> ${rideInfo.getPlaceTo()}</li>
					<li class="list-group-item"><b>Passengers: </b>
						${rideInfo.getNumberOfPassengers()}</li>
					<li class="list-group-item"><b>Type of car: </b>
						${rideInfo.getTypeOfCar().toString()}</li>
					<li class="list-group-item"><b>Comment: </b>
						${rideInfo.getComment()}</li>
				</ul>
			</div>
			<c:if test="${empty param.severalCars }">
				<div class="alert alert-success" role="alert">
					<b>Calculated price: ${rideInfo.calculatePrice()} UAH</b>
				</div>
			</c:if>
		</div>
		<c:if test="${not empty car}">
			<div class="alert alert-success" role="alert">
				You selected the car ${car.toString()}. You can change it before
				submission.

				<form method="post">
					<input type="submit" value="Submit" class="btn btn-success">
				</form>
			</div>
		</c:if>
		<c:choose>
			<c:when test="${ suitableCars.size() gt 0}">
				<h3>Select a car from available</h3>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Model</th>
							<th scope="col">Status</th>
							<th scope="col">Seats available</th>
							<th scope="col">Type</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${suitableCars}" var="car">
							<tr>
								<th scope="row">${car.getModel()}</th>
								<td>${car.getStatus() }</td>
								<td>${car.getSeatsAvailable() }</td>
								<td>${car.getType()}</td>
								<td><a type="button" class="btn btn-success"
									href="ride?carid=${ car.getId()}">Select</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:when test="${not empty param.allAvailable}">
				<c:choose>
					<c:when test="${not empty allAvailableCars}">
						<h3>Select an available car from other categories</h3>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Model</th>
									<th scope="col">Status</th>
									<th scope="col">Seats available</th>
									<th scope="col">Type</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allAvailableCars}" var="car">
									<tr>
										<th scope="row">${car.getModel()}</th>
										<td>${car.getStatus() }</td>
										<td>${car.getSeatsAvailable() }</td>
										<td>${car.getType()}</td>
										<td><a type="button" class="btn btn-success"
											href="ride?allAvailable=true&carid=${ car.getId()}">Select</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<h3>Unfortunately, there are no suitable cars in other
							categories. You may try to edit your ride or wait for available
							cars.</h3>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${ not empty param.severalCars}">
				<c:choose>
					<c:when test="${ not empty pack }">
						<h2>Several orders will be created with these cars if you
							click submit button</h2>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Model</th>
									<th scope="col">Status</th>
									<th scope="col">Seats available</th>
									<th scope="col">Price</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pack}" var="carFromPack">
									<tr>
										<th scope="row">${carFromPack.getModel()}</th>
										<td>${carFromPack.getStatus() }</td>
										<td>${carFromPack.getSeatsAvailable() }</td>
										<td>${rideInfo.calculatePrice(car.getSeatsAvailable(), car.getType())}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<form method="post">
							<input type="submit" value="Submit" class="btn btn-success">
						</form>
					</c:when>
					<c:otherwise>
						<h3>Unfortunately, there are no suitable several cars. You
							may try to edit your ride or wait for available cars.</h3>
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
		<c:if
			test="${empty suitableCars && empty param.severalCars && empty param.allAvailable}">
			<h3 class="mt-3">Oops, there are no suitable cars of
				${rideInfo.getTypeOfCar().toString()} category with
				${rideInfo.getNumberOfPassengers()} seat(s) available.</h3>
			<a type="button" class="btn btn-warning"
				href="ride?allAvailable=true"
				<c:if test="${allAvailableCars.size() gt 0}"><c:out value="disabled='disabled'"/></c:if>>Look
				over suitable cars in other categories</a>


			<a type="button" class="btn btn-warning" href="ride?severalCars=true">Select
				several cars in this category</a>

		</c:if>
	</div>


</body>
</html>