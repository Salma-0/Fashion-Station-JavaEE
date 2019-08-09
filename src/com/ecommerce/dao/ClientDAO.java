package com.ecommerce.dao;
import java.sql.*;

import com.ecommerce.PaymentService;
import com.ecommerce.models.Client;
import com.ecommerce.models.ShippingInfo;
import com.ecommerce.models.User;

public class ClientDAO {
	
	private Connection connection = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private Driver driver = new Driver();
	
	public boolean isClient(String username) throws SQLException{
		String sql = "select * from client where username = ?";
		boolean isClient = false;
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			if(rs.next()){
				isClient = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return isClient;
	}
	
	public void addClient(Client c) throws SQLException{
				String sql = "insert into client(username, password, first_name, last_name, phone_number, email, stripe_id) values(?,?,?,?,?,?,?)";
				UserDAO udao = new UserDAO();
		try{
			if(udao.isUsernameUnique(c.getUsername())){
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, c.getUsername());
			stmt.setString(2, c.getPassword());
			stmt.setString(3, c.getFirstName());
			stmt.setString(4, c.getLastName());
			stmt.setString(5, c.getPhoneNumber());
			stmt.setString(6, c.getEmail());
			PaymentService service = new PaymentService();
	        String stripe_id = service.createCustomer(c);
			stmt.setString(7, stripe_id);
			
			stmt.executeUpdate();
			udao.addUser(new User(c.getUsername(), c.getPassword()));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
	}
	
	public void deleteClient(Client c) throws SQLException{
		String sql = "delete from client where username = ?";
		UserDAO udao = new UserDAO();
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, c.getUsername());
			stmt.executeUpdate();
			udao.deletUser(new User(c.getUsername(), c.getPassword()));
			
			ShippingInfoDAO sid = new ShippingInfoDAO();
			sid.deleteShippingInformation(c.getUsername());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
	}
		
		public Client getClientByUsername(String username)throws SQLException{
			Client client = null;
			String sql = "select * from client where username = ?";
			
			try{
				connection = driver.getConnected();
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, username);
				rs = stmt.executeQuery();
				
				while(rs.next()){
					String uname = rs.getString(1);
					String password = rs.getString(2);
					String firstname = rs.getString(3);
					String lastName = rs.getString(4);
					String phoneNumber = rs.getString(5);
					String email = rs.getString(6);
					client = new Client(uname, password, firstname, lastName, phoneNumber, email, null);
				}
				
				ShippingInfo si = (new ShippingInfoDAO()).getShippingInfo(username);
				if(si.isValid()){
				client.setShipmentInfo(si);
				}
		}catch(Exception e){
		   e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
			return client;
	}
		
     public void updateClient(Client client)throws SQLException{
    	 String sql = "UPDATE client SET password = ?, first_name=?, last_name = ?, phone_number = ?, email = ?, stripe_id = ? WHERE username = ?";
    	 try{
    		 connection = driver.getConnected();
    		 stmt = connection.prepareStatement(sql);
    		 stmt.setString(1, client.getPassword());
    		 stmt.setString(2, client.getFirstName());
    		 stmt.setString(3, client.getLastName());
    		 stmt.setString(4, client.getPhoneNumber());
    		 stmt.setString(5, client.getEmail());
    		 stmt.setString(7, client.getUsername());
    		 stmt.setString(6, client.getStripeID());
    		 
    		 stmt.executeUpdate();
    		 UserDAO udao = new UserDAO();
    		 udao.updateUser(new User(client.getUsername(), client.getPassword()));
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }finally{
    		 driver.closeConnection(rs, stmt, connection);
    	 }
     }
		
		
		
}
