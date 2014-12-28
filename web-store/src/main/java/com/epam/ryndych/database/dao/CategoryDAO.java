package com.epam.ryndych.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.epam.ryndych.database.connection.WebStoreConnectionPool;
import com.epam.ryndych.database.model.Category;
import com.epam.ryndych.database.transformation.CategoryTransformer;

public class CategoryDAO {
	public static final String SELECT_CATEGORY = "SELECT * FROM item_category WHERE id = ?";
	public static final String SELECT_ALL_CATEGORIES = "SELECT * FROM item_category";

	public static final String INSERT_CATEGORY = "INSERT INTO "
			+ "item_category (name,super) "
			+ "VALUES(?,?)";

	public static final String DELETE_CATEGORY_BY_NAME = "DELETE item_category WHERE name LIKE '?'";
	
	public static final String DELETE_CATEGORY_BY_ID = "DELETE item_category WHERE id = '?'";

	public static final String UPDATE_CATEGORY = "UPDATE item_category "
			+ "SET name='?',super='?' "
			+ "WHERE id=?";
	public static WebStoreConnectionPool wbConnection = WebStoreConnectionPool.getInstance();
	
	//select 
	public static Category getCategory(int id) {
		if (id <= 0)
			return null;
		PreparedStatement pStatement = wbConnection.prepareStatement(SELECT_CATEGORY);
		ResultSet rs = null;
		Category category = null;
		try {
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if (rs != null) {
				category = CategoryTransformer.fromResultSetToCategory(rs);
			}
		} catch (SQLException e) {

		}
		return category;
	}
		
	public static ArrayList<Category> getAllItems() {
		Statement pStatement = wbConnection.createStatement();
		ResultSet rs = null;
		ArrayList<Category> categories = null;
		try {
			rs = pStatement.executeQuery(SELECT_ALL_CATEGORIES);
			if (rs != null) {
				categories = CategoryTransformer.fromResultSetToCategoriesArray(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	// delete
	public static boolean deleteItemByID(int id) {
		if (id <= 0)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(DELETE_CATEGORY_BY_ID);
		try {
			pStatement.setInt(1, id);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	
	// update
	public static boolean updateItemById(int id, Category newCategory) {
		if (id <= 0)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(UPDATE_CATEGORY);
		try {
			pStatement.setString(1, newCategory.getName());
			if(newCategory.getSuperCategoty() == null)
				pStatement.setString(2, null);
			else
				pStatement.setInt(2, newCategory.getSuperCategoty().getId());
			pStatement.setInt(3, newCategory.getId());

			pStatement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	// insert
	public static boolean insertItem(Category newCategory) {
		PreparedStatement pStatement = wbConnection.prepareStatement(INSERT_CATEGORY);
		try {
			pStatement.setString(1, newCategory.getName());
			if(newCategory.getSuperCategoty() == null)
				pStatement.setString(2, null);
			else
				pStatement.setInt(2, newCategory.getSuperCategoty().getId());

			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
