package com.ecommerce;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

/**
 * Servlet implementation class CatalogServlet
 */
@WebServlet("/CatalogServlet")
public class CatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cid = request.getParameter("categoryID");
		int categoryID;
		if(cid != null && cid != ""){
			categoryID = Integer.parseInt(cid);
		}else{
			categoryID = 0;
		}

		HttpSession session = request.getSession();
		ProductDAO productDAO = new ProductDAO();
		
		ArrayList<ProductBox> boxes = new ArrayList<ProductBox>();

		try {
			ArrayList<Product> products;
			if(categoryID <= 0){
		    products = productDAO.getProducts();
			}else{
				products = productDAO.getProductsByCategory(categoryID);
			}
			boxes = getBoxes(products);
			session.setAttribute("boxes", boxes);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalog.jsp");
			dispatcher.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchKey = request.getParameter("search");
		HttpSession session = request.getSession();
		
		ProductDAO productDAO = new ProductDAO();
		ArrayList<ProductBox> boxes = new ArrayList<ProductBox>();
		
		try{
			ArrayList<Product>products = productDAO.getProductsByName(searchKey);
			boxes = getBoxes(products);
			session.setAttribute("boxes", boxes);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalog.jsp");
			dispatcher.forward(request, response);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
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
