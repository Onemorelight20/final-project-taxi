package ua.boretskyi.webtask.listener;

import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ua.boretskyi.webtask.dao.util.ConnectionPool;
import ua.boretskyi.webtask.logic.DBException;


@WebListener
public class DatabaseConnectionListener implements ServletContextListener {


	public DatabaseConnectionListener() {
		Connection conn = null;
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			conn = pool.getConnection();
		} catch (DBException e) {
			throw new RuntimeException("Failed to connect to db");
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
