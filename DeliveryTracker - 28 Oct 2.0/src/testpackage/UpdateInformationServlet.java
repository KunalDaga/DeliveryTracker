package testpackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateInformationServlet extends Controller {

	@Override
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("[UpdateInformation][doProcess]");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		DatabaseHelper D = new DatabaseHelper();	
		String currentUsername = (String) session.getAttribute("username");
		
		String firstName = request.getParameter("updateFirstName");
		String lastName = request.getParameter("updateLastName");
		String username = request.getParameter("updateUsername");
		String password = request.getParameter("updatePassword");
		String emailAddress = request.getParameter("updateEmailAddress");
		String phoneNumber = request.getParameter("updatePhoneNumber");
		String zipcode = request.getParameter("updateZipcode");
		
		if (firstName != null) {
			D.updateInformation("firstName", firstName, currentUsername, "customer");
		}
		
		if (lastName != null)  {
			D.updateInformation("lastName", lastName, currentUsername, "customer");
		}
		
		if (username != null)  {
			D.updateInformation("username", username, currentUsername, "customer");
		} 
		
		if (password != null)  {
			D.updateInformation("password", password, currentUsername, "customer");
		} 
		
		if (emailAddress != null)  {
			D.updateInformation("emailAddress", emailAddress, currentUsername, "customer");
		} 
		
		if (phoneNumber != null)  {
			D.updateInformation("phoneNumber", phoneNumber, currentUsername, "customer");
		} 
		
		if (zipcode != null)  {
			D.updateInformation("zipcode", zipcode, currentUsername, "customer");
		}
		request.getRequestDispatcher("/customerActions.jsp").forward(request, response);
		System.out.println("Forwarded to customerActions");
	}
}