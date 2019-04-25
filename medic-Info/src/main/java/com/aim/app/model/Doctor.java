package com.aim.app.model;

public class Doctor {
	
	private int id;
	private String name, address, phone;
	
	
	/**
	 * 
	 */
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param name
	 * @param address
	 * @param phone
	 */
	public Doctor(int id, String name, String address, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	/**
	 * @param id
	 */
	public Doctor(int id) {
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
	@Override
	public String toString() {
		return "doctor [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + "]";
	}
}
