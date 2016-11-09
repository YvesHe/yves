package cn.com.yves.service.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.yves.bean.UserBean;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.dao.impl.UserDaoImpl;
import cn.com.yves.service.Service;

public class UserListService implements Service {

    private final UserDaoInf userDao = new UserDaoImpl();

    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            listUser(request, response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * List user
     * 
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    private void listUser(HttpServletRequest request,
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

        // 判断时候userBeanList 是否已经有值了,有值的话就是查询,就不需要listAllUserBean了
        if (request.getAttribute("userBeanList") == null) {
            request.setAttribute("userBeanList", listAllUserBean());
        }

        try {
            request.getRequestDispatcher("pages/user4/userList.jsp").forward(
                    request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<UserBean> listAllUserBean() {
        List<UserBean> list = null;
        try {
            list = userDao.listAllUserBean();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
