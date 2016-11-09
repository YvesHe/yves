package cn.com.yves.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.yves.bean.UserBean;
import cn.com.yves.constant.Constant;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.dao.impl.UserDaoImpl;
import cn.com.yves.service.inf.UserServiceInf;

/**
 * 现在这一版本是将request直接给了service来获取数据和处理
 * 
 * @author Yves He
 * 
 */
public class UserServiceImpl implements UserServiceInf {

    private final UserDaoInf userDao = new UserDaoImpl();

    public boolean validateUserLogin(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        boolean bool = false;

        // 获取页面的数据
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");

        // 组业务逻辑 都是默认的邮箱账号
        try {
            if (userDao.validateUserBean(userName, Constant.USER_COUNT_EMAIL,
                    userPwd)) {
                // 验证通过,压放数据
                bool = true;
                this.setListData(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

    /**
     * 新增UserBean
     */
    public void addUserBean(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            SQLException {

        // 验证数据库是否已经有了数据
        UserBean uBean = getUserBeanDataFromJSP(request);
        userDao.addUserBean(uBean);
    }

    // 将页面的数据封装成Bean对象
    private UserBean getUserBeanDataFromJSP(HttpServletRequest request) {
        UserBean uBean = new UserBean();
        uBean.setUserCount(Constant.USER_COUNT_EMAIL);
        // id不是null则设置它的值
        if (request.getParameter("userId") != null) {
            uBean.setUserId(request.getParameter("userId"));
        } else {
            uBean.setUserId(createUserId());
        }
        uBean.setUserDesc(request.getParameter("userDesc"));
        uBean.setUserName(request.getParameter("userName"));
        uBean.setUserNickName(request.getParameter("userNickName"));
        uBean.setUserPhoneNumber(request.getParameter("userPhoneNumber"));
        uBean.setUserPowerId(Integer.valueOf(request
                .getParameter("userPowerId").trim()));
        uBean.setUserPwd(request.getParameter("userPwd"));
        return uBean;
    }

    // 设置list数据
    private void setListData(HttpServletRequest request,
            HttpServletResponse response) throws SQLException {
        List<UserBean> list = userDao.listAllUserBean();
        request.setAttribute("userBeanList", list);

        // 存放数据到session
        HttpSession session = request.getSession(true);
        String userName = request.getParameter("userName");
        UserBean userBean = userDao.getUserBeanByAllCount(userName,
                Constant.USER_COUNT_EMAIL);
        session.setAttribute("userBean", userBean);
    }

    // 自动生成uuid作为user的 id
    private String createUserId() {
        return UUID.randomUUID().toString();
    }

    /**
     * updata the UserBean
     */
    public boolean updateUserBean(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            SQLException { // 所有的业务都要放在service中来设计,这里如果是非法的访问都直接放回不做处理也没有提示了.
        this.setEncoding(request, response);
        // 1.不能没登录就恶意访问
        if (!this.isLogin(request, response)) {
            return false;
        }

        // 2.修改的时候不能跨越权限访问
        UserBean editBean = userDao.getUserBeanById(request.getParameter(
                "userId").trim());

        UserBean loginBean = (UserBean) request.getSession().getAttribute(
                "userBean");

        if (Integer.valueOf(loginBean.getUserPowerId()) >= Integer
                .valueOf(editBean.getUserPowerId())) {// 权限不够
            return false;
        }

        UserBean dataBean = getUserBeanDataFromJSP(request);
        // 验证数据库是否已经有了数据 修改的时候name不能相同,但是又允许与当前名字相同.

        if (!editBean.getUserName().equals(dataBean.getUserName())) {// 修改用户名改变
            if (userDao.validateCount(dataBean.getUserName(),
                    Constant.USER_COUNT_EMAIL)) {// 数据库存在同名账号
                return false;
            }
        }

        userDao.updateUserBean(editBean);
        return true;
    }

    /**
     * 判断用户是否已经登录
     */
    public boolean isLogin(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        return session.getAttribute("userBean") != null;
    }

    /**
     * 设置字符编码
     */
    public void setEncoding(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    /**
     * List all userBean
     * 
     * @throws SQLException
     */
    public void listAllUserBean(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            SQLException {
        request.setAttribute("userBeanList", userDao.listAllUserBean());
    }

    /**
     * 删除UserBean
     */
    public void deleteUserBean(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            SQLException {
        userDao.deleteUserBean(request.getParameter("userId"));
    }

    /**
     * 安全退出
     */
    public void exitLogin(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        session.invalidate();// 然session失效
    }

    /**
     * 返回查询的结果
     */
    public List<UserBean> queryByAllEmailFuzzy(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            SQLException {

        return userDao.getUserBeanByAllCountFuzzy(
                request.getParameter("queryWord"), Constant.USER_COUNT_EMAIL);
    }
}
