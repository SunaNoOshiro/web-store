package com.epam.ryndych.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ryndych.database.model.Description;
import com.epam.ryndych.database.model.Item;
import com.epam.ryndych.database.service.DescriptionService;
import com.epam.ryndych.database.service.ItemService;

/**
 * Servlet implementation class Descriptionservlet
 */
public class DescriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DescriptionServlet() {
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
		String descriptionName = request.getParameter("descriptionName");
		String descriptionValue = request.getParameter("descriptionValue");
		String model = request.getParameter("model");
		String manufacturer = request.getParameter("manufacturer");
		int itemId = 0;
		try{
			itemId = Integer.parseInt(request.getParameter("itemId"));
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		String operation = request.getParameter("operation");
		
		if(operation.equals("getDescriptions")){
			if(descriptionName != null && descriptionValue!= null && model!= null && manufacturer!= null ){
				ArrayList<Item> items = ItemService.getAllItems();
				request.setAttribute("items", items);
				request.getRequestDispatcher("/pages/admin/get_items.jsp").forward(request, response);
			}
			else{
				System.out.println("Some of the Description parameters are empty");
			}
			
		}
		
		else if(operation.equals("insertDescriptions")){
			if(descriptionName != null && descriptionValue!= null && model!= null && manufacturer!= null ){
				Description description = new Description();
				
				description.setName(descriptionName);
				description.setValue(descriptionValue);
				
				Item item = ItemService.getItem(model, manufacturer);
				
				if (item != null) {
					boolean success = DescriptionService.insertDescription(
							description, item.getId());
					if (success){
						System.out.println("Description insert was success");
						response.getWriter().write("Description insert was success");
					}						
					else{
						System.out.println("Description insert was not success");
						response.getWriter().write("Description insert was not success");
					}
						
				}
			}
			else{
				System.out.println("Some of the Description parameters are empty");
			}
		}
		else if(operation.equals("deleteDescriptions")){
			if(descriptionName != null && itemId>0 ){				
				boolean success = DescriptionService.deleteDescriptionByID(itemId, descriptionName);
				if (success){
					System.out.println("Description delete was success");
					response.getWriter().write("Description delete was success");
				}						
				else{
					System.out.println("Description delete was not success");
					response.getWriter().write("Description delete was not success");
				}
			}
			else{
				System.out.println("Some of the Description parameters are empty");
			}
		}
		else{
			System.out.println("none");
		}
		
	}

}
