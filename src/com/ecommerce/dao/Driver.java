package com.ecommerce.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


//this class is dedicated to connect to the database named Library_System

public class Driver {
	
	   private String URL; 
	   private String USERNAME; 
	   private String PASSWORD;
	   
	   public Driver(){
		   Properties props = new Properties();
		   InputStream input = null;
		   
		   input = Driver.class.getClassLoader().getResourceAsStream("config.properties");
		   try {
			props.load(input);
			URL = props.getProperty("DB_URL");
			USERNAME = props.getProperty("DB_USER");
			PASSWORD = props.getProperty("DB_PASSWORD");
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		   catch (IOException e) {

			e.printStackTrace();
		}
	   }
	   
	   public Connection getConnected() throws Exception{
		   Connection connection = null;
		   try{
			   Class.forName("com.mysql.jdbc.Driver");
			   connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   return connection;
	   }
	
	
	
	 public void closeConnection(ResultSet rs, PreparedStatement pStmt, Connection conn) throws SQLException{
		if(rs != null){
			rs.close();
		}
		if(pStmt != null){
			pStmt.close();
		}
		if(conn != null){
			conn.close();
		}
	}

	 public void closeConnection(ResultSet rs, Statement stmt, Connection conn) throws SQLException{
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
			
		}


}
