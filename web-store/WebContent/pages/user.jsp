<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Users | E-Shopper</title>
	<jsp:include page="links.jsp" />
</head>
<body>
	<jsp:include page="header.jsp" />
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
		</tr>		
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<c:forEach var="user" items="${users}">
		<tr>
	    	<td><c:out value="${user.id}"></c:out></td>
	    	<td><c:out value="${user.login}"></c:out></td>
			<td><c:out value="${user.firstName}"></c:out></td>
			<td><c:out value="${user.lastName}"></c:out></td>
			<td><c:out value="${user.sex}"></c:out></td>
			<td><c:out value="${user.birthday}"></c:out></td>
			<td><c:out value="${user.permission}"></c:out></td>
			<td><c:out value="${user.country}"></c:out></td>
			<td><c:out value="${user.email}"></c:out></td>
		</tr>
		</c:forEach>
	</table>
	<jsp:include page="scripts.jsp" />
</body>
</html>