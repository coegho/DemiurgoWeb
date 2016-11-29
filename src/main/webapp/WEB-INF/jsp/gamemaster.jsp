<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DemiurgoWeb - GM Panel</title>
</head>
<body>
	<h1>DemiurgoWeb</h1>
	<h4>User: ${username}</h4>
	<div>
		<a href="seeallrooms">See all rooms</a>
	</div>
	<form method="get" action="room">
		<label for='pending'>Pending rooms:</label>
		<c:choose>
			<c:when test="${pendingtotal >= 1}">
				<select>
					<c:forEach var="room" items="${pending}">
						<option value="${room}">${room}</option>
					</c:forEach>
				</select>
				<input type="submit">
			</c:when>

			<c:otherwise>
        There are no pending rooms.
    </c:otherwise>
		</c:choose>

	</form>
	<a href="logout">Logout</a>
</body>
</html>