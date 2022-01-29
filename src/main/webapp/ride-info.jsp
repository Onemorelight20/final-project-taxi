<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List,ua.boretskyi.webtask.dao.entity.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ride info</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container my-4">
		<h4>Ride info</h4>
		<div class="card">
			<ul class="list-group list-group-flush">
				<li class="list-group-item"><b>Id: </b> ${ride.getRideId()}</li>
				<li class="list-group-item"><b>From </b> ${ride.getPlaceFrom()}
					<b> to </b> ${ride.getPlaceTo() }</li>
				<li class="list-group-item"><b>Status: </b> ${ride.getStatus()}</li>
				<li class="list-group-item"><b>People in ride: </b>
					${ride.getPeopleInRide()}</li>
				<li class="list-group-item"><b>Creation time: </b>
					${ride.getTimeCreated()}</li>
				<li class="list-group-item"><b>Driver is expected to arrive
						to you at: </b> ${ride.getExpectedStartTime()}</li>
				<li class="list-group-item"><b>Estimated time of arrival: </b>
					${ride.getExpectedFinishTime()}</li>
			</ul>
		</div>
	</div>

	<div class="container my-4">
		<div class="card">
			<div class="card-header">Driver info</div>
			<ul class="list-group list-group-flush">
				<li class="list-group-item"><b>Name: </b> ${driver.getName()}</li>
				<li class="list-group-item"><b>E-mail: </b>
					${driver.getEmail()}</li>
				<li class="list-group-item"><b>Phone number: </b>
					${driver.getPhoneNumber()}</li>
			</ul>
		</div>
	</div>

	<div class="container my-4">
		<div class="card">
			<div class="card-header">Car info</div>
			<ul class="list-group list-group-flush">
				<li class="list-group-item"><b>Car id: </b> ${car.getId()}</li>
				<li class="list-group-item"><b>Model: </b> ${car.getModel()}</li>
				<li class="list-group-item"><b>Seats available: </b>
					${car.getSeatsAvailable()}</li>
				<li class="list-group-item"><b>Type: </b> ${car.getType() }</li>
			</ul>
		</div>
	</div>
</body>
</html>