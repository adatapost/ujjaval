package com.aim.app.model;

public class Customer {

	private int id;
	private String name, address, phone;

	private User user;

	/**
	 * 
	 */
	public Customer() {
		super();
		user = new User();
	}

	/**
	 * @param id
	 * @param name
	 * @param address
	 * @param phone
	 * @param user
	 */
	public Customer(int id, String name, String address, String phone, User user) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.user = user;
	}

	/**
	 * @param id
	 */
	public Customer(int id) {
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
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", user=" + user
				+ "]";
	}
	
}
