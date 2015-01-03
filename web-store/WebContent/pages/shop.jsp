<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/mytags.tld" prefix="myTag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Login | E-Shopper</title>
	<jsp:include page="links.jsp" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<jsp:include page="category.jsp" />
						<jsp:include page="brands.jsp" />
						<jsp:include page="price_range.jsp" />	
					</div>
				</div>
			
				<div class="col-sm-9 padding-right">	
				<div class="features_items">	
						<h2 class="title text-center"><c:out value="${sessionScope.featuresItemsTitle }"></c:out></h2>
						<c:if test="${pageId == null}">
							<myTag:printFeaturesItems userId="${sessionScope.userId }" pageId="1"/>	
						</c:if>
						<c:if test="${pageId != null}">
							<myTag:printFeaturesItems userId="${sessionScope.userId }" pageId="${pageId}"/>
						</c:if>		
						</div>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="footer.jsp" />
	<jsp:include page="scripts.jsp" />
</body>
</html>