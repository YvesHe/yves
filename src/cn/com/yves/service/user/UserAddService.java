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

public class UserAddService implements Service {
    // 如果是为了解决搞并发,这个要每次都要new 不能是静态的.
    private final UserDaoInf userDao = new UserDaoImpl();

    /**
     * 实现Service中的方法.
     */
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            this.addUser(request, response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 增加user
     * 
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    private void addUser(HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {

        // 设置字符编码
        UserHelp.setEncoding(request, response);

        // 判断是否登录
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

        // 判断有有没添加的权限,待做
        UserBean userBean = UserHelp.getUserBeanDataFromJSP(request);

        // 做添加user
        try {
            if (userDao.addUserBean(userBean)) {
                request.getRequestDispatcher("userControl?service=userList")
                        .forward(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.print("新增失败!");
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
