package cn.com.yves.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.yves.bean.UserBean;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.dao.impl.UserDao;

public class UserLogin extends HttpServlet {
	private UserDaoInf IUserDao = new UserDao();

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
			throws ServletException, IOException {// 成功登录后,跳转到userList.jsp
													// 显示所的user
													// pages/user/userList.jsp
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String userName = (String) request.getParameter("userName");
		String userPwd = (String) request.getParameter("userPwd");
		PrintWriter out = response.getWriter();

		// 验证用户
		boolean bool;
		try {
			bool = IUserDao.validateUserBean(userName, userPwd);
			if (bool) {
				// 获取该用户的所有信息
				UserBean userBean = IUserDao.getUserBeanByName(userName);

				// 用户登录后,保持回话连接
				HttpSession session = request.getSession(true);
				session.setAttribute("userBean", userBean);
				out.println("<html><head><script type='text/javascript'>alert('登陆成功!');</script></head><body></body></html>");
				request.getRequestDispatcher("pages/user/userList.jsp")
						.include(request, response);
			} else {
				// 密码不正确
				out.println("<html><head><script type='text/javascript'>alert('账号或密码错误!');</script></head><body></body></html>");
				response.sendRedirect("pages/user/userLogin.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
