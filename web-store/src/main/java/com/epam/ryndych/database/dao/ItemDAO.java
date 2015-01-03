package com.epam.ryndych.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.epam.ryndych.database.connection.WebStoreConnectionPool;
import com.epam.ryndych.database.model.Item;
import com.epam.ryndych.database.service.DescriptionService;
import com.epam.ryndych.database.transformation.ItemTransformer;

public class ItemDAO {
	public static final String SELECT_ITEM_BY_ID = "SELECT * FROM item WHERE id = ?";
	public static final String SELECT_ITEM_BY_MODEL_AND_MANUFACTURER= "SELECT DISTINCT * FROM item "
			+ "WHERE model LIKE ? AND manufacturer LIKE ?";
	public static final String SELECT_ALL_ITEMS = "SELECT * FROM item";
	public static final String SELECT_ALL_MODELS = "SELECT DISTINCT model FROM item";
	public static final String SELECT_ALL_MANUFACTURER = "SELECT DISTINCT manufacturer FROM item";
	
	public static final String INSERT_ITEM = "INSERT INTO "
			+ "item (category_id,manufacturer,price,model,warranty) "
			+ "VALUES(?,?,?,?,?)";

	public static final String DELETE_ITEM_BY_ID = "DELETE FROM item WHERE id = ?";

	public static final String UPDATE_ITEM_BY_ID = "UPDATE item "
			+ "SET category_id='?',manufacturer='?',price='?',model='?',warranty='?' "
			+ "WHERE id=?";
	public static WebStoreConnectionPool wbConnection = WebStoreConnectionPool.getInstance();
	
	//select 
	public static Item getItemByID(int id) {
		if (id <= 0)
			return null;
		PreparedStatement pStatement = wbConnection.prepareStatement(SELECT_ITEM_BY_ID);
		ResultSet rs = null;
		Item item = null;
		try {
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if (rs != null) {
				item = ItemTransformer.fromResultSetToItem(rs);

			}
		} catch (SQLException e) {

		}
		return item;
	}
	
	public static Item getItem(String model,String manufacturer) {
		if (model == null || manufacturer==null )
			return null;
		PreparedStatement pStatement = wbConnection.prepareStatement(SELECT_ITEM_BY_MODEL_AND_MANUFACTURER);
		ResultSet rs = null;
		Item item = null;
		try {
			pStatement.setString(1, model);
			pStatement.setString(2, manufacturer);
			
			rs = pStatement.executeQuery();
			if (rs != null) {
				item = ItemTransformer.fromResultSetToItem(rs);

			}
		} catch (SQLException e) {

		}
		return item;
	}
	public static ArrayList<Item> getAllItems() {
		Statement pStatement = wbConnection.createStatement();
		ResultSet rs = null;
		ArrayList<Item> items = null;
		try {
			rs = pStatement.executeQuery(SELECT_ALL_ITEMS);
			if (rs != null) {
				items = ItemTransformer.fromResultSetToItemsArray(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	public static ArrayList<String> getAllModels() {
		Statement pStatement = wbConnection.createStatement();
		ResultSet rs = null;
		ArrayList<String> models = new ArrayList<String>();
		try {
			rs = pStatement.executeQuery(SELECT_ALL_MODELS);
			if (rs != null) {
				while (rs.next()) {
					models.add(rs.getString("model"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return models;
	}

	public static ArrayList<String> getAllManufacturers() {
		Statement pStatement = wbConnection.createStatement();
		ResultSet rs = null;
		ArrayList<String> models = new ArrayList<String>();
		try {
			rs = pStatement.executeQuery(SELECT_ALL_MANUFACTURER);
			if (rs != null) {
				while (rs.next()) {
					models.add(rs.getString("manufacturer"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return models;
	}
	// delete
	public static boolean deleteItemByID(int id) {
		if (id <= 0)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(DELETE_ITEM_BY_ID);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			
			 DescriptionService.deleteAllDescriptionsByID(id);					
			
			pStatement.setInt(1, id);
			if(pStatement.executeUpdate()>0){
				wbConnection.getConnection().commit();
				return true;
			}
			else{
				wbConnection.getConnection().rollback();
				return false;
			}
		} catch (SQLException e) {
			try {
				wbConnection.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		
	}

	
	// update
	public static boolean updateItemById(int id, Item newItem) {
		if (id <= 0)
			return false;
		PreparedStatement pStatement = wbConnection
				.prepareStatement(UPDATE_ITEM_BY_ID);
		try {
			pStatement.setInt(1, newItem.getCategory().getId());
			pStatement.setString(2, newItem.getManufacturer());
			pStatement.setFloat(3, newItem.getPrice());
			pStatement.setString(4, newItem.getModel());
			pStatement.setInt(5, newItem.getWarranty());
			pStatement.setInt(6, newItem.getId());

			pStatement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	// insert
	public static boolean insertItem(Item newItem) {
		PreparedStatement pStatement = wbConnection.prepareStatement(INSERT_ITEM);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			
			pStatement.setInt(1, newItem.getCategory().getId());
			pStatement.setString(2, newItem.getManufacturer());
			pStatement.setFloat(3, newItem.getPrice());
			pStatement.setString(4, newItem.getModel());
			pStatement.setInt(5, newItem.getWarranty());

			int i =pStatement.executeUpdate();
			if(i>0){
				wbConnection.getConnection().commit();
				return true;
			}				
			else{
				wbConnection.getConnection().rollback();
				return false;
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				wbConnection.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}
}
