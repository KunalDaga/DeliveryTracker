package testpackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCustomerServlet extends Controller {

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {				
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("[AddCustomer][doProcess]");
		
		String firstName = request.getParameter("addFirstName");
		String lastName = request.getParameter("addLastName");
		String username = request.getParameter("addUsername");
		String password = request.getParameter("addPassword");
		String emailAddress = request.getParameter("addEmailAddress");
		String phoneNumber = request.getParameter("addPhoneNumber");
		String zipcode = request.getParameter("addZipcode");
		
		DatabaseHelper D = new DatabaseHelper();
		D.addCustomers(firstName, lastName, username, password, emailAddress, phoneNumber, zipcode);
		
		if (D.addedCustomer == true) {
			System.out.println("Customer added");
			getServletContext().getRequestDispatcher("/clientActions.jsp").forward(request,response);
			System.out.println("Forwarded to clientActions");
		} else if (D.addedCustomer == false) {
			System.out.println("Customer not added");
			getServletContext().getRequestDispatcher("/clientActions.jsp").forward(request,response);
			System.out.println("Forwarded to clientActions again");
		}
	}
}