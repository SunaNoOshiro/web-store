package com.epam.ryndych.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.ryndych.database.logger.Logger;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		if(request.getSession().getAttribute("userLogin") != null && request.getParameter("logout") != null){
			if(request.getParameter("logout").equals("true")){
				HttpSession session = request.getSession(true);
				session.setAttribute("userLogin", null);
				session.setAttribute("userId", null);
				session.setAttribute("userPermission", null);
				String language = new String("en");
			    String country = new String("US");
				
				Locale locale = new Locale(language, country);
				SetLocaleServlet.setLocaleToSession(locale, request, response);
				request.getRequestDispatcher("login").forward(request, response);
			}	
			else
				request.getRequestDispatcher("home").forward(request, response);
		}
		else{
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		}
	}

}
