package com.epam.ryndych.servlet.jsp.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.epam.ryndych.database.model.Basket;
import com.epam.ryndych.database.model.Item;
import com.epam.ryndych.database.model.Photo;
import com.epam.ryndych.database.service.CartService;
import com.epam.ryndych.database.service.PhotoService;


public class PrintItemsInCart extends SimpleTagSupport {

	Basket cart = null;
	StringWriter sw = new StringWriter();
	int userId ;
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void doTag() throws JspException, IOException {
	
		cart = CartService.getBasket(userId);
		if (cart != null) {
			StringBuilder sb = new StringBuilder();			
			JspWriter out = getJspContext().getOut();
			
			sb.append("<div class=\"table-responsive cart_info\">");	
			sb.append("<table class=\"table table-condensed\">");
			
			sb.append("<thead>");
				sb.append("<tr class=\"cart_menu\">");
					sb.append("<td class=\"image\">Item</td>");
					sb.append("<td class=\"description\"></td>");
					sb.append("<td class=\"price\">Price</td>");
					sb.append("<td class=\"quantity\">Quantity</td>");				
					sb.append("<td class=\"total\">Total</td>");
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
						sb.append("<p>Web ID: "	+ itelMap.getKey().getId()	+ "</p>");
					sb.append("</td>");
					sb.append("<td class=\"cart_price\">");
						sb.append("<p>"	+ "$"+itelMap.getKey().getPrice() + "</p>");
					sb.append("</td>");
					sb.append("<td class=\"cart_quantity\">");
						sb.append("<div class=\"cart_quantity_button\">");
							sb.append("<a class=\"cart_quantity_up\"  onclick="	+ "\"incrementItemInCart("+ userId+","+
										itelMap.getKey().getId() +
										");\""	+ " > + ");
							sb.append("</a>");
							sb.append(" <input class=\"cart_quantity_input "+itelMap.getKey().getId()
										+"\"  name=\"quantity\" "
										+"onclick=\"updateItemInCart("+ userId+","+itelMap.getKey().getId() +");\""
										+ " "+"value=\""
										+ itelMap.getValue()
										+ "\" autocomplete=\"off\" size=\"2\"> ");
							sb.append("<a class=\"cart_quantity_down\"  onclick="
									+ "\"decrementItemInCart("+ userId+","+itelMap.getKey().getId() +");\""
									+ "> - </a>");
						sb.append("</div>");
					sb.append("</td>");
					sb.append("<td class=\"cart_total\">");
						sb.append("<p class=\"cart_total_price\">"
								+ "$"+itelMap.getKey().getPrice()*itelMap.getValue());
						sb.append("</p>");
					sb.append("</td>");
					sb.append("<td class=\"cart_delete\"><a class=\"cart_quantity_delete\" onclick="
						+ "\"deleteItemInCart("+ userId+","+itelMap.getKey().getId() +");\""
						+ ">");
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
