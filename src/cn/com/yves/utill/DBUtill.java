package cn.com.yves.utill;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 
 * ClassName: ConnectionUtils
 * 
 * @Description: get connection
 * 
 * @author Yves He
 * @date 2016-10-7
 */
public class DBUtill {
	private static String driverClass;
	private static String url;
	private static String userName;
	private static String pwd;

	static {
		// read propertiesFile
		Properties pro = new Properties();
		// attention :db.properties in src Dir
		InputStream iStream = DBUtill.class.getClassLoader()
				.getResourceAsStream("db.properties");
		try {
			pro.load(iStream);
			driverClass = pro.getProperty("driverClass");
			url = pro.getProperty("url");
			userName = pro.getProperty("user");
			pwd = pro.getProperty("password");
			// load the class
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get connecton obj
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, userName, pwd);
		} catch (SQLException e) {
			throw e;
		}
		return conn;
	}

	/**
	 * close the connection
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	public static void close(Connection conn) throws SQLException {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
	}

	/**
	 * close the connection
	 * 
	 * @param conn
	 * @param stm
	 * @param rs
	 * @throws SQLException
	 */
	public static void close(Connection conn, Statement stm, ResultSet rs)
			throws SQLException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stm != null) {
				stm.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw e;
		}
	}

}
