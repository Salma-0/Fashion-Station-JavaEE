package com.ecommerce;

import com.ecommerce.models.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.servlet.ServletContext;

import com.ecommerce.dao.*;

public class LoginService {
	
	private UserDAO udao = new UserDAO();
	private String msg = null;
	private boolean admin = false;
	private String ADMIN_USER;
	private String ADMIN_PASSWORD;
	
	
	public LoginService(){
            Properties props = new Properties();
            InputStream input = null;
            try {
            	input = LoginService.class.getClassLoader().getResourceAsStream("config.properties");
				props.load(input);
				ADMIN_USER = props.getProperty("adminUser");
				ADMIN_PASSWORD = props.getProperty("adminPassword");
			}catch(FileNotFoundException e){
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	}
	

	public boolean login(String usr, String pass){
	    User user = new User(usr, pass);
	    boolean logged = false;
	    try {
			if(udao.isUser(user)){
				msg = "Logged successfully..";
				logged = true;	
				if((usr.equals(ADMIN_USER)) &&(pass.equals(ADMIN_PASSWORD))){
					admin = true;
				}
				
			}else{
				msg = "Could not log in";
				logged = false;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    return logged;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public void setAdmin(boolean a){
		admin = a;
	}
	
	public boolean isAdmin(){
		return admin;
	}
	
}
