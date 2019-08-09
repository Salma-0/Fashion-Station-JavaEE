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

import com.ecommerce.dao.ClientDAO;
import com.ecommerce.dao.OrderDAO;
import com.ecommerce.models.Client;
import com.ecommerce.models.Order;


@WebServlet("/ShowCustomerDetailsServlet")
public class ShowCustomerDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ShowCustomerDetailsServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*1. Set a specific client to the session
		 *2. Forward to profile page
		  */
		String username = request.getParameter("username");
		ClientDAO cdao = new ClientDAO();
		OrderDAO odao = new OrderDAO();
		HttpSession session = request.getSession();
		try {
			Client client = cdao.getClientByUsername(username);
			session.setAttribute("client", client);
			
			ArrayList<Order>orders = odao.getOrdersByCustomer(username);
			session.setAttribute("client_orders", orders);
			
			session.setAttribute("shipment_info", client.getShipmentInfo());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
		dispatcher.forward(request, response);
 	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
