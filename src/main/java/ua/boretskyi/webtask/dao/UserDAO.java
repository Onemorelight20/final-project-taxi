package ua.boretskyi.webtask.dao;


import java.util.List;
import ua.boretskyi.webtask.dao.entity.User;
import ua.boretskyi.webtask.logic.DBException;


public interface UserDAO {


	void createUser(User user) throws DBException; 
	
	
	User findUser(int userId) throws DBException;

	User findUser(String email, String password) throws DBException;
	
	void updateUser(User user) throws DBException;

	
	void deleteUser(int userId) throws DBException; 


	User findUser(String userLogin) throws DBException ;
	
	List<User> findAll() throws DBException;

}