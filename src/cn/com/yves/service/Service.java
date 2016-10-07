package cn.com.yves.service;

import java.sql.SQLException;

import cn.com.yves.bean.UserBean;
import cn.com.yves.dao.impl.UserDao;

public class Service {

	private UserDao uDao = new UserDao();

	public UserBean showUserInfo(String userBeanId) {
		UserBean uBean = null;
		try {
			uBean = uDao.getUserBeanById(userBeanId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uBean;
	}
}
