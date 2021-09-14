package testpackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAccess {

	Connection connection;
	java.sql.Statement statement;
	java.sql.ResultSet rs;
	
	public DBAccess(Connection connection)throws Exception {
		this.connection = connection;
		statement = connection.createStatement();
	}
	
	public DBAccess(Connection connection, boolean autoCommit) throws Exception {
		this.connection=connection;
		connection.setAutoCommit(autoCommit);
		statement = connection.createStatement();
	}
	
	public int executeUpdate(String sql) throws SQLException {	
		return statement.executeUpdate(sql);
	}
	
	public ResultSet execute(String sql)throws SQLException {
		rs = statement.executeQuery(sql);
		return rs;
	}
	
	public void commitorRolback(int commit) throws SQLException {
		if (commit ==0) {
			connection.commit();
		} else {
			connection.rollback();
		}
	}

	public void close() {
		try {
			if (rs!=null) {
				rs.close();
			}
		} catch (Exception e) {	
		}
		
		try {
			if (statement!=null) {
				statement.close();
			}
		} catch (Exception e) {
		}
		
		try {
			if (connection!=null) {
				connection.close();
			}
		} catch (Exception e) {
		}
	}
}
