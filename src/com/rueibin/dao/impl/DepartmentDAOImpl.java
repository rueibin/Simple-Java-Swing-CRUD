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

	@Override
	public List<Department> getDepartments() {
		List<Department> depts = new ArrayList<Department>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = JDBCUtil.getConn();
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
			JDBCUtil.closeConn();
			JDBCUtil.close(ps);
			JDBCUtil.close(rs);
		}
		return null;
	}

}
