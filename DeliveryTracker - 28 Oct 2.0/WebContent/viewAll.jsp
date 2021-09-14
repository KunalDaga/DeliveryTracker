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
	<font><strong> Customer details </strong></font>
</h2>

<table align="center" cellpadding="5" cellspacing="5" border="1">
	<tr>

	</tr>
	<tr bgcolor="#A52A2A">
		<td><b>First Name</b></td>
		<td><b>Last Name</b></td>
		<td><b>Username</b></td>
		<td><b>Password</b></td>
		<td><b>Email Address</b></td>
		<td><b>Phone Number</b></td>
		<td><b>Zipcode</b></td>
	</tr>
	<%
	try {
		Connection connect = DriverManager.getConnection(host, username, password); 
		String query = "SELECT * FROM customerInfo";
		Statement stm = connect.createStatement();     
		ResultSet rs = stm.executeQuery(query);
		
		while (rs.next()) {          
			String realUsername = rs.getString("username");
			String realPassword = rs.getString("password"); String firstName = rs.getString("firstName"); 
			String lastName = rs.getString("lastName"); int phoneNumber = rs.getInt("phoneNumber");
			String customerEmail = rs.getString("emailAddress"); int zipcode = rs.getInt("zipcode");
	%>
	<tr bgcolor="#DEB887">
		<td><%=firstName%></td>
		<td><%=lastName%></td>
		<td><%=realUsername%></td>
		<td><%=realPassword%></td>
		<td><%=customerEmail%></td>
		<td><%=phoneNumber%></td>
		<td><%=zipcode%></td>
	</tr>
	<%
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</table>