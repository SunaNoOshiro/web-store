package com.epam.ryndych.database.service;

import java.util.ArrayList;

import com.epam.ryndych.database.dao.CategoryDAO;
import com.epam.ryndych.database.model.Category;

public class CategoryService {
	public static Category getCategory(int id) {
		return CategoryDAO.getCategory(id);
	}
	
	public static Category getCategoryByName(String name) {
		return CategoryDAO.getCategoryByName(name);
	}
		
	public static ArrayList<Category> getAllCategories() {
		return CategoryDAO.getAllCategories();
	}
	
	public static boolean deleteCategoryByID(int id) {
		return CategoryDAO.deleteCategoryByID(id);
	}

	public static boolean updateCategoryById(int id, Category newCategory) {
		return CategoryDAO.updateCategoryById(id, newCategory);
	}

	public static boolean insertCategory(Category category) {
		return CategoryDAO.insertCategory(category);
	}
}
