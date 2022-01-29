package ua.boretskyi.webtask.dao.entity;

public class Car {
	private int id;
	private String model;
	private Status status;
	private int driverId;
	private int seatsAvailable;
	private Type type;
	
	
	public Car() {
		
	}
	
	public Car(String model, Status status, int driverId, int seatsAvailable, Type type) {
		this.model = model;
		this.status = status;
		this.driverId = driverId;
		this.seatsAvailable = seatsAvailable;
		this.type = type;
	}
	
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
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
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
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	

	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + ", status=" + status + ", driverId=" + driverId
				+ ", seatsAvailable=" + seatsAvailable + ", type=" + type + "]";
	}
	
	

	public static enum Status{
		AVAILABLE,
		BUSY,
		UNAVAILABLE;
		
		@Override public String toString() {
			  return this.name().toLowerCase();
		}
	}

	public static enum Type{
		ECONOM,
		LUXURY,
		MINIBUS;
		
		@Override public String toString() {
			  return this.name().toLowerCase();
		}
	}
}
