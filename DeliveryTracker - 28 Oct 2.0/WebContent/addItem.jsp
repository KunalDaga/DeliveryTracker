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
		<form method = post action="${pageContext.request.contextPath}/addItem" class="foreground">
			<h1 class="title">Enter the new items</h1>	
		
 			<input type="text" name="addMain" class="text-input" placeholder="Main" autofocus/> <br/>
        	<input type="text" name="addSide" class="text-input" placeholder="Side" /> <br/>
       	 	<input type="text" name="addDrink"class="text-input" placeholder="Drink" /> <br/>
        	<input type="text" name="addPrice" class="text-input" placeholder="Price" /> <br/>
			<input type="text" name="addWeight" class="text-input" placeholder="Weight" /> <br/>
        	<input type="text" name="addAllergicIngredients" class="text-input" placeholder="Allergic Ingredients" /> <br/>
			
			<input type="submit" value="Add" class="submit-button">
			<input type="hidden" id="mode" name="mode" value="addingMenu">
		</form>	
	</body>
</html>