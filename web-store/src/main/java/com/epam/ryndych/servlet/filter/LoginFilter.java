package com.epam.ryndych.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;

		String uri = req.getRequestURI();
		HttpSession session = req.getSession(true);
		
		if ((uri.endsWith("html") || uri.endsWith("jsp"))) {
			request.getRequestDispatcher("/pages/errors/404.jsp").forward(request, response);
		} else {
			//resources
			if ((uri.endsWith("css") || uri.endsWith("js") || uri.endsWith("svg") || uri.endsWith("png")
					|| uri.endsWith("jpg") || uri.endsWith("woff") || uri.endsWith("otf"))) {
				chain.doFilter(request, response);
			} 
			//anonim
			else if (session.getAttribute("userPermission") == null) {
				if (uri.endsWith("login") || uri.endsWith("home")  || uri.endsWith("/") 
						|| uri.endsWith("product-details")	|| uri.endsWith("user") || uri.endsWith("user")) {
					chain.doFilter(request, response);
				} else {
					request.getRequestDispatcher("/pages/errors/mustToLogin.jsp").forward(request, response);
					}
			} 
			//admin
			else if (session.getAttribute("userPermission").equals("ADMIN")) {
				if ((uri.endsWith("account") || uri.endsWith("userlist") || uri.endsWith("user")|| uri.endsWith("login") 
						|| uri.endsWith("product-details") || uri.endsWith("home") || uri.endsWith("/") || uri.endsWith("admin/category")
						||  uri.endsWith("/admin/item") ||  uri.endsWith("/admin/description") ||uri.endsWith("/admin/photo"))) {
					chain.doFilter(request, response);
				} else {
					request.getRequestDispatcher("/pages/errors/404.jsp").forward(request, response);
				}

			} 
			//user
			else if (session.getAttribute("userPermission").equals("USER")) {
				if ((uri.endsWith("cart") || uri.endsWith("wishlist")
						|| uri.endsWith("product-details")|| uri.endsWith("account") || uri.endsWith("shop")
						|| uri.endsWith("login") || uri.endsWith("home") || uri.endsWith("/"))) {
					chain.doFilter(request, response);
				} else {
					request.getRequestDispatcher("/pages/errors/404.jsp").forward(request, response);
					
					//request.getRequestDispatcher("/pages/errors/notHavePermission.jsp").forward(request, response);
				}

			} else {
				request.getRequestDispatcher("/pages/errors/404.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
