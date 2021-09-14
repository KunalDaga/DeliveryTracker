<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Register here</title>
		<link rel="stylesheet" href="styles.css">
	</head>
	
	<body>
		<form method="post" action="${pageContext.request.contextPath}/register" class="foreground">
       	 	<h1 class="title">Please enter your personal details here: </h1>
			
			<input type="text" name="newFirstName" class="text-input" placeholder="First Name" autofocus/>
        	<input type="text" name="newLastName" class="text-input" placeholder="Last Name" />
       	 	<input type="text" name="newUsername" class="text-input" placeholder="Username" />
        	<input type="password" name="newPassword" class="text-input" placeholder="Password" />
			<input type="text" name="newEmailAddress" class="text-input" placeholder="Email Address" />
        	<input type="text" name="newPhoneNumber" class="text-input" placeholder="Phone Number" />
        	<input type="text" name="newZipcode" class="text-input" placeholder="Zipcode"/>

	        <input type="submit" value="Register" class="submit-button"/>
			<input type="hidden" id="mode" name="mode" value="registeringIn">	
		</form>
	</body>
</html>