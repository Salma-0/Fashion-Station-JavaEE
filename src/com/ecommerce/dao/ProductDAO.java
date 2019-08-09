package com.ecommerce.dao;

import java.sql.*;
import java.util.ArrayList;

import com.ecommerce.models.Category;
import com.ecommerce.models.Product;

public class ProductDAO {
	
	private Connection connection = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private Driver driver = new Driver();
	
	
	//add new product
	public void addProduct(Product p) throws SQLException{
		String sql = "INSERT INTO product(name, description, quantity, category_id, price, colors, sizes) VALUES(?,?,?,?,?,?,?)";
		String colors = p.mergeStrings(p.getColors());
		String sizes = p.mergeStrings(p.getSizes());
		
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, p.getName());
			stmt.setString(2, p.getDescription());
			stmt.setInt(3, p.getQuantity());
			int id = p.getCategory().getId();
			stmt.setInt(4, id);
			stmt.setDouble(5, p.getPrice());
			stmt.setString(6, colors);
			stmt.setString(7, sizes);
			
            stmt.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
	}
	
	
	//delete product
	public void deleteProduct(int pid) throws SQLException{
		String sql = "DELETE FROM product WHERE id = ?";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1,pid);
			stmt.executeUpdate();
			
		   (new ImageDAO()).deleteImagesByProduct(pid);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
	}
	
	
	//retrieve product by id
	public Product getProductByID(int id) throws SQLException{
		String sql = "SELECT * FROM product WHERE id = ?";
		Product p = null;
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				int ID= rs.getInt(1);
				String name = rs.getString(2);
				String desc = rs.getString(3);
				int q = rs.getInt(4);
				
				int cid = rs.getInt(5);
				CategoryDAO cdao = new CategoryDAO();
				Category c = cdao.getCategoryByID(cid);
				double price = rs.getDouble(6);
				
				String[] colors = rs.getString(7).split(",");
				String[] sizes = rs.getString(8).split(",");
				
				 p = new Product(ID, name, desc, q, c, price, colors, sizes);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return p;
	}
	
	public ArrayList<Product> getProducts() throws SQLException{
		String sql = "SELECT * FROM product ORDER BY id DESC";
		ArrayList<Product> products = new ArrayList<Product>();
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				int quantity = rs.getInt(4);
				
				
				int cid = rs.getInt(5);
				CategoryDAO cdao = new CategoryDAO();
				Category c = cdao.getCategoryByID(cid);
				
				double price = rs.getDouble(6);
				String[] colors = rs.getString(7).split(",");
				String[] sizes = rs.getString(8).split(",");
				
				Product p = new Product(id, name, description, quantity, c, price, colors, sizes);
				products.add(p);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return products;
	}
	
	public ArrayList<Product> getProductsByCategory(int category_id) throws SQLException{
		String sql = "SELECT * FROM product WHERE category_id = ?";
		ArrayList<Product> products = new ArrayList<Product>();
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, category_id);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				int quantity = rs.getInt(4);
				
				int cid = rs.getInt(5);
				CategoryDAO cdao = new CategoryDAO();
				Category c = cdao.getCategoryByID(cid);
				
				double price = rs.getDouble(6);
				String[] colors = rs.getString(7).split(",");
				String[] sizes = rs.getString(8).split(",");
				
				Product p = new Product(id, name, description, quantity, c, price, colors, sizes);
				products.add(p);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return products;
	}
	
	public int getProductID(String productName)throws SQLException{
		String sql = "SELECT id FROM product WHERE name = ?";
		int id = 0;
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, productName);
			rs = stmt.executeQuery();
			while(rs.next()){
				id = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return id;
	}
	
	
	//retrieve product by id
		public ArrayList<Product> getProductsByName(String name) throws SQLException{
			String sql = "SELECT * FROM product WHERE name LIKE '%" + name + "%'";
			ArrayList<Product>products = new ArrayList<Product>();
			try{
				connection = driver.getConnected();
				stmt = connection.prepareStatement(sql);
				rs = stmt.executeQuery();
				
				while(rs.next()){
					int ID= rs.getInt(1);
					String pname = rs.getString(2);
					String desc = rs.getString(3);
					int q = rs.getInt(4);
					
					int cid = rs.getInt(5);
					CategoryDAO cdao = new CategoryDAO();
					Category c = cdao.getCategoryByID(cid);
					double price = rs.getDouble(6);
					
					String[] colors = rs.getString(7).split(",");
					String[] sizes = rs.getString(8).split(",");
					
					 Product p = new Product(ID, pname, desc, q, c, price, colors, sizes);
					 products.add(p);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				driver.closeConnection(rs, stmt, connection);
			}
			return products;
		}
		
		
		//update Product
		public boolean updateProduct(Product p)throws SQLException{
			String sql = "UPDATE product SET name = ?, description = ?, quantity = ?, category_id = ?, price = ?, colors = ?, sizes = ? WHERE id = ?";
			try{
				connection = driver.getConnected();
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, p.getName());
				stmt.setString(2, p.getDescription());
				stmt.setInt(3, p.getQuantity());
				stmt.setInt(4, p.getCategory().getId());
				stmt.setDouble(5, p.getPrice());
				stmt.setString(6, p.mergeStrings(p.getColors()));
				stmt.setString(7, p.mergeStrings(p.getSizes()));
				stmt.setInt(8, p.getId());
				
				stmt.executeUpdate();
				return true;
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				driver.closeConnection(rs, stmt, connection);
			}
			return false;
		}
		
		
		
		
	
}
