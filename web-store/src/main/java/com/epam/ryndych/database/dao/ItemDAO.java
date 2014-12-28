package com.epam.ryndych.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.epam.ryndych.database.connection.WebStoreConnectionPool;
import com.epam.ryndych.database.model.Item;
import com.epam.ryndych.database.transformation.ItemTransformer;

public class ItemDAO {
	public static final String SELECT_ITEM_BY_ID = "SELECT * FROM item WHERE id = ?";
	public static final String SELECT_ALL_ITEMS = "SELECT * FROM item";

	public static final String INSERT_ITEM = "INSERT INTO "
			+ "item (category_id,manufacturer,price,model,warranty) "
			+ "VALUES(?,?,?,?,?)";

	public static final String DELETE_ITEM_BY_ID = "DELETE item WHERE id = '?'";

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

	// delete
	public static boolean deleteItemByID(int id) {
		if (id <= 0)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(DELETE_ITEM_BY_ID);
		try {
			pStatement.setInt(1, id);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
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
			pStatement.setInt(1, newItem.getCategory().getId());
			pStatement.setString(2, newItem.getManufacturer());
			pStatement.setFloat(3, newItem.getPrice());
			pStatement.setString(4, newItem.getModel());
			pStatement.setInt(5, newItem.getWarranty());

			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
