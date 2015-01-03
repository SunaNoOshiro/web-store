<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<select name="itemModelsList" class="itemModelsList" >
	<option selected disabled value="">Item Model</option>
	<c:forEach var="model" items="${models}">
		<option value="${model}"><c:out	value="${model}"></c:out></option>
	</c:forEach>
</select>