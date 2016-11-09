package cn.com.yves.service.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.yves.bean.UserBean;
import cn.com.yves.constant.Constant;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.dao.impl.UserDaoImpl;
import cn.com.yves.service.Service;

public class UserQueryService implements Service {

    // 如果是为了解决搞并发,这个要每次都要new 不能是静态的.
    private final UserDaoInf userDao = new UserDaoImpl();

    /**
     * 实现Service中的方法.
     */
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            queryUser(request, response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void queryUser(HttpServletRequest request,
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

        // 获取查询关键字
        String queryWord = request.getParameter("queryWord").trim();

        // 查询
        List<UserBean> list = new ArrayList<UserBean>();
        try {
            list = userDao.getUserBeanByAllCountFuzzy(queryWord,
                    Constant.USER_COUNT_EMAIL);
            request.setAttribute("userBeanList", list);
            request.getRequestDispatcher("userControl?service=userList")
                    .forward(request, response);
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
