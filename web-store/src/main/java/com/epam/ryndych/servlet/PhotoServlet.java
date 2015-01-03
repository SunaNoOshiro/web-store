package com.epam.ryndych.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ryndych.database.model.Item;
import com.epam.ryndych.database.model.Photo;
import com.epam.ryndych.database.service.ItemService;
import com.epam.ryndych.database.service.PhotoService;

/**
 * Servlet implementation class PhotoServlet
 */
public class PhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoServlet() {
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
		String url = request.getParameter("url");
		String description = request.getParameter("description");
		String model = request.getParameter("model");
		String manufacturer = request.getParameter("manufacturer");
		
		
		String operation = request.getParameter("operation");
		
		if(operation.equals("getPhotos")){
			if(description != null && url!= null && model!= null && manufacturer!= null ){
				ArrayList<Item> items = ItemService.getAllItems();
				request.setAttribute("items", items);
				request.getRequestDispatcher("/pages/admin/get_items.jsp").forward(request, response);
			}
			else{
				System.out.println("Some of the Description parameters are empty");
			}
			
		}
		
		else if(operation.equals("insertPhoto")){
			if(description != null && url!= null && model!= null && manufacturer!= null ){
				Photo photo = new Photo();
				
				photo.setUrl(url);;
				photo.setDescription(description);
				
				Item item = ItemService.getItem(model, manufacturer);
				
				if (item != null) {
					boolean success = PhotoService.insertPhoto(photo);					
				
					if (success){						
						System.out.println("Photo insert was success");
						Photo insertedPhoto = PhotoService.getPhotoByURL(photo.getUrl());
					
						if(insertedPhoto!=null){
							success = PhotoService.insertPhotoToCatalog(item.getId(), insertedPhoto.getId())	;
							if (success){	
								response.getWriter().write("Photo insert was success");
							}
							else{
								System.out.println("Photo insert was not success");
								response.getWriter().write("Photo insert was not success");
							}
						}
						
					}						
					else{
						System.out.println("Photo insert was not success");
						response.getWriter().write("Photo insert was not success");
					}
						
				}
				else{
					System.out.println("Item nt found");
				}
			}
			else{
				System.out.println("Some of the Description parameters are empty");
			}
		}
		else if(operation.equals("deletePhoto")){
			if(description!= null ){	
				
				boolean success = PhotoService.deletePhotoByURL(url);
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
