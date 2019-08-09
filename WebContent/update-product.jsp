<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/normalize.css">
<link
	href='http://fonts.googleapis.com/css?family=Lato:100,300,400,300italic'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="styles/admin.css">
<link rel="stylesheet" type="text/css" href="styles/update-product.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-2.2.4.js" charset="utf-8"></script>
<script src="scripts/products.js"></script>
<title>Update Product</title>
</head>
<body>

   <div class="navigation">
     <div class="logo"><img src="images/logo.png"></div>
     
     <h3>ADMINISTRATION</h3>
     <div class="menu-icon"><a href="#"><i class="fa fa-bars"></i></a></div>
   </div>
   
   <div id="menu">
     <a href="index.jsp">Home</a>
     <a href="AdminOrdersServlet">Check Orders</a>
     <a href="add-product.jsp">New Product</a>
     <a href="add-category.jsp">New Category</a>
     <a href="AdminProductsServlet">Products</a>
     <a href="AdminCategoriesServlet">Categories</a>
   </div>
   
   
   <div class="pictures">
     <c:forEach items="${encodedImages}" var="i">
     <div class="container">
       <img class="image" src="data:image/jpg;base64, <c:out value="${i.image}"/>">
       <div class="overlay">
       <a class="icon" href="#" onclick="deleteImage('${i.id}', '${i.product_id}')"><i class="fa fa-remove"></i></a>
       </div>
       </div>
     </c:forEach>
   </div>
   
   
   <div class="upload-form">
   <form  enctype="multipart/form-data" method="post" action="AddImageServlet">
   <input type="file" name="img1">
   <button type="submit">Upload</button>
   </form>
   </div>
   
   <div class="product-box">
   <form action="UpdateProductServlet" method="post">
   <input type="hidden" value="${product.id}" name="pid">
   <table class="form-table">
     <tr>
     <td><label>Product name:</label></td>
     <td><input type="text" name="pname" value="${product.name}"></td>
     </tr>
     <tr>
     <td><label>Product price:</label></td>
     <td><input type="text" name="pprice" value="${product.price}"></td>
     </tr>
     <tr>
     <td><label>Product quantity:</label></td>
     <td><input type="number" name="pquantity" value="${product.quantity}"></td>
     </tr>
      <tr>
      <td><label>Product category:</label></td>
      <td> <select name="pcategory">
       <option value="${product.category.name}">${product.category.name}</option>
       <c:forEach items="${categories}" var="c">
         <option value="${c.name}">${c.name}</option>
       </c:forEach>
     </select></td>
     </tr>
     
      <tr>
      <td><label>Product sizes:</label></td>
      <td><input type="text" name="sizes" value="${sizes}"></td>
      </tr>
      <tr><td><label>Product colors:</label></td>
      <td><input type="text" name="colors" value="${colors}"></td>
      </tr>
      <tr>
      <td><label>Product description:</label></td>
      <td><textarea name="description">${product.description}</textarea></td>
      </tr>
      <tr><td><button type="submit">Update</button></td></tr>
      </table> 
     </form>
   </div>
   
   <script type="text/javascript">
      
      $(document).ready(function(){ 
    
    	  $('.menu-icon').on('click', function(e){
    		  if($('#menu').css('display') == 'none'){
    			  $('#menu').css('display', 'flex');
    		  }else{
    			  $('#menu').css('display', 'none');
    		  }
    		  
    	  });
    	  
    	});
      </script>
   
</body>
</html>