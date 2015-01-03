package com.epam.ryndych.database.model;

public class Item {
	private int id;
	private float price;
	private String manufacturer;
	private String model;
	private int warranty;
	private Category category = null;
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getWarranty() {
		return warranty;
	}
	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean equals(Item other) {
		if(this.id == other.getId()&&
				this.category.equals(other.getCategory())&&
				this.manufacturer == other.getManufacturer()&&
				this.model == other.getModel()&&
				this.getWarranty() == other.getWarranty()&&
				this.price == other.getPrice()){
			return true;
		}
		else return false;
	}
}
