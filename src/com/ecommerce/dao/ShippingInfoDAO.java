package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommerce.models.ShippingInfo;

public class ShippingInfoDAO {
	
	private Connection connection = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private Driver driver = new Driver();
	
	public boolean addShippingInfo(ShippingInfo shippingInfo) throws SQLException{
		String sql = "INSERT INTO shipping_information(username, country, city, street, address1, address2, notes) values(?,?,?,?,?,?,?)";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, shippingInfo.getUsername());
			stmt.setString(2, shippingInfo.getCountry());
			stmt.setString(3, shippingInfo.getCity());
			stmt.setString(4, shippingInfo.getStreet());
			stmt.setString(5, shippingInfo.getAddress1());
			stmt.setString(6, shippingInfo.getAddress2());
			stmt.setString(7, shippingInfo.getNotes());
			
			stmt.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return false;
	}
	
	public void deleteShippingInformation(String username)throws SQLException{
		String sql = "DELETE FROM shipping_information WHERE username = ?";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
	}
	
	public void updateShippingInfo(ShippingInfo si)throws SQLException{
		String sql  = "UPDATE shipping_information SET country =?, city = ?, street = ?, address1 = ?, address2 = ?, notes = ? "
				+ "WHERE username = ?";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, si.getCountry());
			stmt.setString(2, si.getCity());
			stmt.setString(3, si.getStreet());
			stmt.setString(4, si.getAddress1());
			stmt.setString(5, si.getAddress2());
			stmt.setString(6, si.getNotes());
			stmt.setString(7, si.getUsername());
			
			stmt.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
	}
	
	public ShippingInfo getShippingInfo(String username)throws SQLException{
		ShippingInfo si = null;
		String sql = "SELECT * FROM shipping_information WHERE username = ?";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			while(rs.next()){
				String country = rs.getString(2);
				String city = rs.getString(3);
				String street = rs.getString(4);
				String address1 = rs.getString(5);
				String address2 = rs.getString(6);
				String notes = rs.getString(7);
				
				si  = new ShippingInfo(username, country, city, street, address1, address2, notes);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return si;
	}

}
