package com.ecommerce;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.models.Order;

@WebServlet("/FilterOrdersServlet")
public class FilterOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
        public FilterOrdersServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filterChoice = request.getParameter("filterChoice");
		String argument = request.getParameter("filterArgument");
		String formattedDate = "";
		if(filterChoice == "date" && argument.isEmpty()){
		  Date today = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  formattedDate =  formatter.format(today); 
		}
		HttpSession session = request.getSession();
		OrderDAO dao = new OrderDAO();
		ArrayList<Order>orders = new ArrayList<Order>();
		try{
		  if((filterChoice != null) && (!filterChoice.isEmpty())){
			switch(filterChoice){
			case "all": 
				orders = dao.geAlltOrders();
				break;
			case "date": 
				if((argument.isEmpty()) || (argument == null))
					argument = formattedDate;
				orders = dao.getOrdersByDate(argument);
				break;
			case "customer":
				if((argument.isEmpty()) || (argument == null))
					orders = dao.geAlltOrders();
				else
					orders = dao.getOrdersByCustomer(argument);
				break;
			case "orderStatus": 
				if((argument.isEmpty()) || (argument == null))
					orders = dao.geAlltOrders();
				else 
	         		orders = dao.getOrdersByStatus(argument);
				break;
			default: 
				orders = dao.geAlltOrders();
					
			}
		 }
	  }catch(SQLException e){
		e.printStackTrace();
	   }
		
		session.setAttribute("allOrders", orders);
		RequestDispatcher disp = request.getRequestDispatcher("/Admin.jsp");
		disp.forward(request, response);
	}

}
