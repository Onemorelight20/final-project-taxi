<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Block</title>
<!-- 	
	This page needs following attributes for proper work:
			pageCount - amount of pages
			pageSize - maximum records per page
			generalSize - general amount of records
			minPossiblePage, maxPossiblePage
-->
</head>
<body>
	<div>
		Page ${page} of ${pageCount} | General amount of records:
		${generalSize} |

		<c:choose>
			<c:when test="${page - 1 > 0}">
				<a href="<my:param name='page' value='${page-1}'/>">Previous</a>
			</c:when>
			<c:otherwise>
			Previous
		</c:otherwise>
		</c:choose>


		<c:forEach var="p" begin="${minPossiblePage}" end="${maxPossiblePage}">
			<c:choose>
				<c:when test="${page == p}">${p}</c:when>
				<c:otherwise>
					<a href="<my:param name='page' value='${p}'/>">${p}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:choose>
			<c:when test="${page + 1 <= pageCount}">
				<a href="<my:param name='page' value='${page+1}'/>">Next</a>
			</c:when>
			<c:otherwise>
			Next
		</c:otherwise>
		</c:choose>
		|
		<form action="${servletUrl}" style='display: inline;'>
			<select name="page">
				<c:forEach begin="1" end="${pageCount}" var="p">
					<option value="${p}" ${p == param.page ? 'selected' : ''}>${p}</option>
				</c:forEach>
			</select> <input name="pageSize" value="${pageSize}" type="hidden" /> <input
				type="submit" value="Go" />
		</form>
		Records per page: <a href="<my:param name='pageSize' value='5'/>">5</a>
		| <a href="<my:param name='pageSize' value='10'/>">10</a> | <a
			href="<my:param name='pageSize' value='20'/>">20</a>
	</div>
</body>
</html>