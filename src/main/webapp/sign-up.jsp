<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sign up</title>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

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


<link href="css/signup.css" rel="stylesheet">
</head>
<body>
<body class="text-center">

	<main class="form-signin">
		<form method="post" autocomplete="off">
			<input autocomplete="false" name="hidden" type="text" style="display:none;">
			<a href="/final-project"> <img class="mb-4" src="img/beep.png"
				alt="logo" height="57">
			</a>
			<h1 class="h3 mb-3 fw-normal">Please sign up</h1>

			<div class="form-floating">
				<input type="text" class="form-control first" id="floatingInput"
					placeholder="David" name="firstName"> <label
					for="floatingInput">First name</label>
			</div>
			<div class="form-floating">
				<input type="text" class="form-control no-rounded-borders"
					id="floatingInput" placeholder="0955952422" name="phoneNumber">
				<label for="floatingInput">Phone number</label>
			</div>
			<div class="form-floating">
				<input type="email" class="form-control no-rounded-borders"
					id="floatingInput" placeholder="name@example.com" name="email">
				<label for="floatingInput">Email address</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control no-rounded-borders"
					id="floatingPassword" placeholder="Password" name="password">
				<label for="floatingPassword">Password</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control last"
					id="floatingPassword" placeholder="Password" name="conpassword">
				<label for="floatingPassword">Repeat your password</label>
			</div>

			<c:if test="${!empty errMessage}">
				<div class="alert alert-warning" role="alert">
					<%=session.getAttribute("errMessage")%>
				</div>
			</c:if>


			<button class="w-100 btn btn-lg btn-primary" type="submit">Sign
				up</button>
			<p class="mt-5 mb-3 text-muted">© 2022</p>
		</form>
	</main>
</body>
</body>
</html>