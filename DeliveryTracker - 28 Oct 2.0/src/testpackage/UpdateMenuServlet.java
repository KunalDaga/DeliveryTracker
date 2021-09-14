package testpackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateMenuServlet extends Controller {

	@Override
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("[UpdateMenu][doProcess]");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if ("addItem".equals(request.getParameter("updateChoices"))) {
			System.out.println("[UpdateMenu][addItem]");
			try {
				request.getRequestDispatcher("/addItem.jsp").forward(request, response);
				System.out.println("Forwarded to addItem");
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (("removeItem").equals(request.getParameter("updateChoices"))) {
			System.out.println("[UpdateMenu][removeItem]");
			try {
				request.getRequestDispatcher("/removeItem.jsp").forward(request, response);
				System.out.println("Forwarded to removeItem");
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}		
		} else if (("changeItem").equals(request.getParameter("updateChoices"))) {
			System.out.println("[UpdateMenu][changeItem]");
			try {
				request.getRequestDispatcher("/changeItem.jsp").forward(request, response);
				System.out.println("Forwarded to changeItem");
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}