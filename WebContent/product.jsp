<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script data-require="jquery@3.1.1" data-semver="3.1.1"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="scripts/script.js"></script>

<link rel="stylesheet" type="text/css" href="styles/normalize.css">
<link rel="stylesheet" type="text/css" href="styles/product.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href='http://fonts.googleapis.com/css?family=Lato:100,300,400,300italic'
	rel='stylesheet' type='text/css'>

<title><%=session.getAttribute("title") %></title>
</head>
<body>
 
 
	
	<div class="topnav">
		<a href="CartServlet"><img class="cart-icon" src="images/shopping-cart.png"></a>
		<img class="logo" src="images/logo.png" alt="Fashion Station Logo">
	</div>

	<div class="product-box">
		<div class="img-view">
			<c:forEach items="${encodedImages}" var="e">
				<img src="data:image/jpg;base64, <c:out value='${e}'/>"
					class="product-img" />
			</c:forEach>
		</div>
        <div class="choices">
		<h3><%=session.getAttribute("name")%></h3>
		
		<form id="form1" action="CartServlet" method="post">
		 <div class="select-style">
		<select name="color" form="form1" required>
			<option value="${selected}" selected>Select color</option>
			<c:forEach items="${colors}" var="color">
				<c:if test="${color != selected}">
					<option value="${color}">${color}</option>
				</c:if>
			</c:forEach>
		</select></div>
		
		
		<div class="select-style">
		<select name="size" form="form1" required>
			<option value="${selected}" selected>Select size</option>
			<c:forEach items="${sizes}" var="size">
				<c:if test="${size != selected}">
					<option value="${size}">${size}</option>
				</c:if>
			</c:forEach>
		</select></div>
		


		<div class="quantity buttons_added">
			<label>Quantity</label> <input type="button" value="-" class="minus">
            <input
				type="number" step="1" min="1" max="" name="quantity" value="1"
				title="Qty" class="input-text qty text" size="4" pattern=""
				inputmode=""><input type="button" value="+" class="plus">
		</div>
		<h4>Price: $<%=session.getAttribute("price") %></h4>
       
		
		<button class="btn" type="submit" form= "form1">Add to cart</button>
        </form>
	</div>
	</div>
	
	<div class="description-box">
			<h3>Description</h3>
			<p>
				<%=session.getAttribute("desc")%>
			</p>
		</div>
</body>
</html>