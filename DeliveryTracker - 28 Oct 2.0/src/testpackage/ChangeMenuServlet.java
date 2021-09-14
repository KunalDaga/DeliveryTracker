package testpackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeMenuServlet extends Controller {

	@Override
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("[ChangeMenu][doProcess]");

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		DatabaseHelper D = new DatabaseHelper();	
		
		String oldItem = request.getParameter("oldItem");
		String newItem = request.getParameter("newItem");
		String choice = request.getParameter("itemChoices");
	
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(D.getHost(), D.getUsername(), D.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (choice.equals("main")) {
			String query = "UPDATE menu WHERE mainDish = "+ "'" + oldItem + "'" + " SET  mainDish = " + "'" + newItem + "'";
			try {
				Statement statement = connection.createStatement();
				int rowsAffected = statement.executeUpdate(query);
				System.out.println("[ChangeMenu] change made to mainDish");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (choice.equals("side"))  {
			String query = "UPDATE menu WHERE sideDish = '" + oldItem + "'" + "SET  sideDish = " + "'" + newItem + "'";
			try {
				Statement statement = connection.createStatement();
				int rowsAffected = statement.executeUpdate(query);
				System.out.println("[ChangeMenu] change made to sideDish");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (choice.equals("drink"))  {
			String query = "UPDATE menu WHERE drink = '" + oldItem + "'" + "SET  drink = " + "'" + newItem + "'";
			try {
				Statement statement = connection.createStatement();
				int rowsAffected = statement.executeUpdate(query);
				System.out.println("[ChangeMenu] change made to drink");
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		} 
		
		if (choice.equals("price"))  {
			String query = "UPDATE menu WHERE price = '" + oldItem + "'" + "SET  price = " + "'" + newItem + "'";
			try {
				Statement statement = connection.createStatement();
				int rowsAffected = statement.executeUpdate(query);
				System.out.println("[ChangeMenu] change made to price");
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} 
		
		if (choice.equals("weight"))  {
			String query = "UPDATE menu WHERE weight = '" + oldItem + "'" + "SET  weight = " + "'" + newItem + "'";
			try {
				Statement statement = connection.createStatement();
				int rowsAffected = statement.executeUpdate(query);
				System.out.println("[ChangeMenu] change made to weight");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		
		if (choice.equals("allergic"))  {
			String query = "UPDATE menu WHERE allergicIngredients = '" + oldItem + "'" + "SET  allergicIngredients = " + "'" + newItem + "'";
			try {
				Statement statement = connection.createStatement();
				int rowsAffected = statement.executeUpdate(query);
				System.out.println("[ChangeMenu] change made to allergicIngredients");
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} 
	}
}