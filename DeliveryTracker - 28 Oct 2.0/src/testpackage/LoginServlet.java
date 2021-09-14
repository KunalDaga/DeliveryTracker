package testpackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends Controller {

	@Override
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		PrintWriter writer = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("[LoginServlet][doProcess] "  + username + " " + password);
	
		DatabaseHelper D = new DatabaseHelper();
		session.setAttribute("loginCount", 0);
		D.loginCustomers(username, password, false, session);
		
		if (D.getLoggedIn() == true) {
			if (D.getIsCustomer() == true) {
				System.out.println("Customer logged in");
				session.setAttribute("username", username);
				getServletContext().getRequestDispatcher("/customerActions.jsp").forward(request,response);
				System.out.println("Forwarded to customerActions");
			} else if (D.getIsCustomer() == false) {
				System.out.println("Client logged in");
				getServletContext().getRequestDispatcher("/clientActions.jsp").forward(request,response);
				System.out.println("Forwarded to clientActions");
			}
		} else if (D.getLoggedIn() == false) {
			System.out.println("Login failed");
			
			writer.println("<script type=\"text/javascript\">");
			writer.println("alert('Username or password is incorrect.');");
			writer.println("location='login.jsp';");
			writer.println("</script>");
		    
			System.out.println("Forwarded to login again");
		} else {
			System.out.println("Login error");
			
			writer.println("<script type=\"text/javascript\">");
			writer.println("alert('Login error');");
			writer.println("location='login.jsp';");
			writer.println("</script>");
			
			System.out.println("Forwarded to login again");
		}
	}
}