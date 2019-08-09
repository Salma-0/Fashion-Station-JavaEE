package com.ecommerce.dao;
import com.ecommerce.models.Item;
import com.ecommerce.models.Order;
import com.mysql.jdbc.Statement;

import javafx.scene.media.MediaPlayer.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDAO {
	

	private Connection connection = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private Driver driver = new Driver();

	public void addOrder(Order order)throws SQLException{
		String sql = "INSERT INTO orders(username, order_date, status, total) values(?,?,?,?)";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, order.getUsername());
			java.util.Date utilDate = order.getDate();
			java.sql.Date date = new java.sql.Date(utilDate.getTime());
			stmt.setDate(2, date);
			stmt.setString(3, order.getStatus().toString());
			stmt.setDouble(4, order.getTotalPrice());
			int number = stmt.executeUpdate();
			int generatedID = -1;
			rs = stmt.getGeneratedKeys();
			if(rs.next()){
				generatedID = rs.getInt(1);
			}
			addItems(order.getItems(), generatedID);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
	}
	
	public int getTheLastID() throws SQLException{
		int oid = 0;
		String sql = "SELECT LAST_INSERT_ID()";
		java.sql.Statement statement = null;
		try{
		   connection = driver.getConnected();
           statement = connection.createStatement();		
           rs = statement.executeQuery(sql);
           while(rs.next()){
        	   oid = rs.getInt(1);
           }
		}catch(Exception e){
			e.printStackTrace();
		}
       return oid;
	}
	
	public void addItems(ArrayList<Item>items, int order_id)throws SQLException{
	   String sql = "INSERT INTO item(product_id, order_id, color, size, quantity, price) values(?,?,?,?,?,?)";	
	   try{
		   connection = driver.getConnected();
		   for(Item i : items){
			   stmt = connection.prepareStatement(sql);
			   stmt.setInt(1, i.getProduct_id());
			   stmt.setInt(2, order_id);
			   stmt.setString(3, i.getChosenColor());
			   stmt.setString(4, i.getChosenSize());
			   stmt.setInt(5, i.getQuantity());
			   stmt.setDouble(6, i.getPrice());
			   stmt.executeUpdate();
		   }
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	}
	
	public ArrayList<Item>getItems(int oid) throws SQLException{
		ArrayList<Item>items = new ArrayList<Item>();
	    String sql = "SELECT * FROM item WHERE order_id = ?";
	    try{
	    	if(connection.isClosed()){
	    	connection = driver.getConnected();
	    	}
	    	stmt = connection.prepareStatement(sql);
	    	stmt.setInt(1, oid);
	    	rs = stmt.executeQuery();
	    	while(rs.next()){
	    		int id = rs.getInt(1);
	    		int pid = rs.getInt(2);
	    		String color = rs.getString(4);
	    		String size = rs.getString(5);
	    		int quantity = rs.getInt(6);
	    		double price = rs.getDouble(7);
	    		Item i = new Item(pid, color, size, quantity, price);
	    		i.setId(id);
	    		items.add(i);
	    	}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    return items;
	}
	
	public ArrayList<Order>getOrdersByCustomer(String uname)throws SQLException{
		ArrayList<Order>orders = new ArrayList<Order>();
		String sql = "SELECT * FROM orders WHERE username = ? ORDER BY order_id DESC";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, uname);
			rs = stmt.executeQuery();
			while(rs.next()){
				int order_id = rs.getInt(1);
				String username = rs.getString(2);
				java.sql.Date sqlDate = rs.getDate(3);
				java.util.Date date = new java.util.Date(sqlDate.getTime());
				String status = rs.getString(4);
				Order.Status orderStatus = Order.Status.valueOf(status);
				double total = rs.getDouble(5);				
				Order o = new Order(order_id, date, username, null, total);
				o.setStatus(orderStatus);
				orders.add(o);
			}
			
			for(Order o: orders){
				ArrayList<Item>items = getItems(o.getOrder_id());
				o.setItems(items);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return orders;
		
	}
	
	public ArrayList<Order>geAlltOrders()throws SQLException{
		ArrayList<Order>orders = new ArrayList<Order>();
		String sql = "SELECT * FROM orders ORDER BY order_id DESC";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				int order_id = rs.getInt(1);
				String username = rs.getString(2);
				java.sql.Date sqlDate = rs.getDate(3);
				java.util.Date date = new java.util.Date(sqlDate.getTime());
				String status = rs.getString(4);
				Order.Status orderStatus = Order.Status.valueOf(status);
				double total = rs.getDouble(5);				
				Order o = new Order(order_id, date, username, null, total);
				o.setStatus(orderStatus);
				orders.add(o);
			}
			
			for(Order o: orders){
				ArrayList<Item>items = getItems(o.getOrder_id());
				o.setItems(items);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return orders;
		
	}
	
	public Order getOrder(int oid)throws SQLException{
		String sql = "SELECT * FROM orders WHERE order_id = ?";
		Order order = null;
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, oid);
			rs = stmt.executeQuery();
			while(rs.next()){
				int order_id = rs.getInt(1);
				String username = rs.getString(2);
				java.sql.Date sqlDate = rs.getDate(3);
				java.util.Date date = new java.util.Date(sqlDate.getTime());
				String status = rs.getString(4);
				Order.Status orderStatus = Order.Status.valueOf(status);
				double total = rs.getDouble(5);				
			    order = new Order(order_id, date, username, null, total);
				order.setStatus(orderStatus);
				
			}
			
			
				ArrayList<Item>items = getItems(order.getOrder_id());
				order.setItems(items);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return order;
		
	}
	
	public void deleteOrder(int id)throws SQLException{
		String deleteOrderSql = "DELETE FROM orders WHERE order_id = ?";
		String deleteItemsSQl = "DELETE FROM item WHERE order_id = ?";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(deleteOrderSql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
			PreparedStatement st = connection.prepareStatement(deleteItemsSQl);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
	}
	
	public void updateOrder(Order o)throws SQLException{
		String sql = "UPDATE orders SET username = ?, order_date = ?, status = ?, total = ? WHERE order_id = ?";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, o.getUsername());
			java.util.Date utilDate = o.getDate();
			java.sql.Date date = new java.sql.Date(utilDate.getTime());
			stmt.setDate(2, date);
			stmt.setString(3, o.getStatus().toString());
			stmt.setDouble(4, o.getTotalPrice());
			stmt.setInt(5, o.getOrder_id());

			stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
	}
	
	public ArrayList<Order>getOrdersByDate(String odate)throws SQLException{
		ArrayList<Order>orders = new ArrayList<Order>();
		String sql = "SELECT * FROM orders WHERE order_date = ? ORDER BY order_id DESC";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, odate);
			rs = stmt.executeQuery();
			while(rs.next()){
				int order_id = rs.getInt(1);
				String username = rs.getString(2);
				java.sql.Date sqlDate = rs.getDate(3);
				java.util.Date date = new java.util.Date(sqlDate.getTime());
				String status = rs.getString(4);
				Order.Status orderStatus = Order.Status.valueOf(status);
				double total = rs.getDouble(5);				
				Order o = new Order(order_id, date, username, null, total);
				o.setStatus(orderStatus);
				orders.add(o);
			}
			
			for(Order o: orders){
				ArrayList<Item>items = getItems(o.getOrder_id());
				o.setItems(items);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return orders;
		
	}
	
	public ArrayList<Order>getOrdersByStatus(String ostatus)throws SQLException{
		ArrayList<Order>orders = new ArrayList<Order>();
		String sql = "SELECT * FROM orders WHERE status = ? ORDER BY order_id DESC";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, ostatus);
			rs = stmt.executeQuery();
			while(rs.next()){
				int order_id = rs.getInt(1);
				String username = rs.getString(2);
				java.sql.Date sqlDate = rs.getDate(3);
				java.util.Date date = new java.util.Date(sqlDate.getTime());
				String status = rs.getString(4);
				Order.Status orderStatus = Order.Status.valueOf(status);
				double total = rs.getDouble(5);				
				Order o = new Order(order_id, date, username, null, total);
				o.setStatus(orderStatus);
				orders.add(o);
			}
			
			for(Order o: orders){
				ArrayList<Item>items = getItems(o.getOrder_id());
				o.setItems(items);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return orders;
		
	}
}
