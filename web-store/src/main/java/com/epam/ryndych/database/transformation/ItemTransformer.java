package com.epam.ryndych.database.transformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.epam.ryndych.database.model.Category;
import com.epam.ryndych.database.model.Item;
import com.epam.ryndych.database.service.CategoryService;

public class ItemTransformer {
	public static Item fromResultSetToItem(ResultSet rs){
		Item item = new Item();		
		try {
			if(rs.next()){
				int id = rs.getInt("id");
				float price = rs.getFloat("price");;
				int warranty = rs.getInt("warranty");
				String model = rs.getString("model");
				String manufacturer = rs.getString("manufacturer");
				int categoryId = rs.getInt("category_id");
				Category category = CategoryService.getCategory(categoryId);
				
				item.setId(id);				
				item.setManufacturer(manufacturer);				
				item.setModel(model);				
				item.setPrice(price);				
				item.setWarranty(warranty);
				item.setCategory(category);
			}
		} catch (SQLException e) {
			return null;
		}		
		return item;
	}
	
	public static ArrayList<Item> fromResultSetToItemsArray(ResultSet rs){
		ArrayList<Item> items = new ArrayList<Item>();	
		if(rs==null) 
			return null;
		try {
			while(rs.next()){
				Item item = new Item();				

				int id = rs.getInt("id");
				float price = rs.getFloat("price");;
				int warranty = rs.getInt("warranty");
				String model = rs.getString("model");
				String manufacturer = rs.getString("manufacturer");
				int categoryId = rs.getInt("category_id");
				Category category = CategoryService.getCategory(categoryId);
				
				item.setId(id);				
				item.setManufacturer(manufacturer);				
				item.setModel(model);				
				item.setPrice(price);				
				item.setWarranty(warranty);
				item.setCategory(category);
				
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		return items;
	}
}
