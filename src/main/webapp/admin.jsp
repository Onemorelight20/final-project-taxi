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
<style type="text/css">
 .tab_content {
 	display: none;
 }
 
 #tab1:checked ~ .tab_content{
	display: inline-block;
}
</style>
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
		<div class="btn-group my-3" role="group">
			<input type="radio" class="btn-check" name="btnradio" id="tab1"
				checked> <label class="btn btn-outline-primary"
				for="tab1">All time stats</label> 
			<input type="radio" class="btn-check" name="btnradio" id="tab2"> <label
				class="btn btn-outline-primary" for="tab2">Last months
				stats</label>
		</div>
		
				<div class="tab_content">Tab1 Contents</div>
				
		<div class="tab_content">Tab2 Contents</div>
	</div>

</body>
</html>