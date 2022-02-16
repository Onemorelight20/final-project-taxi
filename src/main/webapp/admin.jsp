<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">


</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container my-3">
		<h3>Hello, ${user.getName()}. You successfully logged in WITH
			ADMIN PREFERENCES!</h3>
		<div class="card" style="width: 18rem;">
			<div class="card-header">Your profile</div>
			<ul class="list-group list-group-flush">
				<li class="list-group-item"><b>ID: </b>${user.getId()}</li>
				<li class="list-group-item"><b>Name: </b>${user.getName()}</li>
				<li class="list-group-item"><b>E-mail: </b>${user.getEmail() }</li>
				<li class="list-group-item"><b>Phone number: </b>${user.getPhoneNumber()}</li>
			</ul>
		</div>
		<jsp:include page="short-stats.jsp"></jsp:include>
		<c:if test="${not empty sessionScope.successMessage}">
			<div class="alert alert-success" role="alert">${sessionScope.successMessage}</div>
		</c:if>
		<a type="button" class="btn btn-outline-dark" href="stats">Detailed
			statistics</a>
		<h4 class="mt-3">Drivers control block</h4>
		<a type="button" class="btn btn-outline-dark" href="add-driver">Add
			a driver into system</a>
			<a type="button" class="btn btn-outline-dark" href="add-driver">See all drivers</a>
	</div>


</body>
</html>