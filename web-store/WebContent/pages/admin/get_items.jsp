<%@ page language="java" pageEncoding="UTF-8" %>

	<table class="table">
		<tr>
		    <th>User Id</th>
		    <th>Login</th>
		    <th>First Name</th>
		    <th>Last Name</th>	
		</tr>		
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<c:forEach var="item" items="${items}">
		<tr>
	    	<td><c:out value="${item.manufacturer}"></c:out></td>
	    	<td><c:out value="${item.model}"></c:out></td>
			<td><c:out value="${item.price}"></c:out></td>
			<td><c:out value="${item.warranty}"></c:out></td>
		</tr>
		</c:forEach>
	</table>