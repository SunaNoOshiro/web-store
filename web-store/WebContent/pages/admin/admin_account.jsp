<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Cart | E-Shopper</title>
	<jsp:include page="../links.jsp" />
	<jsp:include page="../scripts.jsp" />
	<jsp:include page="admin_scripts.jsp" />
		<style type="text/css">
		
		#container-toogle {
			width:	100%;
			height: 100%;
			min-height:900px;			
			position: relative;
			overflow: hidden;
			background-color: #F0F0E9;
		}
		#container-toogle .col-sm-3, #container-toogle .col-sm-5 {
			background: #FFF;
			border: solid 1px  #F1F1E0;
		}
		#container-toogle .col-sm-4{
			background: #FFF;
			border: solid 1px  #F1F1E0;
		}
		#container-toogle .col-sm-5 div{
			background: #FFF;
		}
		#container-toogle button{	  
			border: medium none;
			border-radius: 0;
			display: block;
			font-family: 'Roboto', sans-serif;
			padding: 6px 25px;
		}
		#container-toogle .col-sm-5 input:hover{
		 	color: #FDB45E;
		 	background: #FFF;
		 	 box-shadow: inset 0px 0px 5px rgba(0,0,0,0.5);
		 	
		}
		#container-toogle button:hover{
		 	color: #FDB45E;
		 	background: #FFF;
		}
		
		#right, #left {
			-webkit-transition: all 1s ease;
			-moz-transition: all 1s ease;
			width:		100%;
			min-height:900px;	
			height: 100%;;
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
	<script type="text/javascript">
	$( document ).ready(function() {
	    $("#bleft, #bright").click(function(){
		    $("#left, #right").toggleClass("transitioned");
		  });
	});	
	</script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	
	
	<div id="container-toogle">
		<div id="right">			
			<button id="bright">Add Items etc.</button>
			<jsp:include page="admin_list.jsp" />
		</div>
		<div id="left">			
			<button id="bleft">Users</button>
			<jsp:include page="item_adding.jsp" />
		</div>
	</div>

</body>
</html>