package com.ecommerce.models;

public class ProductBox {
	private int id;
	private String name;
	private double price;
	private String image;
	
	public ProductBox(int id, String n, double pr, String img){
		this.id = id;
		this.name = n;
		this.price = pr;
		this.image = img;
	}
	
	

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
