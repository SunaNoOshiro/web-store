package com.epam.ryndych.servlet;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ryndych.database.model.Basket;
import com.epam.ryndych.database.model.Item;
import com.epam.ryndych.database.service.CartService;
import com.epam.ryndych.database.service.ItemService;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		
		int itemId = 0;		
		int userId = 0;
		int qty = 0;
		try{
			itemId=Integer.parseInt(request.getParameter("itemId"));		
			userId=Integer.parseInt(request.getParameter("userId"));		
			qty=Integer.parseInt(request.getParameter("qty"));
		}catch (Exception e){	}
		System.out.println(operation);
		if(operation!=null && operation.equals("insertItem")){
			if(itemId>0 && userId>0){				
				Basket basket = CartService.getBasket(userId);
				Item item = ItemService.getItemById(itemId);

				boolean found = false;
				for(Entry<Item, Integer> e :basket.getItemList().entrySet()){
					if(e.getKey().getId()==item.getId()){
						found=true;
						boolean status = CartService.updateItemToBasket(itemId, userId, qty+e.getValue());
						if(status){
							System.out.println("Update item in cart was success");
						}
						else{
							System.out.println("Update item in cart was not success");
						}
					}
				}
				if(!found){
					boolean status = CartService.insertItemToBasket(itemId, userId, qty);
					if(status){
						System.out.println("Insert item in cart was success");
					}
					else{
						System.out.println("Insert item in cart was not success");
					}
				}
			}
		}
		else if(operation!=null && operation.equals("deleteItem")){			
			deleteItem(itemId, userId, request, response);
		}
		else if(operation!=null && operation.equals("updateItem")){
			if (itemId > 0 && userId > 0) {
				
				Basket basket = CartService.getBasket(userId);
				Item item = ItemService.getItemById(itemId);

				for(Entry<Item, Integer> e :basket.getItemList().entrySet()){
					if(e.getKey().getId()==item.getId()){
						if(e.getValue()==1 && qty==-1){
							deleteItem(itemId, userId, request, response);
						}
						else{
							boolean status = CartService.updateItemToBasket(itemId, userId, qty + e.getValue());
							if (status) {
								System.out
										.println("Update item in cart was success");
								request.getRequestDispatcher(
										"/pages/cart_items.jsp").forward(
										request, response);

							} else {
								System.out
										.println("Update item in cart was not success");
							}
						}
					}
				}
			}

		}
		else{
			request.getRequestDispatcher("pages/cart.jsp").forward(request, response);
		}
		
	}
	
	public void deleteItem(int itemId, int userId , HttpServletRequest request, HttpServletResponse response){
		if (itemId > 0 && userId > 0) {

			boolean status = CartService.deleteItemFromBasket(itemId, userId);

			if (status) {
				System.out.println("Delete item in cart was success");
				try {
					request.getRequestDispatcher("/pages/cart_items.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				System.out.println("Delete item in cart was not success");
			}
		}
	}

}
