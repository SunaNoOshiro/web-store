package com.epam.ryndych.database.transformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.epam.ryndych.database.model.Item;

public class ItemTransformer {
	public static Item fromResultSetToItem(ResultSet rs){
		Item item = new Item();		
		try {
			if(rs.next()){
				
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
			
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		return items;
	}
}
