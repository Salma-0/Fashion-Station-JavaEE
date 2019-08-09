<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="java.util.*, com.ecommerce.models.*, com.ecommerce.dao.*, org.apache.taglibs.standard.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles/normalize.css">
<link
	href='http://fonts.googleapis.com/css?family=Lato:100,300,400,300italic'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="styles/admin.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="scripts/admin.js"></script>
<script src="https://code.jquery.com/jquery-2.2.4.js" charset="utf-8"></script>

<title>Administration Page</title>
</head>
   <body>
   
   <% List<Order.Status>status = Arrays.asList(Order.Status.values()); 
      session.setAttribute("status", status);
   %>
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
      <h4>Orders:</h4>
      <form id="form1" action="FilterOrdersServlet" method="post">
      <label>Filter by</label>
      <select name="filterChoice" form="form1" id="selectFilter">
       <option value="all">All</option>
       <option value="date">Date</option>
       <option value="customer">Customer</option>
       <option value="orderStatus">Order Status</option>
      </select>
      
      <input type="text" name="filterArgument" id="argument"/>
      <button type="submit" form="form1">Submit</button>
      </form>
   </div>
   
   <div class="orders">
   
      <table class="orders-table">
        <tr>
        <th>Username</th>
        <th>Date</th>
        <th>Status</th>
        <th>Total</th>
        <th>Items</th>
        <th></th>
        </tr>
        <c:forEach items="${allOrders}" var="o" varStatus="loop">
          <tr>
          <form action="AdminOrdersServlet" method="post" id="${loop.index}">
          <input type="hidden" name="orderID" value="${o.order_id}">
           <td><a href="#" onclick="gotoCustomer('${o.username}')">${o.username}</a></td>
           <td>${o.formattedDate}</td>
           <td>
           <select name="orderStatusSelection" form="${loop.index}">
            <option value="default">${o.status}</option>
            <c:forEach items="${status}" var="status">
               <option value="${status}">${status}</option>
            </c:forEach>
           </select>
           </td>
           <td>$${o.totalPrice}</td>
           <td><a href="#" onclick="showItems('${o.order_id}')">Items</a></td>
           <td><button type="submit" form="${loop.index}">UPDATE</button></td>  
           </form>  
          </tr>
        </c:forEach>
      </table>
    </div>
   
      <script type="text/javascript">
      
      $(document).ready(function(){ 
    	  $('#selectFilter').on('change', function (e) {
    		    var valueSelected = this.value;
    		    if(valueSelected == "date"){
    		    	$('#argument').attr('placeholder', 'yyyy-MM-dd');
    		    }else{
    		    	$('#argument').attr('placeholder', '');

    		    }
    		});
    	  
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