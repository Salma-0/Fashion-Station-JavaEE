package com.ecommerce;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession  session = request.getSession();
		String productName = request.getParameter("product");
		if(productName == null){
			response.getWriter().println("Product name is null");
			return;
		}else{
			System.out.println(productName);
		}
		
		
		String price = request.getParameter("price");
		double prc;
		if(price == null){
			response.getWriter().println("assign a price");
			return;
		}else{
			prc = Double.parseDouble(price);
		}
		
		
		String quantity = request.getParameter("quantity");
		int q;
		if(quantity == null){
			q =1;
			response.getWriter().println("assign a quantity");
			return;
		}else{
			q = Integer.parseInt(quantity);
			System.out.println("Quanatiy: "+q);
		}
		
		String category = request.getParameter("category");
		if(category == null){
			response.getWriter().println("category was not selected");
			return;
		}
		
		
		String description =request.getParameter("description");
		if(description == null){
			response.getWriter().println("Description cannot be empty");
			return;
		}
		
		String clrs = request.getParameter("colors");
		String[] colors = clrs.split(",");
		String szs = request.getParameter("sizes");
		String[] sizes = szs.split(",");
		
		try{
			CategoryDAO cdao = new CategoryDAO();
			ProductDAO pdao = new ProductDAO();
			
			Category c = cdao.getCategoryByName(category);
			Product p = new Product(0, productName, description, q, c, prc, colors, sizes);
		    pdao.addProduct(p);
		    int id = pdao.getProductID(productName);
		    if(id == 0){
		        response.getWriter().println("Product could not be added!!!");
		    	return;
		    }
		    p.setId(id);
		    p.print();
		    session.setAttribute("product", p);
		   
		    response.getWriter().println("you new product added successfully");
			response.sendRedirect("add-image.jsp");

		}catch(IOException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}
	
	
}
