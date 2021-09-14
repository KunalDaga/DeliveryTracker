<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Update information here</title>
		<link rel="stylesheet" href="styles.css">	
	</head>
	
	<body>
		<form method=post action="${pageContext.request.contextPath}/updateInformation" class="foreground">
			
			<h1 class="title">Please enter your personal details here: </h1>
	
 			<input type="text" name="updateFirstName" class="text-input" placeholder="First Name" autofocus/>
        	<input type="text" name="updateLastName" class="text-input" placeholder="Last Name"/>
       	 	<input type="text" name="updateUsername" class="text-input" placeholder="Username"/>
        	<input type="password" name="updatePassword" class="text-input" placeholder="Password"/>
			<input type="text" name="updateEmailAddress" class="text-input" placeholder="Email Address"/>
        	<input type="text" name="updatePhoneNumber" class="text-input" placeholder="Phone Number"/>
        	<input type="text" name="updateZipcode" class="text-input" placeholder="Zipcode"/>

	        <input type="submit" value="Update" class="submit-button"/>
			<input type="hidden" id="mode" name="mode" value="updatingInformation">
		</form>		
	</body>
</html>