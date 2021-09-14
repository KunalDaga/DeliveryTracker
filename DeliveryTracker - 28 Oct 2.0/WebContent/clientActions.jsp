<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Hello there!</title>
		<link rel="stylesheet" href="styles.css">
		
		<script> 
			function viewOrders() {
		    	location.href="${pageContext.request.contextPath}/viewOrders.jsp";
			}
			
			function updateMenu() {
		    	location.href="${pageContext.request.contextPath}/updateMenu.jsp";
			}
			
			function routeMap() {
		    	location.href="${pageContext.request.contextPath}/routeMap.jsp";
			}
		</script>
	</head>
	
	
	<body>
		<form method = post action="${pageContext.request.contextPath}/customerDetails.jsp" class="bigger-foreground">
			<h1 class="title">What would you like to do?</h1>
			
			<input type="Submit" value="View customer details" class="submit-button"> <br> <br>
			<input type="button" value="View today's orders" onclick="viewOrders()" class="submit-button"> <br> <br>
			<input type="button" value="Update the menu" onclick="updateMenu()" class="submit-button"> <br> <br>
			<input type="button" value="View the route map" onclick="routeMap()" class="submit-button"> <br> <br>
			
			<p class="end-instruction"> <a> Click a button to choose an option. </a> </p>
					
			<input type="hidden" id="mode" name="mode" value="clientActions">
		</form>
	</body>
</html>