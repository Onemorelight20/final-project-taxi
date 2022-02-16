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
		Page ${page} of ${pageCount} 
	
	|

	<c:choose>
		<c:when test="${page - 1 > 0}">
			<a href="page?page=${page-1}&pageSize=${pageSize}">Previous</a>
		</c:when>
		<c:otherwise>
			Previous
		</c:otherwise>
	</c:choose>


	<c:forEach var="p" begin="${minPossiblePage}" end="${maxPossiblePage}">
		<c:choose>
			<c:when test="${page == p}">${p}</c:when>
			<c:otherwise>
				<a href="page?page=${p}&pageSize=${pageSize}">${p}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:choose>
		<c:when test="${page + 1 <= pageCount}">
			<a href="page?page=${page+1}&pageSize=${pageSize}">Next</a>
		</c:when>
		<c:otherwise>
			Next
		</c:otherwise>
	</c:choose>

	|
	
	<form action="page" style='display:inline;'>
		<select name="page">
			<c:forEach begin="1" end="${pageCount}" var="p">
				<option value="${p}" ${p == param.page ? 'selected' : ''}>${p}</option>
			</c:forEach>
		</select>
		<input name="pageSize" value="${pageSize}" type="hidden" />
		<input type="submit" value="Go"/>
	</form>
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
								<th><a
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