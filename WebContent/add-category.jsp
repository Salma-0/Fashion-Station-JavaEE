<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href='http://fonts.googleapis.com/css?family=Lato:100,300,400,300italic'
	rel='stylesheet' type='text/css'>
	
<link rel="stylesheet" type="text/css" href="styles/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Category</title>
</head>
<body>

	<div class="login-page">
		<div class="form">
			<form class="login-form" enctype="multipart/form-data" method="post" action="AddCategoryServlet">
			    <input type="text" name="category" placeholder="Category name.." required><br/>
				<input type="file" name="file" size="50" required> <br/>
				<button type="submit">Add new category</button>
			</form>
		</div>
	</div>
</body>
</html>