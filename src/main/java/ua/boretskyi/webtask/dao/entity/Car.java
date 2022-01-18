package ua.boretskyi.webtask.dao.entity;

public class Car {
	private int id;
	private String model;
	private CarStatus status;
	private int driverId;
	private int seatsAvailable;
	private CarType type;
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public CarStatus getStatus() {
		return status;
	}
	
	public void setStatus(CarStatus status) {
		this.status = status;
	}
	
	public int getDriverId() {
		return driverId;
	}
	
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	
	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	
	public CarType getType() {
		return type;
	}
	
	public void setType(CarType type) {
		this.type = type;
	}
	
	
}


enum CarStatus{
	AVAILABLE,
	IN_RIDE,
	FULLY_UNAVAILABLE
}

enum CarType{
	ECONOM,
	LUXURY,
	MINIBUS
}