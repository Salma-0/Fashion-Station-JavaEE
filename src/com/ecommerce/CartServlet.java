package com.ecommerce;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.models.Item;
import com.ecommerce.models.Product;
import com.ecommerce.models.ShoppingCart;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart cart = ShoppingCart.createCart();
		HttpSession session = request.getSession();
		session.setAttribute("cart", cart);
		session.setAttribute("items", cart.getItems());
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/cart.jsp");
		rd.forward(request, response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String q = request.getParameter("quantity");
		String color = request.getParameter("color");
		String size = request.getParameter("size");
		int quantity = Integer.parseInt(q);
		HttpSession session = request.getSession();
        Product p = (Product)session.getAttribute("product");
        
        if(p.getName().length() > 15){
        	String name = p.getName().substring(0, 14);
        	name += "....";
        	p.setName(name);
        }
		
		Item item = new Item(p.getId(), color, size,quantity, p.getPrice());
		
		ShoppingCart cart = ShoppingCart.createCart();
		cart.addItem(item);
		session.setAttribute("cart", cart);
		session.setAttribute("items", cart.getItems());
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductServlet");
		dispatcher.forward(request, response);
	}

}
