<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, com.ecommerce.models.*, com.ecommerce.dao.*, org.apache.taglibs.standard.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Product</title>
</head>
<body>

	<%
		CategoryDAO cdao = new CategoryDAO();
		ArrayList<Category> categories = cdao.getCategories();
		ArrayList<String>names = new ArrayList<String>();
		for(Category c: categories){
			names.add(c.getName());
		}
		request.setAttribute("categories", names);
	%>

	<div class="login-page">
		<div class="form">
			<form class="login-form" method="post" action="AddProductServlet"
				id="form1">
				<input type="text" name="product" placeholder="Product name .." form="form1" required><br /> 
				<input type="text" name="price" placeholder="Price .." form="form1" required><br />
				<input type="number" name="quantity" min="1"
					placeholder="Quantity.." form="form1" required><br /> <select
					name="category" form="form1" required>
					<option value="${selected}" selected>Select Category</option>
					<c:forEach items="${categories}" var="category">
						<c:if test="${category != selected}">
							<option value="${category}">${category}</option>
						</c:if>
					</c:forEach>
				</select><br/>
				<textarea name="description" rows="10" cols="40"
					placeholder="Product Description" form="form1" required></textarea>
				<input type="text" name="colors" placeholder="Enter the colors seprated by comma: color1,color2,color3" form="form1" required><br/>
				<input type="text" name="sizes" placeholder="Enter the sizes seprated by comma: S,M,L,..." form="form1"><br/>
				<button type="submit" form="form1">Add new product</button>
				
			</form>
		</div>
	</div>

</body>
</html>