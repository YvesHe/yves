package cn.com.yves.dao;

import java.sql.SQLException;
import java.util.List;

import cn.com.yves.bean.UserBean;

/**
 * 
 * @Descripton :UserBean的操作
 * 
 * @author Yves He
 * @date 2016-10-7
 * 
 */
public interface UserDaoInf {

	/**
	 * 
	 * @Description: list返回所有的UserBean
	 * 
	 * @return List
	 * @throws SQLException
	 */
	List<UserBean> listAllUserBean() throws SQLException;

	/**
	 * 
	 * @Description: 查询UserBean by id
	 * 
	 * @param userId
	 * @return UserBean
	 * @throws SQLException
	 */
	UserBean getUserBeanById(String userId) throws SQLException;

	/**
	 * @Description: 查询UserBean by userName
	 * 
	 * @param userName
	 * @return
	 * @throws SQLException
	 */
	UserBean getUserBeanByName(String userName) throws SQLException;;

	/**
	 * 
	 * @Description: 查询UserBean by nickName 模糊查询
	 * 
	 * @param userId
	 * @return UserBean
	 * @throws SQLException
	 */
	List<UserBean> getUserBeanByNickName(String nickName) throws SQLException;

	/**
	 * 
	 * @Descripton :新增UserBean
	 * 
	 * @param uBean
	 * @return boolean
	 * @throws SQLException
	 */
	boolean addUserBean(UserBean uBean) throws SQLException;

	/**
	 * 
	 * @Descripton :删除UserBean By Id
	 * 
	 * @param userId
	 * @return boolean
	 * @throws SQLException
	 */
	boolean deleteUserBean(String userId) throws SQLException;

	/**
	 * 
	 * @Descripton :更新UserBean
	 * 
	 * @param userBean
	 * @param updateAtrributes
	 * @return boolean
	 * @throws SQLException
	 */
	boolean updateUserBean(UserBean userBean, String[] updateAtrributes)
			throws SQLException;

	/**
	 * 
	 * @Descripton :验证是否存在UserBean 三种登录方式
	 * 
	 * @param userId
	 * @return boolean
	 * @throws SQLException
	 */
	boolean validateUserBean(String userName, String userPwd)
			throws SQLException;

	/**
	 * 
	 * @Descripton :验证是否存在 相同的邮箱
	 * 
	 * @param userId
	 * @return boolean
	 * @throws SQLException
	 */
	boolean validateUserBeanByEmail(String userName) throws SQLException;

	/**
	 * 
	 * @Descripton :验证是否存在 相同的手机号
	 * 
	 * @param userId
	 * @return boolean
	 * @throws SQLException
	 */
	boolean validateUserBeanByPhoneNumber(String userPhoneNumber)
			throws SQLException;

	/**
	 * 
	 * @Descripton :验证是否存在 相同的账号
	 * 
	 * @param userId
	 * @return boolean
	 * @throws SQLException
	 */
	boolean validateUserBeanByCount(String userPhoneNumber) throws SQLException;

}
