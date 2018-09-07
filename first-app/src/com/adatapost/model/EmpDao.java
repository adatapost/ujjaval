package com.adatapost.model;

import java.util.ArrayList;
import java.util.List;

import com.adatapost.Db;
import com.adatapost.U;

public class EmpDao {
	public static boolean add(Emp emp) {
		String sql = "insert into emp (emp_name,emp_salary,join_date) values (?,?,?)";
		try (Db x = new Db(sql)) {
			x.getPs().setString(1, emp.getEmpName());
			x.getPs().setInt(2, emp.getEmpSalary());
			x.getPs().setDate(3, U.utilToSql(emp.getJoinDate()));
			return x.execute();
		} catch (Exception ex) {
			System.out.println("Error in add : " + ex);
		}
		return false;
	}

	public static boolean update(Emp emp) {
		String sql = "update emp set emp_name=?,emp_salary=?,join_date=? where emp_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setString(1, emp.getEmpName());
			x.getPs().setInt(2, emp.getEmpSalary());
			x.getPs().setDate(3, U.utilToSql(emp.getJoinDate()));
			x.getPs().setInt(4, emp.getId());
			return x.execute();
		} catch (Exception ex) {
			System.out.println("Error in update : " + ex);
		}
		return false;
	}

	public static boolean delete(Emp emp) {
		String sql = "delete from emp where emp_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setInt(1, emp.getId());
			return x.execute();
		} catch (Exception ex) {
			System.out.println("Error in delete : " + ex);
		}
		return false;
	}

	public static Emp get(Emp emp) {
		String sql = "select * from emp where emp_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setInt(1, emp.getId());
			Object[] ar = x.row();
			if (ar != null) {
				Emp temp = new Emp();
				temp.setId((Integer) ar[0]);
				temp.setEmpName((String) ar[1]);
				temp.setEmpSalary((Integer) ar[2]);
				temp.setJoinDate((java.util.Date) ar[3]);
				return temp;
			}
		} catch (Exception ex) {
			System.out.println("Error in get : " + ex);
		}
		return null;
	}

	public static List<Emp> gets() {
		List<Emp> list = new ArrayList<>();
		String sql = "select * from emp";
		try (Db x = new Db(sql)) {
			for (Object[] ar : x.rows()) {
				Emp temp = new Emp();
				temp.setId((Integer) ar[0]);
				temp.setEmpName((String) ar[1]);
				temp.setEmpSalary((Integer) ar[2]);
				temp.setJoinDate((java.util.Date) ar[3]);
				list.add(temp);
			}
		} catch (Exception ex) {
			System.out.println("Error in gets : " + ex);
		}
		return list;
	}
}
