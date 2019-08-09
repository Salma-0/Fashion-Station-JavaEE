package com.ecommerce;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.models.Client;
import com.ecommerce.models.Item;
import com.ecommerce.models.Order;
import com.ecommerce.models.ShoppingCart;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CheckoutServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		boolean logged = false;
		Client client = null;
		try{
		
	    logged = (boolean)session.getAttribute("logged");
		String strTotal = request.getParameter("total");
		double total = 0;
		if((strTotal != null) && (strTotal != "")){
			total = Double.parseDouble(strTotal);
		}
		
		ShoppingCart cart = ShoppingCart.createCart();
		if(cart.getItems().isEmpty()){
			out.println("You've chosen no items!");
			return;
		}
	   client = (Client)session.getAttribute("client");
		if(client != null){
		Order order = new Order(0, new Date(), client.getUsername(), cart.getItems(), total);
		session.setAttribute("order", order);
		out.println("The order has been submitted successfully!!");
		  if(client.getShipmentInfo() == null){
			  session.setAttribute("caller", "CheckoutServlet");
			  RequestDispatcher dispatcher = request.getRequestDispatcher("/shipping-info.jsp");
			  dispatcher.forward(request, response);
		   }else{
			   session.setAttribute("shippingInfo", client.getShipmentInfo());
			   RequestDispatcher dispatcher = request.getRequestDispatcher("/payment.jsp");
			   dispatcher.forward(request, response);
		   }

		}else{
			out.println("ERROR: no client is assigned!!!");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(!logged){
			session.setAttribute("caller", "cart.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		
}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
