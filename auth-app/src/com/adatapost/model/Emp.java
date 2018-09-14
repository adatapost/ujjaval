package com.adatapost.model;

import java.util.Date;

/*
 * 
 * create table emp
(
    emp_id int PRIMARY key auto_increment,
    emp_name varchar(100) not null,
    emp_salary int,
    join_date date 
    )

 */
public class Emp {
	private Integer id;
	private String empName;
	private int empSalary;
	private java.util.Date joinDate;

	public Emp() {
	}

	public Emp(Integer id, String empName, int empSalary, Date joinDate) {
		super();
		this.id = id;
		this.empName = empName;
		this.empSalary = empSalary;
		this.joinDate = joinDate;
	}

	public Emp(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(int empSalary) {
		this.empSalary = empSalary;
	}

	public java.util.Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(java.util.Date joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emp other = (Emp) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Emp [id=" + id + ", empName=" + empName + ", empSalary=" + empSalary + ", joinDate=" + joinDate + "]";
	}

}
