<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<select name="itemManufacturersList" class="itemManufacturersList" >
	<option selected disabled value="">Item Brand</option>
	<c:forEach var="manufacturer" items="${manufacturers}">
		<option value="${manufacturer}"><c:out	value="${manufacturer}"></c:out></option>
	</c:forEach>
</select>

				