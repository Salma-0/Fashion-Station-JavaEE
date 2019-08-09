<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page
	import="java.util.ArrayList, com.ecommerce.models.*, com.ecommerce.dao.*, org.apache.taglibs.standard.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="styles/main.css">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href='http://fonts.googleapis.com/css?family=Lato:100,300,400,300italic'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="styles/normalize.css">
<link href="https://fonts.googleapis.com/css?family=Cookie" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto:400,500" rel="stylesheet">
<script src="scripts/script.js"></script>
<title>Home</title>
</head>

<body onload="setSign()">

   <% CategoryDAO dao = new CategoryDAO();
      ArrayList<Category>categories =dao.getCategories();
      session.setAttribute("categories", categories);
     %>

   <%session.setAttribute("caller", "index.jsp"); %>
   
    
    <input type="hidden" id="logged" value="${logged}">
    <input type="hidden" id="admin" value="${admin}">
	<nav>
	<div class="row">
		<img class="logo"src="images/logo.png" alt="Fashion Station Logo" height="150px">
       
		<ul class="main-nav" id="myTopnav">
			<li><a href="index.jsp" class="topnav">Home</a></li>
			<li><a href="#" onclick="submitCategory(this.id)" id="0" class="topnav">Catalog</a></li>
			<li><a href="login.jsp" id="sign" class="topnav">Sign in</a></li>
			<li id="profile-link"><a href="profile.jsp" class="topnav">Profile</a></li>
			<li><a href="#" class="topnav">About us</a></li>
			<li><a href="#" class="menu-icon" onclick="displayMenuBar()"><i class="fa fa-bars"></i></a></li>
			
		</ul>
        <a href="CartServlet"><img src="images/shopping-cart.png" class="cart" alt="shopping cart"></a>
        
	</div>
	</nav>

	<header>
	<h1>Welcome to fashion Station</h1>
	</header>

	<h2>Shop now</h2>
	<div class="container">
		<div class="scorllmenu">
			<c:forEach items="${categories}" var="cat">
			  <div class="category" >
			    <a href="#" id="${cat.id}" onclick="submitCategory(this.id)">
			      <h3><c:out value="${cat.name}"/></h3>
			      <img class="category-img" src="data:image/jpg;base64, <c:out value='${cat.image}'/>">
			    </a>
			  </div>
			</c:forEach>
		</div> 
	</div>
	
	<div class="about-us">
	   <h4>About Fashion Station</h4>
	   <img src="images/blue-bag.jpg">
	   <h5>Our Story</h5>
	   <p>Fashion Station is a trendsetting Ecommerce Hand Bags Store, offering products from all the international brands with best quality. We provide worldwide shipment service.</p>
	   <a href="#" onclick="submitCategory(this.id)" id="0">Shop now</a>
	</div>
	
	<div class="contact">
	   <div id="address">
	   Addresses: <br/><br/>
	   899 Jie Fang Bei Lu<br/>
city of Guangzhou, 510000<br/>
China
	   </div>
	   <div id="numbers">
	      Contact:<br/><br/>
         +86 159 9994 8224<br/>
         +86 20 3618 2283
	   </div>
	   <div id="accounts">
	    Follow:<br/><br/>
	    <a href="https://www.instagram.com/waseet_china_2/"><i class="fa fa-instagram"></i></a>
	   </div>
	</div>



</body>
</html>