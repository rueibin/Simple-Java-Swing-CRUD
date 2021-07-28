package com.rueibin.test;

import com.rueibin.dao.EmployeeDAO;
import com.rueibin.dao.impl.EmployeeDAOImpl;

public class EmployeeTest {

	public static void main(String[] args) {
		getEmployees();
	}
	
	public static void getEmployees() {
		EmployeeDAO d=new EmployeeDAOImpl();
		System.out.println(d.getEmployees());
	}

}
