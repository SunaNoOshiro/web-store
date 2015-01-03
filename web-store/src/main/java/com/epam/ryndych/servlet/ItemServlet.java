package com.epam.ryndych.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ryndych.database.model.Category;
import com.epam.ryndych.database.model.Item;
import com.epam.ryndych.database.service.CategoryService;
import com.epam.ryndych.database.service.ItemService;

/**
 * Servlet implementation class ItemServlet
 */
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String model = request.getParameter("model");
		String manufacturer = request.getParameter("manufacturer");
		float price = 0;
		int warranty = 0;
		int itemId = 0;
		try {
			price = Float.parseFloat(request.getParameter("price"));
		} catch (Exception e) {
		}
		try {
			warranty = Integer.parseInt(request.getParameter("warranty"));
		} catch (Exception e) {
		}
		try {
			itemId = Integer.parseInt(request.getParameter("itemId"));
		} catch (Exception e) {
		}
		String categoryName = request.getParameter("category");

		String operation = request.getParameter("operation");
		System.out.println(operation);
		if(operation.equals("getItems")){
			ArrayList<Item> items = ItemService.getAllItems();
			request.setAttribute("items", items);
			request.getRequestDispatcher("/pages/admin/get_items.jsp").forward(request, response);
		}
		else if(operation.equals("getManufacturers")){
			ArrayList<String> manufacturers = ItemService.getAllManufacturers();
			
			request.setAttribute("manufacturers", manufacturers);
			request.getRequestDispatcher("/pages/admin/get_manufacturers.jsp").forward(request, response);
		}
		else if(operation.equals("getModels")){
			ArrayList<String> models = ItemService.getAllModels();
			
			request.setAttribute("models", models);
			request.getRequestDispatcher("/pages/admin/get_models.jsp").forward(request, response);
		}
		else if(operation.equals("insertItem")){
			if(model != null && manufacturer!= null && categoryName!= null && price>0 ){
				Item item = new Item();
				
				item.setModel(model);
				item.setManufacturer(manufacturer);
				item.setPrice(price);
				item.setWarranty(warranty);
				Category category = null;
				category = CategoryService.getCategoryByName(categoryName);
				item.setCategory(category);
				
				boolean success = ItemService.insertItem(item);
				if(success){
					System.out.println("Item insert was success");
					response.getWriter().write("Item insert was success");
				}
				else{
					System.out.println("Item insert was not success");
					response.getWriter().write("Item insert was not success");
				}
			}
			else{
				System.out.println("Some of the Item parameters are empty");
			}
		}
		else if(operation.equals("deleteItem")){
				boolean success = ItemService.deleteItemById(itemId);
				if (success){
					System.out.println("Item delete was success");
					response.getWriter().write("Item delete was success");
				}						
				else{
					System.out.println("Item delete was not success");
					response.getWriter().write("Item delete was not success");
				}
		}
		else{
			System.out.println("none");
		}
		
	}

}
