package ua.boretskyi.webtask.logic;

import java.util.List;

import ua.boretskyi.webtask.dao.CarDAO;
import ua.boretskyi.webtask.dao.DAOFactory;
import ua.boretskyi.webtask.dao.RideDAO;
import ua.boretskyi.webtask.dao.entity.Car.Status;
import ua.boretskyi.webtask.dao.entity.Ride;

public class RideManager {
	private DAOFactory factory = DAOFactory.getInstance();
	private RideDAO rideDAO = factory.getRideDAO();
	private CarDAO carDAO = factory.getCarDAO();
	
	public void createRide(Ride ride) throws DBException {
		rideDAO.createRide(ride);
		carDAO.setCarStatus(ride.getCarId(), Status.BUSY);
	}
	
	public void createRides(Ride ...rides) throws DBException {
		rideDAO.createRides(rides);
	}
	
	public Ride findRide(int rideId) throws DBException {
		return rideDAO.findRide(rideId);
	}
	
	public void updateRide(Ride ride) throws DBException {
		rideDAO.updateRide(ride);
	}
	
	public void updateRide(int rideId, Ride ride) throws DBException {
		rideDAO.updateRide(rideId, ride);
	}
	
	public void deleteRide(Ride ride) throws DBException {
		rideDAO.deleteRide(ride);
	}
	
	public List<Ride> findAll() throws DBException {
		return rideDAO.findAll();
	}
	
	

}
