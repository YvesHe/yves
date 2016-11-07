package cn.com.yves.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.yves.bean.UserBean;
import cn.com.yves.constant.Constant;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.utill.DBUtill;
import cn.com.yves.utill.ReflectUtill;

public class UserDaoImpl implements UserDaoInf {

	public List<UserBean> listAllUserBean() throws SQLException {
		List<UserBean> list = new ArrayList<UserBean>();
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
				uBean.setUserId(rs.getString("userid"));
				uBean.setUserName(rs.getString("name"));
				uBean.setUserPwd(rs.getString("password"));
				uBean.setUserCount(rs.getInt("count"));
				uBean.setUserNickName(rs.getString("nickname"));
				uBean.setUserDesc(rs.getString("desc"));
				uBean.setUserPhoneNumber(rs.getString("phoneNumber"));
				uBean.setUserPowerId(rs.getInt("powerid"));
				list.add(uBean);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtill.close(conn, pstm, rs);
		}
		return list;
	}

	/**
	 * 精确查询 UserName by userId
	 */
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
				uBean.setUserCount(rs.getInt("count"));
				uBean.setUserNickName(rs.getString("nickname"));
				uBean.setUserDesc(rs.getString("desc"));
				uBean.setUserPhoneNumber(rs.getString("phoneNumber"));
				uBean.setUserPowerId(rs.getInt("powerid"));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtill.close(conn, pstm, rs);
		}
		return uBean;
	}

	/**
	 * 精确查询 UserName by allCount
	 */
	public UserBean getUserBeanByAllCount(String allCount, int countType)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		UserBean uBean = null;

		if (countType == Constant.USER_COUNT_NUMBER) {
		} else if (countType == Constant.USER_COUNT_NUMBER) {// 数字账号

		} else if (countType == Constant.USER_COUNT_EMAIL) {// 邮箱账号
			final String sql = "select * from user where name=?";
			try {
				conn = DBUtill.getConnection();
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, allCount);
				rs = pstm.executeQuery();
				while (rs.next()) {
					uBean = new UserBean();
					uBean.setUserId(rs.getString("userid"));
					uBean.setUserName(rs.getString("name"));
					uBean.setUserPwd(rs.getString("password"));
					uBean.setUserCount(rs.getInt("count"));
					uBean.setUserNickName(rs.getString("nickname"));
					uBean.setUserDesc(rs.getString("desc"));
					uBean.setUserPhoneNumber(rs.getString("phoneNumber"));
					uBean.setUserPowerId(rs.getInt("powerid"));
				}
			} catch (SQLException e) {
				throw e;
			} finally {
				DBUtill.close(conn, pstm, rs);
			}

		} else if (countType == Constant.USER_COUNT_PHONENUMBER) {// 手机账号
		}

		return uBean;
	}

	/**
	 * 模糊查询 UserName by allCount
	 */
	public List<UserBean> getUserBeanByAllCountFuzzy(String allCount,
			int countType) throws SQLException {
		// 思考 : 判断账号的类型可以在前台做 也可以在后台做, 用正则表达式验证 ,具体在哪里做比较好呢

		/* 传入的账户类型来建立模糊查询 类型有 email 手机 和数字账号 1: 代表数字账号 2: 邮箱 3: 手机 */

		List<UserBean> list = new ArrayList<UserBean>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		if (countType == Constant.USER_COUNT_NUMBER) {
		} else if (countType == Constant.USER_COUNT_NUMBER) {// 数字账号

		} else if (countType == Constant.USER_COUNT_EMAIL) {// 邮箱账号
			final String sql = "select * from user where name like " + "'%"
					+ allCount + "%'";
			try {
				conn = DBUtill.getConnection();
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				while (rs.next()) {
					UserBean uBean = new UserBean();
					uBean.setUserId(rs.getString("userid"));
					uBean.setUserName(rs.getString("name"));
					uBean.setUserPwd(rs.getString("password"));
					uBean.setUserCount(rs.getInt("count"));
					uBean.setUserNickName(rs.getString("nickname"));
					uBean.setUserDesc(rs.getString("desc"));
					uBean.setUserPhoneNumber(rs.getString("phoneNumber"));
					uBean.setUserPowerId(rs.getInt("powerid"));
					list.add(uBean);
				}
			} catch (SQLException e) {
				throw e;
			} finally {
				DBUtill.close(conn, pstm, rs);
			}

		} else if (countType == Constant.USER_COUNT_PHONENUMBER) {// 手机账号
		}

		return list;
	}

	/**
	 * 模糊查询 by userNickName
	 */
	public List<UserBean> getUserBeanByNickName(String userNickName)
			throws SQLException {
		List<UserBean> list = new ArrayList<UserBean>();
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
				uBean.setUserId(rs.getString("userid"));
				uBean.setUserName(rs.getString("name"));
				uBean.setUserPwd(rs.getString("password"));
				uBean.setUserCount(rs.getInt("count"));
				uBean.setUserNickName(rs.getString("nickname"));
				uBean.setUserDesc(rs.getString("desc"));
				uBean.setUserPhoneNumber(rs.getString("phoneNumber"));
				uBean.setUserPowerId(rs.getInt("powerid"));
				list.add(uBean);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtill.close(conn, pstm, rs);
		}
		return list;
	}

	/**
	 * 新增 userBean
	 */
	public boolean addUserBean(UserBean uBean) throws SQLException {
		boolean bool = false;
		Connection conn = null;
		PreparedStatement pstm = null;
		// id 为int 可以设置自增,String类型的id用uuid类唯一化
		final String sql = "insert into user values(?,?,?,?,?,?,?,?)";
		conn = DBUtill.getConnection();
		try {
			pstm = conn.prepareStatement(sql);
			// 注意 ? 号的顺序应该与数据库字段的顺序一致
			pstm.setString(1, uBean.getUserId());
			pstm.setString(2, uBean.getUserName());
			pstm.setInt(3, (int) uBean.getUserCount());
			pstm.setString(4, uBean.getUserPhoneNumber());
			pstm.setString(5, uBean.getUserPwd());
			pstm.setString(6, uBean.getUserNickName());
			pstm.setString(7, uBean.getUserDesc());
			pstm.setInt(8, uBean.getUserPowerId());
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

	/**
	 * 删除 userBean by userId
	 */
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

	/**
	 * 更新 userBean
	 */
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
	 * 更新userBean (全量)
	 */
	public boolean updateUserBean(UserBean userBean) throws SQLException {
		boolean bool = false;
		Connection conn = null;
		PreparedStatement pstm = null;
		// id 为int 可以设置自增,String类型的id用uuid类唯一化
		final String sql = "update user set name=?,phonenumber=?,password=?,nickname=?,`desc`=?,powerid=? where userid=? ";
		conn = DBUtill.getConnection();
		try {
			pstm = conn.prepareStatement(sql);
			// 注意 ? 号的顺序应该与数据库字段的顺序一致
			pstm.setString(1, userBean.getUserName());
			pstm.setString(2, userBean.getUserPhoneNumber());
			pstm.setString(3, userBean.getUserPwd());
			pstm.setString(4, userBean.getUserNickName());
			pstm.setString(5, userBean.getUserDesc());
			pstm.setInt(6, userBean.getUserPowerId());
			pstm.setString(7, userBean.getUserId());

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

	/**
	 * 验证账号和密码: 三种登录方式验证(用于登录验证)
	 */
	public boolean validateUserBean(String userName, int countType,
			String userPwd) throws SQLException {
		boolean bool = false;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		if (countType == Constant.USER_COUNT_NUMBER) {// 系统分配的账号登录

		} else if (countType == Constant.USER_COUNT_EMAIL) {// 邮箱登录验证
			// 暂时所有的验证都在这里做(手机号登录,邮箱登录,账号登录),现在只支持userName登录,也就是邮箱登录,但是邮箱登录也还验证.
			final String sql = "select * from user where name=? and password=?";
			try {
				conn = DBUtill.getConnection();
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, userName);
				pstm.setString(2, userPwd);
				rs = pstm.executeQuery();
				while (rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				throw e;
			} finally {
				DBUtill.close(conn, pstm, rs);
			}
		} else if (countType == Constant.USER_COUNT_PHONENUMBER) {// 手机号登录验证
		}

		return bool;
	}

	/**
	 * 验证所有的账号是否存在
	 */
	public boolean validateCount(String allCount, int countType)
			throws SQLException {
		boolean bool = false;
		if (countType == Constant.USER_COUNT_NUMBER) {// 是系统分配的数字账号

		} else if (countType == Constant.USER_COUNT_EMAIL) {// 是邮箱账号
			// 邮箱登录验证
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			final String sql = "select * from user where name=?";
			try {
				conn = DBUtill.getConnection();
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, allCount);
				rs = pstm.executeQuery();
				while (rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				throw e;
			} finally {
				DBUtill.close(conn, pstm, rs);
			}
		} else if (countType == Constant.USER_COUNT_PHONENUMBER) {// 是手机账号
		}
		return bool;
	}

}
