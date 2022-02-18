<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<c:if test="true">
		<div class="container">
			<h2 class="pt-3">Hello, ${user.getName()}. You successfully
				logged in!</h2>
			<jsp:include page="user-info-block.jsp"></jsp:include>
			<a class="btn btn-warning mt-3" href="rides?page=1&pageSize=3"
				role="button">My rides</a>
		</div>

		<div class="container pt-3">
			<c:if test="${not empty sessionScope.successMessage}">
				<div class="alert alert-success" role="alert">${sessionScope.successMessage}</div>
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