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
	<%
	RideBean bean = (RideBean) session.getAttribute("rideInfo");
	List<Car> suitableCars = (List<Car>) session.getAttribute("suitableCars");
	List<Car> allAvailableCars = (List<Car>) session.getAttribute("allAvailableCars");
	Car selected = (Car) session.getAttribute("car");
	%>
	<div class="container">
		<div class="py-3">
			<div class="card">
				<div class="card-header ">
					Ride info <a type="button" class="btn btn-warning btn-sm"
						href="javascript:history.go(-1)">Edit</a> <a type="button"
						class="btn btn-warning btn-sm" href="profile">Cancel the ride</a>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><b>From </b> <%=bean.getPlaceFrom()%>
						<b>to </b> <%=bean.getPlaceTo()%></li>
					<li class="list-group-item"><b>Passengers: </b> <%=bean.getNumberOfPassengers()%></li>
					<li class="list-group-item"><b>Type of car: </b> <%=bean.getTypeOfCar().toString()%></li>
					<li class="list-group-item"><b>Comment: </b> <%=bean.getComment()%></li>
				</ul>
			</div>
			<div class="alert alert-success" role="alert">
				<b>Calculated price: <%
				out.append(String.valueOf(bean.calculatePrice()));
				%> UAH
				</b>
			</div>

		</div>
		<c:if test="<%=selected != null%>">
			<div class="alert alert-success" role="alert">
				You selected the car
				<%=selected.toString()%>. You can change it before submission.

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
			<c:when
				test="${ allAvailableCars.size() gt 0 && not empty param.allAvailable}">
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
				<c:if test="${ empty param.carid }">
					<h3 class="mt-3">
						Oops, there are no suitable cars of
						<%=bean.getTypeOfCar().toString()%>
						category with
						<%=bean.getNumberOfPassengers()%>
						seat(s) available.
					</h3>
					<a type="button" class="btn btn-warning"
						href="ride?allAvailable=true">Look over suitable cars in other
						categories</a>
					<a type="button" class="btn btn-warning">Select several cars in
						this category</a>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>


</body>
</html>