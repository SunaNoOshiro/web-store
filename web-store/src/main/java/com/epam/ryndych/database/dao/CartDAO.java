package com.epam.ryndych.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.ryndych.database.connection.WebStoreConnectionPool;
import com.epam.ryndych.database.logger.Logger;
import com.epam.ryndych.database.model.Basket;
import com.epam.ryndych.database.transformation.BasketTransformer;

public class CartDAO {
	public static final String SELECT_BASKET_USER_ID = "SELECT * FROM basket WHERE user_id = ?";

	public static final String INSERT_ITEM_TO_BASKET = "INSERT INTO "
			+ "basket (user_id,item_id,item_qty) "
			+ "VALUES(?,?,?)";

	public static final String DELETE_ITEM_FROM_BASKET = "DELETE FROM basket WHERE user_id = ? AND item_id = ?";
	public static final String UPDATE_ITEM_FROM_BASKET = "UPDATE basket "
			+ "SET item_qty=? "
			+ "WHERE item_id=? AND user_id=?";
	public static WebStoreConnectionPool wbConnection = WebStoreConnectionPool.getInstance();
	
	//select 
	public static Basket getBasket(int userId) {
		if (userId <= 0)
			return null;
		
		PreparedStatement pStatement = wbConnection.prepareStatement(SELECT_BASKET_USER_ID);
		ResultSet rs = null;
		Basket basket = null;
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setInt(1, userId);
			rs = pStatement.executeQuery();
			if (rs != null) {
				basket = BasketTransformer.fromResultSetToItem(rs);
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
		return basket;
	}
	
	
	// delete
	public static boolean deleteItemFromBasket(int itemId,int userId) {
		if (itemId <= 0 || userId<=0)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(DELETE_ITEM_FROM_BASKET);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setInt(1, userId);
			pStatement.setInt(2, itemId);
			if(pStatement.executeUpdate()>0){
				wbConnection.getConnection().commit();
			}
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
	public static boolean insertItemToBasket(int itemId,int userId,int qty ) {
		if (itemId <= 0 || userId<=0 || qty<=0)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(INSERT_ITEM_TO_BASKET);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			
			pStatement.setInt(1, userId);
			pStatement.setInt(2, itemId);
			pStatement.setInt(3, qty);
			
			if(pStatement.executeUpdate()>0){
				wbConnection.getConnection().commit();
			}
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
	
	public static boolean updateItemToBasket(int itemId,int userId,int qty ) {
		if (itemId <= 0 || userId<=0 || qty<=0)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(UPDATE_ITEM_FROM_BASKET);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			
			pStatement.setInt(1, qty);			
			pStatement.setInt(2, itemId);
			pStatement.setInt(3, userId);
			
			if(pStatement.executeUpdate()>0){
				wbConnection.getConnection().commit();
			}
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
