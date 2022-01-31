package ua.boretskyi.webtask.logic;

import ua.boretskyi.webtask.dao.DAOFactory;
import ua.boretskyi.webtask.dao.RideStatsDAO;
import ua.boretskyi.webtask.dao.entity.RideStats;

public class RideStatsManager {
	private DAOFactory factory = DAOFactory.getInstance();
	private RideStatsDAO rideStatsDAO = factory.getRideStatsDAO();
	
	public RideStats getAllTimeStats() throws DBException {
		return rideStatsDAO.getAllTimeStats();
	}
	
	public RideStats getLastMonthStats() throws DBException {
		return rideStatsDAO.getLastMonthStats();
	}
	
	public RideStats getLastWeekStats() throws DBException {
		return rideStatsDAO.getLastWeekStats();
	}
	
	public RideStats get24HoursStats() throws DBException {
		return rideStatsDAO.get24HoursStats();
	}
	
	public RideStats getAllTimeStats(int driverId) throws DBException{
		return rideStatsDAO.getAllTimeStats(driverId);
	}
	
	public RideStats getLastMonthStats(int driverId) throws DBException {
		return rideStatsDAO.getLastMonthStats(driverId);
	}
	
	public RideStats getLastWeekStats(int driverId) throws DBException {
		return rideStatsDAO.getLastWeekStats(driverId);
	}
	
	public RideStats get24HoursStats(int driverId) throws DBException {
		return rideStatsDAO.get24HoursStats(driverId);
	}
}
