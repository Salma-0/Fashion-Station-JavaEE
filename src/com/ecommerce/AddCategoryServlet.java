package com.ecommerce;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Base64;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.models.Category;
import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;
import com.sun.medialib.mlib.Image;





@WebServlet("/AddCategoryServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	
maxFileSize=1024*1024*50,      	
maxRequestSize=1024*1024*100)  
public class AddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    
    public AddCategoryServlet() {
        super();
       
    }

	//DELETE CATEGORY
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("categoryID");
		int id = 0;
		if(cid != null && !cid.isEmpty())
			id = Integer.parseInt(cid);
		try {
			(new CategoryDAO()).deleteCategory(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/AdminCategoriesServlet").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		    
		String name = request.getParameter("category");
		Part filePart = request.getPart("file");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		InputStream fileContent = filePart.getInputStream();
		
		try {
	       
			byte[] bytes = IOUtils.toByteArray(fileContent);
			String encoded = Base64.getEncoder().encodeToString(bytes);
	        
            Category category = new Category(0,name ,encoded);
            CategoryDAO categorydao = new CategoryDAO();
            categorydao.addCategory(category);
            getServletContext().getRequestDispatcher("/AdminCategoriesServlet").forward(request, response);

	    } catch (IOException e) {
	        System.out.println("Error");
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		    
		   doGet(request, response);
       }
    
}
