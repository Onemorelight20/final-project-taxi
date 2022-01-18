<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<h2>Hello, ${user.getName()}. You successfully logged in!</h2> 
	
	<a class="btn btn-warning" href="logout" role="button">Log out</a>
	<hr>
	<% out.println(session.getAttribute("user")); %>
	<hr>
	<a type="button" class="btn btn-warning" href="profile?order=true">Order a taxi</a>
	<h6>user page</h6>
	
	<c:if test="${not empty param.order}">
	<jsp:include page="ride-form.jsp"></jsp:include>
	</c:if>
	<h6>user page</h6>
</body>
</html>