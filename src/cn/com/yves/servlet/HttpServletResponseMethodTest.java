package cn.com.yves.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletResponseMethodTest extends HttpServlet {

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
		/* 有Header都是跟消息头有关的设置 */

		// response.setIntHeader("Refresh", 5);// 设置刷新

		/* 为浏览器发送cookis */
		Cookie cookie = new Cookie("name", "value");
		response.addCookie(cookie);

		response.encodeURL("UTF-8");// 转码

		/* setHeader和addHeader的区别,总结：如果不存在一个属性有多个值一般用setHeader方法就行了 */
		response.setHeader("name", "yves");// 如果header中没有属性,则创建name属性,赋值为yves,如果之前有name属性则覆盖之前的值
		response.addHeader("name", "yves2");// 向name赋值追加一个值为yves2
											// 此时name有两个值,如果之前没有name属性,则创建

		/* 在header中设置Date */
		response.setDateHeader("Date", new Date().getTime());

		/* 判断是否包括特定的消息头 */
		boolean bool = response.containsHeader("Date");
		System.out.println(bool);

		/* 发送错误消息时候的状态码和错误详情 */
		// response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
		// "服务器异常");// 发送错误信息
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
	}

}
