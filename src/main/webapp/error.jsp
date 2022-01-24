<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container my-4">
		<div class="alert alert-warning" role="alert">${message }</div>
	</div>
</body>
</html>