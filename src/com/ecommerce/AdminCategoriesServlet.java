package com.ecommerce;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.models.Category;


@WebServlet("/AdminCategoriesServlet")
public class AdminCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminCategoriesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Category>categories = new ArrayList<Category>();
		CategoryDAO cdao = new CategoryDAO();
		
		try {
			categories = cdao.getCategories();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("allCategories", categories);
		getServletContext().getRequestDispatcher("/categories.jsp").forward(request, response);
	}

	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
