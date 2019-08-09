package com.ecommerce.models;

import java.awt.image.BufferedImage;

public class Category {
	
	private int id;
	private String name;
	private String image;
	public Category(int id, String name, String img){
		this.id = id;
		this.name= name;
		this.image = img;
	}
	
	
	public int getId(){return id;};
	
	public void setId(int id){
		this.id= id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String img) {
		this.image = img;
	}
	
	

}
