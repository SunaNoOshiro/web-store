package com.epam.ryndych.database.service;

import java.util.ArrayList;

import com.epam.ryndych.database.dao.UserDAO;
import com.epam.ryndych.database.model.User;

public class UserService {
	
	public static boolean insertUser(User user){
		return UserDAO.inserteUser(user);
	}
	
	public static ArrayList<User> getAllUsers(){
		return UserDAO.getAllUsers();
	}
	
	public static User getUserById(int id){
		return UserDAO.getUserByID(id);
	}
	
	public static User getUserByLogin(String login){
		return UserDAO.getUserByLogin(login);
	}
}
