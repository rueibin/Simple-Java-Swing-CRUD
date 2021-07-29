package com.rueibin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rueibin.dao.DepartmentDAO;
import com.rueibin.entity.Department;
import com.rueibin.util.JDBCUtil;

public class DepartmentDAOImpl implements DepartmentDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public List<Department> getDepartments() {
		List<Department> depts = new ArrayList<Department>();
		try {
			conn = JDBCUtil.getConn();
			String sql = "select * from department";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Department dept = new Department();
				dept.setId(rs.getInt("id"));
				dept.setName(rs.getString("name"));
				depts.add(dept);
			}
			return depts;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(ps, rs);
		}
		return null;
	}
}
