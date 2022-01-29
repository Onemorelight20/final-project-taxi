package ua.boretskyi.webtask.dao;

import java.util.List;

import ua.boretskyi.webtask.dao.entity.Ride;
import ua.boretskyi.webtask.logic.DBException;

public interface RideDAO {
	void createRide(Ride ride) throws DBException;
	
	void createRides(Ride ...rides) throws DBException;
	
	Ride findRide(int rideId) throws DBException;
	
	void updateRide(int rideId, Ride ride) throws DBException;
	
	void updateRide(Ride ride) throws DBException;
	
	void deleteRide(Ride ride) throws DBException;
	
	List<Ride> findAll() throws DBException;
}
