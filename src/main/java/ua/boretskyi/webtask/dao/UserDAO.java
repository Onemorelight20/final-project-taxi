package ua.boretskyi.webtask.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.boretskyi.webtask.entity.User;
import ua.boretskyi.webtask.util.DBConnection;

public class UserDAO {
	public boolean isPresentInDB(User user) {
		 try {
	 			Class.forName("com.mysql.cj.jdbc.Driver");
	 		} catch (ClassNotFoundException e) {
	 			e.printStackTrace();
	 		}
		
		try(Connection conn = DriverManager.getConnection(DBConnection.URL, DBConnection.LOGIN, DBConnection.PASSWORD)){
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getUserRole(User user) {
		int roleId = this.getUserRoleId(user);
		String query = "SELECT name FROM user_role WHERE id=?";
		if(roleId > 0) {
			try(Connection conn = DriverManager.getConnection(DBConnection.URL, DBConnection.LOGIN, DBConnection.PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(query);){
				stmt.setInt(1, roleId);
				
				ResultSet rs = stmt.executeQuery();
				rs.next();
				return rs.getString(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		return null;
	}
	
	private static int getUserRoleId(User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		String query = "SELECT role_id FROM user WHERE user.email=? AND user.password=?";
		try(Connection conn = DriverManager.getConnection(DBConnection.URL, DBConnection.LOGIN, DBConnection.PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(query);){
			
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int roleId = rs.getInt(1);
			return roleId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
