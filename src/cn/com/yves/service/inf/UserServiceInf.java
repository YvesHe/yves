package cn.com.yves.service.inf;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.yves.bean.UserBean;

/**
 * 用service分层再一次做用户的管理模块
 * 
 * @author Yves He
 * 
 */
public interface UserServiceInf {
    /**
     * 验证用户登录
     * 
     * @throws IOException
     */
    boolean validateUserLogin(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException;

    /**
     * 增加UserBean
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    void addUserBean(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException;

    void deleteUserBean(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException;

    boolean updateUserBean(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            SQLException;

    /**
     * 判断是否已经登录
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    boolean isLogin(HttpServletRequest request, HttpServletResponse response);

    void setEncoding(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    /**
     * 安全退出
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    void exitLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    /**
     * list All userBean
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    void listAllUserBean(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            SQLException;

    /**
     * 模糊查询by Email
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    List<UserBean> queryByAllEmailFuzzy(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            SQLException;
}
