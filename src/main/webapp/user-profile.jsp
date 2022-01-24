<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<%
	String msg = (String) session.getAttribute("successMessage");
	session.removeAttribute("successMessage");
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<c:if test="true">
		<div class="container">
			<h2 class="pt-3">Hello, ${user.getName()}. You successfully
				logged in!</h2>
			<div class="card" style="width: 18rem;">
				<div class="card-header">Your profile</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><b>ID: </b>${user.getId()}</li>
					<li class="list-group-item"><b>Name: </b>${user.getName()}</li>
					<li class="list-group-item"><b>E-mail: </b>${user.getEmail() }</li>
					<li class="list-group-item"><b>Phone number: </b>${user.getPhoneNumber()}</li>
				</ul>
			</div>
			<a class="btn btn-warning mt-3" href="edit-profile" role="button">Edit
				profile</a> <a class="btn btn-warning mt-3" href="profile?rides=show"
				role="button">My rides</a>
		</div>

		<div class="container pt-3">
			<c:if test="<%=msg != null%>">
				<div class="alert alert-success" role="alert">
					<%=msg%>
				</div>
			</c:if>
			<c:if test="${empty param.order}">
				<a type="button" class="btn btn-warning" href="profile?order=true">Order
					a taxi</a>
			</c:if>
			<c:if test="${not empty param.order}">
				<a type="button" class="btn btn-warning" href="profile">Cancel
					the order</a>
				<div class="py-4">
					<jsp:include page="ride-form.jsp"></jsp:include>
				</div>
			</c:if>
		</div>
	</c:if>
</body>
</html>