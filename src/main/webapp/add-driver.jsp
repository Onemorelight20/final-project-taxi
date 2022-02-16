<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add a driver to system</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container my-4">
		<h3>Enter info about the driver</h3>
		<form method="post">
			<div class="mb-3">
				<label for="floatingInput">First name</label> <input type="text"
					class="form-control first" id="floatingInput" placeholder="David"
					name="name" required>
			</div>
			<div class="mb-3">
				<label for="floatingInput">Phone number</label> <input type="text"
					class="form-control no-rounded-borders" id="floatingInput"
					placeholder="0955952422" name="phoneNumber" required>
			</div>
			<div class="mb-3">
				<label for="floatingInput">Email address</label> <input type="email"
					class="form-control no-rounded-borders" id="floatingInput"
					placeholder="name@example.com" name="email" required>
			</div>
			<div class="mb-3">
				<label for="floatingPassword">Password</label> <input
					type="password" class="form-control no-rounded-borders"
					id="floatingPassword" placeholder="Password" name="password"
					required>
			</div>
			<div class="mb-3">
				<label for="floatingPassword">Repeat the password</label> <input
					type="password" class="form-control last" id="floatingPassword"
					placeholder="Password" name="conpassword" required>
			</div>
			<c:if test="${not empty errMessage}">
			<div class="alert alert-warning" role="alert">
			${errMessage}
			</div>
			</c:if>
			<button class="btn btn-outline-dark" type="submit">Add
				into system</button>
			<a class="btn btn-outline-dark" href="profile">Cancel</a>
		</form>
	</div>
</body>
</html>