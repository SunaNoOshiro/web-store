<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
#users tr:hover {
	color: #FDB45E;
	background: #FFF;
	box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.5);
}
#btn{
	cursor: pointer;
}
#btn:hover {
	color: #FDB45E;
}
h2{
	text-align: center;
}
.line{
    border: none; /* Убираем границу */
    background-color: #FDB45E; /* Цвет линии */
    color: #FDB45E; /* Цвет линии для IE6-7 */
    height: 1px; /* Толщина линии */
}
</style>


<script type="text/javascript">
	$(document).ready(function() {
		var usersList = true;
		var itemList = true;
		$('#btn').click(function () {
			if(usersList==false){
				usersList=true;
				$('#users').slideDown();
			}
			else{
				usersList=false;
				$('#users').slideUp();
			}
			
			if(itemList==false){
				itemList=true;
				$('#items').slideDown();
			}
			else{
				itemList=false;
				$('#items').slideUp();
			}	
	    });
		
		$.get("userlist", function(data, status) {
			$("#users").html(data);
		});
		$.get("admin/item?operation=getItems", function(data, status) {
			alert(data);
			$("#items").html(data);
		});

	});
</script>

<section id="form">
	<div class="col-sm-10 col-sm-offset-1 line"></div>
	<div class="col-sm-10 col-sm-offset-1">
		<h2 id="btn">List of all users</h2>
		<div id="users" >
		</div>
	</div>
	<div class="col-sm-10 col-sm-offset-1 line"></div>
	<div class="col-sm-10 col-sm-offset-1">
		<h2 id="btn">List of all items</h2>
		<div class="items"></div>
	</div>
	<div class="col-sm-10 col-sm-offset-1 line"></div>

</section>