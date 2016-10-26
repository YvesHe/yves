package cn.com.yves.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.yves.bean.UserBean;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.utill.DBUtill;
import cn.com.yves.utill.ReflectUtill;

public class UserDao implements UserDaoInf {

	public List<UserBean> listAllUserBean() throws SQLException {
		List<UserBean> userList = new ArrayList<UserBean>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		final String sql = "select * from t_user";
		try {
			conn = DBUtill.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			UserBean uBean = null;
			while (rs.next()) {
				uBean = new UserBean();
				uBean.setUserId((rs.getString("userId")));
				uBean.setUserName(rs.getString("userName"));
				uBean.setUsrPwd(rs.getString("userName"));
				userList.add(uBean);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtill.close(conn, pstm, rs);
		}
		return userList;
	}

	public UserBean getUserBeanById(String userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		UserBean uBean = null;
		String sql = "select * from t_user where userId = ?";
		try {
			conn = DBUtill.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				uBean = new UserBean();
				uBean.setUserId(rs.getString("userId"));
				uBean.setUserName(rs.getString("userName"));
				uBean.setUsrPwd(rs.getString("userPwd"));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtill.close(conn, pstm, rs);
		}
		return uBean;
	}

	public boolean addUserBean(UserBean uBean) throws SQLException {
		boolean bool = false;
		Connection conn = null;
		PreparedStatement pstm = null;
		// userBean的id如何自增.
		final String sql = "insert into t_user values(123,?,?)";
		conn = DBUtill.getConnection();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, uBean.getUserName());
			pstm.setString(2, uBean.getUsrPwd());
			int result = pstm.executeUpdate();
			if (result != -1) {
				bool = true;
			}
		} catch (SQLException e) {
			throw e;
			// 新增失败回滚。
		} finally {
			DBUtill.close(conn, pstm, null);
		}
		return bool;
	}

	public boolean deleteUserBean(String userId) throws SQLException {
		boolean bool = false;
		Connection conn = null;
		PreparedStatement pstm = null;
		final String sql = "delete from t_user where userId=?";
		try {
			conn = DBUtill.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			int result = pstm.executeUpdate();
			if (result != -1) {
				bool = true;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtill.close(conn, pstm, null);
		}
		return bool;
	}

	public boolean updateUserBean(UserBean userBean, String[] updateAtrributes)
			throws SQLException {
		// 如何只更新某一部分的字段.
		boolean bool = false;
		int result = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		// String sql = "update t_user set userName=?,userPwd=? where userId=?";
		String sql = "update t_user set ";
		for (String ss : updateAtrributes) {
			try {
				sql += ss + "=" + (String) ReflectUtill.getValue(userBean, ss)
						+ ",";
				sql = sql.substring(0, sql.lastIndexOf(","));
				sql += " where userId=?";
				// 如果反射没出错执行
				try {
					conn = DBUtill.getConnection();
					pstm = conn.prepareStatement(sql);
					pstm.setString(1, userBean.getUserId());
					result = pstm.executeUpdate();
					if (result != -1) {
						bool = true;
					}
				} catch (SQLException e) {
					// update失败 事务回滚.
					e.printStackTrace();
					throw e;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return bool;
	}

	public boolean validateUserBean(String userId) throws SQLException {
		boolean bool = false;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from t_user where userId = ?";
		try {
			conn = DBUtill.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bool = true;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtill.close(conn, pstm, rs);
		}
		return bool;
	}

	public boolean validateUserBean(String userName, String userPwd)
			throws SQLException {
		boolean bool = false;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from t_user where userName=? and userPwd=?";
		try {
			conn = DBUtill.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userName);
			pstm.setString(2, userPwd);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bool = true;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtill.close(conn, pstm, rs);
		}
		return bool;
	}
}
