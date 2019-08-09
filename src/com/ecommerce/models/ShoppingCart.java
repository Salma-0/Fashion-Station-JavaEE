package com.ecommerce.models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ShoppingCart {
	private static ShoppingCart cart = null;
	private ArrayList<Item>items;
	private int cart_id;
	private static AtomicInteger count = new AtomicInteger(0);
	private double totalPrice = 0;

	

	private ShoppingCart(){
		this.items = new ArrayList<Item>();
		cart_id = count.incrementAndGet();
	}
	
	public static ShoppingCart createCart(){
		if(cart == null){
			cart = new ShoppingCart();
		}
		return cart;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	
	public void addItem(Item item){
		this.items.add(item);
	}
	
	public void removeItem(int i){
		this.items.remove(i);
	}

	public double getTotalPrice() {
		for(Item i: items){
			totalPrice += i.getPrice() * i.getQuantity();
		}
		DecimalFormat format = new DecimalFormat(".##");
		format.format(totalPrice);
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void checkout(){
		
	}
	
	
	
	
}
