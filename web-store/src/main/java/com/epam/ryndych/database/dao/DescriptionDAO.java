package com.epam.ryndych.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.epam.ryndych.database.connection.WebStoreConnectionPool;
import com.epam.ryndych.database.model.Description;
import com.epam.ryndych.database.transformation.DescriptionTransformer;

public class DescriptionDAO {
	public static final String SELECT_DESCRIPTIONS_BY_ID = "SELECT * FROM item_description WHERE item_id = ?";
	public static final String SELECT_DESCRIPTION = "SELECT * FROM item_description WHERE item_id = ? AND description_name LIKE ?";
	
	public static final String INSERT_DESCRIPTION = "INSERT INTO "
			+ "item_description (description_name,description_value,item_id) "
			+ "VALUES(?,?,?)";

	public static final String DELETE_DESCRIPTION = "DELETE FROM item_description WHERE item_id = ? AND description_name LIKE ?";
	public static final String DELETE_DESCRIPTIONS_BY_ID= "DELETE FROM item_description WHERE item_id = ?";

	public static final String UPDATE_DESCRIPTION = "UPDATE item_description "
			+ "SET description_value=? "
			+ "WHERE item_id=? AND description_name LIKE ?";
	public static WebStoreConnectionPool wbConnection = WebStoreConnectionPool.getInstance();
	
	//select 
	public static ArrayList<Description> getDescriptions(int itemId) {
		if (itemId <= 0)
			return null;
		PreparedStatement pStatement = wbConnection.prepareStatement(SELECT_DESCRIPTIONS_BY_ID);
		ResultSet rs = null;
		ArrayList<Description> descriptions = new ArrayList<Description>();
		try {
			pStatement.setInt(1, itemId);
			rs = pStatement.executeQuery();
			if (rs != null) {
				descriptions = DescriptionTransformer.fromResultSetToItemsArray(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return descriptions;
	}
		
	public static Description getDescription(int itemId, String descriptionName ) {
		PreparedStatement pStatement = wbConnection.prepareStatement(SELECT_DESCRIPTION);
		ResultSet rs = null;
		Description description = null;
		try {
			pStatement.setInt(1, itemId);
			pStatement.setString(2, descriptionName);
			rs = pStatement.executeQuery();
			
			if (rs != null) {
				description = DescriptionTransformer.fromResultSetToItem(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return description;
	}

	// delete
	public static boolean deleteDescriptionByID(int itemId, String descriptionName ) {
		if (itemId <= 0)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(DELETE_DESCRIPTION);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setInt(1, itemId);
			pStatement.setString(2, descriptionName);
			int i = pStatement.executeUpdate();
			if(i>0){
				wbConnection.getConnection().commit();
				return true;
			}
			else 
				return false;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static boolean deleteAllDescriptionsByID(int itemId ) {
		if (itemId <= 0)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(DELETE_DESCRIPTIONS_BY_ID);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setInt(1, itemId);
			int i = pStatement.executeUpdate();
			if(i>0){
				wbConnection.getConnection().commit();
				return true;
			}
			else 
				return false;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	
	// update
	public static boolean updateDescriptionById(int itemId, String descriptionName,String descriptionValue) {
		if (itemId <= 0)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(UPDATE_DESCRIPTION);
		try {
			pStatement.setString(1, descriptionValue);
			pStatement.setInt(2, itemId);
			pStatement.setString(3, descriptionName);

			pStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	// insert
	public static boolean insertDescription(Description description, int itemId) {
		PreparedStatement pStatement = wbConnection.prepareStatement(INSERT_DESCRIPTION);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setString(1, description.getName());
			pStatement.setString(2, description.getValue());			
			pStatement.setInt(3, itemId);		
			
			int i =pStatement.executeUpdate();
			if(i>0){
				wbConnection.getConnection().commit();
				return true;
			}
			else {
				wbConnection.getConnection().rollback();
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
