package com.ecommerce.models;

public class User {
	
	protected String username;
	private String password;
	
	public User(String usr, String pass){
		this.username = usr;
		this.password = pass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
