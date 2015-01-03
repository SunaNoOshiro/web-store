package com.epam.ryndych.database.service;

import java.util.ArrayList;

import com.epam.ryndych.database.dao.ItemDAO;
import com.epam.ryndych.database.model.Item;

public class ItemService {
	public static Item getItemById(int id){
		return ItemDAO.getItemByID(id);
	}
	
	public static Item getItem(String model,String manufacturer){
		return ItemDAO.getItem(model, manufacturer);
	}
	
	public static ArrayList<Item> getAllItems(){
		return ItemDAO.getAllItems();
	}
	
	public static boolean updateItemById(int id, Item newItem){
		return ItemDAO.updateItemById(id, newItem);
	}
	
	public static boolean deleteItemById(int id){
		return ItemDAO.deleteItemByID(id);
	}
	
	public static boolean insertItem(Item newItem){
		return ItemDAO.insertItem(newItem);
	}
	
	public static ArrayList<String> getAllModels(){
		return ItemDAO.getAllModels();
	}
	
	public static ArrayList<String> getAllManufacturers(){
		return ItemDAO.getAllManufacturers();
	}
}
