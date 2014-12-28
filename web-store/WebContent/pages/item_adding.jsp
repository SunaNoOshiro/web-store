<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section id="form">
	<div class="col-sm-5 col-sm-offset-1">
		<h2>Item adding</h2>
		<div class="item_add">
			<input type="text" placeholder="Model" name="model"> 
			<input type="text" placeholder="Manufacturer" name="manufacturer">
			<input type="text" placeholder="Price" name="price"> 
			<select	name="category">
				<option selected disabled value="">Category</option>
				<option value="">None</option>
				<option value="Komputer">Komputer</option>
			</select> <select name="warranty">
				<option selected disabled value="">Warranty</option>
				<option value="0">None</option>
				<option value="1">1 Month</option>
				<option value="3">3 Month</option>
				<option value="6">6 Month</option>
				<option value="12">12 Month</option>
				<option value="24">24 Month</option>
			</select> 
			<input type="button" value="addItem" name="addItem">
		</div>
		<div class=" ">
		<h2>Category adding</h2>
		<div class="category_add">
			<input type="text" placeholder="Category name" name="categoryName">
			<select name="categorySuper">
				<option selected disabled value="">Category</option>
				<option value="">None</option>
				<option value="Komputer">Komputer</option>
			</select> <input type="button" value="addCategory" name="addCategory">
		</div>
	</div>
	</div>

	<div class="col-sm-5 ">
		<h2>Discription adding</h2>
		<div class="discription_add">
			<table class="table">
				<tr>
					<th>Name</th>
					<th>Discription</th>
				</tr>
				<tr>
					<td><input type="text" placeholder="Name" name="descriptionName"></td>
					<td><input type="text" placeholder="Description" name="descriptionValue"></td>
				</tr>
			</table>
			<input type="button" value="+" name="addDescriptionItem"> 
			<input type="button" value="addDescription" name="addDescription">
		</div>
		<div>
		<h2>Image adding</h2>
		<div class="discription_add">
			<table class="table">
				<tr>
					<th>URL</th>
					<th>Discription</th>
				</tr>
				<tr>
					<td><input type="text" placeholder="URL" name="imageURL"></td>
					<td><input type="text" placeholder="Description" name="imageDescriptionValue"></td>
				</tr>
			</table>
			<input type="button" value="+" name="addImageItem"> 
			<input type="button" value="addDescription" name="addImage">
		</div>
		</div>
	</div>

	
</section>