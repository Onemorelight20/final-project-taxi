package ua.boretskyi.webtask.dao.entity;

public class RideStats {
	private int ridesAmount;
	private double avgPeople;
	private double avgPrice;
	private double summaryCost;
	
	public RideStats() {
	}

	public RideStats(int ridesAmount, double avgPeople, double avgPrice, double summaryCost) {
		this.ridesAmount = ridesAmount;
		this.avgPeople = avgPeople;
		this.avgPrice = avgPrice;
		this.summaryCost = summaryCost;
	}

	public int getRidesAmount() {
		return ridesAmount;
	}
	
	public void setRidesAmount(int ridesAmount) {
		this.ridesAmount = ridesAmount;
	}
	
	public double getAvgPeople() {
		return avgPeople;
	}
	
	public void setAvgPeople(double avgPeople) {
		this.avgPeople = avgPeople;
	}
	
	public double getAvgPrice() {
		return avgPrice;
	}
	
	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}
	
	
	public double getSummaryCost() {
		return summaryCost;
	}
	
	public void setSummaryCost(double summaryCost) {
		this.summaryCost = summaryCost;
	}
	
	
}
