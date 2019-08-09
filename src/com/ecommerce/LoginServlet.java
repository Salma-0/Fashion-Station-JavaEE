package com.ecommerce;

import java.io.IOException;
import java.io.PrintWriter;
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


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String adminUser;
	private String adminPass;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
    


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sign out
		Client c = null;
		HttpSession session = request.getSession();
		session.setAttribute("client", c);
		session.setAttribute("logged", false);
		String uri = (String)session.getAttribute("caller");
		RequestDispatcher rd = request.getRequestDispatcher(uri);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String username = request.getParameter("uname");
	    String password = request.getParameter("password");
	    	    
		HttpSession session = request.getSession();
	    LoginService loginService = new LoginService();

	   /* adminUser = getServletContext().getInitParameter("AdminUser");
		adminPass = getServletContext().getInitParameter("AdminPassword");
		System.out.println("AdminUser: "+adminUser+"\nAdminPass: "+adminPass);
		*/
		PrintWriter out = response.getWriter();
		
		boolean logged = loginService.login(username, password);
		session.setAttribute("logged", logged);
		session.setAttribute("admin", loginService.isAdmin());
		
		//if login succeeded
		if(logged){
			
			if(!loginService.isAdmin()){
				ClientDAO cdao = new ClientDAO();
				OrderDAO odao = new OrderDAO();
				try {
					Client client = cdao.getClientByUsername(username);
					ArrayList<Order>orders = odao.getOrdersByCustomer(username);
					
					session.setAttribute("client", client);
					session.setAttribute("shipment_info", client.getShipmentInfo());
					session.setAttribute("client_orders", orders);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			String uri = (String)session.getAttribute("caller");
			RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
			dispatcher.forward(request, response);
		}else{
			out.println(loginService.getMsg());
		}
	}

}
