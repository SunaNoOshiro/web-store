<script type="text/javascript">
	function insertToCart(id,userId,qty){
		//alert(id+""+userId);
		$.get("cart?operation=insertItem"+
				"&userId="+userId+
				"&itemId="+id+
				"&qty="+qty,
				function(data, status) {
					alert(data);					
				});
		
	};
	function incrementItemInCart(userId,itemId){
		$.get("cart?operation=updateItem"+
				"&userId="+userId+
				"&itemId="+itemId+
				"&qty="+1,
				function(data, status) {
					$("#cart_items").html(data);
				});
		
	};
	
	function decrementItemInCart(userId,itemId){
		$.get("cart?operation=updateItem"+
				"&userId="+userId+
				"&itemId="+itemId+
				"&qty="+(-1),
				function(data, status) {
					$("#cart_items").html(data);			
				});
		
	};
	
	function deleteItemInCart(userId,itemId){
		$.get("cart?operation=deleteItem"+
				"&userId="+userId+
				"&itemId="+itemId,
				function(data, status) {
					$("#cart_items").html(data);
				});
		
	};
</script>