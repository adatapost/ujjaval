package com.aim.app.model;

public class Supplier {
	
	private int id;
	private String name, city, address, phone;
	private User user;
	/**
	 * 
	 */
	public Supplier() {
		super();
		user = new User();
	}
	/**
	 * @param id
	 * @param name
	 * @param city
	 * @param address
	 * @param phone
	 * @param user
	 */
	public Supplier(int id, String name, String city, String address, String phone, User user) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.phone = phone;
		this.user = user;
	}
	/**
	 * @param id
	 */
	public Supplier(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", city=" + city + ", address=" + address + ", phone=" + phone
				+ ", user=" + user + "]";
	}
	
}
