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

public class EmployeeDAOImpl implements EmployeeDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public List<Employee> getEmployees() {
		List<Employee> emps = new ArrayList<Employee>();
		try {
			conn = JDBCUtil.getConn();
			String sql = "select e.id, e.name, e.gender, e.email, e.dept_id, d.name as dept_name from employee e,department d where e.dept_id=d.id";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				Department dept=new Department();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setGender(rs.getInt("gender"));
				emp.setEmail(rs.getString("email"));
				emp.setDeptId(rs.getInt("dept_id"));
				dept.setId(emp.getDeptId());
				dept.setName(rs.getString("dept_name"));
				emp.setDept(dept);
				emps.add(emp);
			}
			return emps;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(ps, rs);
		}
		return null;
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		List<Employee> emps = new ArrayList<Employee>();
		try {
			conn = JDBCUtil.getConn();
			String sql = "select e.id, e.name, e.gender, e.email, e.dept_id, d.name as dept_name from employee e,department d where e.dept_id=d.id and e.name like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+name+"%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				Department dept=new Department();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setGender(rs.getInt("gender"));
				emp.setEmail(rs.getString("email"));
				emp.setDeptId(rs.getInt("dept_id"));
				dept.setId(emp.getDeptId());
				dept.setName(rs.getString("dept_name"));
				emp.setDept(dept);
				emps.add(emp);
			}
			return emps;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(ps, rs);
		}
		return null;
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		List<Employee> emps = new ArrayList<Employee>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = JDBCUtil.getConn();
			String sql = "select e.id, e.name, e.gender, e.email, e.dept_id, d.name as dept_name from employee e,department d where e.dept_id=d.id and e.id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Employee emp = new Employee();
			Department dept=new Department();
			while (rs.next()) {
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setGender(rs.getInt("gender"));
				emp.setEmail(rs.getString("email"));
				emp.setDeptId(rs.getInt("dept_id"));
				dept.setId(emp.getDeptId());
				dept.setName(rs.getString("dept_name"));
				emp.setDept(dept);
				emps.add(emp);
			}
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(ps, rs);

		}
		return null;
	}

	@Override
	public int save(Employee emp) {
		int num = 0;
		try {
			conn = JDBCUtil.getConn();
			String sql = "insert into employee(name, gender, email, dept_id) values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, emp.getName());
			ps.setInt(2, emp.getGender());
			ps.setString(3, emp.getEmail());
			ps.setInt(4, emp.getDeptId());
			num = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(ps, rs);
		}
		return num;
	}

	@Override
	public int update(Employee emp) {
		int num = 0;
		try {
			conn = JDBCUtil.getConn();
			String sql = "update employee set name=?, gender=?, email=?, dept_id=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, emp.getName());
			ps.setInt(2, emp.getGender());
			ps.setString(3, emp.getEmail());
			ps.setInt(4, emp.getDeptId());
			ps.setInt(5, emp.getId());
			num = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(ps, rs);
		}
		return num;
	}

	@Override
	public int delete(Integer id) {
		int num = 0;
		try {
			conn = JDBCUtil.getConn();
			String sql = "delete from employee where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			num = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(ps, rs);
		}
		return num;
	}

}
