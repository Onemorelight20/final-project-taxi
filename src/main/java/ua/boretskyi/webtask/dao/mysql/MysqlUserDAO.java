package ua.boretskyi.webtask.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.boretskyi.webtask.dao.UserDAO;
import ua.boretskyi.webtask.dao.entity.User;
import ua.boretskyi.webtask.dao.entity.User.Role;
import ua.boretskyi.webtask.dao.util.ConnectionPool;
import ua.boretskyi.webtask.logic.DBException;


public class MysqlUserDAO implements UserDAO {

	private static final String PS_INSERT_USER_BY_NAME_EMAIL_PNONE_PASSWORD = "INSERT INTO user (name, email, phone_number, password) VALUES (?, ?, ?, ?)";
	private static final String PS_FIND_USER_BY_ID = "SELECT * FROM user WHERE id=?";
	private static final String PS_FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE email=?";
	private static final String PS_FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM user where email=? AND password=?";
	private static final String PS_UPDATE_ALL_USER_FIELDS = "UPDATE user SET name=?,email=?,phone_number=?,password=?, spent_money=? WHERE id=?";
	private static final String PS_DELETE_USER_BY_ID = "DELETE FROM user WHERE id=?"; 
	private static final String SELECT_ALL_USERS = "SELECT * FROM user";
	
	private static ConnectionPool pool = ConnectionPool.getInstance();
	
	@Override
	public void createUser(User user) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(PS_INSERT_USER_BY_NAME_EMAIL_PNONE_PASSWORD, PreparedStatement.RETURN_GENERATED_KEYS);
			fillInUserData(user, ps);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			user.setId(rs.getInt(1));
			user.setRole(User.Role.CLIENT);
		} catch (SQLException e) {
			//here goes logging
			
			throw new DBException("failed to prepare a statement", e);
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}
	}


	@Override
	public User findUser(int userId) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = new User();
		
		try {
			ps = conn.prepareStatement(PS_FIND_USER_BY_ID);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			rs.next();
			fillAllUserDataFromResultSet(user, rs);
			return user;
		} catch (SQLException e) {
			//here goes logging
			
			throw new DBException("failed to find a user by id", e);
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}
	}


	@Override
	public User findUser(String email, String password) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		User user = new User();
		try {
			conn = pool.getConnection();
			ps = conn.prepareStatement(PS_FIND_USER_BY_EMAIL_AND_PASSWORD);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			rs.next();
			fillAllUserDataFromResultSet(user, rs);
			return user;
		} catch(SQLException e) {
			//log
			
			throw new DBException("failed to find a user", e);
		}
	}


	@Override
	public void updateUser(User user) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(PS_UPDATE_ALL_USER_FIELDS);

			int k = 1;
			ps.setString(k++, user.getName());
			ps.setString(k++, user.getEmail());
			ps.setString(k++, user.getPhoneNumber());
			ps.setString(k++, user.getPassword());
			ps.setDouble(k++, user.getSpentMoney());
			ps.setInt(k++, user.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// log
			e.printStackTrace();
			
			throw new DBException("failed to update a user", e);
		} finally {
			close(ps);
			close(conn);
		}
	}

	@Override
	public void deleteUser(int userId) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(PS_DELETE_USER_BY_ID);
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new DBException("failed to delete a user", e);
		} finally {
			close(ps);
			close(conn);
		}

	}

	@Override
	public User findUser(String email) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = new User();
		try {
			ps = conn.prepareStatement(PS_FIND_USER_BY_EMAIL);
			ps.setString(1, email);
			rs =  ps.executeQuery();
			rs.next();
			fillAllUserDataFromResultSet(user, rs);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new DBException("failed to find a user by email " + email, e);
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
	}

	@Override
	public List<User> findAll() throws DBException {
		Connection conn = pool.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<User> result = new ArrayList<>();
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(SELECT_ALL_USERS);
			while(rs.next()) {
				User user = new User();
				fillAllUserDataFromResultSet(user, rs);
				result.add(user);
			}
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new DBException("failed to find all users with list:" + result, e);
		}
	}

	private void fillInUserData(User user, PreparedStatement ps) throws SQLException {
		ps.setString(1, user.getName());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPhoneNumber());
		ps.setString(4, user.getPassword());
	}


	
	private void fillAllUserDataFromResultSet(User user, ResultSet rs) throws SQLException {
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPhoneNumber(rs.getString("phone_number"));
		user.setPassword(rs.getString("password"));
		user.setSpentMoney(rs.getDouble("spent_money"));
		user.setRole(Role.valueOf(rs.getString("role").toUpperCase()));
	}
	
	private void close(PreparedStatement ps) {
		try {
			if(ps != null)
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void close(Connection connection) {
		try {
			if(connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void close(ResultSet rs) {
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
