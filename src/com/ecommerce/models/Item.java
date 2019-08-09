package com.ecommerce.models;

import java.sql.SQLException;

import com.ecommerce.dao.ImageDAO;
import com.ecommerce.dao.ProductDAO;

public class Item {
	private int id;
	private int product_id;
	private String name = null;
	private String image = null;
	private String chosenSize;
	private String chosenColor;
	private int quantity;
	private double price;
	
	public Item(int pid, String color, String size, int q, double p){
		this.product_id = pid;
		this.chosenColor = color;
		this.chosenSize = size;
		this.quantity = q;
		this.price = p;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getChosenSize() {
		return chosenSize;
	}

	public void setChosenSize(String chosenSize) {
		this.chosenSize = chosenSize;
	}

	public String getChosenColor() {
		return chosenColor;
	}

	public void setChosenColor(String chosenColor) {
		this.chosenColor = chosenColor;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public String getImage() {
		String image = null;
		try {
			image = (new ImageDAO()).getFirstImage(product_id).getImage();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		String name = null;
		try{
			name = (new ProductDAO().getProductByID(product_id)).getName();
			if(name.length() > 15){
				name = name.substring(0,14);
				name += "...";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
