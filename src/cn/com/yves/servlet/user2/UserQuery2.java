package cn.com.yves.servlet.user2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.yves.bean.UserBean;
import cn.com.yves.service.impl.UserServiceImpl;
import cn.com.yves.service.inf.UserServiceInf;

public class UserQuery2 extends HttpServlet {
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
        userService.setEncoding(request, response);

        if (!userService.isLogin(request, response)) {
            return;
        }

        try {
            List<UserBean> list = userService.queryByAllEmailFuzzy(request,
                    response);
            request.setAttribute("userBeanList", list);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("pages/user2/userQueryShow.jsp").forward(
                request, response);
    }
}
