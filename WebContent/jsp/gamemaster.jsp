<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DemiurgoWeb - GM Panel</title>
</head>
<body>
<h1>DemiurgoWeb</h1>
<h4>User: ${sessionScope.user.username}</h4>
	<form method="get" action="room">
		<label for='pending'>Pending rooms:</label> <select>
			<c:forEach var="room" items="${pending}">
				<option value="${room}">${room}</option>
			</c:forEach>
		</select>
		<input type="submit">
	</form>
	<a href="logout">Logout</a>
</body>
</html>