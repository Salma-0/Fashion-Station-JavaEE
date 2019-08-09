<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="styles/normalize.css">
<link rel="stylesheet" type="text/css" href="styles/catalog.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link
	href='http://fonts.googleapis.com/css?family=Lato:100,300,400,300italic'
	rel='stylesheet' type='text/css'>
	
	<script data-require="jquery@3.1.1" data-semver="3.1.1"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<title>Catalog</title>
</head>
<body>
	<div class="topnav">
        
        <img class="logo" src="images/logo.png" alt="Fashion Station Logo">

		
		<div class="search-container">
			<form action="CatalogServlet" method="post">
				<input type="text" placeholder="Search.." name="search">
				<button type="submit">
					<i class="fa fa-search"></i>
				</button>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
           function	passProduct(clickedID){
        	   var redirect="http://localhost:8080/FashionStation/ChooseProductServlet?linkID="+clickedID;
        	    window.open(redirect,"_self");
           }
	</script>

    
   <div class="container">
		<div class="scrollmenue">
		
        <c:forEach items="${boxes}" var="b">
       
			<div class="category">
				<a href="#" onClick="passProduct(this.id)" id="${b.id}">
					<img class="category-img" src="data:image/jpg;base64, <c:out value="${b.image }"/>">
                    <h5><c:out value="${b.name}"/></h5>
                    <h6><c:out value="$${b.price }"/></h6>
                </a>
			</div>
		
	     </c:forEach>
	     

    		</div>
	</div>
	
	
	
</body>
</html>