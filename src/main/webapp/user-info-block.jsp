<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User info</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="card" style="width: 18rem;">
		<div class="card-header">
			Your profile <c:if test="${user.role == 'CLIENT'}"><a href="edit-profile" role="button">Edit</a></c:if>
		</div>
		<ul class="list-group list-group-flush">
			<li class="list-group-item"><b>ID: </b>${user.getId()}</li>
			<li class="list-group-item"><b>Name: </b>${user.getName()}</li>
			<li class="list-group-item"><b>E-mail: </b>${user.getEmail() }</li>
			<li class="list-group-item"><b>Phone number: </b>${user.getPhoneNumber()}</li>
		</ul>
	</div>
</body>
</html>