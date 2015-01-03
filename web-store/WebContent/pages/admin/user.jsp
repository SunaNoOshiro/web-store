<%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/javascript">

</script>
	<table class="table">
		<tr>
		    <th>User Id</th>
		    <th>Login</th>
		    <th>First Name</th>
		    <th>Last Name</th>	    
		    <th>Sex</th>
		    <th>Birthday</th>
		    <th>Permission</th>
		    <th>Country</th>	    
		    <th>Email</th>
		    <th></th>
		</tr>		
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<c:forEach var="user" items="${users}">
		<tr class="user${user.id}">
	    	<td><c:out value="${user.id}"></c:out></td>
	    	<td><c:out value="${user.login}"></c:out></td>
			<td><c:out value="${user.firstName}"></c:out></td>
			<td><c:out value="${user.lastName}"></c:out></td>
			<td><c:out value="${user.sex}"></c:out></td>
			<td><c:out value="${user.birthday}"></c:out></td>
			<td><c:out value="${user.permission}"></c:out></td>
			<td><c:out value="${user.country}"></c:out></td>
			<td><c:out value="${user.email}"></c:out></td>
			<td><button id="user${user.id}" onclick="deleteUser(${user.id});">X</button></td>
		</tr>
		</c:forEach>
	</table>