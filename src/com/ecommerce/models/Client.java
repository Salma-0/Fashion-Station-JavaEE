package com.ecommerce.models;

import java.sql.SQLException;

import com.ecommerce.dao.ShippingInfoDAO;

public class Client extends User{

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
	private ShippingInfo shipment_info;
	private String stripeID = null;

	
	public Client(String usr, String pass, String fname, String lname, String pn, String email, ShippingInfo si) {
		super(usr, pass);
		this.lastName = lname;
		this.firstName = fname;
		this.phoneNumber = pn;
		this.email = email;
		this.shipment_info = si;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public ShippingInfo getShipmentInfo() throws SQLException {
		ShippingInfo si = null;
		ShippingInfoDAO sid = new ShippingInfoDAO();
	    si = sid.getShippingInfo(username);
	    return si;
	}


	public void setShipmentInfo(ShippingInfo shipment_info) {
		this.shipment_info = shipment_info;
	}


	public String getStripeID() {
		return stripeID;
	}


	public void setStripeID(String stripeID) {
		this.stripeID = stripeID;
	}

	
}
