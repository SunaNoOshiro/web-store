package com.epam.ryndych.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.epam.ryndych.database.connection.WebStoreConnectionPool;
import com.epam.ryndych.database.model.Photo;
import com.epam.ryndych.database.transformation.PhotoTransformer;

public class PhotoDAO {
	public static final String SELECT_IMAGE_BY_ID = "SELECT * FROM photo WHERE id = ?";
	public static final String SELECT_IMAGE_BY_URL = "SELECT * FROM photo WHERE url LIKE ?";
	public static final String SELECT_IMAGES_BY_ITEM_ID = "SELECT * FROM photo JOIN photo_catalog ON id=photo_id WHERE item_id = ?";
	
	public static final String INSERT_IMAGE = "INSERT INTO "
			+ "photo (description, url) "
			+ "VALUES(?,?)";
	public static final String INSERT_IMAGE_TO_CATALOG = "INSERT INTO "
			+ "photo_catalog (item_id,photo_id) "
			+ "VALUES(?,?)";

	public static final String DELETE_IMAGE_BY_ID = "DELETE FROM item_description WHERE item_id = ? AND description_name LIKE ?";
	public static final String DELETE_IMAGE_FROM_CATALOG_BY_ID = "DELETE FROM item_description WHERE item_id = ? AND description_name LIKE ?";
	public static final String DELETE_IMAGE_BY_URL = "DELETE FROM item_description WHERE item_id = ? AND description_name LIKE ?";
	public static final String DELETE_IMAGE_FROM_CATALOG_BY_URL = "DELETE FROM item_description WHERE item_id = ? AND description_name LIKE ?";
	
	public static final String DELETE_IMAGES_BY_ITEM_ID= "DELETE FROM item_description WHERE item_id = ?";

	public static final String UPDATE_IMAGE = "UPDATE item_description "
			+ "SET description_value=? "
			+ "WHERE item_id=? AND description_name LIKE ?";
	public static WebStoreConnectionPool wbConnection = WebStoreConnectionPool.getInstance();
	
	//select 
	public static ArrayList<Photo> getPhotoGalery(int itemId) {
		if (itemId <= 0)
			return null;
		PreparedStatement pStatement = wbConnection.prepareStatement(SELECT_IMAGES_BY_ITEM_ID);
		ResultSet rs = null;
		ArrayList<Photo> photos = new ArrayList<Photo>();
		try {
			pStatement.setInt(1, itemId);
			rs = pStatement.executeQuery();
			if (rs != null) {
				photos = PhotoTransformer.fromResultSetToItemsArray(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return photos;
	}
		
	public static Photo getPhoto(int id) {
		PreparedStatement pStatement = wbConnection.prepareStatement(SELECT_IMAGE_BY_ID);
		ResultSet rs = null;
		Photo photo = null;
		try {
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			
			if (rs != null) {
				photo = PhotoTransformer.fromResultSetToItem(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return photo;
	}
	
	public static Photo getPhotoByURL(String url) {
		if(url==null)
			return null;
		PreparedStatement pStatement = wbConnection.prepareStatement(SELECT_IMAGE_BY_URL);
		ResultSet rs = null;
		Photo photo = null;
		try {
			pStatement.setString(1, url);
			rs = pStatement.executeQuery();
			
			if (rs != null) {
				photo = PhotoTransformer.fromResultSetToItem(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return photo;
	}

	// delete
	public static boolean deletePhotoFromCatalog(int photoId) {
		
		PreparedStatement pStatement = wbConnection.prepareStatement(DELETE_IMAGE_FROM_CATALOG_BY_ID);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setInt(1, photoId);
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
	
	public static boolean deletePhoto(int itemId ) {
		if (itemId <= 0)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(DELETE_IMAGE_FROM_CATALOG_BY_ID);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setInt(1, itemId);
			pStatement.executeUpdate();
			
			pStatement = wbConnection.prepareStatement(DELETE_IMAGE_BY_ID);
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
	public static boolean deletePhotoByURL(String url ) {
		if (url == null)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(DELETE_IMAGE_FROM_CATALOG_BY_URL);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setString(1, url);
			pStatement.executeUpdate();
			
			pStatement = wbConnection.prepareStatement(DELETE_IMAGE_BY_URL);
			pStatement.setString(1, url);
			
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

	// insert
	public static boolean insertPhoto(Photo photo) {
		PreparedStatement pStatement = wbConnection.prepareStatement(INSERT_IMAGE);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setString(1, photo.getDescription());
			pStatement.setString(2, photo.getUrl());	
			
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
	
	public static boolean insertPhotoToCatalog(int itemId, int photoId) {
		PreparedStatement pStatement = wbConnection.prepareStatement(INSERT_IMAGE_TO_CATALOG);
		try {
			wbConnection.getConnection().setAutoCommit(false);
			pStatement.setInt(1,itemId);
			pStatement.setInt(2,photoId);
			
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
