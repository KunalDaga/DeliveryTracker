<head>
	<link rel="stylesheet" href="styles.css">
</head>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

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
	<font><strong> Today's orders </strong></font>
</h2>

<table align="center" cellpadding="5" cellspacing="5" border="1">
	<tr>

	</tr>
	<tr bgcolor="#A52A2A">
		<td><b>Customer ID</b></td>
		<td><b>First Name</b></td>
		<td><b>Last Name</b></td>
		<td><b>Username</b></td>
		<td><b>Quantity</b></td>
		<td><b>Email Address</b></td>
		<td><b>Phone Number</b></td>
		<td><b>Zipcode</b></td>
		<td><b>Main Dish</b></td>
	</tr>
	<%
	try {
		Connection connect = DriverManager.getConnection(host, username, password); 
		String query = "SELECT * FROM orders";
		Statement stm = connect.createStatement();     
		ResultSet rs = stm.executeQuery(query);
		
		while (rs.next()) {          
			int customerID = rs.getInt("customerID"); String realUsername = rs.getString("username");
			String firstName = rs.getString("firstName"); String lastName = rs.getString("lastName"); 
			int phoneNumber = rs.getInt("phoneNumber"); int quantity = rs.getInt("quantity");
			String customerEmail = rs.getString("emailAddress"); int zipcode = rs.getInt("zipcode");
			String mainDish = rs.getString("mainDish");
			
	%>
	<tr>
		<td><%=customerID%></td>
		<td><%=firstName%></td>
		<td><%=lastName%></td>
		<td><%=realUsername%></td>
		<td><%=quantity%></td>
		<td><%=customerEmail%></td>
		<td><%=phoneNumber%></td>
		<td><%=zipcode%></td>
		<td><%=mainDish%></td>
	</tr>

	<%
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	%>
</table>