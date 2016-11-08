package cn.com.yves.servlet.user2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.yves.service.impl.UserServiceImpl;
import cn.com.yves.service.inf.UserServiceInf;

public class UserExit2 extends HttpServlet {
    private final UserServiceInf userService = new UserServiceImpl();

    /**
     * The doGet method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to get.
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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userService.setEncoding(request, response);

        if (!userService.isLogin(request, response)) {
            request.getRequestDispatcher("pages/user2/userLogin.jsp").forward(
                    request, response);
            return;
        }

        // 业务: 销毁session
        userService.exitLogin(request, response);

        request.getRequestDispatcher("pages/user2/userLogin.jsp").forward(
                request, response);
    }
}
