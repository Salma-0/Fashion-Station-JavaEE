package com.ecommerce;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.dao.ImageDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.models.Product;
import com.ecommerce.models.ProductBox;
import com.ecommerce.models.ProductImage;


@WebServlet("/AdminProductsServlet")
public class AdminProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminProductsServlet() {
        super();
    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			/*1.get an array of all products 
			 *2.set the array as session attribute
			 *3.forward to products.jsp page
			 */
			
			HttpSession session = request.getSession();
			ProductDAO pdao = new ProductDAO();
			ArrayList<Product>products = new ArrayList<Product>();
			
			try {
				products = pdao.getProducts();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			ArrayList<ProductBox>boxes = getBoxes(products);
			session.setAttribute("allProducts", boxes);
			RequestDispatcher disp = request.getRequestDispatcher("/products.jsp");
			disp.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
	protected ArrayList<ProductBox> getBoxes(ArrayList<Product>products){
		ArrayList<ProductBox>boxes = new ArrayList<ProductBox>();
		ImageDAO imageDAO = new ImageDAO();
		try{
		
		for (Product p : products) {
			if(p.getName().length() > 20){
				String subName = p.getName().substring(0, 19);
				subName += "...";
				p.setName(subName);
			}
			ProductImage pImage = imageDAO.getFirstImage(p.getId());
		    String image = pImage.getImage();
		    ProductBox pb = new ProductBox(p.getId(),p.getName(), p.getPrice(), image);
		    System.out.println(pb.getId());
			boxes.add(pb);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return boxes;
	}


}
