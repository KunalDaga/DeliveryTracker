package testpackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewMenuServlet extends Controller {

	DatabaseHelper D = new DatabaseHelper();
	EmailSender E = new EmailSender();
	
	
	@Override
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		double price = 0;
		double perDishPrice = 0;
		double totalPrice = 0;
		String quantity = "";
		String message = "Your order is being processed and delivered. \nIt is as follows:";
		

		System.out.println("[ViewMenu][doProcess]");
		
		
		
		//Enumeration <String> parameterNames = request.getParameterNames();
		//System.out.println(parameterNames);
       
		String[] quantities = request.getParameterValues("quantity");
		String[] dishes = request.getParameterValues("dish");
		
		System.out.println(request.getParameter("test"));
    	
		for(int i = 0; i < dishes.length; i++) {
    		System.out.println("reached inside while");
    		
    		System.out.println("value of i: " + i);
    		System.out.println("quantity value: " + quantities[i]);
    		System.out.println("dish value: " + dishes[i]);
    		
    		String dish = dishes[i];
    		quantity = quantities[i];
    		Connection connection = null;
    		try {
				connection = DriverManager.getConnection(D.getHost(), D.getUsername(), D.getPassword());
				String query = "INSERT INTO orders(firstName, lastName, username, quantity, phoneNumber, emailAddress, zipcode, mainDish) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, (String) session.getAttribute("firstName")); ps.setString(2, (String) session.getAttribute("lastName"));
				ps.setString(3, (String) session.getAttribute("username")); ps.setString(5, (String) session.getAttribute("phoneNumber"));
				ps.setString(6, (String) session.getAttribute("emailAddress")); ps.setString(7, (String) session.getAttribute("zipcode"));
				ps.setString(4, quantity); ps.setString(8, dish);	
				
				ps.execute();


				String query2 = "SELECT price FROM menu WHERE mainDish = '" + dish + "'";
				Statement statement2 = connection.createStatement();
				ResultSet rs = statement2.executeQuery(query2);

				while (rs.next()) {
			        price = rs.getInt("price");
			    }
				perDishPrice = price * Integer.parseInt(quantity);
				message = message + "\n You have ordered " + quantity + " set(s) of " + dish + " and the per dish price for this is " + price + " with a total price of " + perDishPrice + ".";
				totalPrice = totalPrice + perDishPrice;

			} catch (SQLException e) { 
				e.printStackTrace();
				
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
  
    		/* if (paramName.startsWith("quantity")) {
    			System.out.println("reached inside if");
    			System.out.println("This is quantity: " + request.getParameter("quantity"));
    			if(!paramName.substring(8).isEmpty() && !request.getParameter("quantity").isEmpty()) {
    				System.out.println("reached inside inner if");
    				String mainDish = paramName.substring(8);
    				System.out.println(mainDish);
    				quantity = request.getParameter("quantity");
    				System.out.println(quantity);
    				
    				
    			}	
    		} */
    	}
		
    	message = message + "\n \n In total, your total bill is worth " + totalPrice + ".";
		E.emailStatement((String) session.getAttribute("emailAddress"), "Order Confirmed", message);  					
				
		//check if item exists already, if it does then add it normally, but otherwise add it together
		//create a shopping cart object that stores each product id and quantity 
		//record the number of orders made each day and the money earned each day -- create a jsp to see this
		
		//do error handling for if field isn't found
		//give a dropdown
    	
		request.getRequestDispatcher("/customerActions.jsp").forward(request, response);
    	System.out.println("Forwarded to customerActions");
	}
}