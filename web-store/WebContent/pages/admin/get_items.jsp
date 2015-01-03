<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytags.tld" prefix="myTag" %>

<style>
.descriptions_hs{
	display: none;
}
</style>

<script type="text/javascript">
	
</script>

	<table class="table">
		<tr>
			<th>Item id</th>
		    <th>Manufacturer</th>
		    <th>Model</th>
		    <th>Price</th>
		    <th>Warranty</th>	
		    <th>Category</th>	
		    <th></th>
		</tr>		

		<c:forEach var="item" items="${items}">
		<tr class='item${item.id}' onclick="toogle(${item.id});">
			<td><c:out value="${item.id}"></c:out></td>
	    	<td><c:out value="${item.manufacturer}"></c:out></td>
	    	<td><c:out value="${item.model}"></c:out></td>
			<td><c:out value="${item.price}"></c:out> $</td>
			<td><c:out value="${item.warranty}"></c:out></td>
			<td><c:out value="${item.category.name}"></c:out></td>
			<td><button class="deleteButton" id='item${item.id}' onclick="deleteItem(${item.id})">X</button></td>
		</tr>

		<myTag:printItemsDescriptions itemId="${item.id}" />
		<myTag:printItemsPhotosInfo itemId="${item.id}" />


	</c:forEach>
	</table>