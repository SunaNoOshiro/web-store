package com.epam.ryndych.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.epam.ryndych.database.connection.WebStoreConnectionPool;
import com.epam.ryndych.database.model.User;
import com.epam.ryndych.database.transformation.UserTransformer;

public class UserDAO {
	public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
	public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login LIKE ?";
	public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email LIKE ?";
	public static final String SELECT_USER_BY_PHONE = "SELECT * FROM user WHERE phone LIKE ?";
	public static final String SELECT_ALL_USERS = "SELECT * FROM user";

	public static final String INSERT_USER = "INSERT INTO "
			+ "user (first_name,last_name,login,password,black_list,birthday,sex,permission,country,email,phone_number,city,state,address) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String DELETE_USER_BY_ID = "DELETE user WHERE id = '?'";
	public static final String DELETE_USER_BY_LOGIN = "DELETE user WHERE login LIKE '?'";
	public static final String DELETE_USER_BY_EMAIL = "DELETE user WHERE email LIKE '?'";
	public static final String DELETE_USER_BY_PHONE = "DELETE user WHERE phone LIKE '?'";

	public static final String UPDATE_USER_BY_ID = "UPDATE user "
			+ "SET first_name='?',last_name='?',login='?',password='?',black_list='?',birthday='?',registration='?',sex='?',permission='?',country='?',email='?',phone='?' "
			+ "WHERE id=?";
	public static final String UPDATE_USER_BY_LOGIN = "";
	public static final String UPDATE_USER_BY_EMAIL = "";
	public static final String UPDATE_USER_BY_PHONE = "";

	public static WebStoreConnectionPool wbConnection = WebStoreConnectionPool.getInstance();

	
	//select 
	public static User getUserByID(int id) {
		if (id <= 0)
			return null;
		PreparedStatement pStatement = wbConnection.prepareStatement(SELECT_USER_BY_ID);
		ResultSet rs = null;
		User user = null;
		try {
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if (rs != null) {
				user = UserTransformer.fromResultSetToUser(rs);

			}
		} catch (SQLException e) {

		}
		return user;
	}

	private static User getUserByString(String SQL, String value){		
		if (value == null)
			return null;
		PreparedStatement pStatement = wbConnection.prepareStatement(SQL);
		ResultSet rs = null;
		User user = null;
		try {
			pStatement.setString(1, value);
			rs = pStatement.executeQuery();
			if (rs != null) {
				user = UserTransformer.fromResultSetToUser(rs);
			}
		} catch (SQLException e) {
			
		}
		return user;
	}
	
	public static User getUserByLogin(String login) {
		return getUserByString(SELECT_USER_BY_LOGIN, login);
	}
	
	public static User getUserByEmail(String email) {
		return getUserByString(SELECT_USER_BY_EMAIL, email);
	}
	
	public static User getUserByPhone(String phone) {
		return getUserByString(SELECT_USER_BY_PHONE, phone);
	}
	
	public static ArrayList<User> getAllUsers(){
		Statement pStatement = wbConnection.createStatement();
		ResultSet rs = null;
		ArrayList<User> users = null;
		try {
			rs = pStatement.executeQuery(SELECT_ALL_USERS);
			if (rs != null) {
				users = UserTransformer.fromResultSetToUsersArray(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	//delete
	public static boolean deleteUserByID(int id){
		if (id <= 0)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(DELETE_USER_BY_ID);
		
		try {
			pStatement.setInt(1, id);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	private static boolean deleteUserByString(String SQL, String value){
		if (value != null)
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(SQL);
		
		try {
			pStatement.setString(1, value);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public static boolean deleteUserByLogin(String login){
		return deleteUserByString(DELETE_USER_BY_LOGIN, login);
	}
	
	public static boolean deleteUserByEmail(String email){
		return deleteUserByString(DELETE_USER_BY_EMAIL, email);
	}
	
	public static boolean deleteUserByPhone(String phone){
		return deleteUserByString(DELETE_USER_BY_PHONE, phone);
	}

	//update
	public static boolean updateUserById(int id,User newUser){
		if(id<=0) 
			return false;
		PreparedStatement pStatement = wbConnection.prepareStatement(UPDATE_USER_BY_ID);
		try {
			pStatement.setString(1, newUser.getFirstName());
			pStatement.setString(2, newUser.getLastName());
			pStatement.setString(3, newUser.getLogin());
			pStatement.setString(4, newUser.getPassword());
			pStatement.setBoolean(5, newUser.isBlackList());
			pStatement.setDate(6, newUser.getBirthday());
			pStatement.setDate(7, newUser.getRegistration());
			pStatement.setString(8, newUser.getSex());			
			pStatement.setString(9, newUser.getPermission());
			pStatement.setString(10, newUser.getCountry());
			pStatement.setString(11, newUser.getEmail());
			pStatement.setString(12, newUser.getPhoneNumber());			
			pStatement.setInt(13, newUser.getId());
			
			pStatement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;		
	}
	
	//insert	
	public static boolean inserteUser(User newUser){
		PreparedStatement pStatement = wbConnection.prepareStatement(INSERT_USER);
		try {
			pStatement.setString(1, newUser.getFirstName());
			pStatement.setString(2, newUser.getLastName());
			pStatement.setString(3, newUser.getLogin());
			pStatement.setString(4, newUser.getPassword());
			pStatement.setBoolean(5, newUser.isBlackList());
			pStatement.setDate(6, newUser.getBirthday());
			pStatement.setString(7, newUser.getSex());			
			pStatement.setString(8, newUser.getPermission());
			pStatement.setString(9, newUser.getCountry());
			pStatement.setString(10, newUser.getEmail());
			pStatement.setString(11, newUser.getPhoneNumber());			
			pStatement.setString(12, newUser.getCity());	
			pStatement.setString(13, newUser.getState());	
			pStatement.setString(14, newUser.getAddress());	
			
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;		
	}
	
}
