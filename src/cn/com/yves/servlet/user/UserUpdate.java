package cn.com.yves.servlet.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.yves.bean.UserBean;
import cn.com.yves.dao.UserDaoInf;
import cn.com.yves.dao.impl.UserDaoImpl;

public class UserUpdate extends HttpServlet {
	private UserDaoInf iUserDao = new UserDaoImpl();

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
		// a标签跳转 走doGet()方法
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

		/* 1.判断是否登录 2.高级权限才可以修改低级权限的 */
		String userId = request.getParameter("userId").trim();
		HttpSession session = request.getSession();
		UserBean selfBean = (UserBean) session.getAttribute("userBean");

		// 没有登录,返回登录
		if (selfBean == null) {
			String message = "<html><head><script type='text/javascript'>alert('请先登录!');</script></head><body></body></html>";
			request.setAttribute("message", message);
			request.getRequestDispatcher("pages/user/userLogin.jsp").forward(
					request, response);
			return;
		}

		// 只能修改比自己权限低的用户(虽然页面只显示了可以修改的用户,但是为了防止用户恶意直接来访问,在这里还是做一次权限验证)

		// 获取要修改userBean
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

			} else {// 权限足够,可以更新

				try {
					if (iUserDao.updateUserBean(getUpdateInfoFromJSp(request,
							response)) == true) {// 更新成功

						String message = "<html><head><script type='text/javascript'>alert('更新成功!');</script></head><body></body></html>";
						request.setAttribute("message", message);

						request.getRequestDispatcher("userList").include(
								request, response);
					} else {// 更新失败
						String message = "<html><head><script type='text/javascript'>alert('更新失败!');</script></head><body></body></html>";
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
	 * 从页面获取数据要更新的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private static UserBean getUpdateInfoFromJSp(HttpServletRequest request,
			HttpServletResponse response) {
		UserBean uBean = new UserBean();

		String userId = (String) request.getParameter("userId");
		String userName = (String) request.getParameter("userName");
		String userPhoneNumber = (String) request
				.getParameter("userPhoneNumber");
		String userPwd = (String) request.getParameter("userPwd");
		String userNickName = (String) request.getParameter("userNickName");
		String userDesc = (String) request.getParameter("userDesc");
		String userPowerId = (String) request.getParameter("userPowerId");

		uBean.setUserId(userId.trim());
		uBean.setUserName(userName.trim());
		uBean.setUserPhoneNumber(userPhoneNumber.trim());
		uBean.setUserPwd(userPwd.trim());
		uBean.setUserNickName(userNickName.trim());
		uBean.setUserDesc(userDesc.trim());
		uBean.setUserPowerId(Integer.valueOf(userPowerId.trim()));

		return uBean;
	}
}
