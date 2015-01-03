<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="mainmenu pull-left">
	<ul class="nav navbar-nav collapse navbar-collapse">
		<li><a href="home" class="active"><c:out value="${sessionScope.headerBottomHome }"></c:out></a></li>
		<li class="dropdown"><a href="#"><c:out value="${sessionScope.headerBottomShop }"></c:out><i class="fa fa-angle-down"></i></a>
			<ul role="menu" class="sub-menu">
				<li><a href="shop"><c:out value="${sessionScope.headerBottomShop }"></c:out></a></li>
				<li><a href="cart"><c:out value="${sessionScope.headerBottomCart }"></c:out></a></li>
				<li><a href="login"><c:out value="${sessionScope.headerBottomLogin }"></c:out></a></li>
			</ul>
		</li>
	</ul>
</div>