package testpackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServlet extends Controller {

	@Override
	public void doProcess(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {		
		System.out.println("WelcomeServlet");
	}
}