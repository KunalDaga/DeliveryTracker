<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Add customer here</title>
		<link rel="stylesheet" href="styles.css">	
	</head>
	
	<body>
		<form method=post action="${pageContext.request.contextPath}/addCustomer" class="bigger-foreground">
		<h1 class="title"> Please enter the personal details here: </h1>
		
			<input type="text" name="addFirstName" class="text-input" placeholder="First Name" autofocus/>
        	<input type="text" name="addLastName" class="text-input" placeholder="Last Name" />
       	 	<input type="text" name="addUsername" class="text-input" placeholder="Username" />
        	<input type="password" name="addPassword" class="text-input" placeholder="Password" />
			<input type="text" name="addEmailAddress" class="text-input" placeholder="Email Address" />
        	<input type="text" name="addPhoneNumber" class="text-input" placeholder="Phone Number" />
        	<input type="text" name="addZipcode" class="text-input" placeholder="Zipcode"/>

			<input type="submit" value="Add Customer" class="submit-button"/>
						
			<input type="hidden" id="mode" name="mode" value="addCustomer">
		</form>
	</body>
</html>