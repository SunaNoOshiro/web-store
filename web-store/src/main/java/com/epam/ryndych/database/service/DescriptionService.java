package com.epam.ryndych.database.service;

import java.util.ArrayList;

import com.epam.ryndych.database.dao.DescriptionDAO;
import com.epam.ryndych.database.model.Description;

public class DescriptionService {
	public static ArrayList<Description> getDescriptions(int itemId) {
		return DescriptionDAO.getDescriptions(itemId);
	}
		
	public static Description getDescription(int itemId, String descriptionName ) {
		return DescriptionDAO.getDescription(itemId, descriptionName);
	}

	// delete
	public static boolean deleteDescriptionByID(int itemId, String descriptionName ) {
		return DescriptionDAO.deleteDescriptionByID(itemId, descriptionName);
	}
	
	public static boolean deleteAllDescriptionsByID(int itemId ) {
		return DescriptionDAO.deleteAllDescriptionsByID(itemId);
	}
	
	// update
	public static boolean updateDescriptionById(int itemId, String descriptionName,String descriptionValue) {
		return DescriptionDAO.updateDescriptionById(itemId, descriptionName, descriptionValue);
	}

	// insert
	public static boolean insertDescription(Description description, int itemId) {
		return DescriptionDAO.insertDescription(description,itemId);
	}
}
