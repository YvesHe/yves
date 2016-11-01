package cn.com.yves.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		try {
			boolean bool = iUserDao.deleteUserBean(userId);
			if (bool == true) {// 删除成功
				out.println("<html><head><script type='text/javascript'>alert('删除成功!');</script></head><body></body></html>");
				request.getRequestDispatcher("pages/user/userList.jsp")
						.include(request, response);
			} else {// 删除失败
				out.println("<html><head><script type='text/javascript'>alert('删除失败!');</script></head><body></body></html>");
				request.getRequestDispatcher("pages/user/userList.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
