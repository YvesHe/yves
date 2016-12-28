package cn.com.yves.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 具体查看cookie过程用浏览器debug
 */
public class CookieConfig extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        /* 第一块: 获取用户cookie */
        Cookie[] cookies = request.getCookies();
        System.out.println("********遍历开始*******");
        for (Cookie coo : cookies) {
            // 获取cookie信息后做功能
            System.out.println(coo.getName() + ":" + coo.getValue());
            System.out.println(coo.getPath());

            coo.getName();
            coo.getValue();
            coo.getComment();
            coo.getMaxAge();
            coo.getPath();
            coo.getDomain();
            coo.getVersion();
        }
        System.out.println("********遍历结束*******");

        /* 第二块: servlet中设置cookie,然后发送给客户端 */
        // 创建cookie
        Cookie cookie = new Cookie(request.getParameter("cookieKey"),
                request.getParameter("cookieValue"));

        // 设置cookie失效时间 (设置过期日期为 24 小时后)
        cookie.setMaxAge(60 * 60 * 24);

        // 设置产生cookis的uri 详情见文档解析
        cookie.setPath("/Data");

        // 在响应头中添加 Cookies
        response.addCookie(cookie);

        // 发送数据 cookie与是否是sendRedirect还是forward无关.
        response.sendRedirect("pages/cookieConfig.jsp");
        // request.getRequestDispatcher("pages/cookieConfig.jsp").forward(request,
        // response);

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
        doGet(request, response);
    }

}
