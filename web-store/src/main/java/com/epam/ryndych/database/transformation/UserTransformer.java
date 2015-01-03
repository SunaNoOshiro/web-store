package com.epam.ryndych.database.transformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.epam.ryndych.database.logger.Logger;
import com.epam.ryndych.database.model.User;

public class UserTransformer {
	
	public static User fromResultSetToUser(ResultSet rs){
		User user = new User();		
		try {
			if(rs.next()){
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setBlackList(false);
				user.setBirthday(rs.getDate("birthday"));
				user.setRegistration(rs.getDate("registration_date"));
				user.setSex(rs.getString("sex"));
				user.setCountry(rs.getString("country"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNumber(rs.getString("phone_number"));
				user.setCity(rs.getString("city"));	
				user.setState(rs.getString("state"));	
				user.setAddress(rs.getString("address"));	
				user.setPermission(rs.getString("permission"));	
			}
		} catch (SQLException e) {
			Logger.LOGGER.error(e.getMessage());
			return null;
		}		
		return user;
	}
	
	public static ArrayList<User> fromResultSetToUsersArray(ResultSet rs){
		ArrayList<User> users = new ArrayList<User>();	
		if(rs==null) 
			return null;
		try {
			while(rs.next()){
				User user = new User();
				
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setBlackList(false);
				user.setBirthday(rs.getDate("birthday"));
				user.setRegistration(rs.getDate("registration_date"));
				user.setSex(rs.getString("sex"));
				user.setPermission(rs.getString("permission"));
				user.setCountry(rs.getString("country"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNumber(rs.getString("phone_number"));
				user.setCity(rs.getString("city"));	
				user.setState(rs.getString("state"));	
				user.setAddress(rs.getString("address"));	
				users.add(user);
			}
		} catch (SQLException e) {
			Logger.LOGGER.error(e.getMessage());
			return null;
		}		
		return users;
	}
}
