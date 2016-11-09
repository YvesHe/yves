package cn.com.yves.service.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.dao.impl.UserDaoImpl;
import cn.com.yves.service.Service;

public class UserDelService implements Service {
    private final UserDaoInf userDao = new UserDaoImpl();

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            DelUser(request, response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除User
     * 
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    private void DelUser(HttpServletRequest request,
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

        String userId = request.getParameter("userId");
        try {
            if (userDao.deleteUserBean(userId)) {

                request.getRequestDispatcher("userControl?service=userList")
                        .forward(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.print("删除失败!");
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
