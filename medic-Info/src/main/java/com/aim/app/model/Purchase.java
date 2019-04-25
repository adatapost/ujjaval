package com.aim.app.model;

import java.sql.Date;

public class Purchase {
	private int id;
	private Date purDate;
	private Supplier supplier;
	private User user;
	//still remain FK  
	//	foreign key(supplier_id) references supplier(id),
	//	foreign key(shop_admin_id) references user(user_id)
	/**
	 * 
	 */
	public Purchase() {
		super();
		supplier = new Supplier();
		user = new User();
	}
	/**
	 * @param id
	 * @param purDate
	 * @param supplier
	 * @param user
	 */
	public Purchase(int id, Date purDate, Supplier supplier, User user) {
		super();
		this.id = id;
		this.purDate = purDate;
		this.supplier = supplier;
		this.user = user;
	}
	
	/**
	 * @param id
	 */
	public Purchase(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getPurDate() {
		return purDate;
	}
	public void setPurDate(Date purDate) {
		this.purDate = purDate;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Purchase [id=" + id + ", purDate=" + purDate + ", supplier=" + supplier + ", user=" + user + "]";
	}
	
}
