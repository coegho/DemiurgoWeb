<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DemiurgoWeb - Room Panel</title>
</head>
<body>
	<h1>DemiurgoWeb - Room Panel</h1>
	<h2>${path}</h2>
	<c:forEach var="decision" items="${room.decisions}">
		<h3>${decision.username}</h3>
		<p>${decision.text}</p>
	</c:forEach>
	<a href="index">Return to GM Panel</a>
</body>
</html>