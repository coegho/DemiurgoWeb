<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
	<tr>
		<th>Object ID</th>
		<th>Class</th>
		<th>User</th>
	</tr>
	<c:forEach var="obj" items="${room.objects}">
		<tr>
			<td>${obj.id}</td>
			<td>${obj.className}</td>
			<td>${obj.username}</td>
		</tr>
	</c:forEach>
</table>