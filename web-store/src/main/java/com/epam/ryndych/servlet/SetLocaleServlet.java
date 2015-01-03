package com.epam.ryndych.servlet;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.ryndych.database.logger.Logger;

/**
 * Servlet implementation class SetLocaleServlet
 */
@WebServlet("/SetLocaleServlet")
public class SetLocaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetLocaleServlet() {
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
		
		String language = new String("en");
	    String country = new String("US");
		
		Locale locale = new Locale(language, country);
		setLocaleToSession(locale, request, response);

		//Locale bLocale = new Locale.Builder().setLanguage(language).setRegion(country).build();

	}
	public static void setLocaleToSession(Locale locale,HttpServletRequest request,HttpServletResponse response){		
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		HttpSession session = request.getSession(true);
		
		session.setAttribute("headerTopCart", bundle.getString("header-top.cart"));
		session.setAttribute("headerTopHome", bundle.getString("header-top.home"));
		session.setAttribute("headerTopWishlist", bundle.getString("header-top.wishlist"));
		session.setAttribute("headerTopLogin", bundle.getString("header-top.login"));
		session.setAttribute("headerTopLogout", bundle.getString("header-top.logout"));
		
				
		session.setAttribute("headerBottomCart", bundle.getString("header-bottom.cart"));
		session.setAttribute("headerBottomHome", bundle.getString("header-bottom.home"));
		session.setAttribute("headerBottomLogin", bundle.getString("header-bottom.login"));
		session.setAttribute("headerBottomProducts", bundle.getString("header-bottom.products"));
		session.setAttribute("headerBottomShop", bundle.getString("header-bottom.shop"));
		
		session.setAttribute("featuresItemsTitle", bundle.getString("features-items.title"));
		session.setAttribute("featuresItemsAddToCart", bundle.getString("features-items.add-to-cart"));
		session.setAttribute("featuresItemsAddToWishlist", bundle.getString("features-items.add-to-wishlist"));
		session.setAttribute("featuresItemsDetails", bundle.getString("features-items.details"));
		
		session.setAttribute("categoryTitle", bundle.getString("category.title"));
		session.setAttribute("brandsTitle", bundle.getString("brands.title"));
		session.setAttribute("priceRange", bundle.getString("price-range"));
		
		session.setAttribute("loginFirstName", bundle.getString("login.first-name"));
		session.setAttribute("loginLastName", bundle.getString("login.last-name"));
		session.setAttribute("loginLogin", bundle.getString("login.login"));
		session.setAttribute("loginPassword", bundle.getString("login.password"));
		session.setAttribute("loginConfirmPassword", bundle.getString("login.confirm-password"));
		session.setAttribute("loginEmail", bundle.getString("login.email"));
		session.setAttribute("loginTelNumber", bundle.getString("login.tel-number"));
		session.setAttribute("loginSelectCountry", bundle.getString("login.select-country"));
		session.setAttribute("loginState", bundle.getString("login.state"));
		session.setAttribute("loginCity", bundle.getString("login.city"));
		session.setAttribute("loginAddress", bundle.getString("login.address"));
		session.setAttribute("loginSelectSex", bundle.getString("login.select-sex"));
		session.setAttribute("loginTitle", bundle.getString("login.title"));
		session.setAttribute("loginSigninTitle", bundle.getString("login.signin-title"));
		session.setAttribute("loginOr", bundle.getString("login.or"));
		session.setAttribute("loginSignup", bundle.getString("login.signup"));
		session.setAttribute("loginMale", bundle.getString("login.male"));
		session.setAttribute("loginFemale", bundle.getString("login.female"));
		session.setAttribute("loginBirthday", bundle.getString("login.birthday"));
		
		Logger.LOGGER.info("set Locale");
	}

}
