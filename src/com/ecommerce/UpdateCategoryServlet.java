package com.ecommerce;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.models.Category;


@WebServlet("/UpdateCategoryServlet")
public class UpdateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UpdateCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	//retrieve the category and forward it to update-category page
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("categoryID");
		int categoryID = 0;
		if(cid != null && !cid.isEmpty())
			categoryID = Integer.parseInt(cid);
		
		CategoryDAO cdao = new CategoryDAO();
		try {
			Category c = cdao.getCategoryByID(categoryID);
			request.getSession().setAttribute("selectedCategory", c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/update-category.jsp").forward(request, response);
		
	}

	
	//update category name
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cname = request.getParameter("cname");
		
		CategoryDAO cdao = new CategoryDAO();
		Category category = (Category)request.getSession().getAttribute("selectedCategory");
		
		try {
			
			category.setName(cname);
			cdao.updateCategory(category);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/update-category.jsp").forward(request, response);
		
	}

}
