package com.aim.app.model;



public class User {
	private int id, roleId;
	private String email;
	private String pass;
	private Role role  = new Role();
	private boolean active;
	private String lastName, firstName, address, city, gender, phone, longCor, latCor;
	byte[] photo;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param id
	 * @param email
	 * @param pass
	 * @param role
	 * @param active
	 * @param lastName
	 * @param firstName
	 * @param address
	 * @param city
	 * @param gender
	 * @param phone
	 * @param photo
	 * @param longCor
	 * @param latCor
	 */
	public User(String email, String pass) {
		this.email = email;
		this.pass = pass;
	}

	public User(String email) {
		this.email = email;
	}

	public User(int id, String email, String pass, Role role, boolean active, String lastName, String firstName,
			String address, String city, String gender, String phone, byte[] photo, String longCor, String latCor) {
		super();
		this.id = id;
		this.email = email;
		this.pass = pass;
		this.role = role;
		this.active = active;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.city = city;
		this.gender = gender;
		this.phone = phone;
		this.photo = photo;
		this.longCor = longCor;
		this.latCor = latCor;
	}

	/**
	 * @param id
	 */
	public User(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getLongCor() {
		return longCor;
	}

	public void setLongCor(String longCor) {
		this.longCor = longCor;
	}

	public String getLatCor() {
		return latCor;
	}

	public void setLatCor(String latCor) {
		this.latCor = latCor;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", pass=" + pass + ", role=" + role + ", active=" + active
				+ ", lastName=" + lastName + ", firstName=" + firstName + ", address=" + address + ", city=" + city
				+ ", gender=" + gender + ", phone=" + phone + ", longCor=" + longCor + ", latCor="
				+ latCor + "]";
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	
}
