<%@ page language="java" pageEncoding="UTF-8" %>

<!--login form-->
<div class="login-form">
	<h2>Login to your account</h2>
	<form action="user" method="post">
		<input name="from"  value="login" type="hidden"/> 
		<input type="text" placeholder="Login" name="login" /> 
		<input type="password"	placeholder="Password" name="password" />
		<span> 
			<input type="checkbox"	class="checkbox" name="keep"> Keep me signed in
		</span>
		<button type="submit" class="btn btn-default">Login</button>
	</form>
</div>
<!--/login form-->