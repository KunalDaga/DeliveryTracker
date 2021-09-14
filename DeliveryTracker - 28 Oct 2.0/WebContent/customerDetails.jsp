<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Hello!</title>
		<link rel="stylesheet" href="styles.css">
		<script>
			function removeCustomers() {
	    		location.href="${pageContext.request.contextPath}/removeCustomers.jsp";
			}
		
			function viewAll() {
	    		location.href="${pageContext.request.contextPath}/viewAll.jsp";
			}		
		</script>
	</head>
	
	<body>
		<form method=post action="${pageContext.request.contextPath}/addCustomer.jsp" class="bigger-foreground">	
			<h1 class="title">What would you like to do?</h1>
			
			<input type="Submit" value="Add customers" class="submit-button"> <br> <br>
			<input type="button" value="Remove customers" onclick="removeCustomers()" class="submit-button"> <br> <br>
			<input type="button" value="View all customers" onclick="viewAll()" class="submit-button"> <br> <br>
			
			<p class="end-instruction"> <a> Click a button to choose an option. </a> </p>
			
			<input type="hidden" id="mode" name="mode" value="customerDetails">
		</form>
	</body>
</html>