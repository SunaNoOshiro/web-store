package com.epam.ryndych.database.transformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.epam.ryndych.database.logger.Logger;
import com.epam.ryndych.database.model.Basket;
import com.epam.ryndych.database.model.Item;
import com.epam.ryndych.database.service.ItemService;

public class BasketTransformer {
	public static Basket fromResultSetToItem(ResultSet rs){
		Basket basket = new Basket();		
		HashMap<Item, Integer> map = new HashMap<Item, Integer>();
		basket.setItemList(map);
		if(rs==null)
			return null;
		try {
			while(rs.next()){
				Item item = ItemService.getItemById(rs.getInt("item_id"));
				if(item!=null) basket.getItemList().put(item, rs.getInt("item_qty"));
				basket.setUserId(rs.getInt("user_id"));
			}
		} catch (SQLException e) {
			Logger.LOGGER.error(e.getMessage());
			return null;
		}		
		return basket;
	}
}
