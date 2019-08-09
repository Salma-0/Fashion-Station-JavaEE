package com.ecommerce.models;

public class ShippingInfo {

	private String username;
	private String country;
	private String city;
	private String street;
	private String address1;
	private String address2;
	private String notes;
	
	public ShippingInfo(String username,String co, String ci, String str, String addr1, String addr2, String notes){
		this.username = username;
		this.country = co;
		this.city = ci;
		this.street = str;
		this.address1 = addr1;
		this.address2 = addr2;
		this.notes = notes;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isValid(){
		if(this == null)
			return false;
		return (country != null) && (city != null) && (street != null) && (address1 != null);
	}
	
	
}
