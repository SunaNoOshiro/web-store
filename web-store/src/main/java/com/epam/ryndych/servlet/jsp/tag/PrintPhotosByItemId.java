package com.epam.ryndych.servlet.jsp.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.epam.ryndych.database.model.Photo;
import com.epam.ryndych.database.service.PhotoService;


public class PrintPhotosByItemId extends SimpleTagSupport {

	int itemId ;
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException {
		ArrayList<Photo> photos = PhotoService.getPhotoGalery(itemId);
		
		if (photos != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<tr class=\"image"+itemId+" descriptions_hs\""+"><td>Images info:</td></tr>");
			sb.append("</td></tr>");
			JspWriter out = getJspContext().getOut();
			for(Photo photo : photos){				
				sb.append("<tr class=\"image"+itemId+" descriptions_hs\""+"><td>");
				sb.append(photo.getId());
				sb.append("</td><td>");
				sb.append(photo.getUrl());	
				sb.append("</td><td>");
				sb.append(photo.getDescription());	
				sb.append("</td><td>");
				sb.append("<button onclick=\"deleteImage('"+photo.getId()+");\">X</button>");		
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
