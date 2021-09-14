package testpackage;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//to handle after login, during login, add a variable to session to boolean logged in
// in controller, check if loggedin exists, otherwise throw to LoginServlet


public abstract class Controller extends HttpServlet {
	
	HttpSession session;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		doPost(request , response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		session = request.getSession(false);
		if (session != null) {
			System.out.println("[Controller][doPost] GettingSession " + session);
			if (request.getParameter("mode") == null) {
				getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);	
			} else if (("newCustomer").equals(request.getParameter("infotab"))) {
				try {
					System.out.println("[Controller][doPost] Forwarding to Register");
					getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}	
			} else if (("returningCustomer").equals(request.getParameter("infotab"))) {
				try {
					System.out.println("[Controller][doPost] Forwarding to Login");
					getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}	
			} else if (request.getParameter("mode").equals("registeringIn")) {
				doProcess(request, response);
			} else if (request.getParameter("mode").equals("loggingIn")) {
				doProcess(request, response);
			} else if (("clientActions").equals(request.getParameter("mode"))) {
				doProcess(request, response);
			} else if (("customerDetails").equals(request.getParameter("mode"))) {
				doProcess(request, response);
			} else if (("addCustomer").equals(request.getParameter("mode"))) {
				doProcess(request, response);
			} else if(("removeCustomer").equals(request.getParameter("mode"))) {
				doProcess(request, response);
			} else if(("updatingMenu").equals(request.getParameter("mode"))) {
				doProcess(request, response);
			}  else if (("addingMenu").equals(request.getParameter("mode"))) {
				doProcess(request, response);
			} else if (("removingItem").equals(request.getParameter("mode"))) {
				doProcess(request, response);
			} else if (("changingItem").equals(request.getParameter("mode"))) {
				doProcess(request, response);
			} else if (("customerActions").equals(request.getParameter("mode"))) {
				doProcess(request, response);
			} else if (("updatingInformation").equals(request.getParameter("mode"))) {
				doProcess(request, response);
			} else if (("viewingMenu").equals(request.getParameter("mode"))) {
				doProcess(request, response);
			} else if (session.getAttribute("loggedIn")!= null && (boolean) (session.getAttribute("loggedIn")) == true ) {
				doProcess(request, response);
			} else {
				try {
					System.out.println("[Controller][doPost] Else statement, forwarding to login");
					getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public abstract void doProcess(HttpServletRequest request, HttpServletResponse response) throws  IOException,ServletException;
}
