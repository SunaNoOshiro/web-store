package com.epam.ryndych.servlet.jsp.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.epam.ryndych.database.model.Category;
import com.epam.ryndych.database.service.CategoryService;


public class PrintCategoriesMenu extends SimpleTagSupport {
	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException {
		ArrayList<Category>  categoties = CategoryService.getCategoriesBySuper("root");
		
		
		if (categoties != null) {
			
			JspWriter out = getJspContext().getOut();
			for(Category c : categoties){
				ArrayList<Category>  categotiesChild =null;
				categotiesChild = CategoryService.getCategoriesBySuper(c.getName());
				System.out.println(categotiesChild);
				StringBuilder sb = new StringBuilder();
				sb.append("<div class=\"panel panel-default\">");
				sb.append("<div class=\"panel-heading\">");
				sb.append("<h4 class=\"panel-title\">");
				
				if(categotiesChild.size()==0){
					sb.append("<a href=\"#");				
					sb.append(c.getName());
					sb.append("\">");
					sb.append(c.getName());
					sb.append("</a>");
					sb.append("</h4>");
					sb.append("</div>");
				}
				else{
					sb.append("<a data-toggle=\"collapse\" data-parent=\"#accordian\"	href=\"#");
					sb.append(c.getName());
					sb.append("\">");
					sb.append("<span class=\"badge pull-right\">");		
					sb.append("<i class=\"fa fa-plus\"></i></span>" );
					
					sb.append(c.getName() );
					
					sb.append("</a>");
					sb.append("</h4>");
					sb.append("</div>");
					
					
					sb.append("<div id=\"");
					sb.append(c.getName());
					sb.append("\" class=\"panel-collapse collapse\">");
					sb.append("<div class=\"panel-body\">");
					sb.append("<ul>");
					for(Category child:categotiesChild){
						
						sb.append("<li><a href=\"#\">");
						sb.append(child.getName());
						sb.append("</a></li>");
						
					}
					sb.append("</ul>");
					sb.append("</div>");
					sb.append("</div>");
				}
				
				
				sb.append("</div>");
				
				out.println(sb.toString());
			}
			
		} else {
			/* use message from the body */
			getJspBody().invoke(sw);
			getJspContext().getOut().println(sw.toString());
		}
	}
}
