<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--login form-->
<div class="login-form">
	<h2><c:out value="${sessionScope.loginTitle }"></c:out></h2>
	<form action="user" method="post">
		<input name="from"  value="login" type="hidden"/> 
		<input type="text" placeholder="${sessionScope.loginLogin }" name="login" /> 
		<input type="password"	placeholder="${sessionScope.loginPassword }" name="password" />		
		<button type="submit" class="btn btn-default"><c:out value="${sessionScope.loginLogin }"></c:out></button>
	</form>
</div>
<!--/login form-->