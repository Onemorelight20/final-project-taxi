package ua.boretskyi.webtask.logic;

import java.util.List;

import ua.boretskyi.webtask.dao.DAOFactory;
import ua.boretskyi.webtask.dao.UserDAO;
import ua.boretskyi.webtask.dao.entity.User;

public class UserManager {
	private DAOFactory factory = DAOFactory.getInstance();
	private UserDAO udao = factory.getUserDAO();
	
	public void createUser(User user) throws DBException {
		udao.createUser(user);
	}
	
	public User findUser(int userId) throws DBException {
		return udao.findUser(userId);
	}
	
	public User findUser(String email) throws DBException {
		return udao.findUser(email);
	}
	
	public User findUser(String email, String password) throws DBException {
		return udao.findUser(email, password);
	}
	
	public void updateUser(User user) throws DBException {
		udao.updateUser(user);
	}
	
	public void deleteUser(int userId) throws DBException {
		udao.deleteUser(userId);
	}
	
	public List<User> findAll() throws DBException {
		return udao.findAll();
	}
	
}
