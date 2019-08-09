package com.ecommerce.models;

public class Admin extends User {
	
	private String firstName;
	private String lastName;
	
	public Admin(String usr, String pass, String fname, String lname) {
		super(usr, pass);
		this.firstName = fname; 
		this.lastName= lname;
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
	
	

}
