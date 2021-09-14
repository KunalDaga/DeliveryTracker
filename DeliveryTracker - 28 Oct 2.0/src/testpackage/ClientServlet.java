package testpackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientServlet extends Controller {

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
		System.out.println("[ClientServlet][doProcess]");

		if (("customerDetails").equals(request.getParameter("clientAction"))) {
			System.out.println("[ClientServlet][customerDetails]");
			try {
				request.getRequestDispatcher("/customerDetails.jsp").forward(request, response);
				System.out.println("Forwarded to customerDetails");
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (("viewOrder").equals(request.getParameter("clientAction"))) {
			System.out.println("[ClientServlet][viewOrder]");
			try {
				request.getRequestDispatcher("/viewOrders.jsp").forward(request, response);
				System.out.println("Forwarded to viewOrder");
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (("updateMenu").equals(request.getParameter("clientAction"))) {
			System.out.println("[ClientServlet][updateMenu]");
			try {
				request.getRequestDispatcher("/updateMenu.jsp").forward(request, response);
				System.out.println("Forwarded to updateMenu");
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (("routeMap").equals(request.getParameter("clientAction"))) {
			System.out.println("[ClientServlet][routeMap]");
			try {
				request.getRequestDispatcher("/routeMap.jsp").forward(request, response);
				System.out.println("Forwarded to routeMap");
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}