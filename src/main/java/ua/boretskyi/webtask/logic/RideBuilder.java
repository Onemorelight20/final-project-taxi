package ua.boretskyi.webtask.logic;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import ua.boretskyi.webtask.dao.entity.Car;
import ua.boretskyi.webtask.dao.entity.Ride;
import ua.boretskyi.webtask.dao.entity.User;
import ua.boretskyi.webtask.dao.entity.Car.Type;

public class RideBuilder {
	private String placeFrom;
	private String placeTo;
	private int numberOfPassengers;
	private Car.Type typeOfCar;
	private String comment;

	public RideBuilder(String placeFrom, String placeTo, int numberOfPassengers, Type typeOfCar, String comment) {
		this.placeFrom = placeFrom;
		this.placeTo = placeTo;
		this.numberOfPassengers = numberOfPassengers;
		this.typeOfCar = typeOfCar;
		this.comment = comment;
	}

	public String getPlaceFrom() {
		return placeFrom;
	}

	public void setPlaceFrom(String placeFrom) {
		this.placeFrom = placeFrom;
	}

	public String getPlaceTo() {
		return placeTo;
	}

	public void setPlaceTo(String placeTo) {
		this.placeTo = placeTo;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	public Car.Type getTypeOfCar() {
		return typeOfCar;
	}

	public void setTypeOfCar(Car.Type typeOfCar) {
		this.typeOfCar = typeOfCar;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean hasMoreThanFourPassengers() {
		return numberOfPassengers > 4;
	}

	public double calculatePrice() {
		double carClassBasedPayment = 0;
		if (typeOfCar == Car.Type.ECONOM)
			carClassBasedPayment = 30;
		else if (typeOfCar == Type.MINIBUS)
			carClassBasedPayment = 50;
		else if (typeOfCar == Type.LUXURY)
			carClassBasedPayment = 80;

		return 30 + carClassBasedPayment + numberOfPassengers * 2 * 0.1 * carClassBasedPayment;
	}

	// For cases when we need several cars of certain type for big amount of
	// passengers
	public static double calculatePrice(int passengers, Car.Type type) {
		double carClassBasedPayment = 0;
		if (type == Car.Type.ECONOM)
			carClassBasedPayment = 30;
		else if (type == Type.MINIBUS)
			carClassBasedPayment = 50;
		else if (type == Type.LUXURY)
			carClassBasedPayment = 80;

		return 30 + carClassBasedPayment + passengers * 2 * 0.1 * carClassBasedPayment;
	}

	@Override
	public String toString() {
		return "RideBean [placeFrom=" + placeFrom + ", placeTo=" + placeTo + ", numberOfPassengers="
				+ numberOfPassengers + ", typeOfCar=" + typeOfCar + ", comment=" + comment + "]";
	}

	// (String from, String to, int peopleInRide, Timestamp expectedFinishTime,
	// Timestamp expectedFinishTime,
	// int carId, int userId, int driverId, double price)

	public Ride buildRide(Car car, User user) {
		TimeManager timeManager = new TimeManager();

		Ride ride = new Ride(getPlaceFrom(), getPlaceTo(), getNumberOfPassengers(), timeManager.calculateStartTime(),
				timeManager.calculateFinishTime(), car.getId(), user.getId(), car.getDriverId(), calculatePrice());

		return ride;
	}

	public Ride buildRide(int passengers, Car car, User user) {
		TimeManager timeManager = new TimeManager();

		Ride ride = new Ride(getPlaceFrom(), getPlaceTo(), passengers, timeManager.calculateStartTime(),
				timeManager.calculateFinishTime(), car.getId(), user.getId(), car.getDriverId(),
				calculatePrice(passengers, car.getType()));

		return ride;
	}

}
