package cn.com.yves.servlet.user2;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.yves.service.impl.UserServiceImpl;
import cn.com.yves.service.inf.UserServiceInf;

public class UserList2 extends HttpServlet {
    private final UserServiceInf userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * The doPost method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to
     * post.
     * 
     * @param request
     *            the request send by the client to the server
     * @param response
     *            the response send by the server to the client
     * @throws ServletException
     *             if an error occurred
     * @throws IOException
     *             if an error occurred
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置编码
        userService.setEncoding(request, response);
        // 判断是否登录
        if (!userService.isLogin(request, response)) {
            return;
        }
        // 封装页面数据 list userBean
        try {
            userService.listAllUserBean(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("pages/user2/userList.jsp").forward(
                request, response);
    }
}
