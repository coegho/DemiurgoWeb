<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Demiurgo Web - Login</title>
</head>
<body>
	<tags:login />
	<c:if test="${error}">
		<p>Login error</p>
	</c:if>
</body>
</html>