<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
	
<!--header-bottom-->
<div class="header-bottom">
	<div class="container">
		<div class="row">
			<div class="col-sm-9">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> 
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span>
					</button>
				</div>
				<jsp:include page="menu.jsp" />
			</div>
			<div class="col-sm-3">
				<div class="search_box pull-right">
					<input type="text" placeholder="Search" />
				</div>
			</div>
		</div>
	</div>
</div>
<!--/header-bottom-->