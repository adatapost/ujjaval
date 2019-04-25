package com.aim.app.model;

public class Company {
	private int id;
	private String name;
	private String address;
	private String city;
	private String website;
	private String phone;
	/**
	 * 
	 */
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param name
	 * @param address
	 * @param city
	 * @param website
	 */
	public Company(int id, String name, String address, String city, String website) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.website = website;
	}
	
	/**
	 * @param id
	 */
	public Company(int id) {
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city + ", website="
				+ website + ", phone=" + phone + "]";
	}
}

