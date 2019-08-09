package com.ecommerce;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.dao.ImageDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.models.ProductImage;


@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UpdateProductServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product editableProduct = null;
		ArrayList<ProductImage>images = null;
		String pid = request.getParameter("productID");
		System.out.println(pid);
		int productID = 0;
		if(pid != null && !pid.isEmpty()){
			productID = Integer.parseInt(pid);
		}
		
		
		ProductDAO pdao = new ProductDAO();
		ImageDAO idao = new ImageDAO();
		CategoryDAO cdao = new CategoryDAO();
		try {
			 editableProduct = pdao.getProductByID(productID);
			images = idao.getImages(productID);
			ArrayList<Category>categories = cdao.getCategories();
			String sizes = editableProduct.mergeStrings(editableProduct.getSizes());
			String colors = editableProduct.mergeStrings(editableProduct.getColors());
			
			HttpSession session = request.getSession();
			session.setAttribute("product", editableProduct);
			session.setAttribute("sizes", sizes);
			session.setAttribute("colors", colors);
			session.setAttribute("encodedImages", images);
			session.setAttribute("categories", categories);
			session.setAttribute("caller", "/UpdateProductServlet");
			
			RequestDispatcher disp = request.getRequestDispatcher("/update-product.jsp");
			disp.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*1.get product parameters
		 *2. update product in the database
		 *3.forward to update-product.jsp page
		 */
		String pid = request.getParameter("pid");
		String pname = request.getParameter("pname");
		String pprice = request.getParameter("pprice");
		String pquantity = request.getParameter("pquantity");
		String pcategory = request.getParameter("pcategory");
		String psizes = request.getParameter("sizes");
		String pcolors = request.getParameter("colors");
		String pdescription = request.getParameter("description");
		
		String[] sizes = {};
		String[] colors = {};
		
		double price = 0; 
		int quantity = 0;
		int productID = 0;
		
		if(pid != null && !pid.isEmpty())
			productID = Integer.parseInt(pid);
		if(pprice != null && !pprice.isEmpty())
			price = Double.parseDouble(pprice);
		if(pquantity != null && !pquantity.isEmpty())
			quantity = Integer.parseInt(pquantity);
		if(psizes != null && !psizes.isEmpty())
			sizes = psizes.split(",");
		if(pcolors != null && !pcolors.isEmpty())
			colors = pcolors.split(",");
		
		
		ProductDAO pdao = new ProductDAO();
		CategoryDAO cdao = new CategoryDAO();
		try {
			Category category = cdao.getCategoryByName(pcategory);
			Product p = new Product(productID ,pname, pdescription, quantity, category, price, colors, sizes);
			pdao.updateProduct(p);
			HttpSession session = request.getSession();
			session.setAttribute("product", p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/update-product.jsp");
		rd.forward(request, response);
			
	}

}
