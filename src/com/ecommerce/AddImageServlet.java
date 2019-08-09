package com.ecommerce;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.ecommerce.dao.ImageDAO;
import com.ecommerce.models.Product;
import com.ecommerce.models.ProductImage;

import org.apache.commons.io.IOUtils;




/**
 * Servlet implementation class AddImageServlet
 */
@WebServlet("/AddImageServlet")
@MultipartConfig
public class AddImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	//delete image
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*1.get product_id and image id parameters
		 *2.delete image
		 *3.forward to update servlet*/
		String productID = request.getParameter("productID");
		String imageID = request.getParameter("imageID");
		
		int pid = 0;
		int image_id = 0;
		if(productID != null && !productID.isEmpty())
			pid = Integer.parseInt(productID);
		if(imageID != null && !imageID.isEmpty())
			image_id = Integer.parseInt(imageID);
		
		ImageDAO idao = new ImageDAO();
		try {
			idao.deleteImage(image_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher disp = request.getRequestDispatcher("/UpdateProductServlet");
		disp.forward(request, response);
		
	}

	
	//add new Image
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<BufferedImage>images = new ArrayList<BufferedImage>();
		ImageDAO imagedao = new ImageDAO();
		HttpSession session = request.getSession();
		Product product = (Product) session.getAttribute("product");
		int product_id = product.getId();
		Part img1 = request.getPart("img1");
		
		try{
		
			InputStream stream = img1.getInputStream();
			byte[] bytes = IOUtils.toByteArray(stream);
			String encoded = Base64.getEncoder().encodeToString(bytes);
			if(encoded != null)
			{
				ProductImage pImage = new ProductImage(0, encoded, product_id);
				imagedao.addImage(pImage);
				ArrayList<ProductImage>imgs = imagedao.getImages(product_id);
				session.setAttribute("encodedImages", imgs);
			}

			String uri = (String)session.getAttribute("caller");
			if(uri == "/UpdateProductServlet")
				response.sendRedirect(request.getContextPath()+"/update-product.jsp");
			else
				response.sendRedirect(request.getContextPath()+"/add-image.jsp");
			
	}catch(Exception e){
		e.printStackTrace();
	}
 }

}
