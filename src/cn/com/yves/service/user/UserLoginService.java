package cn.com.yves.service.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.yves.constant.Constant;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.dao.impl.UserDaoImpl;
import cn.com.yves.service.Service;

public class UserLoginService implements Service {
    private final UserDaoInf userDao = new UserDaoImpl();

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            loginUser(request, response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     * 
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    private void loginUser(HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {

        UserHelp.setEncoding(request, response);

        if (UserHelp.isLogin(request)) {// 如果是登录的就直接跳userList.jsp
            try {
                request.getRequestDispatcher("userControl?service=userList")
                        .forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        String userName = request.getParameter("userName").trim();
        String userPwd = request.getParameter("userPwd").trim();
        try {
            if (userDao.validateUserBean(userName, Constant.USER_COUNT_EMAIL,
                    userPwd)) {// 验证通过
                HttpSession session = request.getSession(true);
                session.setAttribute("userBean", userDao.getUserBeanByAllCount(
                        userName, Constant.USER_COUNT_EMAIL));

                request.getRequestDispatcher("userControl?service=userList")
                        .forward(request, response);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 账号或密码错误
        try {
            PrintWriter out = response.getWriter();
            out.print("账号或密码错误!");
            request.getRequestDispatcher("pages/user4/userLogin.jsp").include(
                    request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
