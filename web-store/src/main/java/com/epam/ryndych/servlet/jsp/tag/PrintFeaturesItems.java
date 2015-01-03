package com.epam.ryndych.servlet.jsp.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.epam.ryndych.database.logger.Logger;
import com.epam.ryndych.database.model.Item;
import com.epam.ryndych.database.model.Photo;
import com.epam.ryndych.database.service.ItemService;
import com.epam.ryndych.database.service.PhotoService;


public class PrintFeaturesItems extends SimpleTagSupport {

	int userId ;
	int pageId;
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	
	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException {
		Logger.LOGGER.info(this.getClass().getSimpleName());
		ArrayList<Item> items = ItemService.getAllItems();
		
		if (items != null) {
			StringBuilder sb = new StringBuilder();			
			JspWriter out = getJspContext().getOut();
			
			
			
			for(int i=((pageId-1)*12); i<items.size() ;i++){
				
				ArrayList<Photo> photos = PhotoService.getPhotoGalery(items.get(i).getId());
				sb.append("<div class=\"col-sm-4\">");
				sb.append("<div class=\"product-image-wrapper\">");
					sb.append("<div class=\"single-products\">");
					
						sb.append("<div class=\"productinfo text-center\">");
						try{
							sb.append("<img src=\"" + photos.get(0).getUrl() + "\" alt=\"\" />");
						}
						catch(Exception e){
							sb.append("<img src=\""  + "\" alt=\"\" />");
						}
						sb.append("<h2>");
						sb.append("$"+items.get(i).getPrice());
						sb.append("</h2>");
						sb.append("<p>"+items.get(i).getManufacturer()+" " + items.get(i).getModel()+"</p>");
						sb.append("<p  class=\"btn btn-default add-to-cart\" "
								+ "onclick=\"insertToCart("+items.get(i).getId()+","+userId+","+1
								+");\">");
						sb.append("<i class=\"fa fa-shopping-cart\"></i>Add to cart</p>");
						sb.append("</div>");
	
						sb.append("<div class=\"product-overlay\">");
						sb.append("<div class=\"overlay-content\">");
						sb.append("<h2>");
						sb.append("$"+items.get(i).getPrice());
						sb.append("</h2>");
						sb.append("<p>"+items.get(i).getManufacturer()+" " + items.get(i).getModel()+"</p>");
						sb.append("<p  class=\"btn btn-default add-to-cart\" "
								+ "onclick=\"insertToCart("+items.get(i).getId()+","+userId +","+1
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
							+ "/web-store/product-details?itemId="+items.get(i).getId()
							+ "\"><i class=\"fa fa-plus-square\"></i>");
					sb.append("Detales</a></li>");
					sb.append("</ul>");
					sb.append("</div>");
					
					
				sb.append("</div>");
				sb.append("</div>");
				if(i==((pageId)*12 -1))
					break;
			}
			System.out.println(items.size());
			if((float)items.size()/12>(float)1){
				if(pageId>1){
					sb.append("<ul class=\"pagination\">");
					sb.append("<li ><a href=\"/web-store/shop?pageId="
							+ (pageId-1)
							+ "\">"
							+ (pageId-1)
							+ "</a></li>");
					sb.append("<li class=\"active\"><a href=\"/web-store/shop?pageId="
							+ pageId
							+ "\">"
							+ pageId
							+ "</a></li>");
				}
				else{
					sb.append("<ul class=\"pagination\">");
					sb.append("<li class=\"active\"><a href=\"/web-store/shop?pageId="
							+ (pageId)
							+ "\">"
							+ (pageId)
							+ "</a></li>");
				}
				
				if(pageId<(float)items.size()/12)
				sb.append("<li><a href=\"/web-store/shop?pageId="
						+ (pageId+1)
						+ "\">"
						+ (pageId+1)
						+ "</a></li>");
				
				sb.append("<li><a href=\"\" onclick=\"nextPage("
						+ ""
						+ ");\">&raquo;</a></li>");
				sb.append("</ul>");
			}
			
			
			
			out.println(sb.toString());
		} else {
			/* use message from the body */
			getJspBody().invoke(sw);
			getJspContext().getOut().println(sw.toString());
		}
	}
}
