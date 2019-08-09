package com.ecommerce;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.ecommerce.dao.ClientDAO;
import com.ecommerce.dao.OrderDAO;
import com.ecommerce.models.Client;
import com.ecommerce.models.Order;
import com.ecommerce.models.Order.Status;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;



@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Client client = (Client)session.getAttribute("client");
		Order order = (Order)session.getAttribute("order");
		String stripeID = null;
		String token = (String)request.getParameter("stripeToken");
		int chargeAmountCents = (int)session.getAttribute("totalInCents");
		
		try {
			PaymentService service = new PaymentService();
			if(client.getStripeID() == "" || client.getStripeID() == null){
				stripeID  = service.createCustomer(client);
				session.setAttribute("client", client);
				
			}else{
				stripeID = client.getStripeID();
			}
			
			 boolean charged = service.chargeCreditCard(order, token);
			/*
			Map<String, Object> chargeParams = new HashMap<String, Object>();
			chargeParams.put("amount", chargeAmountCents);
			chargeParams.put("currency", "usd");
			chargeParams.put("description", "Monthly Charges");		
			chargeParams.put("source", token);
			chargeParams.put("receipt_email", client.getEmail());

			Map<String, String> metadata = new HashMap<>();
			String order_id = String.valueOf(order.getOrder_id());
			metadata.put("order_id", order_id);
			chargeParams.put("metadata", metadata);
			
			Charge.create(chargeParams);
			*/
			
			//After payment successeed update status of order and add it to the session 
			 OrderDAO odao = new OrderDAO();
			if(!charged){
				order.setStatus(Status.AWAITING_PAYMENT);
				odao.addOrder(order);
				response.getWriter().write("<script type='text/javascript'>window.alert('payment failed! you can try to sign out, then sign in and order again');window.location='index.jsp';</script>");
			}else{
			order.setStatus(Status.AWAITING_FULLFILLMENT);
			 odao = new OrderDAO();
			odao.addOrder(order);
			@SuppressWarnings("unchecked")
			ArrayList<Order>clientOrders = (ArrayList<Order>)session.getAttribute("client_orders");
			clientOrders.add(order);
			session.setAttribute("client_orders", clientOrders);
			out.println("The order has been submitted successfully");
			//go to the profile page 
			RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
			rd.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

	}

}
