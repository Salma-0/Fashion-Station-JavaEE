package com.ecommerce.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;

import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


public class CategoryDAO {
	
	private Connection connection = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private Driver driver = new Driver();
	
	public Category getCategoryByID(int id)throws SQLException{
		String sql = "select * from category where id = ?";
		Category c = null;
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				int ID = rs.getInt(1);
				String name = rs.getString(2);
				
				String encodedImage;
				java.sql.Blob blob = rs.getBlob(3);
				if(blob.equals(null))
					encodedImage = null;
				else{
				InputStream in = blob.getBinaryStream();  
				byte[] bytes = IOUtils.toByteArray(in);
				 encodedImage = Base64.encode(bytes);
				}
				
				c = new Category(ID, name, encodedImage);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return c;
	}
	
	public Category getCategoryByName(String name)throws SQLException{
		String sql = "select * from category where name = ?";
		Category c = null;
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				int id =rs.getInt(1);
				String categoryName = rs.getString(2);
				java.sql.Blob blob = rs.getBlob(3);  
				InputStream in = blob.getBinaryStream();  
				byte[] bytes = IOUtils.toByteArray(in);
				String encodedImage = Base64.encode(bytes);
				
				c = new Category(id, categoryName, encodedImage);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return c;
	}
	
	public void addCategory(Category c) throws SQLException{
		String sql = "insert into category(name, image) values(?,?)";
		
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, c.getName());
			
			byte[] decodedBytes =  Base64.decode(c.getImage());
			Blob blFile = new SerialBlob(decodedBytes);
			stmt.setBlob(2, blFile);
			stmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
	}
	
	//delete category
		public void deleteCategory(int id) throws SQLException{
			String sql = "delete from category where id = ?";
			try{
				connection = driver.getConnected();
				stmt = connection.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.executeUpdate();
				
				ProductDAO pdao  = new ProductDAO();
				ArrayList<Product>products = pdao.getProductsByCategory(id);
				for(Product p : products){
					pdao.deleteProduct(p.getId());
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				driver.closeConnection(rs, stmt, connection);
			}
		}
		
		
		
		public ArrayList<Category> getCategories()throws SQLException {
			String sql = "select * from category";
			ArrayList<Category> categories = new ArrayList<Category>();
			try{
				connection = driver.getConnected();
				stmt = connection.prepareStatement(sql);
				rs = stmt.executeQuery();
				
				while(rs.next()){
					int id = rs.getInt(1);
					String name = rs.getString(2);
					java.sql.Blob blob = rs.getBlob(3);  
					InputStream in = blob.getBinaryStream();  
					byte[] bytes = IOUtils.toByteArray(in);
					String encodedImage = Base64.encode(bytes);
					
					Category c = new Category(id,name, encodedImage);
					c.setId(id);
					categories.add(c);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				driver.closeConnection(rs, stmt, connection);
			}
			return categories;
		}
		
		
		
		public int getCategoryID(String name) throws SQLException{
			String sql = "select * from category where name = ?";
			int id = 0;
			try{
				connection = driver.getConnected();
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, name);
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
		
		public void updateCategory(Category c)throws SQLException{
			String sql = "UPDATE category SET name = ?, image = ? WHERE id = ?";
			try{
				connection = driver.getConnected();
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, c.getName());
				
				  byte[] decodedBytes =  Base64.decode(c.getImage());
				 Blob blFile = new SerialBlob(decodedBytes);
				  stmt.setBlob(2, blFile);

				
				stmt.setInt(3, c.getId());
				stmt.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				driver.closeConnection(rs, stmt, connection);
			}
		}
		

}
