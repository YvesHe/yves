package cn.com.yves.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.yves.bean.UserBean;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.dao.impl.UserDao;

public class UserAdd extends HttpServlet {
	UserDaoInf IUserDao = new UserDao();

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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		PrintWriter out = response.getWriter();

		UserBean ub = new UserBean();
		ub.setUserName(userName);
		ub.setUserPwd(userPwd);
		// 数据库中id为int可以设置自动增长
		// SELECT * FROM user ORDER BY userId DESC LIMIT 1
		// 这里用的uui生成唯一的String 类型的id
		UUID uuid = UUID.randomUUID();
		ub.setUserId(uuid.toString());

		try {
			if (IUserDao.addUserBean(ub)) {
				out.println("<html><head><script type='text/javascript'>alert('新增成功!');</script></head><body></body></html>");
				request.getRequestDispatcher("pages/user/userList.jsp")
						.include(request, response);
			} else {
				out.println("<html><head><script type='text/javascript'>alert('新增失败!');</script></head><body></body></html>");
				request.getRequestDispatcher("pages/user/userList.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
