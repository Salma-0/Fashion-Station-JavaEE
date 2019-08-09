package com.ecommerce;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.dao.ClientDAO;
import com.ecommerce.dao.UserDAO;
import com.ecommerce.models.Client;
import com.ecommerce.models.ShippingInfo;
import com.ecommerce.dao.ShippingInfoDAO;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Client c = (Client)session.getAttribute("client");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String notes = request.getParameter("notes");
		ShippingInfoDAO siDao = new ShippingInfoDAO();
		ShippingInfo si = new ShippingInfo(c.getUsername(), country, city, street, address1, address2, notes);
		c.setShipmentInfo(si);
		try {
			siDao.addShippingInfo(si);
			session.setAttribute("client", c);
			String uri = (String)session.getAttribute("caller");
			RequestDispatcher rd = request.getRequestDispatcher(uri);
			rd.forward(request, response);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("uname");
		String password = request.getParameter("password");
		String pass2 = request.getParameter("password2");
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String phoneNumber = request.getParameter("pn");
		String email = request.getParameter("email");
		PrintWriter out = response.getWriter();
		
		if(password != pass2){
			out.println("password dre-enter your password");
		}
		
		UserDAO userdao = new UserDAO();
        try {
			if(userdao.isUsernameUnique(username)){
				ClientDAO clientdao = new ClientDAO();
				Client client = new Client(username, password, firstName, lastName, phoneNumber, email, null);
				clientdao.addClient(client);
				out.println("Registered Successfully");
				response.sendRedirect("login.jsp");
			}else{
				out.println("User name is already existed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
