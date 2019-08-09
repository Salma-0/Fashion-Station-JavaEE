package com.ecommerce.dao;

import java.sql.*;

import com.ecommerce.models.User;

public class UserDAO {
	
    private Connection connection = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private Driver driver = new Driver();
    
    
    public boolean isUser(User user) throws SQLException{
    	String sql = "SELECT * FROM user WHERE username=? AND password = ?";
    	boolean isUser = false;
    	try{
    		connection = driver.getConnected();
    		stmt = connection.prepareStatement(sql);
    		stmt.setString(1, user.getUsername());
    		stmt.setString(2, user.getPassword());
    		rs = stmt.executeQuery();
    		if(rs.next()){
    			isUser = true;
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		driver.closeConnection(rs, stmt, connection);
    	}
    	return isUser;
    }
    
    public boolean isUsernameUnique(String uname) throws SQLException{
    	String sql = "SELECT username FROM user WHERE username= ?";
    	boolean unique = false;
    	try{
    		connection = driver.getConnected();
    		stmt = connection.prepareStatement(sql);
    		stmt.setString(1, uname);
    		rs = stmt.executeQuery();
    		if(!rs.next()){
    			unique = true;
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		driver.closeConnection(rs, stmt, connection);
    	}
    	return unique;
    }
    
    public void addUser(User user) throws SQLException{
    	String sql = "insert into user values(?,?)";
    	try{
    		connection = driver.getConnected();
    		stmt = connection.prepareStatement(sql);
    		stmt.setString(1, user.getUsername());
    		stmt.setString(2, user.getPassword());
    		stmt.executeUpdate();
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		driver.closeConnection(rs, stmt, connection);
    	}
    }
    
    public void deletUser(User user) throws SQLException{
    	String sql = "DELETE FROM user WHERE username = ?";
    	try{
    		connection = driver.getConnected();
    		stmt = connection.prepareStatement(sql);
    		stmt.setString(1, user.getUsername());
    		stmt.executeUpdate();
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		driver.closeConnection(rs, stmt, connection);
    	}
    }
    
    public void updateUser(User u)throws SQLException{
    	String sql = "UPDATE user SET password = ? WHERE username = ?";
    	try{
    		connection = driver.getConnected();
    		stmt = connection.prepareStatement(sql);
    		stmt.setString(1, u.getPassword());
    		stmt.setString(2, u.getUsername());
    		
    		stmt.executeUpdate();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
