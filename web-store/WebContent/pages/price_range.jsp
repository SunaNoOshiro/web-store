<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="price-range">
	<h2><c:out value="${sessionScope.priceRange }"></c:out></h2>
	<div class="well text-center">
		<input type="text" class="span2" value="" data-slider-min="0"	data-slider-max="5000" data-slider-step="5"	data-slider-value="[250,450]" id="sl2"><br /> 
		<b 	class="pull-left">$ 0</b> <b class="pull-right">$ 5000</b>
	</div>
</div>
<!--/price-range-->
