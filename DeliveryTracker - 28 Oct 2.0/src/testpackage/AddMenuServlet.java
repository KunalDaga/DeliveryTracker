package testpackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddMenuServlet extends Controller {

	@Override
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("[AddMenu][doProcess]");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		DatabaseHelper D = new DatabaseHelper();	
		
		String main = request.getParameter("addMain");
		String side = request.getParameter("addSide");
		String drink = request.getParameter("addDrink");
		String price = request.getParameter("addPrice");
		String weight = request.getParameter("addWeight");
		String allergicIngredients = request.getParameter("addAllergicIngredients");
		
		try {
			Connection connection = DriverManager.getConnection(D.getHost(), D.getUsername(), D.getPassword());
			String query = "INSERT INTO menu(mainDish, sideDish, drink, price, weight, allergicIngredients) " 
					+ "VALUES('" + main + "', '"+ side + "', '" + drink + "', "
					+ "'" + price + "', '"+ weight + "', '" + allergicIngredients
					+ "')";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			
			System.out.println("[AddMenu] added items");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("[AddMenu] forwarding to clientActions");
		request.getRequestDispatcher("/clientActions.jsp").forward(request, response);
	}
}