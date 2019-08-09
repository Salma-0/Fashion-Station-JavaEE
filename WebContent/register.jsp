<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/login.css">
<link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,300italic' rel='stylesheet' type='text/css'>

<title>Sign up</title>
</head>
<body>
  <div class="login-page">
        <div class="form">
    <form class="login-form"  action="RegisterServlet" method="post">
    <input type="text" placeholder="Username" name="uname" required>
    <input type="password" placeholder="Password" name="password" required>
    <input type="password" placeholder="Password again" name="password2" required>
    <input type="text" placeholder="First name" name="fname" required>
    <input type="text" placeholder="Last name" name="lname" required>
    <input type="tel" placeholder="Phone number" name="pn" required>
    <input type="email" placeholder="Email" name="email" required>
    <button type="submit">Craete Account</button>
    </form>
        </div>
    </div>
</body>
</html>