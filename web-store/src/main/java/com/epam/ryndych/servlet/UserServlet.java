package com.epam.ryndych.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.ryndych.database.model.User;
import com.epam.ryndych.database.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String operation = "";
		String from = request.getParameter("from");
		int userId=0;
		try{
			operation=request.getParameter("operation");
			userId = Integer.parseInt(request.getParameter("userId"));
		}
		catch(Exception e){
			
		}
		System.out.println(operation);
		if (from != null) {
			if (from.equals("login")) {
				User user = login(request);
				if (user != null) {
					System.out.println("User was found");

					// Create a session object if it is already not created.
					HttpSession session = request.getSession(true);

					// Check if this is new comer on your web page.
					session.setAttribute("userLogin", user.getLogin());
					session.setAttribute("userId", user.getId());
					session.setAttribute("userPermission", user.getPermission());
					
					request.getRequestDispatcher("home").forward(
							request, response);
				}

			} else if (from.equals("signin")) {
				// add user to bd
				insertNewUser(request);
				request.getRequestDispatcher("pages/login.jsp").forward(
						request, response);
			}
			else if(from.equals("update_profile")){
				//update user's info
			}
		} 
		else if(operation.equals("deleteItem")){
			boolean success = UserService.deleteUserByID(userId);
			if (success){
				System.out.println("User delete was success");
				response.getWriter().write("User delete was success");
			}						
			else{
				System.out.println("User delete was not success");
				response.getWriter().write("User delete was not success");
			}
	}
		else {
			request.getRequestDispatcher("home").forward(request, response);
		}

	}

	private User login(HttpServletRequest request) {
		String login = request.getParameter("login");
		System.out.println("Finding user...");
		User user = UserService.getUserByLogin(login);
		return user;
	}

	private User insertNewUser(HttpServletRequest request) {
		User user = new User();
		System.out.println("Adding user to DB...");

		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setLogin(request.getParameter("login"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setPhoneNumber(request.getParameter("phone"));
		user.setCity(request.getParameter("city"));
		user.setAddress(request.getParameter("address"));
		user.setState(request.getParameter("state"));
		user.setPermission("user");
		user.setBlackList(false);
		user.setSex(request.getParameter("sex"));
		user.setCountry(request.getParameter("country"));
		try {
			user.setBirthday(Date.valueOf(request.getParameter("birthday")));

		} catch (IllegalArgumentException e) {
			System.out.println("Request parameter birthday was null");
		}

		if (user.getAddress() == null || user.getBirthday() == null
				|| user.getCity() == null || user.getCountry() == null
				|| user.getEmail() == null || user.getFirstName() == null
				|| user.getLastName() == null || user.getLogin() == null
				|| user.getPassword() == null || user.getPhoneNumber() == null) {
			System.out
					.println("User wasn't added to DB, because one of the parameters was null");
		} else {
			if (UserService.insertUser(user))
				System.out.println("User was added to DB");
			else
				System.out.println("User was not added to DB");
		}

		return user;
	}
}
