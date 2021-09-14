package testpackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerServlet extends Controller {

	@Override
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("[CustomerServlet][doProcess]");

		if (("updateInformation").equals(request.getParameter("customerAction"))) {
			System.out.println("[ClientServlet][updateInformation]");
			try {
				request.getRequestDispatcher("/updateInformation.jsp").forward(request, response);
				System.out.println("Forwarded to updateInformation");
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (("viewMenu").equals(request.getParameter("customerAction"))) {
			System.out.println("[CustomerServlet][viewMenu]");
			try {
				request.getRequestDispatcher("/viewMenu.jsp").forward(request, response);
				System.out.println("Forwarded to viewMenu");
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}