package com.epam.ryndych.database.service;

import java.util.ArrayList;

import com.epam.ryndych.database.dao.PhotoDAO;
import com.epam.ryndych.database.model.Photo;

public class PhotoService {
	public static ArrayList<Photo> getPhotoGalery(int itemId) {
		return PhotoDAO.getPhotoGalery(itemId);
	}
		
	public static Photo getPhoto(int id) {
		return PhotoDAO.getPhoto(id);
	}
	
	public static Photo getPhotoByURL(String url) {
		return PhotoDAO.getPhotoByURL(url);
	}
	// delete
	public static boolean deletePhotoFromCatalog(int photoId) {
		return PhotoDAO.deletePhotoFromCatalog(photoId);
	}
	
	public static boolean deletePhoto(int itemId ) {
		return PhotoDAO.deletePhoto(itemId);
	}
	
	public static boolean deletePhotoByURL(String url ) {
		return PhotoDAO.deletePhotoByURL(url);
	}
	
	
	// insert
	public static boolean insertPhoto(Photo photo) {
		return PhotoDAO.insertPhoto(photo);
	}
	
	public static boolean insertPhotoToCatalog(int itemId, int photoId) {
		return PhotoDAO.insertPhotoToCatalog(itemId, photoId);
	}
}
