<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Welcome!</title>
		<link rel="stylesheet" href="styles.css">
	</head>
	
	<body>
		<form method=post action="${pageContext.request.contextPath}/login" class="foreground">
		<h1 class="title">Login here!</h1>
		
       	 	<input type="text" name="username" class="text-input" placeholder="Username" autofocus/>
        	<input type="password" name="password" class="text-input" placeholder="Password" autofocus/>
	        <input type="submit" value="Login" class="submit-button" />
			<p class="end-instruction"> <a href="${pageContext.request.contextPath}/register.jsp"> New customer? Register here.</a></p>
			
			<input type="hidden" id="mode" name="mode" value="loggingIn">
		</form>
	</body>
</html>