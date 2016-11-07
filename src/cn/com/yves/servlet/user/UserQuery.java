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
import cn.com.yves.constant.Constant;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.dao.impl.UserDaoImpl;

public class UserQuery extends HttpServlet {
	UserDaoInf iUserDao = new UserDaoImpl();

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

		/* 由于是内部使用,查询我这里也做权限处理; 1.要登录 2.显示自己等级低的账号信息(暂时先显示符合条件的所有数据) */
		// 判断登录
		HttpSession session = request.getSession(true);
		UserBean seftBean = (UserBean) session.getAttribute("userBean");
		if (seftBean == null) {
			String message = "<html><head><script type='text/javascript'>alert('请先登录!');</script></head><body></body></html>";
			request.setAttribute("message", message);
			request.getRequestDispatcher("pages/user/userLogin.jsp").forward(
					request, response);
			return;
		}

		String queryWord = request.getParameter("queryWord").trim();
		try {
			// 暂时先去查询email账号
			List<UserBean> list = iUserDao.getUserBeanByAllCountFuzzy(queryWord,
					Constant.USER_COUNT_EMAIL);

			request.setAttribute("userBeanList", list);
			request.getRequestDispatcher("pages/user/userQueryShow.jsp")
					.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
