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
		final String sql = "select * from user";
		try {
			conn = DBUtill.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			UserBean uBean = null;
			while (rs.next()) {
				uBean = new UserBean();
				uBean.setUserId((rs.getString("userid")));
				uBean.setUserName(rs.getString("name"));
				uBean.setUserPwd(rs.getString("password"));
				userList.add(uBean);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtill.close(conn, pstm, rs);
		}
		return userList;
	}

	/**
	 * 模糊查询 by userNickName
	 */
	public List<UserBean> getUserBeanByNickName(String userNickName)
			throws SQLException {
		List<UserBean> userList = new ArrayList<UserBean>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// 思考: 如何防止sql注入,先用?代替,应该有两种方式实现 一种是包括% , 一种是不包括%
		// 正确的语句:select * from user where name like "%yves%" ;
		final String sql = "select * from user where nickname like " + "'%"
				+ userNickName + "%'";
		try {
			conn = DBUtill.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			UserBean uBean = null;
			while (rs.next()) {
				uBean = new UserBean();
				uBean.setUserId((rs.getString("userid")));
				uBean.setUserName(rs.getString("name"));
				uBean.setUserPwd(rs.getString("password"));
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
		final String sql = "select * from user where userid = ?";
		try {
			conn = DBUtill.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				uBean = new UserBean();
				uBean.setUserId(rs.getString("userid"));
				uBean.setUserName(rs.getString("name"));
				uBean.setUserPwd(rs.getString("password"));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtill.close(conn, pstm, rs);
		}
		return uBean;
	}

	/**
	 * 通过账号名来获取所有信息
	 */
	public UserBean getUserBeanByName(String userName) throws SQLException {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		UserBean uBean = null;
		final String sql = "select * from user where name = ?";
		try {
			conn = DBUtill.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userName);
			rs = pstm.executeQuery();
			while (rs.next()) {
				uBean = new UserBean();
				uBean.setUserId(rs.getString("userid"));
				uBean.setUserName(rs.getString("name"));
				uBean.setUserPwd(rs.getString("password"));
				uBean.setUserCount(rs.getInt("count"));
				uBean.setUserNickname(rs.getString("nickname"));
				uBean.setUserDesc(rs.getString("desc"));
				uBean.setUserPhoneNumber(rs.getString("phoneNumber"));
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
		// id 为int 可以设置自增,String类型的id用uuid类唯一化
		final String sql = "insert into user values(?,?,?,?,?,?,?)";
		conn = DBUtill.getConnection();
		try {
			pstm = conn.prepareStatement(sql);
			// 注意 ? 号的顺序应该与数据库字段的顺序一致
			pstm.setString(1, uBean.getUserId());
			pstm.setString(2, uBean.getUserName());
			pstm.setInt(3, (int) uBean.getUserCount());
			pstm.setString(4, uBean.getUserPhoneNumber());
			pstm.setString(5, uBean.getUserPwd());
			pstm.setString(6, uBean.getUserNickname());
			pstm.setString(7, uBean.getUserDesc());
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
		final String sql = "delete from user where userid=?";
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
		// String sql = "update user set name=?,password=? where userid=?";
		String sql = "update user set ";
		for (String ss : updateAtrributes) {
			try {
				sql += ss + "=" + (String) ReflectUtill.getValue(userBean, ss)
						+ ",";
				sql = sql.substring(0, sql.lastIndexOf(","));
				sql += " where userid=?";
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

	/**
	 * 验证账号和密码: 三种登录方式验证
	 */
	public boolean validateUserBean(String userName, String userPwd)
			throws SQLException {
		boolean bool = false;

		if (false) {// 手机号登录验证

		} else if (true) {// 邮箱登录验证
			// 暂时所有的验证都在这里做(手机号登录,邮箱登录,账号登录),现在只支持userName登录,也就是邮箱登录,但是邮箱登录也还验证.
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			final String sql = "select * from user where name=? and password=?";
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
		} else {// 系统分配的账号登录
		}

		return bool;
	}

	/**
	 * 默认是邮箱登录,验证邮箱
	 */
	public boolean validateUserBeanByEmail(String userName) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 验证是否存在 相同的手机号
	 */
	public boolean validateUserBeanByPhoneNumber(String userPhoneNumber)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 验证是否存在 相同的账号
	 */
	public boolean validateUserBeanByCount(String userPhoneNumber)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
