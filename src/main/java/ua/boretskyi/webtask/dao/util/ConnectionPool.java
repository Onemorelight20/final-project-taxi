package ua.boretskyi.webtask.dao.util;


import java.sql.Connection;
import java.sql.SQLException;
 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ua.boretskyi.webtask.logic.DBException;


public class ConnectionPool {
	
	private ConnectionPool() {}
	
	private static ConnectionPool instance;

	public static ConnectionPool getInstance() {
		if(instance == null ) {
			instance = new ConnectionPool();
		}
		return instance;
	}
		
	 
	  public Connection getConnection() throws DBException {
		 Context ctx;
		 DataSource dts = null;;
		 Connection connection = null;
		 
		 try {
			ctx = new InitialContext();
			dts = (DataSource)ctx.lookup("java:comp/env/jdbc/connectionpool");
			connection = dts.getConnection();
		} catch (NamingException e) {
			System.out.println();
			e.printStackTrace();
		} catch (SQLException e) {
			throw new DBException("failed to get a connection", e);
		} 
		 
		 
		 return connection;
	  }
	
}
