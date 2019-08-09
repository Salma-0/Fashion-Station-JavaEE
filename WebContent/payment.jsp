<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.ecommerce.models.*, com.ecommerce.dao.*, org.apache.taglibs.standard.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/payment.css">

<link
	href='http://fonts.googleapis.com/css?family=Lato:100,300,400,300italic'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="styles/normalize.css">
<link rel="stylesheet" type="text/css" href="styles/main.css">
<title>Payment</title>
</head>
<body>

<nav>
	<div class="row">

		<img class="logo" src="images/logo.png" alt="Fashion Station Logo">

       
		<ul class="main-nav">
			<li><a href="index.jsp">Home</a></li>
			<li><a href="CatalogServlet">Catalog</a></li>
			<li id="profile-link"><a href="#">Profile</a></li>
			<li><a href="#">About us</a></li>
		</ul>
        
        <a href="CartServlet"><img src="images/shopping-cart.png" class="cart" alt="shopping cart"></a>
        
	</div>
	</nav> 
 
 <div class="shipping-info">
  <h3>Your recorded address: </h3><br/>
  <span>${shippingInfo.country} - ${shippingInfo.city} - ${shippingInfo.street}</span><br/>
  <span>${shippingInfo.address1}</span><br/>
  <span>${shippingInfo.address2}</span><br/>
  <span>Notes: ${shippingInfo.notes}</span><br/>
  <a href="profile.jsp">Update address</a>
 </div>
 
 <div class="order">
   <h4>Order Summery</h4>
   <table>
   <tr class="row">
    <th class="col">Image</th>
    <th class="col">Name</th>
    <th class="col">Size</th>
    <th class="col">Color</th>
    <th class="col">Quantity</th>
   </tr>
   <c:forEach items="${order.items}" var="i" varStatus="loop">
    <tr class="row">
     <td class="col">
       <img src="data:image/jpg;base64, <c:out value="${i.image}"/>" width="120px" height="90px"/>
     </td>
     <td class="col">${i.name}</td>
     <td class="col">${i.chosenSize}</td>
     <td class="col">${i.chosenColor}</td>
     <td class="col">x ${i.quantity}</td>
     </tr>
     </c:forEach>

  </table>
  
  
  <% Order o = (Order)session.getAttribute("order");
     int total = (int)Math.round(o.getTotalPrice() * 100);
     session.setAttribute("totalInCents", total);
  %>
  
  <form action="PaymentServlet" method="POST">
  <script
    src="https://checkout.stripe.com/checkout.js" class="stripe-button"
    data-key="pk_test_zgPYLYyTh5m9s5ijjxJgwSqf"
    data-amount="${totalInCents}"
    data-name="Fashion Station"
    data-description="Charge ${order.totalPrice}"
    data-image="images/logo2.png"
    data-locale="auto"
    data-zip-code="true" 
    >
  </script>
</form>


 
  
 </div>
 
 
  



</body>
</html>