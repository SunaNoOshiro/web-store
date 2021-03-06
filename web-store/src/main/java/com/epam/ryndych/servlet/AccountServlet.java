package com.epam.ryndych.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ryndych.database.logger.Logger;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
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
		if(request.getSession().getAttribute("userPermission")!=null){
			if(request.getSession().getAttribute("userPermission").equals("USER")){
				request.getRequestDispatcher("pages/user/account.jsp").forward(request, response);
			}
			else if(request.getSession().getAttribute("userPermission").equals("ADMIN")){
				request.getRequestDispatcher("pages/admin/admin_account.jsp").forward(request, response);
			}
		}
		else request.getRequestDispatcher("home").forward(request, response);		
	}

}
