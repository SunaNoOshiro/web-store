package com.epam.ryndych.database.service;

import com.epam.ryndych.database.dao.CartDAO;
import com.epam.ryndych.database.model.Basket;

public class CartService {
	public static Basket getBasket(int userId) {
		return CartDAO.getBasket(userId);
	}

	public static boolean deleteItemFromBasket(int itemId,int userId) {
		return CartDAO.deleteItemFromBasket(itemId,userId);
	}

	public static boolean insertItemToBasket(int itemId,int userId,int qty ) {
		return CartDAO.insertItemToBasket(itemId,userId,qty);
	}
	
	public static boolean updateItemToBasket(int itemId,int userId,int qty ) {
		return CartDAO.updateItemToBasket(itemId, userId, qty);
	}
}
