<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="java.util.*, com.ecommerce.models.*, com.ecommerce.dao.*, org.apache.taglibs.standard.*"%>
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
<script src="scripts/products.js"></script>
<title>Prodcuts Summery</title>
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
   
   <div class="orders-filter-box">
      <h4>Products:</h4>
      <form id="form1" action="FilterProductServlet" method="post">
      <label>Filter by</label>
      <select name="filterChoice" form="form1" id="selectFilter">
       <option value="all">All</option>
       <option value="category">Category</option>
      </select>
      
      <input type="text" name="filterArgument" id="argument"/>
      <button type="submit" form="form1">Submit</button>
      </form>
   </div>
   
   <div class="products">
     <h4>Products Summery</h4>
     <table>
        <tr>
         <th>Count</th>
         <th>Image</th>
         <th>Product Name</th>
         <th>Price</th>
         <th></th>
        </tr>
        <c:forEach items="${allProducts}" var="p" varStatus="loop">
          <tr>
           <td>${loop.index+1}</td>
           <td><img width="120px" height="90px" src="data:image/jpg;base64, <c:out value="${p.image}"/>"></td>
           <td><a href="#" onclick="gotoProductPage('${p.id}')">${p.name}</a></td>
           <td>${p.price}</td>
           <td>
           <a href="#" onclick="updateProduct('${p.id}')"><i class="fa fa-pencil-square-o"></i></a>
           <a href="#" onclick="deleteProduct('${p.id}')"><i class="fa fa-trash"></i></a>
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