<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2><c:out value="${sessionScope.categoryTitle }"></c:out></h2>
<!--category-products-->
<div class="panel-group category-products" id="accordian">
	<jsp:include page="get_categories.jsp"></jsp:include>
</div>
<!--/category-products-->