<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, com.ecommerce.models.*, com.ecommerce.dao.*, org.apache.taglibs.standard.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/cart.css">
<script src="https://code.jquery.com/jquery-2.2.4.js" charset="utf-8"></script>
<script type="text/javascript">

function calculateTotal(){
    var quantities = document.getElementsByName("name");
    var p = document.getElementsByClassName("total-price");
    var total = 0;
    var j;
     for( j = 0; j<p.length; j++){
       var price = Number.parseFloat(p[j].innerHTML);
         total += price * quantities[j].value;
    }
     document.getElementById("totalPrice").value = "Total: $"+total;
  }

</script>
<title>Shopping Cart</title>
</head>
<body onload="calculateTotal()">

	<%ShoppingCart cart = (ShoppingCart)session.getAttribute("cart"); %>

	<div class="shopping-cart">
		<!-- Title -->
		<div class="title">Shopping Cart</div>

		<!-- Product #1 -->
		<c:forEach items="${items}" var="item" varStatus="loop">
			<div class="item" id="${loop.index}">
				<div class="buttons">
					<span class="delete-btn" onClick="removeItem(${loop.index})"></span>
					<span class="like-btn"></span>
				</div>

				<div class="image">
					<img src="data:image/jpg;base64,<c:out value="${item.image}"/>"
						width="120px" height="90px" alt="" />
				</div>

				<div class="description">
					<span>${item.name}</span> <span></span>
					 <span>${item.chosenColor}</span>
				</div>

				<div class="quantity">
					<button class="plus-btn" type="button" name="button">
						<img src="images/plus.svg" alt="" />
					</button>
					<input type="text" name="name" value="${item.quantity}">
					<button class="minus-btn" type="button" name="button">
						<img src="images/minus.svg" alt="" />
					</button>
				</div>

				<div class="total-price">${item.price}</div>
			</div>
		</c:forEach>

		<div class="total">
			<input type="text" name="totalPrice" id="totalPrice" disabled>
			<button class="checkout-btn" onclick="checkout()">Checkout</button>
		</div>

	</div>
	
	<script type="text/javascript">
	 $('.minus-btn').on('click', function(e) {
 		e.preventDefault();
 		var $this = $(this);
 		var $input = $this.closest('div').find('input');
 		var value = parseInt($input.val());

 		if (value > 1) {
 			value = value - 1;
 		} else {
 			value = 0;
 		}

     $input.val(value);
     calculateTotal();

 	});

 	$('.plus-btn').on('click', function(e) {
 		e.preventDefault();
 		var $this = $(this);
 		var $input = $this.closest('div').find('input');
 		var value = parseInt($input.val());

 		if (value < 100) {
   		value = value + 1;
 		} else {
 			value =100;
 		}

 		$input.val(value);
 		calculateTotal();
 	});

   $('.like-btn').on('click', function() {
     $(this).toggleClass('is-active');
   });
   
   function removeItem(index){
 	  $("#"+index).hide();
 	  var redirect="http://localhost:8080/FashionStation/RemoveFromCart?index="+index;
	      window.open(redirect,"_self");
   } 
   
   function checkout(){
		  var str = document.getElementById("totalPrice").value;
		  var total = str.substr(8);
		  var redirect = "http://localhost:8080/FashionStation/CheckoutServlet?total="+total;
		  window.open(redirect,"_self");
	  }
	</script>

</body>
</html>