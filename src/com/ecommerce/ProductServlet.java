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
import com.ecommerce.models.Item;
import com.ecommerce.models.Product;
import com.ecommerce.models.ProductImage;
import com.ecommerce.models.ShoppingCart;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    Product product = (Product)session.getAttribute("product");
	    if(product.equals(null)){
	    	String linkID = request.getParameter("linkID");
	    	int pid = 0;
	    	if((linkID != null) && (!linkID.isEmpty())){
	          pid = Integer.parseInt(linkID);
	    	}
	    	ProductDAO pdao = new ProductDAO();
	    	try {
				product = pdao.getProductByID(pid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    session.setAttribute("title", product.getName());
	    session.setAttribute("name", product.getName());
	    session.setAttribute("desc", product.getDescription());
	    session.setAttribute("price", product.getPrice());
	    ImageDAO imageDAO = new ImageDAO();
	    ArrayList<String>encodedImages = new ArrayList<String>();
	    try {
			ArrayList<ProductImage>pImages = imageDAO.getImages(product.getId());
			for(ProductImage e : pImages){
				encodedImages.add(e.getImage());
			}
			session.setAttribute("encodedImages", encodedImages);
			session.setAttribute("colors", product.getColors());
			session.setAttribute("sizes", product.getSizes());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product.jsp");
			dispatcher.forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    
		response.getWriter().append("From ProductServlet Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
		

