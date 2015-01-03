package com.epam.ryndych.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.epam.ryndych.database.connection.WebStoreConnectionPool;
import com.epam.ryndych.database.logger.Logger;
import com.epam.ryndych.database.model.Category;
import com.epam.ryndych.database.transformation.CategoryTransformer;

public class CategoryDAO {
	public static final String SELECT_CATEGORY = "SELECT * FROM item_category WHERE id = ?";
	public static final String SELECT_CATEGORY_BY_NAME = "SELECT * FROM item_category WHERE name LIKE ?";
	public static final String SELECT_CATEGORIES_BY_SUPER = "SELECT * FROM item_category WHERE super LIKE ?";
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
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if (rs != null) {
				category = CategoryTransformer.fromResultSetToCategory(rs);
				wbConnection.getConnection().commit();
			}
			
		} catch (SQLException e) {
			Logger.LOGGER.error(e.getMessage());
			try {
				wbConnection.getConnection().rollback();
			} catch (SQLException e1) {
				Logger.LOGGER.error(e1.getMessage());
			}
		}
		return category;
	}
	
	public static Category getCategoryByName(String name) {
		
		PreparedStatement pStatement = wbConnection.prepareStatement(SELECT_CATEGORY_BY_NAME);
		ResultSet rs = null;
		Category category = null;
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setString(1, name);
			rs = pStatement.executeQuery();
			if (rs != null) {
				category = CategoryTransformer.fromResultSetToCategory(rs);
				wbConnection.getConnection().commit();
			}
			
		} catch (SQLException e) {
			Logger.LOGGER.error(e.getMessage());
			try {
				wbConnection.getConnection().rollback();
			} catch (SQLException e1) {
				Logger.LOGGER.error(e1.getMessage());
			}
		}
		return category;
	}
	
public static ArrayList<Category> getCategoriesBySuper(String superCategory) {
		
		PreparedStatement pStatement = wbConnection.prepareStatement(SELECT_CATEGORIES_BY_SUPER);
		ResultSet rs = null;
		ArrayList<Category> categories = null;
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setString(1,superCategory);
			rs = pStatement.executeQuery();
			if (rs != null) {
				categories = CategoryTransformer.fromResultSetToCategoriesArray(rs);
				wbConnection.getConnection().commit();
			}
			
		} catch (SQLException e) {
			Logger.LOGGER.error(e.getMessage());
			try {
				wbConnection.getConnection().rollback();
			} catch (SQLException e1) {
				Logger.LOGGER.error(e1.getMessage());;
			}
		}
		return categories;
	}
	
	public static ArrayList<Category> getAllCategories() {
		Statement pStatement = wbConnection.createStatement();
		ResultSet rs = null;
		ArrayList<Category> categories = null;
		try {
			wbConnection.getConnection().setAutoCommit(false);
			rs = pStatement.executeQuery(SELECT_ALL_CATEGORIES);
			if (rs != null) {
				categories = CategoryTransformer.fromResultSetToCategoriesArray(rs);
				wbConnection.getConnection().commit();
			}
		} catch (SQLException e) {
			Logger.LOGGER.error(e.getMessage());
			try {
				wbConnection.getConnection().rollback();
			} catch (SQLException e1) {
				Logger.LOGGER.error(e1.getMessage());
			}
		}
		return categories;
	}

	// delete
	public static boolean deleteCategoryByID(int id) {
		if (id <= 0)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(DELETE_CATEGORY_BY_ID);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setInt(1, id);
			pStatement.executeUpdate();
			wbConnection.getConnection().commit();
		} catch (SQLException e) {
			Logger.LOGGER.error(e.getMessage());
			try {
				wbConnection.getConnection().rollback();
				return false;
			} catch (SQLException e1) {
				Logger.LOGGER.error(e1.getMessage());
				return false;
			} 
		}
		return true;
	}

	
	// update
	public static boolean updateCategoryById(int id, Category newCategory) {
		if (id <= 0 || newCategory==null)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(UPDATE_CATEGORY);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setString(1, newCategory.getName());
			pStatement.setString(2, newCategory.getSuperCategoty());
			pStatement.setInt(3, newCategory.getId());

			pStatement.executeUpdate();
			wbConnection.getConnection().commit();
		} catch (SQLException e) {
			Logger.LOGGER.error(e.getMessage());
			try {
				wbConnection.getConnection().rollback();
				return false;
			} catch (SQLException e1) {
				Logger.LOGGER.error(e1.getMessage());
				return false;
			} 
		}
		return true;
	}

	// insert
	public static boolean insertCategory(Category newCategory) {
		if(newCategory==null)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(INSERT_CATEGORY);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setString(1, newCategory.getName());
			pStatement.setString(2, newCategory.getSuperCategoty());

			pStatement.executeUpdate();
			wbConnection.getConnection().commit();
		} catch (SQLException e) {
			Logger.LOGGER.error(e.getMessage());
			try {
				wbConnection.getConnection().rollback();
				return false;
			} catch (SQLException e1) {
				Logger.LOGGER.error(e1.getMessage());
				return false;
			} 
		}
		return true;
	}
}
