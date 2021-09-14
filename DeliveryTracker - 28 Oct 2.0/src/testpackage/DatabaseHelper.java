package testpackage;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHelper {
	
	private static String host = "jdbc:mysql://mysqldatabase.ciloz6zv9mq8.ap-southeast-1.rds.amazonaws.com:3306/masterdatabase";
	private String username = "kunaldaga28";
	private String password = "kunalandy2001";
	private String addCustomerProgress = "tbd";
	private String removeCustomerProgress = "tbd";
	private boolean isCustomer = true;
	
	boolean loggedIn = false;
	boolean addedCustomer = false;
	boolean removedCustomer = false;
	
	EmailSender Email = new EmailSender();

	public void DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addCustomers(String firstName, String lastName, String username, String password, 
			String customerEmail, String phoneNumber, String zipcode) {
		try {	
			Connection connection = DriverManager.getConnection(getHost(), getUsername(), getPassword());
			
			//Create Prepared Statement  ? , ?
			//r_gopal_k@yahoo.com and 1=1
			
			// SQL Injection .. OWASP -top ten 
			
			//"SELECT username, emailAddress FROM customerInfo where username =?  and emailAddress = ? "
			//ps.setString (1, username);
			//ps.setString(2. custIomerEmail);
					
			// use count instead of select
			String query = "SELECT username, emailAddress FROM customerInfo where username ='" + username + "' and emailAddress ='" + customerEmail +"' ";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				if (username.equals(rs.getString("username"))) {
					addCustomerProgress = "duplicateUsername";
					addedCustomer = false;
					System.out.println(addCustomerProgress);
				} else if (customerEmail.equals(rs.getString("emailAddress"))) {
					addCustomerProgress = "duplicateEmailAddress";
					addedCustomer = false;
					System.out.println(addCustomerProgress);
				}
			} else {
				try {					
					statement.executeUpdate("INSERT INTO customerInfo(firstName, lastName, username, password, "
							+ "emailAddress" + ", phoneNumber, zipcode, customerOrNot) VALUES('" + firstName + "', '"+ lastName + "', '" + username + "', "
							+ "'" + password + "', '"+ customerEmail + "', '" + phoneNumber
							+ "', '" + zipcode + "', '1')");
					addedCustomer = true;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			connection.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeCustomers(String username) {		
		try {
			//connection Pool.
			Connection connection = DriverManager.getConnection(getHost(), getUsername(), getPassword());
			//connection.setAutoCommit(false);

			Statement statement = connection.createStatement();
			String query = "DELETE FROM customerInfo WHERE username = '" + username +"'";
			statement.executeUpdate(query);
			removedCustomer = true;

			
			//what if no customer with that username
			//remove customer not working
			
			/* if (rowsAffected == 1) {
				connection.commit();
			} else {
				connection.rollback();
				System.out.println("Rows Affected: " + rowsAffected);
				System.out.println("Delete Complete");
			
				DBAccess db = new DBAccess(connection, false);
				int i = db.executeUpdate(query);
				db.commitorRolback(1);
				db.close();
			} */
			
			connection.close();
			statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void updateInformation(String col, String valueToSet, String primaryKey, String customerOrMenu) {
		try {
			if (customerOrMenu.equalsIgnoreCase("customer")) {
				Connection connection = DriverManager.getConnection(getHost(), getUsername(), getPassword());
				String query = "UPDATE customerInfo SET " + col + " = '" + valueToSet + "' WHERE username = '" + primaryKey + "'";
				Statement statement = connection.createStatement();
				statement.executeUpdate(query);
		
				statement.close();
				connection.close(); 
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}

	public void loginCustomers(String username, String password, boolean loggedIn, HttpSession session) {
		try {	
			System.out.println("Login customers called");
			Connection connection = DriverManager.getConnection(getHost(), getUsername(), getPassword());
			String query = "SELECT firstName, lastName, username, password, emailAddress, phoneNumber, zipcode, customerOrNot FROM customerInfo WHERE username ='" + username + "'" ;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			int i = 0;
			if (rs.next()) {
				System.out.println("	Iteration " + (i++));
				if (username.equals(rs.getString("username")) && password.equals(rs.getString("password"))) {
					setLoggedIn(true);
										
					session.setAttribute("firstName", rs.getString("firstName"));
					session.setAttribute("lastName", rs.getString("lastName"));
					session.setAttribute("phoneNumber", rs.getString("phoneNumber"));
					session.setAttribute("emailAddress", rs.getString("emailAddress"));
					session.setAttribute("zipcode", rs.getString("zipcode"));

					if (rs.getBoolean("customerOrNot") == true) {
							setIsCustomer(true);
					} else {
						setIsCustomer(false);
					}
				} else {
					int tempLoginCount = (int) session.getAttribute("loginCount");
					session.setAttribute("loginCount", tempLoginCount + 1);
					System.out.println("Login Count is: " + session.getAttribute("loginCount"));
				}
				
				if ((int) session.getAttribute("loginCount") == 3) {
					setLoggedIn(false);
					Email.emailStatement(rs.getString("username"), "Login Failed", 
							"There have been 3 unauthorized attempts to access your account. Please change your password.");
					//column in database for locked account
					//add time for locked account?
				}
			} else {
				System.out.println("	Login username not found");
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getHost() { return host; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public String getAddCustomerProgress() { return addCustomerProgress; }
	public String getRemoveCustomerProgress() { return removeCustomerProgress; }
	public boolean getIsCustomer() { return isCustomer; }
	public boolean getLoggedIn() { return loggedIn; }

	public void setHost (String newHost) { host = newHost; }
	public void setUsername (String newUsername) { username = newUsername; }
	public void setPassword (String newPassword) { password = newPassword; }
	public void setAddCustomerProgress(String add) { addCustomerProgress = add; }
	public void setRemoveCustomerProgress(String remove) { removeCustomerProgress = remove; }
	public void setIsCustomer (boolean value) { isCustomer = value; }
	public void setLoggedIn (boolean value) { loggedIn = value; }
}