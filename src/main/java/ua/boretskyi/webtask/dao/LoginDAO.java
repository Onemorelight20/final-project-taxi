package ua.boretskyi.webtask.dao;

import java.sql.*;
import ua.boretskyi.webtask.entity.User;
import ua.boretskyi.webtask.util.DBConnection;

public class LoginDAO {

	public User checkLogin(String email, String password) {
		User user = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(DBConnection.URL, DBConnection.LOGIN, DBConnection.PASSWORD)) {
			String st = "SELECT * FROM user WHERE email = ? AND password = ?";

			PreparedStatement preparedStatement = conn.prepareStatement(st);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();

			
			if(rs.next()) { 
				user = new User(); 
				System.out.println("The user was found");
				user.setName(rs.getString("name")); 
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				System.out.println(rs.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
