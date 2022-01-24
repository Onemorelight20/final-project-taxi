package ua.boretskyi.webtask.dao;

import java.util.List;

import ua.boretskyi.webtask.dao.entity.Car;
import ua.boretskyi.webtask.logic.DBException;

public interface CarDAO {
	
	void createCar(Car car)  throws DBException;
	
	Car findCar(int carId) throws DBException;
	
	void updateCar(Car car) throws DBException;
	
	void updateCar(int id, Car car) throws DBException;
	
	void deleteCar(int carId) throws DBException;
	
	Car findCar(Car car) throws DBException;
	
	List<Car> findAll() throws DBException;
}
