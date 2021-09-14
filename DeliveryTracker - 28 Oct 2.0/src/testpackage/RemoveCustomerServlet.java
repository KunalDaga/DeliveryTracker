package testpackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveCustomerServlet extends Controller {

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {		
		System.out.println("[RemoveCustomer][doProcess]");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String username = request.getParameter("removeUsername");
		
		DatabaseHelper D = new DatabaseHelper();
		D.removeCustomers(username);
		
		if (D.removedCustomer == true) {
			System.out.println("Customer removed");
			getServletContext().getRequestDispatcher("/clientActions.jsp").forward(request,response);
			System.out.println("Forwarded to clientActions");
		} else if (D.removedCustomer == false) {
			System.out.println("Customer not removed");
			getServletContext().getRequestDispatcher("/clientActions.jsp").forward(request,response);
			System.out.println("Forwarded to clientActions again");
		}
	}
}