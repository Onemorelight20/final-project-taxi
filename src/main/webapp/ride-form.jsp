<%@page import="ua.boretskyi.webtask.dao.entity.Car"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ride form</title>
</head>
<body>
	<c:set var="carTypes" value="<%=Car.Type.values()%>"/>
	<div class="container">
	<form method="post">
	<div class="mb-3">
		<label for="streetFrom" class="form-label">From</label>
		<input type="text" class="form-control" id="streetFrom" placeholder="Street and number" name="pointA">
	  </div>
	  
	  <div class="mb-3">
		<label for="streetTo" class="form-label">To</label>
		<input type="text" class="form-control" id="streetTo" placeholder="Street and number" name="pointB">
	  </div>
	  <div class="mb-3">                  
			<label for="numberPassangers" class="form-label">Number of passangers</label>    
			<input type="number" id="numberPassangers" data-bind="value:replyNumber" min="1" max="10" name="passangersAmount"/>
	  </div>
	  <div class="mb-3">
		<label for="carType" class="form-label">Select the type of car</label>    
		<select class="form-select" name="carType">
			<option selected>Open this select menu</option>
			<c:forEach var="type" items="<%=Car.Type.values()%>">
			<option>${ type }</option>
		</c:forEach>
		</select>
			  </div>
	  <div class="form-floating mb-3">
  		<textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px" name="comment"></textarea>
  		<label for="floatingTextarea2">Comments</label>
	 </div>
	 <c:if test="${!empty errMessage}">
    <div class="alert alert-warning" role="alert">
 		<%=session.getAttribute("errMessage")%>
	</div>
    </c:if>
	 <button type="submit" class="btn btn-primary">Calculate the price ant select a car</button>
	 <input type="reset" value="Reset" class="btn btn-primary">
	</form>
	</div>
</body>
</html>