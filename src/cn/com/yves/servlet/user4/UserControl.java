package cn.com.yves.servlet.user4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.yves.service.user.UserServiceFactory;

public class UserControl extends HttpServlet {

    /**
     * 直接重写service方法,不管是doGet方法还是doPost方法在在这里做
     */
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userService = request.getParameter("service").trim();

        // 首先,如果是登录service,那么记得要用户数据保存在session中.
        if (userService.equals("login")) {
            UserServiceFactory.createLoginService().execute(request, response);
        } else if (userService.equals("userList")) {// 登录成功就显示
            UserServiceFactory.createListService().execute(request, response);
        } else if (userService.equals("userAdd")) {
            UserServiceFactory.createAddService().execute(request, response);

        } else if (userService.equals("userDel")) {
            UserServiceFactory.createDelService().execute(request, response);

        } else if (userService.equals("userUpdate")) {
            UserServiceFactory.createUpdateService().execute(request, response);

        } else if (userService.equals("userQuery")) {
            UserServiceFactory.createQueryService().execute(request, response);
        } else if (userService.endsWith("exit")) {
            UserServiceFactory.createExitLoginService().execute(request,
                    response);
        }
    }
}
