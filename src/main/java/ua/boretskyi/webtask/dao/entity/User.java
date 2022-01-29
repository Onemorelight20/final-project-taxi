package ua.boretskyi.webtask.dao.entity;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String name;
	private String phoneNumber;
	private String email;
	private String password;
	private Role role;
	private double spentMoney;
	
	public User() {
	}
	
	public User(String name, String phoneNumber, String email, String password) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setSpentMoney(double amountOfMoney) {
		
		spentMoney = amountOfMoney;
	}
	
	public double getSpentMoney() {
		return spentMoney;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}
	
	public enum Role{
		CLIENT,
		ADMIN,
		DRIVER;
		
		@Override public String toString() {
			return this.name().toLowerCase();
		}
	}
}
