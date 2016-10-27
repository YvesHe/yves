package cn.com.yves.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * session 的声明周期是一次回话,只要浏览器没关闭,session 在有效的session时间内session就一直有效
 * 
 * @author User
 * 
 */
public class SessionTrack extends HttpServlet {

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
		// true 如果没有session 则创建
		HttpSession session = request.getSession(true);
		session.setAttribute("yves", "yves");

		// session id
		System.out.println(session.getId());

		// 遍历session的Attribute
		System.out.println("****遍历开始******");
		Enumeration en = session.getAttributeNames();
		while (en.hasMoreElements()) {
			Object obj = en.nextElement();
			System.out.println(obj);
		}
		System.out.println("****遍历结束******");

		// 数据传递 : session与 是sendRedirect 还是 forward 无关.
		response.sendRedirect("sessionTrack2");
		// request.getRequestDispatcher("sessionTrack2")
		// .forward(request, response);

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
