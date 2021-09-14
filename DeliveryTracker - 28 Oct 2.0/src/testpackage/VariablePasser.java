package testpackage;

public class VariablePasser {
	public boolean loggedIn = false;
	public int loginCount = 0;
	public boolean addedCustomer;
	public boolean removedCustomer;
	public String addCustomerProgress;
	public String removeCustomerProgress;
	
	public VariablePasser() {}
	
	public VariablePasser(boolean loggedIn, int loginCount, boolean addedCustomer, boolean removedCustomer) {
		super();
		this.loggedIn = loggedIn;
		this.loginCount = loginCount;
		this.addedCustomer = addedCustomer;
	}	
}