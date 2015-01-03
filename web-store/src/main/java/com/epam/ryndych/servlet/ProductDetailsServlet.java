package com.epam.ryndych.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ryndych.database.logger.Logger;

/**
 * Servlet implementation class ProductDetailsServlet
 */
public class ProductDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailsServlet() {
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
		int itemId=0;
		try{
			itemId = Integer.parseInt(request.getParameter("itemId"));
			request.setAttribute("itemId", itemId);
			request.getRequestDispatcher("pages/product-details.jsp").forward(request, response);
		}catch(Exception e) {
			request.getRequestDispatcher("pages/erors/404.jsp").forward(request, response);
		}
		
	}

}
