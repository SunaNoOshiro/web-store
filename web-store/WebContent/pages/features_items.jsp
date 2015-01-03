<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="/WEB-INF/mytags.tld" prefix="myTag" %>
<div class="features_items">	
	<h2 class="title text-center"><c:out value="${sessionScope.featuresItemsTitle }"></c:out></h2>
<jsp:include page="user/user_scripts.jsp"></jsp:include>
</div>
<myTag:printFeaturesItems userId="${sessionScope.userId }" pageId="1" bundle="${sessionScope.bundle }"/>	
