package testpackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends Controller {

	@Override
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("[RegisterServlet][doProcess]"  + request.getParameter("newFirstName"));
		
		String firstName = request.getParameter("newFirstName");
		String lastName = request.getParameter("newLastName");
		String username = request.getParameter("newUsername");
		String password = request.getParameter("newPassword");
		String emailAddress = request.getParameter("newEmailAddress");
		String phoneNumber = request.getParameter("newPhoneNumber");
		String zipcode = request.getParameter("newZipcode");
		
		DatabaseHelper D = new DatabaseHelper();
		D.addCustomers(firstName, lastName, username, password, emailAddress, phoneNumber, zipcode);
		
		if (D.addedCustomer == true) {
			System.out.println("Customer added");
			getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
			System.out.println("Forwarded to login");
		} else if (D.addedCustomer == false) {
			System.out.println("Customer not added");
			getServletContext().getRequestDispatcher("/register.jsp").forward(request,response);
			System.out.println("Forwarded to register again");
		}
	}
}