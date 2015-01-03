package com.epam.ryndych.servlet.jsp.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import netscape.javascript.JSObject;

import com.epam.ryndych.database.logger.Logger;
import com.epam.ryndych.database.model.Basket;
import com.epam.ryndych.database.model.Item;
import com.epam.ryndych.database.model.Photo;
import com.epam.ryndych.database.service.CartService;
import com.epam.ryndych.database.service.PhotoService;


public class PrintItemsInCart extends SimpleTagSupport {

	private Basket cart = null;
	private ResourceBundle bundle = null;
	
	private StringWriter sw = new StringWriter();
	private int userId ;
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setBundle(ResourceBundle bundle) {
		try{
			this.bundle = bundle;
		}
		catch(Exception e){
			Logger.LOGGER.error(e.getMessage());
		}
	}
	
	public void doTag() throws JspException, IOException {
		Logger.LOGGER.info(this.getClass().getSimpleName());
		cart = CartService.getBasket(userId);
		if (cart != null && bundle !=null) {
			StringBuilder sb = new StringBuilder();			
			JspWriter out = getJspContext().getOut();
			
			sb.append("<div class=\"table-responsive cart_info\">");	
			sb.append("<table class=\"table table-condensed\">");
			
			sb.append("<thead>");
				sb.append("<tr class=\"cart_menu\">");
					sb.append("<td class=\"image\">");
							//+ "Item"
					sb.append(bundle.getString("cart.item"));
					sb.append("</td>");
					sb.append("<td class=\"description\"></td>");
					sb.append("<td class=\"price\">");
							//+ "Price"
					sb.append(bundle.getString("cart.price"));
					sb.append("</td>");
					sb.append("<td class=\"quantity\">");
							//+ "Quantity"
					sb.append(bundle.getString("cart.qty"));
					sb.append("</td>");				
					sb.append("<td class=\"total\">");
							//+ "Total"
					sb.append(bundle.getString("cart.total"));
					sb.append("</td>");
					sb.append("<td></td>");
				sb.append("</tr>");
			sb.append("</thead>");
			
			
			sb.append("<tbody>");	
						
			for(Entry<Item, Integer> itelMap :cart.getItemList().entrySet()){
				ArrayList<Photo> photos = PhotoService.getPhotoGalery(itelMap.getKey().getId());
				sb.append("<tr>");
					sb.append("<td class=\"cart_product\">");
						sb.append("<a href=\"\">");			
							sb.append("<img src=\"" + photos.get(0).getUrl()+"\" alt=\"\">");
						sb.append("</a>");
					sb.append("</td>");				
					sb.append("<td class=\"cart_description\">");
						sb.append("<h4>");
							sb.append("<a href=\"\">");
								sb.append(itelMap.getKey().getManufacturer() +" ");
								sb.append(itelMap.getKey().getModel());
							sb.append("</a>");
						sb.append("</h4>");
						sb.append("<p>");
								//+ "Web ID: "
						sb.append(bundle.getString("cart.webid"));
						sb.append(": ");
						sb.append(itelMap.getKey().getId());
						sb.append("</p>");
					sb.append("</td>");
					sb.append("<td class=\"cart_price\">");
						sb.append("<p>$");
						sb.append(itelMap.getKey().getPrice());
						sb.append("</p>");
					sb.append("</td>");
					sb.append("<td class=\"cart_quantity\">");
						sb.append("<div class=\"cart_quantity_button\">");
							sb.append("<a class=\"cart_quantity_up\"  onclick=\"incrementItemInCart(");
							sb.append(userId);
							sb.append(",");
							sb.append(itelMap.getKey().getId());
							sb.append(");\"");
							sb.append(" > + ");
							sb.append("</a>");
							
							sb.append(" <input class=\"cart_quantity_input ");
							sb.append(itelMap.getKey().getId());
							sb.append("\"  name=\"quantity\" ");
							sb.append("onclick=\"updateItemInCart(");
							sb.append(userId);
							sb.append(",");
							sb.append(itelMap.getKey().getId());
							sb.append(");\" ");
							sb.append("value=\"");
							sb.append(itelMap.getValue());
							sb.append("\" autocomplete=\"off\" size=\"2\"> ");
							sb.append("<a class=\"cart_quantity_down\"  onclick=");
							sb.append("\"decrementItemInCart(");
							sb.append(userId);
							sb.append(",");
							sb.append(itelMap.getKey().getId());
							sb.append(");\"> - </a>");
						sb.append("</div>");
					sb.append("</td>");
					sb.append("<td class=\"cart_total\">");
						sb.append("<p class=\"cart_total_price\">");
						sb.append("$"+itelMap.getKey().getPrice()*itelMap.getValue());
						sb.append("</p>");
					sb.append("</td>");
					sb.append("<td class=\"cart_delete\"><a class=\"cart_quantity_delete\" onclick=");
					sb.append("\"deleteItemInCart(");
					sb.append(userId);
					sb.append(",");
					sb.append(itelMap.getKey().getId());
					sb.append(");\">");
					sb.append("<i class=\"fa fa-times\"></i></a></td>");
				sb.append("</tr>");
				
			}
			sb.append("</tbody>");			
			sb.append("</table>");
			sb.append("</div>");
			
			out.println(sb.toString());
		} else {
			/* use message from the body */
			getJspBody().invoke(sw);
			getJspContext().getOut().println(sw.toString());
		}
	}
}
