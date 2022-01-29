<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List,ua.boretskyi.webtask.dao.entity.Ride"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My rides</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container my-4">
		<c:choose>
			<c:when test="${not empty userRides}">
				<h3>My rides</h3>
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Route</th>
							<th scope="col">Status</th>
							<th scope="col">People</th>
							<th scope="col">Creation time</th>
							<th scope="col">Price</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${userRides}" var="ride">
							<tr>
								<th scope="row">${ride.getRideId()}</th>
								<th>From ${ride.getPlaceFrom()} to ${ride.getPlaceTo()}</th>
								<th>${ride.getStatus()}</th>
								<th>${ride.getPeopleInRide()}</th>
								<th>${ride.getTimeCreated() }</th>
								<th>${ride.getPrice() }</th>
								<th><button type="button" class="btn btn-danger"
										onclick="return confirm('Are you sure?');">Delete</button> <a
									type="button" class="btn btn-secondary"
									href="ride-info?id=${ride.getRideId()}">All info</a></th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<h3>You have not created any rides yet.</h3>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>