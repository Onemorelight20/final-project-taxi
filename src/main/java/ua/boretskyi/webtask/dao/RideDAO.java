package ua.boretskyi.webtask.dao;

import ua.boretskyi.webtask.dao.entity.Ride;

public interface RideDAO {
	void createRide(Ride ride);
	
	Ride findRide(int rideId);
	
	void updateRide(int rideId);
	
	void updateRide(Ride ride);
	
	void deleteRide(Ride ride);
}
