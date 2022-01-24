<%@ page language="java" contentType="text/html;" 
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Taxi</title>
<link href="css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
	crossorigin="anonymous">
</head>
<body>

	
	<header class="p-3 bg-dark text-white">
	
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none px-3" href="/final-project">
         <img src="img/beep.png" class="img-fluid logo" style="max-width: 80px">
        </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="/final-project" class="nav-link px-3 text-white" >Home</a></li>
          <li><a href="#" class="nav-link px-3 text-white">Pricing</a></li>
          <li><a href="#" class="nav-link px-3 text-white">FAQs</a></li>
          <li><a href="#" class="nav-link px-3 text-white">About</a></li>
        </ul>


        <div class="text-end">
        	 <% if (session.getAttribute("user") == null) { %>
       	  		<a class="btn btn-outline-light me-2" href="login" role="button">Login</a>
       	 		 <a class="btn btn-warning" href="signup" role="button">Sign-up</a>
    		  <% } else { %>
         		<a class="btn btn-outline-light me-2" href="profile" role="button">Profile</a>
         		<a class="btn btn-warning me-2" href="logout" role="button">Log out</a>
  		      <% } %>
       	  
        </div>
      </div>
    </div>
  </header>
</body>
</html>