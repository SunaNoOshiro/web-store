package com.epam.ryndych.servlet.jsp.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.epam.ryndych.database.model.Description;
import com.epam.ryndych.database.model.Item;
import com.epam.ryndych.database.model.Photo;
import com.epam.ryndych.database.service.DescriptionService;
import com.epam.ryndych.database.service.ItemService;
import com.epam.ryndych.database.service.PhotoService;


public class PrintItemDetails extends SimpleTagSupport {

	int itemId ;
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException {
		ArrayList<Photo> photos = PhotoService.getPhotoGalery(itemId);
		Item item = ItemService.getItemById(itemId);
		if (item!=null) {
			JspWriter out = getJspContext().getOut();
				StringBuilder sb = new StringBuilder();
				
				
					sb.append(" <div class=\"product-details\"><!--product-details-->");
					sb.append("<div class=\"col-sm-5\">");
						sb.append("<div class=\"view-product\">");
						sb.append("<img src=\""
								+ photos.get(0).getUrl()
								+ "\" alt=\"\" />");
						sb.append("<h3>ZOOM</h3>");
						sb.append("</div>");
					
					sb.append("<div id=\"similar-product\" class=\"carousel slide\" data-ride=\"carousel\">");
					sb.append("<!-- Wrapper for slides --> <div class=\"carousel-inner\">");
					
					if(photos.size()<=3){
						sb.append("<div class=\"item active\">");
						for(Photo photo:photos){
							sb.append("<a href=\"\">"
									+ "<img src=\""
									+ photo.getUrl()
									+ "\" alt=\"\">"
									+ "</a>");
						}
						sb.append("</div>");
					}
					else {
						sb.append("<div class=\"item active\">");
						for(int i=0; i<3; i++){
							sb.append("<a href=\"\">"
									+ "<img src=\""
									+ photos.get(i).getUrl()
									+ "\" alt=\"\">"
									+ "</a>");
						}
						sb.append("</div>");
						for(int i=4, l=0; i<photos.size(); i++){
							if(i%3==1){
								sb.append("<div class=\"item \">");
							}
								sb.append("<a href=\"\">"
										+ "<img src=\""
										+ photos.get(i-1).getUrl()
										+ "\" alt=\"\">"
										+ "</a>");
							if(i%3==0 || i==photos.size()){
								sb.append("</div>");
							}
						}
						sb.append("</div>");
					}
				
				
					
				sb.append("</div>");
				sb.append(" <!-- Controls -->");
				sb.append(" <a class=\"left item-control\" href=\"#similar-product\" data-slide=\"prev\">");
				sb.append("<i class=\"fa fa-angle-left\"></i>");
				sb.append(" </a>");
				sb.append("<a class=\"right item-control\" href=\"#similar-product\" data-slide=\"next\">");
				sb.append("<i class=\"fa fa-angle-right\"></i>");
				sb.append("</a>");
				sb.append("</div>");
				sb.append("</div>");
				
				sb.append("<div class=\"col-sm-7\">");
				sb.append("<div class=\"product-information\"><!--/product-information-->");
				sb.append("<img src=\"images/product-details/new.jpg\" class=\"newarrival\" alt=\"\" />");
				sb.append("<h2>"
						+ item.getManufacturer() +" " +item.getModel()
						+ "</h2>");
				sb.append("<p>Web ID: "
						+ item.getId()
						+ "</p>");
				sb.append("<img src=\"images/product-details/rating.png\" alt=\"\" />");
				sb.append("<span>");
				sb.append("<span>"
						+ "$" + item.getPrice()
						+ "</span>");
				sb.append("<label>Quantity:</label>");
				sb.append("<input type=\"text\" value=\"1\" />");
				sb.append("<button type=\"button\" class=\"btn btn-fefault cart\">");
				sb.append("<i class=\"fa fa-shopping-cart\"></i>");
				sb.append("Add to cart");
				sb.append("</button>");
				sb.append("</span>");
				
				sb.append("<p><b>Availability:</b> In Stock</p>");
				sb.append("<p><b>Condition:</b> New</p>");
				sb.append("<p><b>Brand:</b> "
						+ item.getManufacturer()
						+ "</p>");
				sb.append("<p><b>Warranty:</b> "
						+ item.getWarranty() +" month"
						+ "</p>");
				
				ArrayList<Description> descriptions = DescriptionService.getDescriptions(itemId);
				if(descriptions!=null && descriptions.size()>0){
					for(Description d:descriptions)
					sb.append("<p><b>"
							+ d.getName()+":"
							+ "</b> "
							+ d.getValue()
							+ "</p>");
				}
				
				
				sb.append("<a href=\"\"><img src=\"images/product-details/share.png\" class=\"share img-responsive\"  alt=\"\" /></a>");
				sb.append("</div><!--/product-information-->");
				sb.append("</div>");
				sb.append("</div><!--/product-details-->");
				
				out.println(sb.toString());
			
			
		} 
	}
}
