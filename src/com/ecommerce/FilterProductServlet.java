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

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.dao.ImageDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.models.Product;
import com.ecommerce.models.ProductBox;
import com.ecommerce.models.ProductImage;
import com.ecommerce.models.Category;
/**
 * Servlet implementation class FilterProductServlet
 */
@WebServlet("/FilterProductServlet")
public class FilterProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FilterProductServlet() {
        super();
    }

    
	//delete Product
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productID = request.getParameter("productID");
		System.out.println(productID);
		int pid = 0;
		if(productID != null && !productID.isEmpty())
			pid = Integer.parseInt(productID);
		
		ProductDAO pdao = new ProductDAO();
		try {
			pdao.deleteProduct(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminProductsServlet");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String filter = request.getParameter("filterChoice");
		String argument = request.getParameter("filterArgument");
		ArrayList<Product>products = new ArrayList<Product>();
		ProductDAO dao = new ProductDAO();
		CategoryDAO cdao = new CategoryDAO();
		Category c = null;
		try{
		if(filter == null || filter.isEmpty()){
			filter = "all";
		}else{
			switch(filter){
			case "category": 
				if(argument == null || argument.isEmpty())
					products = dao.getProducts();
				else{
				    c = cdao.getCategoryByName(argument);
					products = dao.getProductsByCategory(c.getId());
				}
				break;
			default: 
				products = dao.getProducts();
			}
		}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		ArrayList<ProductBox>boxes = getBoxes(products);
		HttpSession session = request.getSession();
		session.setAttribute("allProducts", boxes);
		
		RequestDispatcher disp = request.getRequestDispatcher("/products.jsp");
		disp.forward(request, response);
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


