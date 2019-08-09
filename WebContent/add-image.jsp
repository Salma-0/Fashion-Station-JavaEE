<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/login.css">
<title>Upload Images</title>
</head>
<body>
<div class="login-page">
		<div class="form">
			<form class="login-form" enctype="multipart/form-data" method="post" action="AddImageServlet">
			    <input type="file" name="img1" size="50" required><br/>
				<button type="submit">Upload</button>	
				<p class="message">
				 No more photos? <a href="ProductServlet">Show product page</a>
				</p>			
			</form>
		</div>
	</div>
	
</body>
</html>