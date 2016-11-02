package cn.com.yves.servlet.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.yves.bean.UserBean;
import cn.com.yves.constant.Constant;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.dao.impl.UserDao;

public class UserAdd extends HttpServlet {
	UserDaoInf IUserDao = new UserDao();

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
		/**
		 * 思路: 在进行新增界面的时候,应该有给账号设置权限的功能,能进这个页面说明已经是已经登录了
		 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		UserBean ubBean = (UserBean) session.getAttribute("userBean");
		// 判断是否已经登录
		if (ubBean == null) {
			String message = "<html><head><script type='text/javascript'>alert('请先登录!');</script></head><body></body></html>";
			request.setAttribute("message", message);
			request.getRequestDispatcher("pages/user/userLogin.jsp").forward(
					request, response);
			return;
		}

		// 获取页面数据
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		int userPowerId = Integer.valueOf(request.getParameter("userPowerId")
				.trim());// 输入时 1 2 3 要在前台做验证.

		// 1.本user模块旨在让超级管理员创建删除下属的账号 所以新增的时候应该是有限制的

		// 2. 判断数据库是否已经有改邮箱注册了.有就提示该用户名已经注册,新增失败
		try {
			if (IUserDao.validateCount(userName, Constant.USER_COUNT_EMAIL)) {// 数据中该邮箱已经注册

				String message = "<html><head><script type='text/javascript'>alert('新增失败,该邮箱已经注册!');</script></head><body></body></html>";
				request.setAttribute("message", message);
				request.getRequestDispatcher("userList").forward(request,
						response);
				return;

			} else {// 数据中无该该邮箱
				UserBean ub = new UserBean();

				UUID uuid = UUID.randomUUID(); // 数据库中id为int可以设置自动增长
												// SELECT * FROM user ORDER BY
												// userId DESC LIMIT 1
												// 这里用的uui生成唯一的String 类型的id
				ub.setUserId(uuid.toString());
				ub.setUserName(userName);
				ub.setUserPwd(userPwd);
				ub.setUserPowerId(userPowerId);// 设置权限

				try {
					if (IUserDao.addUserBean(ub)) {
						String message = "<html><head><script type='text/javascript'>alert('新增成功!');</script></head><body></body></html>";
						request.setAttribute("message", message);
						request.getRequestDispatcher("userList").forward(
								request, response);
						return;
					} else {
						String message = "<html><head><script type='text/javascript'>alert('新增失败!');</script></head><body></body></html>";
						request.setAttribute("message", message);
						request.getRequestDispatcher("userList").forward(
								request, response);
						return;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}
