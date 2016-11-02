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

public class UserDelete extends HttpServlet {
	private UserDaoInf iUserDao = new UserDao();

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
		// 思考: 如何才能让a标签是是doget方式提交的,这样才能保证数据的安全性.
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId").trim();

		// 验证请求是否合法
		HttpSession session = request.getSession();
		UserBean selfBean = (UserBean) session.getAttribute("userBean");

		// 1.判断是否登录
		if (selfBean == null) {// 没有登录,挑战登录界面
			response.sendRedirect("pages/user/userLogin.jsp");
			return;
		}

		// 获取要删除userBean
		UserBean uBean = null;
		try {
			uBean = iUserDao.getUserBeanById(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (uBean != null) {
			// 2.判断权限是否够
			if (selfBean.getUserPowerId() >= uBean.getUserPowerId()) {// 权限不够

				String message = "<html><head><script type='text/javascript'>alert('用户权限不够!');</script></head><body></body></html>";
				request.setAttribute("message", message);
				request.getRequestDispatcher("userList").include(request,
						response);
				return;

			} else {// 权限足够,可以删除

				try {
					if (iUserDao.deleteUserBean(userId) == true) {// 删除成功

						String message = "<html><head><script type='text/javascript'>alert('删除成功!');</script></head><body></body></html>";
						request.setAttribute("message", message);

						request.getRequestDispatcher("userList").include(
								request, response);
					} else {// 删除失败
						String message = "<html><head><script type='text/javascript'>alert(' 删除失败!');</script></head><body></body></html>";
						request.setAttribute("message", message);

						request.getRequestDispatcher("userList");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		}

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
		doGet(request, response);
	}
}
