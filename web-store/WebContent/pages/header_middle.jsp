<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!--header-middle-->
<div class="header-middle">
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<div class="logo pull-left">
					<a href="home"><img src="images/logo.png" alt="" /></a>
				</div>
				<div class="btn-group pull-right">
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle usa"
							data-toggle="dropdown">
							UA <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a href="#">UA</a></li>
							<li><a href="#">UK</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="shop-menu pull-right">
					<ul class="nav navbar-nav">
						<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
						
						<c:if test="${sessionScope.userLogin == null}">
							<li><a href="login"><i class="fa fa-lock"></i><c:out value="${sessionScope.headerTopLogin }"></c:out></a></li>
							<li><a href="wishlist"><i class="fa fa-star"></i><c:out value="${sessionScope.headerTopWishlist }"></c:out></a></li>
							<li><a href="home"><i class="glyphicon glyphicon-home"></i><c:out value="${sessionScope.headerTopHome }"></c:out></a></li>
							<li><a href="cart"><i class="fa fa-shopping-cart"></i><c:out value="${sessionScope.headerTopCart }"></c:out></a></li>
							</c:if>						
						
						<c:if test="${sessionScope.userLogin != null}">
							<c:if test="${sessionScope.userPermission == 'ADMIN'}">
								<li><a href="account"><i class="fa fa-user"></i><c:out value="${sessionScope.userLogin }"></c:out></a></li>
								<li><a href="home"><i class="glyphicon glyphicon-home"></i><c:out value="${sessionScope.headerTopHome }"></c:out></a></li>
								<li><a href="login?logout=true"><i class="fa fa-lock"></i><c:out value="${sessionScope.headerTopLogout}"></c:out></a></li>
							</c:if>
							<c:if test="${sessionScope.userPermission != 'ADMIN'}">
								<li><a href="account"><i class="fa fa-user"></i><c:out value="${sessionScope.userLogin }"></c:out></a></li>
								<li><a href="wishlist"><i class="fa fa-star"></i><c:out value="${sessionScope.headerTopWishlist }"></c:out></a></li>
								<li><a href="home"><i class="glyphicon glyphicon-home"></i><c:out value="${sessionScope.headerTopHome }"></c:out></a></li>
								<li><a href="cart"><i class="fa fa-shopping-cart"></i><c:out value="${sessionScope.headerTopCart }"></c:out></a></li>
								<li><a href="login?logout=true"><i class="fa fa-lock"></i><c:out value="${sessionScope.headerTopLogout }"></c:out></a></li>
							</c:if>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<!--/header-middle-->