<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Cart | E-Shopper</title>
	<jsp:include page="links.jsp" />
	<jsp:include page="scripts.jsp" />
		<style type="text/css">
		#container-toogle {
			width:	1300px;
			height: 700px;
			position: relative;
			overflow: hidden;
			background: #FE980F;
		}
		#container-toogle .col-sm-3, #container-toogle .col-sm-5 {
			background: #FFF;
			border: solid 1px  #F1F1E0;
		}
		#container-toogle .col-sm-4{
			background: #FFF;
			border-top: solid 1px  #F1F1E0;
		}
		#container-toogle button{	  
			border: medium none;
			border-radius: 0;
			display: block;
			font-family: 'Roboto', sans-serif;
			padding: 6px 25px;
		}
		
		#container-toogle button:hover{
		 	color: #FDB45E;
		 	background: #FFF;
		}
		
		#right, #left {
			-webkit-transition: all 1s ease;
			-moz-transition: all 1s ease;
			width:	1300px;
			height: 700px;
			position: absolute;
			padding: 10px;
		}
		
		#left {
			background-color: #F1F1E0;
			left: 0
		}
		
		#right {
			background-color: #F0F0E9;
			left: 1300px;
		}
		
		.transitioned {
			-webkit-transform: translate(-1300px, 0);
			-moz-transform: translate(-1300px, 0);
		}
	</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<script type="text/javascript">
	$( document ).ready(function() {
	    $("#bleft, #bright").click(function(){
		    $("#left, #right").toggleClass("transitioned");
		  });
	});
	
	</script>
	
	<div id="container-toogle">
		<div id="right">			
			<button id="bright">Add Items etc.</button>
			<jsp:include page="item_adding.jsp" />
		</div>
		<div id="left">			
			<button id="bleft">Users</button>
			<jsp:include page="item_adding.jsp" />
		</div>
	</div>

</body>
</html>