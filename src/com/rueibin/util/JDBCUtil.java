package com.rueibin.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	private static Properties props = new Properties();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	static {
		try {
			InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("dbconfig.properties");
			props.load(in);
			driver = props.getProperty("jdbc.driver");
			url = props.getProperty("jdbc.url");
			username = props.getProperty("jdbc.username");
			password = props.getProperty("jdbc.password");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConn() throws Exception {
		Connection conn = tl.get();
		if (conn == null) {
			conn = DriverManager.getConnection(url, username, password);
			tl.set(conn);
		}
		return conn;
	}

	public static void closeConn(){
		Connection conn = tl.get();
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tl.set(null);
	}

	public static void close(PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
