package com.epam.ryndych.database.model;

import java.util.ArrayList;

public class PhotoCatalog {
	private int itemId;
	private ArrayList<Photo> photoList = new ArrayList<Photo>();

	public ArrayList<Photo> getPhotoList() {
		return photoList;
	}

	public void addPhoto(Photo photo) {
		if (photo != null)
			photoList.add(photo);
	}

	public void setPhotoList(ArrayList<Photo> photoList) {
		this.photoList = photoList;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
}
