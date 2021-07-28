package com.rueibin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rueibin.dao.EmployeeDAO;
import com.rueibin.entity.Department;
import com.rueibin.entity.Employee;
import com.rueibin.util.JDBCUtil;

public class EmployeeDAOImpl implements EmployeeDAO{

	@Override
	public List<Employee> getEmployees() {
		List<Employee> emps = new ArrayList<Employee>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = JDBCUtil.getConn();
			String sql = "select * from employee";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setGender(rs.getInt("gender"));
				emp.setEmail(rs.getString("email"));
				emps.add(emp);
			}
			return emps;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn();
			JDBCUtil.close(ps);
			JDBCUtil.close(rs);
		}
		return null;
	}

	@Override
	public Employee getEmployeeByName(String name) {
		return null;
	}

	@Override
	public int save(Employee emp) {
		return 0;
	}

	@Override
	public int update(Employee emp) {
		return 0;
	}

	@Override
	public int delete(Employee id) {
		return 0;
	}
}
