package ua.boretskyi.webtask.dao.entity;

import ua.boretskyi.webtask.dao.entity.Car.Type;

public class RideBean {
	private String placeFrom;
	private String placeTo;
	private int numberOfPassengers;
	private Car.Type typeOfCar;
	private String comment;
	
	
	public RideBean(String placeFrom, String placeTo, int numberOfPassengers, Type typeOfCar, String comment) {
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
	
	public double calculatePrice() {
		double carClassBasedPayment = 0;
		if(typeOfCar == Car.Type.ECONOM)
			carClassBasedPayment = 30;
		else if(typeOfCar == Type.MINIBUS)
			carClassBasedPayment = 50;
		else if(typeOfCar == Type.LUXURY)
			carClassBasedPayment = 80;
		
		return 30 + carClassBasedPayment + numberOfPassengers*2;
	}

	@Override
	public String toString() {
		return "RideBean [placeFrom=" + placeFrom + ", placeTo=" + placeTo + ", numberOfPassengers="
				+ numberOfPassengers + ", typeOfCar=" + typeOfCar + ", comment=" + comment + "]";
	}
	
	
}
