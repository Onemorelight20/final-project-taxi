package ua.boretskyi.webtask.dao;

import ua.boretskyi.webtask.dao.entity.RideStats;
import ua.boretskyi.webtask.logic.DBException;

public interface RideStatsDAO {
	
	RideStats getAllTimeStats() throws DBException;
	
	RideStats getLastMonthStats() throws DBException;
	
	RideStats getLastWeekStats() throws DBException;
	
	RideStats get24HoursStats() throws DBException;
	
	RideStats getAllTimeStats(int driverId) throws DBException;
	
	RideStats getLastMonthStats(int driverId) throws DBException;
	
	RideStats getLastWeekStats(int driverId) throws DBException;
	
	RideStats get24HoursStats(int driverId) throws DBException;
}
