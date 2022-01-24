package ua.boretskyi.webtask.logic;

import java.sql.Date;

import ua.boretskyi.webtask.dao.entity.Car;
import ua.boretskyi.webtask.dao.entity.Ride;
import ua.boretskyi.webtask.dao.entity.RideBean;
import ua.boretskyi.webtask.dao.entity.User;

public class RideBuilder {
	//(String from, String to, int peopleInRide, Date expectedFinishTime, 
	//int carId, int userId, int driverId, double price)
	
	public Ride buildRide(RideBean bean, Car car, User user) {
		Ride ride = new Ride(bean.getPlaceFrom(), bean.getPlaceTo(), bean.getNumberOfPassengers(), 
				new Date(System.currentTimeMillis()), car.getId(), user.getId(), car.getDriverId(), bean.calculatePrice());
		
		return ride;
	}
}
