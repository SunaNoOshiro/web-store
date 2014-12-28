package com.epam.ryndych.database.transformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.epam.ryndych.database.model.Category;

public class CategoryTransformer {
	public static Category fromResultSetToCategory(ResultSet rs){
		Category category = new Category();		
		try {
			if(rs.next()){
				
			}
		} catch (SQLException e) {
			return null;
		}		
		return category;
	}
	
	public static ArrayList<Category> fromResultSetToCategoriesArray(ResultSet rs){
		ArrayList<Category> categories = new ArrayList<Category>();	
		if(rs==null) 
			return null;
		try {
			while(rs.next()){
				Category category = new Category();				
			
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		return categories;
	}
}
