package com.ecommerce.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

public class Order {
	public enum Status {
			PENDING, AWAITING_PAYMENT, AWAITING_FULLFILLMENT, AWAITING_SHIPMENT, AWAITING_PICKUP, PARTIALLY_SHIPPED, SHIPPED, COMPLETED, CANCELLED, DECLINED, REFUNDED, DISPUTED;
			}
    private String username;
    private Date date;
    private ArrayList<Item>items;
    private int order_id;
    private double totalPrice;
    private Status orderStatus = Status.PENDING;
    private String formattedDate; 
    
    public Order(int oid, Date d, String username, ArrayList<Item>items, double total){
        this.order_id = oid;
        this.date = d;
        this.username = username;
        this.items = items;
        this.totalPrice = total;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String uname) {
		this.username = uname;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
    
	public void setStatus(Status orderStatus){
		this.orderStatus = orderStatus; 
	}
	
	public Status getStatus(){
		return orderStatus;
	}
	
	public String getFormattedDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
		this.formattedDate = formatter.format(date);
		return formattedDate;
	}
    

}
