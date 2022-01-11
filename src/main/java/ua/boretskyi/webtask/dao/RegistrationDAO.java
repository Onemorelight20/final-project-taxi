package ua.boretskyi.webtask.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ua.boretskyi.webtask.entity.User;
import ua.boretskyi.webtask.util.DBConnection;

public class RegistrationDAO {
	
	public boolean registerUser(User user){
		 String name = user.getName();
		 String phoneNumper = user.getPhoneNumber();
         String email = user.getEmail();
         String password = user.getPassword();
         
         try {
 			Class.forName("com.mysql.cj.jdbc.Driver");
 		} catch (ClassNotFoundException e) {
 			e.printStackTrace();
 		}
               
         try(Connection con = DriverManager.getConnection(DBConnection.URL, DBConnection.LOGIN, DBConnection.PASSWORD)){
             PreparedStatement preparedStatement = null;   
            
             String query = "insert into user(name, email, phone_number, password, role_id) values (?,?,?,?,?)";
             preparedStatement = con.prepareStatement(query);
             preparedStatement.setString(1, name);
             preparedStatement.setString(2, email);
             preparedStatement.setString(3, phoneNumper);
             preparedStatement.setString(4, password);
             preparedStatement.setInt(5, 2);
             
             int i= preparedStatement.executeUpdate();
             
             if (i!=0)
             return true; 
         }
         catch(SQLException e)
         {
            e.printStackTrace();
         }       
         return false; 
	}
}
