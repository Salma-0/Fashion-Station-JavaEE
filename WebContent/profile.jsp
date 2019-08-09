<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href='http://fonts.googleapis.com/css?family=Lato:100,300,400,300italic'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="styles/profile.css">
<link rel="stylesheet" typ="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/normalize.css">
<title>${client.username}</title>
</head>
<body>

 <script src="scripts/profile.js"> </script>
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
	<h4>Personal Information</h4>
	
	<div class="personal-information">
    <form id="form1"action="UpdateClientServlet" method="get" onsubmit="update()">
	<table>
	<tr>
	   <td><label>Username: </label></td>
	   <td><input type="text" name="username" value="${client.username}" readonly></td>
	</tr>
	<tr>
	   <td><label>First name: </label></td>
	   <td><input id="fname" type="text" name="fname" value="${client.firstName}" readonly></td>
	</tr>
	<tr>
	   <td><label>Last name: </label></td>
	   <td><input id="lname" type="text" name="lname" value="${client.lastName}" readonly></td>
	</tr>
	<tr>
	   <td><label>Phone number: </label></td>
	   <td><input id="pnumber" type="text" name="pnumber" value="${client.phoneNumber}" readonly></td>
	</tr>
	<tr>
	   <td><label>Email: </label></td>
	   <td><input id="email" type="text" name="email" value="${client.email}" readonly></td>
	</tr>
    </table>
    <a href="#" onclick="editInformation('${client.username}')">edit<img src="images/edit-icon.png"></a>
    <button form="form1" type="submit" id="update-btn">Update</button>
    </form>
	</div>
	
	<h4>Shipping Information</h4>
	<div class="personal-information">
    <form id="form2"action="UpdateClientServlet" method="post" onsubmit="updateShip()">
	<table>
	<tr>
	   <td><label>Country: </label></td>
	   <td><input type="text" name="country" value="${shipment_info.country}" readonly></td>
	</tr>
	<tr>
	   <td><label>City: </label></td>
	   <td><input id="city" type="text" name="city" value="${shipment_info.city}" readonly></td>
	</tr>
	<tr>
	   <td><label>Street: </label></td>
	   <td><input id="street" type="text" name="street" value="${shipment_info.street}" readonly></td>
	</tr>
	<tr>
	   <td><label>Address line 1: </label></td>
	   <td><input id="address1" type="text" name="address1" value="${shipment_info.address1}" readonly></td>
	</tr>
	<tr>
	   <td><label>Address line 2: </label></td>
	   <td><input id="address2" type="text" name="address2" value="${shipment_info.address2}" readonly></td>
	</tr>
	<tr>
	   <td><label>Notes: </label></td>
	   <td><textarea id="notes" type="text" name="notes" value="${shipment_info.notes}" readonly></textarea></td>
	</tr>
	
    </table>
    <a href="#" onclick="editShipment()">edit<img src="images/edit-icon.png"></a>
    <button form="form2" type="submit" id="update-ship">Update</button>
    </form>
	</div>
	
	<h4>My Orders</h4>
	<div class="orders">
	  <c:forEach items="${client_orders}" var="o" varStatus="loop">
	 <div class="order">
   <h5>Order Summery on ${o.date}</h5>
   <table>
   <tr class="row">
    <th class="col">Image</th>
    <th class="col">Name</th>
    <th class="col">Size</th>
    <th class="col">Color</th>
    <th class="col">Quantity</th>
   </tr>
   <c:forEach items="${o.items}" var="i" varStatus="loop">
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
  <h5>Total: $${o.totalPrice}</h5>
  <h5>Order Status: ${o.status}</h5>
  </div>
	  </c:forEach>
	</div>

	
</body>
</html>