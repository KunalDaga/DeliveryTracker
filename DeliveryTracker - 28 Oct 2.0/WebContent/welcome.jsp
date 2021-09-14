<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Welcome!</title>
		<link rel="stylesheet" href="styles.css">
		
		<script>
		function register() {
		    location.href="${pageContext.request.contextPath}/register.jsp";
		}
		</script>
	</head>
	
	<body>
		<form method=post action="${pageContext.request.contextPath}/Welcome">
			<h1 class="login-title">Simple Login</h1>
			
			<input type="hidden" id="mode" name="mode" value="none">
			<input type="Submit" value="Login here!"> 
			<input type="button" value="Register Here!" onclick="register()">
		</form>
	</body>		
</html>
