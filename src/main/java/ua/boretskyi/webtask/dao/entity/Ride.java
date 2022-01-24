package ua.boretskyi.webtask.dao.entity;

import java.sql.Date;

public class Ride {
	private int rideId;
	private String placeFrom;
	private String placeTo;
	private Status status;
	private int peopleInRide;
	private Date timeCreated;
	private Date expectedFinishTime;
	private int carId;
	private int userId;
	private int driverId;
	private String description;
	private double price;
	
	public Ride() {
		
	}
	
	
	public Ride(String from, String to, int peopleInRide, Date expectedFinishTime, 
			int carId, int userId, int driverId, double price) {
		placeFrom = from;
		placeTo = to;
		this.peopleInRide = peopleInRide;
		this.expectedFinishTime = expectedFinishTime;
		this.carId = carId;
		this.userId = userId;
		this.driverId = driverId;
		this.price = price;
		this.status = Status.CREATED;
	}
	
	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


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
	
	public int getDriverId() {
		return driverId;
	
	}
	
	public void setDriverId(int driverId) {
		this.driverId = driverId;
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

	@Override
	public String toString() {
		return "Ride [rideId=" + rideId + ", placeFrom=" + placeFrom + ", placeTo=" + placeTo + ", peopleInRide="
				+ peopleInRide + ", timeCreated=" + timeCreated + ", expectedFinishTime=" + expectedFinishTime
				+ ", carId=" + carId + ", userId=" + userId + ", driverId=" + driverId + ", description=" + description
				+ ", price=" + price + "]";
	}
	
	
	public enum Status {
		CREATED,
		SUBMITTED,
		FINISHED
	}
}
