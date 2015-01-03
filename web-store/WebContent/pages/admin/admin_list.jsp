<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
#users tr:hover,#items tr:hover {
	color: #FDB45E;
	background: #FFF;
	box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.5);
}
#users tr button:hover,#items tr button:hover {
	color: #FFF;
	background: #FDB45E;
	box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.5);
}
.btnl{
	cursor: pointer;
}
.btnl:hover {
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

</script>

<section id="form">
	<div class="col-sm-10 col-sm-offset-1 line"></div>
	<div class="col-sm-10 col-sm-offset-1">
		<h2 class="btnl" id="btn1">List of all users</h2>
		<div id="users" >
		</div>
	</div>
	<div class="col-sm-10 col-sm-offset-1 line"></div>
	<div class="col-sm-10 col-sm-offset-1">
		<h2 class="btnl" id="btn2">List of all items</h2>
		<div id="items"></div>
	</div>
	<div class="col-sm-10 col-sm-offset-1 line"></div>

</section>