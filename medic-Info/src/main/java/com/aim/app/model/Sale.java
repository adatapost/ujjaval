package com.aim.app.model;

import java.sql.Date;

public class Sale {
	
	private int id;
	private Date saleDate;
	private Customer customer;
	private Doctor doctor;
	private User user;

	/*foreign key(customer_id) references customer(id),
	  foreign key(doctor_id) references doctor(id),
	foreign key(shop_admin_id) references user(user_id)*/
	/**
	 * 
	 */
	public Sale() {
		super();
		customer = new Customer();
		doctor = new Doctor();
		user = new User();
	}
	
	/**
	 * @param id
	 */
	public Sale(int id) {
		super();
		this.id = id;
	}
	


	/**
	 * @param id
	 * @param saleDate
	 * @param customer
	 * @param doctor
	 * @param user
	 */
	public Sale(int id, Date saleDate, Customer customer, Doctor doctor, User user) {
		super();
		this.id = id;
		this.saleDate = saleDate;
		this.customer = customer;
		this.doctor = doctor;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + ", salDate=" + saleDate + ", customer=" + customer + ", doctor=" + doctor + ", user="
				+ user + "]";
	}

	
}
