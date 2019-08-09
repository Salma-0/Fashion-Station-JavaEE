<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/login.css">
<link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,300italic' rel='stylesheet' type='text/css'>
<title>Sign in</title>
</head>
<body>
	<div class="login-page">
		<div class="form">
			<form class="login-form" action="LoginServlet" method="post">
				<input type="text" placeholder="username" name="uname" /> 
				<input type="password" placeholder="password"  name="password"/>
				<button type="submit">login</button>
				<p class="message">
					Not registered? <a href="register.jsp">Create an account</a>
				</p>
			</form>
		</div>
	</div>
</body>
</html>