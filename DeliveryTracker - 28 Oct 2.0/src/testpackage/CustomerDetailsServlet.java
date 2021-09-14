package testpackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerDetailsServlet extends Controller {

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {		
		System.out.println("[CustomerDetails][doProcess]");

		if (("addCustomers").equals(request.getParameter("customerDetails"))) {
			System.out.println("[CustomerDetails][addCustomer]");
			
			try {
				request.getRequestDispatcher("/addCustomer.jsp").forward(request, response);
				System.out.println("Forwarded to addCustomer");
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			
		} else if (("removeCustomers").equals(request.getParameter("customerDetails"))) {
			System.out.println("[CustomerDetails][removeCustomers]");
			
			try {
				request.getRequestDispatcher("/removeCustomers.jsp").forward(request, response);
				System.out.println("Forwarded to removeCustomers");
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			
		} else if (("viewAll").equals(request.getParameter("customerDetails"))) {
			System.out.println("[ClientServlet][viewAll]");
			
			try {
				request.getRequestDispatcher("/viewAll.jsp").forward(request, response);
				System.out.println("Forwarded to viewAll");
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}