<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List,ua.boretskyi.webtask.dao.entity.*"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detailed statistics</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container my-5">
		<div class="row">
			<div class="col-3">
				<h6>Filter by date</h6>
				<form method="GET" action="stats">
					<label for="startDate">From</label> <input id="startDate"
						class="form-control" type="date" name="from" value="${param.from}"
						required /> <span id="startDateSelected"></span> <label
						for="endDate">To</label> <input id="endDate" class="form-control"
						type="date" name="to" value="${param.to}" required /> <span
						id="endDateSelected"></span>
					<div class="mt-2">
						<button type="submit" class="btn btn-outline-warning btn-sm">Submit</button>
						<a href="<my:param name='rdf' value='true'/>"
							class="btn btn-outline-warning btn-sm">Reset</a>
					</div>
				</form>
			</div>
			<div class="col-9">
				<div>
					<h6>Sorting</h6>
					<div class="btn-group">
						<a type="button" class="btn btn-outline-dark"
							href="<my:param name='sortby' value='status' />">Sort by
							status</a> <a type="button" class="btn btn-outline-dark"
							href="<my:param name='sortby' value='passengers' />">Sort by
							passengers amount</a> <a type="button" class="btn btn-outline-dark"
							href="<my:param name='sortby' value='crt' />">Sort by
							creation time</a> <a type="button" class="btn btn-outline-dark"
							href="<my:param name='sortby' value='cost'/>">Sort by cost</a>
					</div>
					<div class="btn-group">
						<a href="<my:param name='order' value='asc'/>"
							class="btn btn-dark active" aria-current="page">Ascending</a> <a
							href="<my:param name='order' value='desc'/>" class="btn btn-dark">Descending</a>
					</div>
					<a type="button" class="btn btn-dark"
						href="<my:param name='sortby' value='none' />">Reset sorting</a>
				</div>
				<div class="mt-3 p-2 float-end">
					<jsp:include page="pagination-block.jsp"></jsp:include>
				</div>
			</div>
			<table class="table table-hover my-1">
				<thead>
					<tr>
						<th scope="col">ID ${request.RequestURL }</th>
						<th scope="col">Route</th>
						<th scope="col">Status</th>
						<th scope="col">Passengers</th>
						<th scope="col"
							style="${param.sortby == 'crt' ? 'background-color: rgba(0, 0, 0, 0.2)' : ''}">Creation
							time</th>
						<th scope="col">Finish time</th>
						<th scope="col">Car id</th>
						<th scope="col">User id</th>
						<th scope="col">Driver id</th>
						<th scope="col"
							<c:if test="${param.sortby == 'cost'}">style="background-color: rgba(0, 0, 0, 0.2);"</c:if>>Price</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="ride">
						<tr>
							<th scope="row">${ride.getRideId()}</th>
							<td>From ${ride.getPlaceFrom()} to ${ride.getPlaceTo()}</td>
							<td>${ride.getStatus()}</td>
							<td>${ride.getPeopleInRide()}</td>
							<td>${ride.getTimeCreated()}</td>
							<td>${ride.getExpectedFinishTime()}</td>
							<td>${ride.getCarId()}</td>
							<td>${ride.getUserId()}</td>
							<td>${ride.getDriverId()}</td>
							<td>${ride.getPrice()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>