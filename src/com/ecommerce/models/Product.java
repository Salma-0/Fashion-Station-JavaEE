package com.ecommerce.models;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Product {
	private int id;
	private String name;
	private String description;
	private int quantity;
	private Category category;
	private double price;
	private String[]colors;
	private String[]sizes;
	
	public Product(int id, String name, String description, int q, Category c, double pr, String[]colors, String[]sizes){
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = q;
		this.category = c;
		this.price = pr;
		this.colors = colors;
		this.sizes = sizes;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String[] getColors() {
		return colors;
	}

	public void setColors(String[] colors) {
		this.colors = colors;
	}

	public String[] getSizes() {
		return sizes;
	}

	public void setSizes(String[] sizes) {
		this.sizes = sizes;
	}

	public void print(){
		System.out.println("ID: "+this.id
				+"\nName: "+this.name
				+"\nQuantity: "+this.quantity
				+"\nCategory: "+this.category.getName());
	}
	
	public String mergeStrings(String[]array){
		String result = "";
		if(array[0] != ""){
		 result = array[0];
		for(int i=1; i<array.length; i++){
			result +=","+array[i];
		}
	}
		return result;
		
	}
	
	

}
