package com.aim.app.model;

public class SaleDetail {
	
	private int detId;
	private double qty;
	private double rate;
	private double discount;
	
	private Sale sale;
	private Medicine medicine;
	private User user;
	/*foreign key(sale_id) references sale(id),
	foreign key(medicine_id) references medicine(id),
	foreign key(shop_admin_id) references user(user_id)*/
	
	
	/**
	 * 
	 */
	public SaleDetail() {
		super();
		sale = new Sale();
		medicine = new Medicine();
		user = new User();
	}

	/**
	 * @param detId
	 */
	public SaleDetail(int detId) {
		super();
		this.detId = detId;
	}

	/**
	 * @param detId
	 * @param qty
	 * @param rate
	 * @param discount
	 * @param sale
	 * @param medicine
	 * @param user
	 */
	public SaleDetail(int detId, double qty, double rate, double discount, Sale sale, Medicine medicine, User user) {
		super();
		this.detId = detId;
		this.qty = qty;
		this.rate = rate;
		this.discount = discount;
		this.sale = sale;
		this.medicine = medicine;
		this.user = user;
	}

	public int getDetId() {
		return detId;
	}

	public void setDetId(int detId) {
		this.detId = detId;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "SaleDetail [detId=" + detId + ", qty=" + qty + ", rate=" + rate + ", discount=" + discount + ", sale="
				+ sale + ", medicine=" + medicine + ", user=" + user + "]";
	}

}
