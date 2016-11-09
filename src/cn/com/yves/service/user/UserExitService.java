package cn.com.yves.service.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.yves.service.Service;

public class UserExitService implements Service {

    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            exitLogin(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void exitLogin(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        // 设置字符编码
        UserHelp.setEncoding(request, response);

        // 登录就让session失效
        if (UserHelp.isLogin(request)) {
            HttpSession session = request.getSession(true);
            session.invalidate();
        }

        response.sendRedirect("pages/user4/userLogin.jsp");
    }
}
