package com.aim.app.model;

public class Medicine {
	private int id;
	private String name;
	private boolean available, isPrescribed;
	private String unit;
	private double reorderLevel;
	private User user; //foreign key(shop_admin_id) references user(user_id)
	private Category category;//foreign key(category_id) references category(id)
	private Company company;//foreign key(company_id) references company(id)
	
	/**
	 * 
	 */
	public Medicine() {
		super();
		user = new User();
		category = new Category();
		company = new Company();
	}
	/**
	 * @param id
	 */
	public Medicine(int id) {
		super();
		this.id = id;
	}
	/**
	 * @param id
	 * @param name
	 * @param available
	 * @param isPrescribed
	 * @param unit
	 * @param reorderLevel
	 * @param user
	 * @param category
	 * @param company
	 */
	public Medicine(int id, String name, boolean available, boolean isPrescribed, String unit, double reorderLevel,
			User user, Category category, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.available = available;
		this.isPrescribed = isPrescribed;
		this.unit = unit;
		this.reorderLevel = reorderLevel;
		this.user = user;
		this.category = category;
		this.company = company;
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
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public boolean isPrescribed() {
		return isPrescribed;
	}
	public void setPrescribed(boolean isPrescribed) {
		this.isPrescribed = isPrescribed;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getReorderLevel() {
		return reorderLevel;
	}
	public void setReorderLevel(double reorderLevel) {
		this.reorderLevel = reorderLevel;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	@Override
	public String toString() {
		return "Medicine [id=" + id + ", name=" + name + ", available=" + available + ", isPrescribed=" + isPrescribed
				+ ", unit=" + unit + ", reorderLevel=" + reorderLevel + ", user=" + user + ", category=" + category
				+ ", company=" + company + "]";
	}
	
}
