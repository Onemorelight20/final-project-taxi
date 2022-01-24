package ua.boretskyi.webtask.dao.mysql;

import ua.boretskyi.webtask.dao.CarDAO;
import ua.boretskyi.webtask.dao.DAOFactory;
import ua.boretskyi.webtask.dao.RideDAO;
import ua.boretskyi.webtask.dao.UserDAO;

public class MysqlDAOFactory extends DAOFactory{
	public MysqlDAOFactory() {
		
	}
	
	@Override
	public UserDAO getUserDAO() {
		return new MysqlUserDAO();
	}

	@Override
	public CarDAO getCarDAO() {
		return new MysqlCarDAO();
	}

	@Override
	public RideDAO getRideDAO() {
		return new MysqlRideDAO();
	}

}
