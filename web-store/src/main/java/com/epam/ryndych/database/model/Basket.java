package com.epam.ryndych.database.model;

import java.util.HashMap;
import java.util.Map;

public class Basket {
	private Map<Item, Integer> itemList = new HashMap<Item, Integer>();
	private int userId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Map<Item, Integer> getItemList() {
		return itemList;
	}
	public void setItemList(Map<Item, Integer> itemList) {
		this.itemList = itemList;
	}
}
