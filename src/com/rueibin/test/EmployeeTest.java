package com.rueibin.test;

import com.rueibin.dao.EmployeeDAO;
import com.rueibin.dao.impl.EmployeeDAOImpl;
import com.rueibin.entity.Department;
import com.rueibin.entity.Employee;

public class EmployeeTest {
	private static EmployeeDAO d = new EmployeeDAOImpl();

	public static void main(String[] args) {
//		getEmployees();
//		getEmployeeById(1);
//		getEmployeeByName("t");
		
		Employee emp = new Employee();
		emp.setId(17);
		emp.setName("hhh");
		emp.setGender(1);
		emp.setEmail("abc@abc.com.tw");
		emp.setDeptId(2);
		
//		save(emp);
		update(emp);
	}

	public static void getEmployees() {
		System.out.println(d.getEmployees());
	}

	public static void getEmployeeByName(String name) {
		System.out.println(d.getEmployeeByName(name));
	}

	public static void getEmployeeById(Integer id) {
		System.out.println(d.getEmployeeById(id));
	}

	public static void save(Employee emp) {
		System.out.println(d.save(emp));
	}

	public static void update(Employee emp) {
		System.out.println(d.update(emp));
	}
}
