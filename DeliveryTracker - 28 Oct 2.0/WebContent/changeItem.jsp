<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form method = post action="${pageContext.request.contextPath}/changeItem">
 			<select name="itemChoices">
 				<option value="main"> Main  </option>
				<option value="side"> Side </option>
				<option value="drink"> Drink </option>
				<option value="price"> Price  </option>
				<option value="weight"> Weight </option>
				<option value="allergic"> AllergicIngredients </option>
				<br/>
			</select>
 			
 			Item to be changed: <input type="text" name="oldItem"/> <br/>
        	New item: <input type="text" name="newItem"/> <br/>
			<input type="submit" value="Submit">
			<input type="hidden" id="mode" name="mode" value="changingItem">
		</form>		
	</body>
</html>