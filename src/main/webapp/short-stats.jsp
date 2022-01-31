<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Statistics</title>
<link href="css/statistic-tabs.css" rel="stylesheet">
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
	<div class="container my-3">
		<h4>Brief statistics</h4>
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item" role="presentation">
				<button class="nav-link active" id="all-time-stats-tab"
					data-bs-toggle="tab" data-bs-target="#all-time" type="button"
					role="tab" aria-controls="all-time" aria-selected="true">All
					time</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link" id="last-month-tab" data-bs-toggle="tab"
					data-bs-target="#last-month" type="button" role="tab"
					aria-controls="last-month" aria-selected="false">Last
					month</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link" id="last-week-tab" data-bs-toggle="tab"
					data-bs-target="#last-week" type="button" role="tab"
					aria-controls="last-week" aria-selected="false">Last week</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link" id="last-24-hours-tab" data-bs-toggle="tab"
					data-bs-target="#last-24-hours" type="button" role="tab"
					aria-controls="last-24-hours" aria-selected="false">Last
					24 hours</button>
			</li>
		</ul>
		<div class="tab-content pt-3" id="myTabContent">
			<div class="tab-pane fade show active" id="all-time" role="tabpanel"
				aria-labelledby="all-time-stats-tab">
				<h4>Number of rides: ${allTimeStats.getRidesAmount() }</h4>
				<p>Average amount of people in ride:
					${allTimeStats.getAvgPeople() }</p>
				<p>Average price: ${allTimeStats.getAvgPrice() }</p>
				<p>Summary cost: ${allTimeStats.getSummaryCost() }</p>
			</div>
			<div class="tab-pane fade" id="last-month" role="tabpanel"
				aria-labelledby="last-month-tab">
				<h4>Number of rides: ${lastMonthStats.getRidesAmount() }</h4>
				<p>Average amount of people in ride:
					${lastMonthStats.getAvgPeople() }</p>
				<p>Average price: ${lastMonthStats.getAvgPrice() }</p>
				<p>Summary cost: ${lastMonthStats.getSummaryCost() }</p>
			</div>
			<div class="tab-pane fade" id="last-week" role="tabpanel"
				aria-labelledby="last-week-tab">
				<h4>Number of rides during last week:
					${lastWeekStats.getRidesAmount() }</h4>
				<p>Average amount of people in ride:
					${lastWeekStats.getAvgPeople() }</p>
				<p>Average price: ${lastWeekStats.getAvgPrice() }</p>
				<p>Summary cost: ${lastWeekStats.getSummaryCost() }</p>
			</div>
			<div class="tab-pane fade" id="last-24-hours" role="tabpanel"
				aria-labelledby="last-24-hours-tab">
				<h4>Number of rides: ${last24HoursStats.getRidesAmount() }</h4>
				<p>Average amount of people in ride:
					${last24HoursStats.getAvgPeople() }</p>
				<p>Average price: ${last24HoursStats.getAvgPrice() }</p>
				<p>Summary cost: ${last24HoursStats.getSummaryCost() }</p>
			</div>
			<hr>
		</div>
		<a type="button" class="btn btn-outline-dark" href="stats">Detailed statistics</a>
	</div>
</body>
</html>