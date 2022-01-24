package ua.boretskyi.webtask.logic;

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

	public List<Car> findAll() throws DBException {
		return carDAO.findAll();
	}

	public List<Car> findSpecificCars(int seats, Type type) throws DBException {
		return findAll().stream().filter(car -> car.getSeatsAvailable() >= seats).filter(car -> car.getType() == type)
				.collect(Collectors.toList());
	}

	public List<Car> findAllAvailableCars() throws DBException {
		return findAll().stream().filter(car -> car.getStatus() == Status.AVAILABLE).collect(Collectors.toList());
	}
}
