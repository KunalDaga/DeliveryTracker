<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Hello there!</title>
		<link rel="stylesheet" href="styles.css">
		
		<script>
		function viewMenu() {
		    location.href="${pageContext.request.contextPath}/viewMenu.jsp";
		}
		</script>
		
	</head>
	<body>
		<form method=post action="${pageContext.request.contextPath}/updateInformation.jsp" class="bigger-foreground">
			<h1 class="title">What would you like to do?</h1>
			
			<input type="Submit" value="Update your information" class="submit-button"> <br> <br>
			<input type="button" value="View the menu" onclick="viewMenu()" class="submit-button">
			<p class="end-instruction"> <a> Click a button to choose an option. </a> </p>
			
			<input type="hidden" id="mode" name="mode" value="customerActions">
		</form>
	</body>
</html>