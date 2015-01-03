<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<select name="categorySuper" id="categorySuper">
	<option selected disabled value="">Category Super</option>
	<c:forEach var="category" items="${categories}">
		<option value="${category.name}"><c:out	value="${category.name}"></c:out></option>
	</c:forEach>
</select>
