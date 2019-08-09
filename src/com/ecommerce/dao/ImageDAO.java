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

import com.ecommerce.models.Product;
import com.ecommerce.models.ProductImage;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class ImageDAO {
	
	private Connection connection = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private Driver driver = new Driver();
	
	public void addImage(ProductImage pImage) throws SQLException{
		String sql = "INSERT INTO product_image(image, product_id) VALUES(?,?)";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
				byte[] decodedBytes =  Base64.decode(pImage.getImage());
				Blob blFile = new SerialBlob(decodedBytes);
				stmt.setBlob(1, blFile);
				stmt.setInt(2, pImage.getProduct_id());
				stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
	}
	
	
	public ArrayList<ProductImage>getImages(int pid) throws SQLException{
		
		ArrayList<ProductImage>images = new ArrayList<ProductImage>();
		String sql = "select id,image from product_image where product_id = ?";
		
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, pid);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt(1);
				java.sql.Blob blob = rs.getBlob(2);  
				InputStream in = blob.getBinaryStream();  
				byte[] bytes = IOUtils.toByteArray(in);
				String encodedImage = Base64.encode(bytes);
				ProductImage pImage = new ProductImage(id, encodedImage, pid);
				images.add(pImage);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		
		return images;
	}
	
	public ProductImage getFirstImage(int pid)throws SQLException{
		ProductImage pImage = null;
		String sql = "select id, image from product_image where product_id = ? limit 1";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, pid);
			rs = stmt.executeQuery();
			if(rs.next()){
			int id = rs.getInt(1);
			java.sql.Blob blob = rs.getBlob(2);  
			InputStream in = blob.getBinaryStream();  
			byte[] bytes = IOUtils.toByteArray(in);
			String encodedImage = Base64.encode(bytes);
			 pImage = new ProductImage(id, encodedImage, pid);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
		return pImage;
	}
	
	public void updateImages(ProductImage pImage)throws SQLException{
		String sql = "UPDATE product_image SET image = ? WHERE product_id = ? and id = ?";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, pImage.getImage());
			stmt.setInt(2, pImage.getProduct_id());
			stmt.setInt(3, pImage.getId());
			stmt.executeUpdate();
	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs,stmt, connection);
		}		
	}
	
	public void deleteImage(int id)throws SQLException{
		String sql = "DELETE FROM product_image WHERE id = ?";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
	}
	
	public void deleteImagesByProduct(int pid)throws SQLException{
		String sql = "DELETE FROM product_image WHERE product_id = ?";
		try{
			connection = driver.getConnected();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, pid);
			stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			driver.closeConnection(rs, stmt, connection);
		}
	}
	

}
