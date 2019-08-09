package com.ecommerce;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.models.Order;


@WebServlet("/ShowOrderServlet")
public class ShowOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ShowOrderServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*1.get the order id
		 *2. retrieve it
		 *3. set it as a session attribute
		 *4. forward to order.jsp page
		 */
		HttpSession session = request.getSession();
		String orderID = request.getParameter("orderID");
		int oid = 0;
		if((orderID != null) && (!orderID.isEmpty())){
		  oid  = Integer.parseInt(orderID);
		 }
		OrderDAO odao = new OrderDAO();
		try {
			Order order = odao.getOrder(oid);
			System.out.println("Order:\nID: "+order.getOrder_id()+"\nCustomer: "+order.getUsername()+"\nTotal: "+order.getTotalPrice());
			session.setAttribute("selectedOrder", order);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/order.jsp");
		dispatcher.forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
