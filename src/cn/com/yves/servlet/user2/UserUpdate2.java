package cn.com.yves.servlet.user2;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.yves.service.impl.UserServiceImpl;
import cn.com.yves.service.inf.UserServiceInf;

public class UserUpdate2 extends HttpServlet {

    private final UserServiceInf userService = new UserServiceImpl();

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

        // 防止恶意的直接访问该servlet,在这里做一个判断是否登录验证.
        userService.setEncoding(request, response);
        if (!userService.isLogin(request, response)) {
            return;
        }

        try {
            userService.updateUserBean(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("userList2").forward(request, response);
    }
}
