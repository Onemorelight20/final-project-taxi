package ua.boretskyi.webtask.dao;

import java.util.List;

import ua.boretskyi.webtask.dao.entity.Car;

public interface CarDAO {
	
	void createCar(Car car);
	
	Car findCar(int carId);
	
	void updateCar(Car car);
	
	void deleteCar(int carId);
	
	Car findCar(Car car);
	
	List<Car> findAll();
	
	List<Car> findSpecificCars(int minSeatsAvailable);
}
