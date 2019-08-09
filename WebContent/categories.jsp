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
<link rel="stylesheet" type="text/css" href="styles/products.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-2.2.4.js" charset="utf-8"></script>
<script src="scripts/categories.js"></script>
<title>Categories</title>
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
   
    <div class="products">
   
      <table>
        <tr>
        <th>Count</th>
        <th>Image</th>
        <th>Name</th>
        <th></th>
        </tr>
        <c:forEach items="${allCategories}" var="c" varStatus="loop">
          <tr>
             <td>${loop.index+1}</td>
             
             <td>
             <img width="120px" height="90px" src="data:image/jpg;base64, <c:out value="${c.image}"/>">
             </td>
             
             <td><a>${c.name}</a></td>
              
              <td>
               <a href="#" onclick="updateCategory('${c.id}')"><i class="fa fa-pencil-square-o"></i></a>
               <a href="#" onclick="deleteCategory('${c.id}')"><i class="fa fa-trash"></i></a>
           </td>
          
          </tr>
        </c:forEach>
      </table>
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