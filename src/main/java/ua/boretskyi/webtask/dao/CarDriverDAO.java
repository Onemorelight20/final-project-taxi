package ua.boretskyi.webtask.dao;

import ua.boretskyi.webtask.dao.entity.CarDriver;

public interface CarDriverDAO {

	void createDriver(CarDriver driver);
	
	CarDriver findDriver(int carDriverId);
	
	void updateDriver(CarDriver driver);
	
	void deleteDriver(CarDriver drvier);
}
