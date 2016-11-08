package cn.com.yves.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.yves.bean.UserBean;
import cn.com.yves.constant.Constant;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.dao.impl.UserDaoImpl;

public class UserLogin extends HttpServlet {
	private UserDaoInf IUserDao = new UserDaoImpl();

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
			bool = IUserDao.validateUserBean(userName,
					Constant.USER_COUNT_EMAIL, userPwd);
			if (bool) {
				// 获取该用户的所有信息 --由于账号验证通过,账号也是唯一的,
				UserBean userBean = IUserDao.getUserBeanByAllCount(userName,
						Constant.USER_COUNT_EMAIL);// 先默认是邮箱登录

				// 用户登录后,保持回话连接
				HttpSession session = request.getSession(true);
				session.setAttribute("userBean", userBean);

				// 封装显示数据
				List<UserBean> list = IUserDao.listAllUserBean();
				request.setAttribute("userBeanList", list);

				String message = "<html><head><script type='text/javascript'>alert('登陆成功!');</script></head><body></body></html>";
				request.setAttribute("message", message);

				request.getRequestDispatcher("pages/user/userList.jsp")
						.include(request, response);
			} else {
				// 密码不正确
				String message = "<html><head><script type='text/javascript'>alert('账号或密码错误!');</script></head><body></body></html>";
				request.setAttribute("message", message);

				request.getRequestDispatcher("pages/user/userLogin.jsp")
						.include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
