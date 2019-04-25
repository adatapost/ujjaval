package com.aim.app.model;

public class Category {
	private int id;
	private String name;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Category(int id) {
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
	

}
