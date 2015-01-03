package com.epam.ryndych.servlet.jsp.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.epam.ryndych.database.model.Item;
import com.epam.ryndych.database.model.Photo;
import com.epam.ryndych.database.service.ItemService;
import com.epam.ryndych.database.service.PhotoService;


public class PrintFeaturesItems extends SimpleTagSupport {

	int userId ;
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException {
		ArrayList<Item> items = ItemService.getAllItems();
		
		if (items != null) {
			StringBuilder sb = new StringBuilder();			
			JspWriter out = getJspContext().getOut();
			
			sb.append("<div class=\"features_items\">");	
			sb.append("<h2 class=\"title text-center\">Features Items</h2>");
			
			for(Item item : items){
				ArrayList<Photo> photos = PhotoService.getPhotoGalery(item.getId());
				sb.append("<div class=\"col-sm-4\">");
				sb.append("<div class=\"product-image-wrapper\">");
					sb.append("<div class=\"single-products\">");
					
						sb.append("<div class=\"productinfo text-center\">");
						sb.append("<img src=\"" + photos.get(0).getUrl() + "\" alt=\"\" />");
						
						sb.append("<h2>");
						sb.append("$"+item.getPrice());
						sb.append("</h2>");
						sb.append("<p>"+item.getManufacturer()+" " + item.getModel()+"</p>");
						sb.append("<p  class=\"btn btn-default add-to-cart\" "
								+ "onclick=\"insertToCart("+item.getId()+","+userId+","+1
								+");\">");
						sb.append("<i class=\"fa fa-shopping-cart\"></i>Add to cart</p>");
						sb.append("</div>");
	
						sb.append("<div class=\"product-overlay\">");
						sb.append("<div class=\"overlay-content\">");
						sb.append("<h2>");
						sb.append("$"+item.getPrice());
						sb.append("</h2>");
						sb.append("<p>"+item.getManufacturer()+" " + item.getModel()+"</p>");
						sb.append("<p  class=\"btn btn-default add-to-cart\" "
								+ "onclick=\"insertToCart("+item.getId()+","+userId +","+1
								+");\">");
						sb.append("<i class=\"fa fa-shopping-cart\"></i>Add to cart</p>");
						sb.append("</div>");
						sb.append("</div>");
				
					sb.append("</div>");
					
					
					sb.append("<div class=\"choose\">");
					sb.append("<ul class=\"nav nav-pills nav-justified\">");
					sb.append("<li><a href=\"#\"><i class=\"fa fa-plus-square\"></i>");
					sb.append("Add to wishlist</a></li>");
					sb.append("<li><a href=\""
							+ "/web-store/product-details?itemId="+item.getId()
							+ "\"><i class=\"fa fa-plus-square\"></i>");
					sb.append("Detales</a></li>");
					sb.append("</ul>");
					sb.append("</div>");
					
					
				sb.append("</div>");
				sb.append("</div>");
				
			}
			sb.append("</div>");
			out.println(sb.toString());
		} else {
			/* use message from the body */
			getJspBody().invoke(sw);
			getJspContext().getOut().println(sw.toString());
		}
	}
}
