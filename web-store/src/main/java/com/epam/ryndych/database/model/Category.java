package com.epam.ryndych.database.model;

public class Category {
	private int id;
	private String name;
	private String superCategoty = null;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSuperCategoty() {
		return superCategoty;
	}
	
	public void setSuperCategoty(String superCategoty) {
		this.superCategoty = superCategoty;
	}
	
}
