package ua.boretskyi.webtask.dao.entity;

import java.sql.Date;

public class Ride {
	private int rideId;
	private String placeFrom;
	private String placeTo;
	private int peopleInRide;
	private Date timeCreated;
	private Date expectedFinishTime;
	private int carId;
	private int userId;
	private String description;
	private double price;
	
	
	public int getRideId() {
		return rideId;
	}
	
	public void setRideId(int rideId) {
		this.rideId = rideId;
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
	
	public int getPeopleInRide() {
		return peopleInRide;
	}
	
	public void setPeopleInRide(int peopleInRide) {
		this.peopleInRide = peopleInRide;
	}
	
	public Date getTimeCreated() {
		return timeCreated;
	}
	
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}
	
	public Date getExpectedFinishTime() {
		return expectedFinishTime;
	}
	
	public void setExpectedFinishTime(Date expectedFinishTime) {
		this.expectedFinishTime = expectedFinishTime;
	}
	
	public int getCarId() {
		return carId;
	}
	
	public void setCarId(int carId) {
		this.carId = carId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
