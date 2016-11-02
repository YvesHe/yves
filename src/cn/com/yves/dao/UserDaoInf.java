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
	 * @Description: 查询UserBean by allCount 精确查询
	 * @param allCount
	 * @param countType
	 * @return
	 * @throws SQLException
	 */
	UserBean getUserBeanByAllCount(String allCount, int countType)
			throws SQLException;

	/**
	 * @Description: 查询UserBean by allCount 模糊查询
	 * 
	 * @param userName
	 * @return
	 * @throws SQLException
	 */
	List<UserBean> getUserBeanByAllCountFuzzy(String allCount, int countType)
			throws SQLException;;

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
	 * Descripton :全量修改
	 * 
	 * @param userBean
	 * @return
	 */
	boolean updateUserBean(UserBean userBean) throws SQLException;;

	/**
	 * 
	 * @Descripton :验证是否存在UserBean 三种登录方式
	 * 
	 * @param userId
	 * @return boolean
	 * @throws SQLException
	 */
	boolean validateUserBean(String userName, int countType, String userPwd)
			throws SQLException;

	/**
	 * Descripton :验证是否存在 该账号(如果是邮箱就查询邮箱,是手机号就查询手机号)
	 * 
	 * @param allCount
	 * @return
	 * @throws SQLException
	 */
	boolean validateCount(String allCount, int countType) throws SQLException;
}
