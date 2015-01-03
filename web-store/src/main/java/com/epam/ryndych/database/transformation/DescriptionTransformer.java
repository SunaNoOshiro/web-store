package com.epam.ryndych.database.transformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.epam.ryndych.database.logger.Logger;
import com.epam.ryndych.database.model.Description;

public class DescriptionTransformer {
	public static Description fromResultSetToItem(ResultSet rs){
		Description description = new Description();		
		if(rs==null)
			return null;
		try {
			if(rs.next()){
				description.setItemId(rs.getInt("item_id"));
				description.setName(rs.getString("description_name"));
				description.setValue(rs.getString("description_value"));
			}
		} catch (SQLException e) {
			Logger.LOGGER.error(e.getMessage());
			return null;
		}		
		return description;
	}
	
	public static ArrayList<Description> fromResultSetToItemsArray(ResultSet rs){
		ArrayList<Description> descriptions = new ArrayList<>();	
		if(rs==null) 
			return null;
		try {
			while(rs.next()){
				Description description = new Description();			

				description.setItemId(rs.getInt("item_id"));
				description.setName(rs.getString("description_name"));
				description.setValue(rs.getString("description_value"));
				
				descriptions.add(description);
			}
		} catch (SQLException e) {
			Logger.LOGGER.error(e.getMessage());
			return null;
		}		
		return descriptions;
	}
}
