<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList, com.ecommerce.models.*, com.ecommerce.dao.*, org.apache.taglibs.standard.*"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/payment.css">
<title>Order Datails</title>
</head>
<body>
 
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
   <c:forEach items="${selectedOrder.items}" var="i" varStatus="loop">
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
  </div>

</body>
</html>