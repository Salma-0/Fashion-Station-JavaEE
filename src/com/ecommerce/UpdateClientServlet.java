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

import com.ecommerce.dao.ClientDAO;
import com.ecommerce.dao.ShippingInfoDAO;
import com.ecommerce.models.Client;
import com.ecommerce.models.ShippingInfo;

/**
 * Servlet implementation class UpdateClientServlet
 */
@WebServlet("/UpdateClientServlet")
public class UpdateClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String pnumber = request.getParameter("pnumber");
		String email = request.getParameter("email");
		
		HttpSession session = request.getSession();
		Client client = (Client)session.getAttribute("client");
		client.setFirstName(fname);
		client.setLastName(lname);
		client.setPhoneNumber(pnumber);
		client.setEmail(email);
        ClientDAO dao =  new ClientDAO();
        try {
			dao.updateClient(client);
			RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String country = request.getParameter("country");
	    String city = request.getParameter("city");
	    String street = request.getParameter("street");
	    String address1 = request.getParameter("address1");
	    String address2 = request.getParameter("address2");
	    String notes = request.getParameter("notes");
	    
	    HttpSession session = request.getSession();
	    ShippingInfo si = (ShippingInfo)session.getAttribute("shipment_info");
	    si.setCountry(country);
        si.setCity(city);
        si.setStreet(street);
        si.setAddress1(address1);
        si.setAddress2(address2);
        si.setNotes(notes);
        
        ShippingInfoDAO dao = new ShippingInfoDAO();
        RequestDispatcher rd= request.getRequestDispatcher("/profile.jsp");
        rd.forward(request, response);
        try {
			dao.updateShippingInfo(si);
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}
