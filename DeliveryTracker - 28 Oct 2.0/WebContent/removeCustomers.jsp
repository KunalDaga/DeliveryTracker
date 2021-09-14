<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Hello!</title>
		<link rel="stylesheet" href="styles.css">
	</head>

	<body>
		<form method=post action="${pageContext.request.contextPath}/removeCustomer" class="foreground">
			<h1 class="title">Remove a customer</h1>
			<p class="remove-instruction"> Enter the username of the customer to remove </p> <br> 
			
			<input type="text" name="removeUsername" class="text-input" placeholder="Username" autofocus/>
			<input type="submit" value="Remove" class="submit-button" />
			
			<input type="hidden" id="mode" name="mode" value="removeCustomer">
		</form>
	</body>
</html>