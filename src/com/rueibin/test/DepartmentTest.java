package com.rueibin.test;

import com.rueibin.dao.DepartmentDAO;
import com.rueibin.dao.impl.DepartmentDAOImpl;

public class DepartmentTest {
	public static void main(String[] args) {
		getDept();
	}

	private static void getDept() {
		DepartmentDAO d = new DepartmentDAOImpl();
		System.out.println(d.getDepartments());
	}
}
