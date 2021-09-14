<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.util.Base64"%>

<%
	String host = "jdbc:mysql://mysqldatabase.ciloz6zv9mq8.ap-southeast-1.rds.amazonaws.com:3306/masterdatabase";	String dbName = "jsptutorials";
	String username = "kunaldaga28";
	String password = "kunalandy2001";

	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
%>

<h2 align="center">
	<font><strong> Here is the menu for today </strong></font>
</h2>

	<form method=post action="${pageContext.request.contextPath}/viewMenu">


<table id="menuTable" align="center" cellpadding="5" cellspacing="5"
	border="1">
	<tr>

	</tr>
	<tr bgcolor="#A52A2A">
		<td><b>Main Dish</b></td>
		<td><b>Side Dish</b></td>
		<td><b>Drink</b></td>
		<td><b>Price</b></td>
		<td><b>Weight</b></td>
		<td><b>Picture</b></td>
		<td><b>Allergic Ingredients</b></td>
		<td><b>Order quantity</b></td>
	</tr>
	
	<input type="text" name="test">
	<%
		try {
			Connection connect = DriverManager.getConnection(host, username, password);
			Statement stm = connect.createStatement();
			String query = "SELECT * FROM menu";

			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				Blob item_image = rs.getBlob("picture");
				int bloblength = (int) item_image.length();
				byte[] bytes = item_image.getBytes(1, bloblength);
				String encodedBase64 = new String(Base64.getEncoder().encode(bytes));
				encodedBase64 = "data:image/jpeg;base64," + encodedBase64;
				
				
	%>
	<tr bgcolor="#DEB887">
		<td><%=rs.getString("mainDish")%></td>
		<td><%=rs.getString("sideDish")%></td>
		<td><%=rs.getString("drink")%></td>
		<td><%=rs.getInt("price")%></td>
		<td><%=rs.getInt("weight")%></td>
		<td><img src=encodebase64><</td>
		<td><%=rs.getString("allergicIngredients")%></td>
		<td>
				<input type="text" name="quantity">
				<input type="hidden" name="dish" value=<%=rs.getString("mainDish")%> >
			
	</tr>

	<%
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</table>

<br>
<br>
	    <input type="submit" name="orderSubmit" value = "Submit"/>
		<input type="hidden" id="mode" name="mode" value="viewingMenu">
</form>