package com.epam.ryndych.database.transformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.epam.ryndych.database.model.Photo;

public class PhotoTransformer {
	public static Photo fromResultSetToItem(ResultSet rs){
		Photo photo = new Photo();		
		if(rs==null)
			return null;
		try {
			if(rs.next()){
				photo.setId(rs.getInt("id"));
				photo.setDescription(rs.getString("description"));
				photo.setUrl(rs.getString("url"));
			}
		} catch (SQLException e) {
			return null;
		}		
		return photo;
	}
	
	public static ArrayList<Photo> fromResultSetToItemsArray(ResultSet rs){
		ArrayList<Photo> photos = new ArrayList<>();	
		if(rs==null) 
			return null;
		try {
			while(rs.next()){
				Photo photo = new Photo();			

				photo.setId(rs.getInt("id"));
				photo.setDescription(rs.getString("description"));
				photo.setUrl(rs.getString("url"));
				
				photos.add(photo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		return photos;
	}
}
