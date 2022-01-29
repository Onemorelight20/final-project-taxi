package ua.boretskyi.webtask.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ua.boretskyi.webtask.dao.CarDAO;
import ua.boretskyi.webtask.dao.DAOFactory;
import ua.boretskyi.webtask.dao.entity.Car;
import ua.boretskyi.webtask.dao.entity.Car.Status;
import ua.boretskyi.webtask.dao.entity.Car.Type;

public class CarManager {
	private DAOFactory factory = DAOFactory.getInstance();
	private CarDAO carDAO = factory.getCarDAO();

	public void createCar(Car car) throws DBException {
		carDAO.createCar(car);
	}

	public Car findCar(int carId) throws DBException {
		return carDAO.findCar(carId);
	}

	public void updateCar(Car car) throws DBException {
		carDAO.updateCar(car);
	}

	public void updateCar(int id, Car car) throws DBException {
		carDAO.updateCar(id, car);
	}

	public void deleteCar(int carId) throws DBException {
		carDAO.deleteCar(carId);
	}

	public Car findCar(Car car) throws DBException {
		return carDAO.findCar(car);
	}

	public void setCarStatus(int id, Status status) throws DBException {
		carDAO.setCarStatus(id, status);
	}
	
	public List<Car> findAll() throws DBException {
		return carDAO.findAll();
	}

	/**
	 * 
	 * @param seats
	 * @param type
	 * @return Required list or empty in case there are no suitable cars with that amount of seats of that type
	 * @throws DBException
	 */
	public List<Car> findSpecificCars(int seats, Type type) throws DBException {
		return findAll().stream()
				.filter(car -> car.getSeatsAvailable() >= seats)
				.filter(car -> car.getType() == type)
				.filter(car -> car.getStatus() == Status.AVAILABLE)
				.collect(Collectors.toList());
	}

	public List<Car> findAllAvailableCarsWithSeatsAvailable(int seats) throws DBException {
		return findAll().stream()
				.filter(car -> car.getStatus() == Status.AVAILABLE)
				.filter(car -> car.getSeatsAvailable() >= seats)
				.collect(Collectors.toList());
	}
	
	public List<Car> findSeveralAvailableCarOfSelectedType(int people, Car.Type type) throws DBException{
		List<Car> carsOfNeededType = findAll().stream()
				.filter(car -> car.getType() == type)
				.filter(car -> car.getStatus() == Car.Status.AVAILABLE)
				.collect(Collectors.toList()); 
		List<Car> resultPack = new ArrayList<>();
		int peopleRemaining = people;
		for(Car car : carsOfNeededType) {
			resultPack.add(car);
			peopleRemaining -= car.getSeatsAvailable();
			if(peopleRemaining <= 0) {
				break;
			}
		}
		
		if(peopleRemaining > 0)
		return null;
		else 
		return resultPack;
	}
}
