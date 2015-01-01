<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	$(document).ready(
			function() {
				
				$.get("admin/category?operation=getCategories", function(data, status) {
					$(".category").html(data);
				});
				
				$("#addCategory").click(
						function() {
							alert(""+$("#categorySuper").val());
							$.get("admin/category?operation=insertCategory"+
									"&categoryName="+$("#categoryName").val()+
									"&categorySuper="+$("#categorySuper").val(),
									function(data, status) {
										alert(data);
									});
							$.get("admin/category?operation=getCategories", function(data, status) {
								$(".category").html(data);
							});
						});
				$("#addItem").click(
						function() {							
							$.get("admin/item?operation=insertItem"+
									"&model="+$("#model").val()+
									"&manufacturer="+$("#manufacturer").val()+
									"&price="+$("#price").val()+
									"&category="+$(".item_add #categorySuper").val()+
									"&warranty="+$(".item_add #warranty").val(),
									function(data, status) {
										alert(data);
									});
						});
			});
</script>

<section id="form">
	<div class="col-sm-5 col-sm-offset-1">
		<div class=" ">
			<h2>Category adding</h2>
			<div class="category_add">
				<input type="text" placeholder="Category name" name="categoryName" id="categoryName">
				<div class="category">
					<select name="categorySuper" id="categorySuper">
						<option selected disabled value="">Category Super</option>
						<option value="">None</option>
					</select>
				</div> 
				<input type="button" value="addCategory" name="addCategory" id="addCategory">
			</div>
		</div>

		<h2>Item adding</h2>
		<div class="item_add">
			<input type="text" placeholder="Model" name="model" id="model"> <input
				type="text" placeholder="Manufacturer" name="manufacturer" id="manufacturer">
			<input type="text" placeholder="Price" name="price" id="price">
			<div class="category">
					<select name="categorySuper" id="categorySuper">
						<option selected disabled value="">Category Super</option>
						<option value="null">None</option>
					</select>
			</div> 
			<select name="warranty" id="warranty">
				<option selected disabled value="">Warranty</option>
				<option value="0">None</option>
				<option value="1">1 Month</option>
				<option value="3">3 Month</option>
				<option value="6">6 Month</option>
				<option value="12">12 Month</option>
				<option value="24">24 Month</option>
			</select> <input type="button" value="addItem" name="addItem" id="addItem">
		</div>
	</div>

	<div class="col-sm-5 ">
		<h2>Discription adding</h2>
		<div class="description_add">
			<select name="itemManufacturersList">
				<option selected disabled value="">Item Brand</option>
				<option value="1">Model</option>
				<option value="3">3 Month</option>
				<option value="6">6 Month</option>
				<option value="12">12 Month</option>
				<option value="24">24 Month</option>
			</select>
			<select name="itemModelsList">
				<option selected disabled value="">Item Model</option>
				<option value="1">Model</option>
				<option value="3">3 Month</option>
				<option value="6">6 Month</option>
				<option value="12">12 Month</option>
				<option value="24">24 Month</option>
			</select>
			<table class="table">
				<tr>
					<th>Name</th>
					<th>Discription</th>
				</tr>
				<tr>
					<td><input type="text" placeholder="Name"
						name="descriptionName"></td>
					<td><input type="text" placeholder="Description"
						name="descriptionValue"></td>
				</tr>
			</table>
			 
			<input type="button" value="+" name="addDescriptionItem"> 
			<input type="button" value="addDescription" name="addDescription">
		</div>
		<div>
			<h2>Image adding</h2>
			<div class="description_add">
				<select name="itemManufacturersList">
					<option selected disabled value="">Item Brand</option>
					<option value="1">Model</option>
					<option value="3">3 Month</option>
					<option value="6">6 Month</option>
					<option value="12">12 Month</option>
					<option value="24">24 Month</option>
				</select>
				<select name="itemModelsList">
					<option selected disabled value="">Item Model</option>
					<option value="1">Model</option>
					<option value="3">3 Month</option>
					<option value="6">6 Month</option>
					<option value="12">12 Month</option>
					<option value="24">24 Month</option>
				</select>
				<table class="table">
					<tr>
						<th>URL</th>
						<th>Discription</th>
					</tr>
					<tr>
						<td><input type="text" placeholder="URL" name="imageURL"></td>
						<td><input type="text" placeholder="Description"
							name="imageDescriptionValue"></td>
					</tr>
				</table>
				<input type="button" value="+" name="addImageItem"> <input
					type="button" value="addImage" name="addImage">
			</div>
		</div>
	</div>


</section>