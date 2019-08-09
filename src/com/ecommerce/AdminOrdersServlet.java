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

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.models.Order;

/**
 * Servlet implementation class AdminOrdersServlet
 */
@WebServlet("/AdminOrdersServlet")
public class AdminOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		OrderDAO odao = new OrderDAO();
		ArrayList<Order>orders = new ArrayList<Order>();
		
			try {
				orders = odao.geAlltOrders();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		session.setAttribute("allOrders", orders);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin.jsp");
		dispatcher.forward(request, response);
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 1.get selected order id
		 * 2.retrieve order
		 * 3.update its status
		 * 4.forward to admin page
		 */
		
		String order_id = request.getParameter("orderID");
		String orderStatus = request.getParameter("orderStatusSelection");
		HttpSession session = request.getSession();
		
		int oid = 0;
		if((order_id != null) && (!order_id.isEmpty())){
			oid = Integer.parseInt(order_id);
		}
		
		OrderDAO odao = new OrderDAO();
		try {
			Order order = odao.getOrder(oid);
			if((orderStatus != null) && (!orderStatus.isEmpty())){
				order.setStatus(Order.Status.valueOf(orderStatus));
				odao.updateOrder(order);
			}
			
			session.setAttribute("allOrders", odao.geAlltOrders());
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("/Admin.jsp");
		dis.forward(request, response);
	}

}
