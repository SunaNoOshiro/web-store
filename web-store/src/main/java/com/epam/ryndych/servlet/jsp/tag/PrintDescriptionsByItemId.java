package com.epam.ryndych.servlet.jsp.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.epam.ryndych.database.logger.Logger;
import com.epam.ryndych.database.model.Description;
import com.epam.ryndych.database.service.DescriptionService;


public class PrintDescriptionsByItemId extends SimpleTagSupport {

	int itemId ;
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException {
		Logger.LOGGER.info(this.getClass().getSimpleName());
		ArrayList<Description> descriptions = DescriptionService.getDescriptions(itemId);
		
		if (descriptions != null) {
			
			JspWriter out = getJspContext().getOut();
			StringBuilder sb = new StringBuilder();
			sb.append("<tr class=\"description"+itemId+" descriptions_hs\""+"><td>Descriptions info:</td></tr>");
			sb.append("</td></tr>");
			for(Description description : descriptions){			
				
				sb.append("<tr class=\"description"+itemId+" descriptions_hs\""+"><td>");
				sb.append(description.getName());
				sb.append("</td><td>");
				sb.append(description.getValue());	
				sb.append("</td><td>");
				sb.append("<button onclick=\"deleteDescripton('"+description.getName()+"',"+description.getItemId()+");\">X</button>");		
				sb.append("</td></tr>");
			
			}
			out.println(sb.toString());
		} else {
			/* use message from the body */
			getJspBody().invoke(sw);
			getJspContext().getOut().println(sw.toString());
		}
	}
}
