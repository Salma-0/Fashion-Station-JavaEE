package com.ecommerce.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.ecommerce.*;
import com.ecommerce.models.Category;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class tester {
	
	public static void main(String []args){
		
		CategoryDAO cdao = new CategoryDAO();
		try {
			Category c = cdao.getCategoryByID(7);
			byte[] bytes = Base64.decode(c.getImage());
		    ByteArrayInputStream input = new ByteArrayInputStream(bytes);
		    BufferedImage img = ImageIO.read(input);
		    JFrame frame = new JFrame("frame");
		    frame.setIconImage(img);
		    frame.setVisible(true);
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Base64DecodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}