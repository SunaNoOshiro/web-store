package com.epam.ryndych.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ryndych.database.logger.Logger;
import com.epam.ryndych.database.model.Category;
import com.epam.ryndych.database.service.CategoryService;

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger.LOGGER.info(request.getRequestURI());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger.LOGGER.info(request.getRequestURI());
		String categoryName = request.getParameter("categoryName");
		String categorySuperName = request.getParameter("categorySuper");
		String operation = request.getParameter("operation");
		
		Logger.LOGGER.info(  operation);
		if(operation.equals("getCategories")){
			ArrayList<Category> categories = CategoryService.getAllCategories();
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/pages/admin/get_categories.jsp").forward(request, response);
		}
		else if(operation.equals("insertCategory")){
			if(categoryName != null ){
				Category category = new Category();
				category.setName(categoryName);
				Category categorySuper =  CategoryService.getCategoryByName(categorySuperName);
				category.setSuperCategoty(categorySuper.getName());
				boolean success = CategoryService.insertCategory(category);
				if(success)
					Logger.LOGGER.info("Category insert was success");
				else
					Logger.LOGGER.info("Category insert was not success");
			}
			else{
				Logger.LOGGER.info("Some of the Category parameters are empty");
			}
		}
		else{
			Logger.LOGGER.error("none");
		}
		
	}

}
