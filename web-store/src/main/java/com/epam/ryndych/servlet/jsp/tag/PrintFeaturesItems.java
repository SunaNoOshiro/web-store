package com.epam.ryndych.servlet.jsp.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
	private ResourceBundle bundle = null;
	private StringWriter sw = new StringWriter();
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setPageId(int pageId) {
		this.pageId = pageId;
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
		ArrayList<Item> items = ItemService.getAllItems();
		
		if (items != null && bundle !=null) {
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
						sb.append("<p>");
						sb.append(items.get(i).getManufacturer());
						sb.append(" ");
						sb.append(items.get(i).getModel());
						sb.append("</p>");
						
						sb.append("<p  class=\"btn btn-default add-to-cart\" ");
						sb.append("onclick=\"insertToCart(");
						sb.append(items.get(i).getId());
						sb.append(",");
						sb.append(userId);
						sb.append(",");
						sb.append(1);
						sb.append(");\">");
						
						
						sb.append("<i class=\"fa fa-shopping-cart\"></i>");
								//+ "Add to cart"
						sb.append(bundle.getString("features-items.add-to-cart"));
						sb.append("</p>");						
						sb.append("</div>");
	
						sb.append("<div class=\"product-overlay\">");
						sb.append("<div class=\"overlay-content\">");
						sb.append("<h2>$");
						sb.append(items.get(i).getPrice());
						sb.append("</h2>");
						sb.append("<p>");
						sb.append(items.get(i).getManufacturer());
						sb.append(" ");
						sb.append(items.get(i).getModel());
						sb.append("</p>");
						
						sb.append("<p  class=\"btn btn-default add-to-cart\" ");
						sb.append("onclick=\"insertToCart(");
						sb.append(items.get(i).getId());
						sb.append(",");
						sb.append(userId);
						sb.append(",");
						sb.append(1);
						sb.append(");\">");
						sb.append("<i class=\"fa fa-shopping-cart\"></i>Add to cart</p>");
						sb.append("</div>");
						sb.append("</div>");
				
					sb.append("</div>");
					
					
					sb.append("<div class=\"choose\">");
					sb.append("<ul class=\"nav nav-pills nav-justified\">");
					sb.append("<li><a href=\"#\"><i class=\"fa fa-plus-square\"></i>");
					sb.append(//"Add to wishlist"
							bundle.getString("features-items.add-to-wishlist"));
					sb.append("</a></li>");
					sb.append("<li><a href=\"");
					sb.append("/web-store/product-details?itemId=");
					sb.append(items.get(i).getId());
					sb.append("\"><i class=\"fa fa-plus-square\"></i>");
					sb.append(//"Detales"
							bundle.getString("features-items.details"));
					sb.append("</a></li>");
					sb.append("</ul>");
					sb.append("</div>");
					
					
				sb.append("</div>");
				sb.append("</div>");
				if(i==((pageId)*12 -1))
					break;
			}
			
			printPagination(items, sb);
			
			
			out.println(sb.toString());
		}
	}
	
	private void printPagination(ArrayList<Item> items, StringBuilder sb){
		if(sb==null)
			return;
		
		if((float)items.size()/12>(float)1){
			if(pageId>1){
				sb.append("<ul class=\"pagination\">");
				sb.append("<li ><a href=\"/web-store/shop?pageId=");
				sb.append(pageId-1);
				sb.append("\">");
				sb.append(pageId-1);
				sb.append("</a></li>");
						
				sb.append("<li class=\"active\"><a href=\"/web-store/shop?pageId=");
				sb.append(pageId);
				sb.append("\">");
				sb.append(pageId);
				sb.append("</a></li>");
			}
			else{
				sb.append("<ul class=\"pagination\">");
				sb.append("<li class=\"active\"><a href=\"/web-store/shop?pageId=");
				sb.append(pageId);
				sb.append("\">");
				sb.append(pageId);
				sb.append("</a></li>");
			}
			
			if(pageId<(float)items.size()/12){
				sb.append("<li><a href=\"/web-store/shop?pageId=");
				sb.append(pageId+1);
				sb.append("\">");
				sb.append(pageId+1);
				sb.append("</a></li>");
			}			
			
			sb.append("<li><a href=\"\" onclick=\"nextPage(");
			sb.append("");
			sb.append( ");\">&raquo;</a></li>");
			sb.append("</ul>");
		}
	}
}
