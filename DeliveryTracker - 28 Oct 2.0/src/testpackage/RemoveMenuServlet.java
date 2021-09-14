package testpackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveMenuServlet extends Controller {

	@Override
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("[RemoveMenu][doProcess]");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		DatabaseHelper D = new DatabaseHelper();	
		String main = request.getParameter("removeMain");
		
		try {
			Connection connection = DriverManager.getConnection(D.getHost(), D.getUsername(), D.getPassword());
			String query = "DELETE FROM menu WHERE mainDish = '" + main +"'";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			
			System.out.println("[RemoveMenu] removed item");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("[RemoveMenu] forwarding to clientActions");
		request.getRequestDispatcher("/clientActions.jsp").forward(request, response);
	}
}