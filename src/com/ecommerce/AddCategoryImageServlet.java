package com.ecommerce;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.models.Category;
import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;


@WebServlet("/AddCategoryImageServlet")
@javax.servlet.annotation.MultipartConfig
public class AddCategoryImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddCategoryImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    //delete category image
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	//add categoryImage 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDAO cdao = new CategoryDAO();		
		Part img1 = request.getPart("img1");
		HttpSession session = request.getSession();
		
		try{
		   Category c = (Category)session.getAttribute("selectedCategory");
		   InputStream input = null;
		   if(img1 != null){
		     input = img1.getInputStream();
		     byte[] bytes = IOUtils.toByteArray(input);
		     String encoded = Base64.getEncoder().encodeToString(bytes);
			c.setImage(encoded);
			cdao.updateCategory(c);
		 
		}
		}catch(SQLException e){
			e.printStackTrace();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/update-category.jsp").forward(request, response);
		
	}

}
