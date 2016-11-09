package cn.com.yves.service;

import java.sql.SQLException;

import cn.com.yves.bean.UserBean;
import cn.com.yves.constant.Constant;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.dao.impl.UserDaoImpl;

public class UserServiceForServletTest {

	private UserDaoInf uDao = new UserDaoImpl();

	public UserBean showUserInfo(String userBeanId) {
		UserBean uBean = null;
		try {
			uBean = uDao.getUserBeanById(userBeanId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uBean;
	}

	/**
	 * 验证是是否存在User by userName
	 * 
	 * @param userId
	 * @return
	 */
	public boolean validateUserByName(String userName, String userPwd) {
		boolean bool = false;
		try {
			bool = uDao.validateUserBean(userName, Constant.USER_COUNT_EMAIL,
					userPwd);
		} catch (SQLException e) {
			// 异常处理
		}
		return bool;
	}
}
