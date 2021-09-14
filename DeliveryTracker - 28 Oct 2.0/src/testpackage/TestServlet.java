package testpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	EmailSender E = new EmailSender();
	DatabaseHelper D = new DatabaseHelper();
	
	public TestServlet() {
		super();
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(("infoTab ="+request.getParameter("infotab")) + System.currentTimeMillis());
		HttpSession session = request.getSession(false);
		
		if (session !=null && session.getAttribute("machineState") == null) {
			System.out.println("Session State "+ session.getAttribute("machineState"));
			//session.setAttribute("machineState","none");
			
			//session.setAttribute("loginCount", 0);
			//session.setAttribute("loggedIn", false);
			welcome(request, response, session);

		}
		else if ("registeringIn".equals(request.getParameter("mode"))){
			// Register process starts here 
			//
		}
		else {
			session = request.getSession(true);
			System.out.println("What is happening");
			System.out.println("Session State "+ session.getAttribute("machineState"));
			String machineState = (String) session.getAttribute("machineState");
		
		session.setAttribute("failedLogin", 0);
		session.setAttribute("correctLogin", false);
		}
		
		/* if (request.getParameter("mode").equals("none")) {
			welcome(request, response, session);
		} else if (request.getParameter("mode").equals("login")) {
			login(request, response, session);
		} else if (request.getParameter("mode").equals("register")) {
			register(request, response, session);
		} */
				
		/* if (session.getAttribute("machineState").equals("null")) {
			login(request, response);
		} else if (machineState.equals("clientActions")) {
			clientActions(request, response);
		} else if (machineState.equals("client viewCustomers")) {
			clientActions(request, response);
		} else if (machineState.equals("customerActions")) {
			customerActions(request, response);
		} else if (machineState.equals("customer updateInfo")) {
			customerActions(request, response);
		} */
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void welcome(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		System.out.println("Welcome starts here ");
		D.DBConnection();		
		
		System.out.println("After the DB connection " + request.getParameter("infotab"));
		if (("newCustomer").equals(request.getParameter("infotab"))) {
			try {
				request.getRequestDispatcher("/register.jsp").include(request, response);
			} catch (ServletException | IOException e) {
				System.out.println(session.getAttribute("machineState"));
				e.printStackTrace();
			}
		} else {
			try {
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				//request.getRequestDispatcher("/login.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
				System.out.println(session.getAttribute("machineState"));
			}
		}
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int loginCount = (int) session.getAttribute("loginCount");
		boolean loggedIn = (boolean) session.getAttribute("loggedIn");
		
		if (loggedIn == true) {
			session.setAttribute("username", username);
			try {
				request.getRequestDispatcher("/customerActions").include(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		//VariablePasser loggedIn = D.VariablePasser();
		//session.setAttribute("loggedIn", loggedIn);
		/* ArrayList<String> loginArrayList = new ArrayList<String>();
		loginArrayList = D.loginCustomers(username, password, loginCount, loggedIn); */
	}
	
	protected void register(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String firstName = request.getParameter("newFirstName");
		String lastName = request.getParameter("newLastName");
		String username = request.getParameter("newUsername");
		String password = request.getParameter("newPassword");
		String emailAddress = request.getParameter("newEmailAddress");
		String phoneNumber = request.getParameter("newPhoneNumber");
		String zipcode = request.getParameter("newZipcode");

		D.addCustomers(firstName, lastName, username, password, emailAddress, phoneNumber, zipcode);
	}

	public void clientActions(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		D.DBConnection();
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (("viewCustomers").equals(request.getParameter("clientAction"))) {
			try {
				//request.getRequestDispatcher("/viewCustomers.jsp").forward(request, response);
				request.getRequestDispatcher("/viewCustomers.jsp").include(request, response);
				session.setAttribute("machineState", "client viewCustomers");
			} catch (ServletException | IOException e) {
				e.printStackTrace();
				System.out.println(session.getAttribute("machineState"));
			}
			// add customers doesn't work
			if ("addCustomers".equals(request.getParameter("viewCustomers"))) {
				session.setAttribute("machineState", "client addCustomers");
				try {
					// request.getRequestDispatcher("/addCustomers.jsp").forward(request, response);
					request.getRequestDispatcher("/addCustomers.jsp").include(request, response);
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					try {
						Connection connect = DriverManager.getConnection(D.getHost(), D.getUsername(), D.getPassword());
						String query = "SELECT * FROM customerInfo";
						Statement stm = connect.createStatement();
						String dbFirstName = request.getParameter("addFirstName");
						String dbLastName = request.getParameter("addLastName");
						String dbUsername = request.getParameter("addUsername");
						String dbPassword = request.getParameter("addPassword");
						String dbPhoneNumber = request.getParameter("addPhoneNumber");
						String dbCustomerEmail = request.getParameter("addEmailAddress");
						String dbZipcode = request.getParameter("addZipcode");

						ResultSet rs = stm.executeQuery(query);

						while (rs.next()) {
							String realUsername = rs.getString("username");
							String customerEmail = rs.getString("emailAddress");

							if (realUsername.equals(dbUsername)) {
								// writer.println("Sorry, the username you inputted is not available");
								//request.getRequestDispatcher("/addCustomers.jsp").forward(request, response);
								request.getRequestDispatcher("/addCustomers.jsp").include(request, response);
							} else if (customerEmail.equals(dbCustomerEmail)) {
								// writer.println("Sorry, an account with your email already exists");
								//request.getRequestDispatcher("/addCustomers.jsp").forward(request, response);
								request.getRequestDispatcher("/addCustomers.jsp").include(request, response);
							}
						}

						stm.executeUpdate(
								"INSERT INTO customerInfo(firstName, lastName, username, password, emailAddress"
										+ ", phoneNumber, zipcode, customerOrNot) " + "VALUES('" + dbFirstName + "', '"
										+ dbLastName + "', '" + dbUsername + "', '" + dbPassword + "', '"
										+ dbCustomerEmail + "', '" + dbPhoneNumber + "', '" + dbZipcode + "', '1')");
						stm.close();
						connect.close();

					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				} catch (ServletException | IOException e) {
					Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, e);
					e.printStackTrace();
					System.out.println(session.getAttribute("machineState"));
				}

			} else if ("removeCustomers".equals(request.getParameter("viewCustomers"))) {
				D.removeCustomers((String) session.getAttribute("username"));
			} else if (("viewAll").equals(request.getParameter("viewCustomers"))) {
				session.setAttribute("machineState", "client viewAll");
				try {
					//request.getRequestDispatcher("/viewAll.jsp").forward(request, response);
					request.getRequestDispatcher("/viewAll.jsp").include(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
					System.out.println(session.getAttribute("machineState"));
				}
			}
		} else if ("View Orders".equals(request.getParameter("clientAction"))) {
			session.setAttribute("machineState", "client viewOrders");
			try {
				//request.getRequestDispatcher("/viewOrders.jsp").forward(request, response);
				request.getRequestDispatcher("/viewOrders.jsp").include(request, response);

			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (("Update Menu").equals(request.getParameter("clientAction"))) {
			session.setAttribute("machineState", "client updateMenu");
			// need to start code for this block
			// need to make updateMenu.jsp
		} else if (("Route Map").equals(request.getParameter("clientAction"))) {
			session.setAttribute("machineState", "client routeMap");
			try {
				//request.getRequestDispatcher("/routeMap.jsp").forward(request, response);
				request.getRequestDispatcher("/routeMap.jsp").include(request, response);

			} catch (ServletException | IOException e) {
				e.printStackTrace();
				System.out.println(session.getAttribute("machineState"));
				writer.println(session.getAttribute("machineState"));
			}
			// high complexity
		}
	}

	public void customerActions(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		try {
			//request.getRequestDispatcher("/customerActions.jsp").forward(request, response);
			request.getRequestDispatcher("/customerActions.jsp").include(request, response);

		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		if (("viewMenu").equals(request.getParameter("customerAction"))) {
			session.setAttribute("machineState", "customer viewMenu");
			/* try {
				request.getRequestDispatcher("/viewMenu.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
				System.out.println(session.getAttribute("machineState"));
			} */
		} else if (("updateInformation").equals(request.getParameter("customerAction"))) {
			// write code
		}
	}
}