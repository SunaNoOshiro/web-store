<script type="text/javascript">
	$(document).ready(function() {
		
			
			$.get("admin/category?operation=getCategories", function(data, status) {
				$(".category").html(data);
			});
			
			$.get("admin/item?operation=getModels", function(data, status) {
				$(".models").html(data);
			});
			
			$.get("admin/item?operation=getManufacturers", function(data, status) {
				$(".manufacturers").html(data);
			});
			//$("#addDescriptionItem").click(
			///		function(){
			//			$(".description_add .table");
			//			
			//});
			
			$("#addCategory").click(
					function() {							
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
						$.get("admin/item?operation=getItems", function(data, status) {
							$("#items").html(data);
						});
					});
			$("#addDescription").click(
					function() {							
						$.get("admin/description?operation=insertDescriptions"+
								"&model="+$(".description_add .itemModelsList").val()+
								"&manufacturer="+$(".description_add .itemManufacturersList").val()+
								"&descriptionName="+$(".description_add #descriptionName").val()+
								"&descriptionValue="+$(".description_add #descriptionValue").val(),
								function(data, status) {
									alert(data);
								});
						
						$.get("admin/item?operation=getItems", function(data, status) {
							$("#items").html(data);
						});
					});
			$(".image_add #addImage").click(				
					function() {
						alert("add image");
						$.get("admin/photo?operation=insertPhoto"+
								"&model="+$(".image_add .itemModelsList").val()+
								"&manufacturer="+$(".image_add .itemManufacturersList").val()+
								"&url="+$(".image_add #imageURL").val()+
								"&description="+$(".image_add #description").val(),
								function(data, status) {
									alert(data);
								});
						$.get("admin/item?operation=getItems", function(data, status) {
							$("#items").html(data);
						});
					});
		
		var usersList = true;
		var itemList = true;
		
		$('#btn1').click(function () {
			if(usersList==false){
				usersList=true;
				$('#users').slideDown();
			}
			else{
				usersList=false;
				$('#users').slideUp();
			}	
	    });
		$('#btn2').click(function () {
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
			$("#items").html(data);
		});

	});
	
	function deleteUser(id){
		$.get("user?operation=deleteItem"+
				"&userId="+id,
				function(data, status) {
					alert(data);
				});
		$.get("userlist", function(data, status) {
			$("#users").html(data);
		});
	};
	
	function deleteImage(id){
		
	};
	function toogle(id) {
		//alert(id);
		$(".description").toggleClass("descriptions_hs"); 
		$(".description"+id).toggleClass("descriptions_hs"); 
		
		$(".image").toggleClass("descriptions_hs"); 
		$(".image"+id).toggleClass("descriptions_hs"); 
	}
	function deleteItem(id){
		$.get("admin/item?operation=deleteItem"+
				"&itemId="+id,
				function(data, status) {
					alert(data);
				});
		$.get("admin/item?operation=getItems", function(data, status) {
			$("#items").html(data);
		});
	}
	function deleteDescripton(name,id) {
									
			$.get("admin/description?operation=deleteDescriptions"+
					"&itemId="+id+
					"&descriptionName="+name,
					function(data, status) {
						alert(data);
					});
			$.get("admin/item?operation=getItems", function(data, status) {
				$("#items").html(data);
			});
			
		
	}
</script>