package cn.com.yves.servlet.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.yves.bean.UserBean;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.dao.impl.UserDao;

public class UserList extends HttpServlet {
	private UserDaoInf iUserDao = new UserDao();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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

		// 判断用户是否已经登录, 在userList.jsp也做了想通的验证
		HttpSession session = request.getSession(true);
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		if (userBean == null) {
			String message = "<html><head><script type='text/javascript'>alert('请先登录!');</script></head><body></body></html>";
			request.setAttribute("message", message);
			request.getRequestDispatcher("pages/user/userLogin.jsp").forward(
					request, response);
			return;
		}

		// 封装页面数据 list userBean
		try {
			List<UserBean> list = iUserDao.listAllUserBean();
			request.setAttribute("userBeanList", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("pages/user/userList.jsp").forward(
				request, response);

	}
}
