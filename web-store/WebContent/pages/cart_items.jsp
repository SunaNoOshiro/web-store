<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="/WEB-INF/mytags.tld" prefix="myTag" %>

	<jsp:include page="user/user_scripts.jsp" />
	
<section id="cart_items">
	<div class="container">
		<div class="breadcrumbs">
			<ol class="breadcrumb">
				<li><a href="#">Home</a></li>
				<li class="active">Shopping Cart</li>
			</ol>
		</div>
	

	<div id="printItemsInCart">
		<myTag:printItemsInCart userId="${sessionScope.userId}" bundle="${sessionScope.bundle }"/>		
	</div>
		
	</div>
</section>
<!--/#cart_items-->