<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Cart | E-Shopper</title>
	<jsp:include page="links.jsp" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="cart_items">
		<jsp:include page="cart_items.jsp" />
	</div>	
	<jsp:include page="cart_action.jsp" />
	<jsp:include page="footer.jsp" />
	<jsp:include page="scripts.jsp" />
</body>
</html>