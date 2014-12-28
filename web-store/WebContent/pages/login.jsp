<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Login | E-Shopper</title>
	<jsp:include page="links.jsp" />
</head>
<!--/head-->

<body>
	<jsp:include page="header.jsp" />

	<!--form-->
	<section id="form">
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-1">
					<jsp:include page="login_form.jsp" />
				</div>
				<div class="col-sm-2">
					<h2 class="or">OR</h2>
				</div>
				<div class="col-sm-4">
					<jsp:include page="signin_form.jsp" />
				</div>
			</div>
		</div>
	</section>
	<!--/form-->

	<!--   -->
	<jsp:include page="scripts.jsp" />
</body>
</html>
