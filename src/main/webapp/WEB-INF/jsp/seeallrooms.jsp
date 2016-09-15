<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DemiurgoWeb - Room Panel</title>
</head>
<body>
	<h1>DemiurgoWeb - Room Panel</h1>
	<c:forEach var="p" items="${paths}">
		<c:if test="${p.roomFilled}">
			<p>
				<a href="room?room=${p.path}">${p.path}</a>
			</p>
		</c:if>
		<c:if test="${!p.roomFilled}">
			<p>${p.path}</p>
		</c:if>
	</c:forEach>
	<a href="index">Return to GM Panel</a>
</body>
</html>