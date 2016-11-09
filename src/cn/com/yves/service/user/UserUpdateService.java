package cn.com.yves.service.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.yves.bean.UserBean;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.dao.impl.UserDaoImpl;
import cn.com.yves.service.Service;

public class UserUpdateService implements Service {
    private final UserDaoInf userDao = new UserDaoImpl();

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            updateUser(request, response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新
     * 
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    private void updateUser(HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {

        UserHelp.setEncoding(request, response);

        if (!UserHelp.isLogin(request)) {
            try {
                request.getRequestDispatcher("pages/errorPage/noLogin.jsp")
                        .forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        UserBean userBean = UserHelp.getUserBeanDataFromJSP(request);
        try {
            if (userDao.updateUserBean(userBean)) {
                request.getRequestDispatcher("userControl?service=userList")
                        .forward(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.print("更新失败!");
                request.getRequestDispatcher("userControl?service=userList")
                        .include(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
