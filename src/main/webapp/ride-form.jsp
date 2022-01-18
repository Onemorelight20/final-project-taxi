<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	<form method="get">
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
			<input type="number" id="numberPassangers" data-bind="value:replyNumber" min="1" max="10"/>
	  </div>
	  <div class="mb-3">
		<label for="carType" class="form-label">Select the type of car</label>    
		<select class="form-select" aria-label="Default select example" id="carType">
			<option selected>Open this select menu</option>
			<option value="1">One</option>
			<option value="2">Two</option>
			<option value="3">Three</option>
		  </select>
	  </div>
	  <div class="form-floating mb-3">
  		<textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px"></textarea>
  		<label for="floatingTextarea2">Comments</label>
	 </div>
	 <button type="submit" class="btn btn-primary">Calculate the price</button>
	 <input type="reset" value="Reset" class="btn btn-primary">
	</form>
	</div>
</body>
</html>