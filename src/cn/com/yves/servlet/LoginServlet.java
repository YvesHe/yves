package cn.com.yves.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String userName = request.getParameter("userName").trim();
		String encodeUserName = new String(userName.getBytes("ISO-8859-1"),
				"UTF-8");
		System.out.println(encodeUserName);

		String userPwd = request.getParameter("userPwd").trim();
		System.out.println(userName);

		// validate the data
		if (userName.equals("何宇") && userPwd.equals("123456")) {
			PrintWriter pw = response.getWriter();
			pw.write("登录成功!");
		} else {
			PrintWriter pw = response.getWriter();
			pw.write("登录失败!");
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String userName = request.getParameter("userName").trim();
		String userPwd = request.getParameter("userPwd").trim();
		System.out.println(userName);
		System.out.println( request.getParameter("name").trim());

		// validate the data
		if (userName.equals("何宇") && userPwd.equals("123456")) {
			PrintWriter pw = response.getWriter();
			pw.write("登录成功!");
		} else {
			PrintWriter pw = response.getWriter();
			pw.write("登录失败!");
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
