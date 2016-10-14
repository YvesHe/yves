package cn.com.yves.servlet;

import java.io.IOException;
import java.security.Principal;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletRequestMethodTest extends HttpServlet {

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

		/* anthType，数据来源？ */
		String anthType = request.getAuthType();// 返回用于保护 servlet 的验证方案的名称。
												// 所有 servlet 容器都支持 basic、form 和
												// client certificate 验证，并且可能还支持
												// digest 验证。如果没有验证 servlet，则返回
												// null。

		/* 上下文路径。路径以 "/" 字符开头但不以 "/" 字符结束。 */
		String contextPath = request.getContextPath();// 返回的是 ： /项目名

		/* 获取浏览器端发来的cookie */
		Cookie[] cookies = request.getCookies();
		for (Cookie cc : cookies) {
			String cookieName = cc.getName();// 具体对cookis的操作在cookieServlet中查看
			System.out.println(cookieName);
		}

		/* 从头中获取日期 */
		long time1 = request.getDateHeader("Date");// 没有改头返回时 -1
		long time2 = request.getDateHeader("If-Modified-Since");// 没有改头返回时 -1

		/* 通过属性名从header中获取值 :针对不同返回值有不同的方法 */
		String strValue = request.getHeader("Date");
		int intValue = request.getIntHeader("name1");
		Enumeration<String> headesrEn = request.getHeaders("Accept-Language");// 一些头（比如
																				// Accept-Language）能够以具有不同值的几个头的形式由客户端发送，
																				// 而不采用以逗号分隔的列表的形式发送该头。
																				// 也就是说一些头可能有多个值
		Enumeration<String> en = request.getHeaderNames();// 获取所有的header头中所有的属性名
		while (en.hasMoreElements()) {
			String strMessage = en.nextElement();
			System.out.println(strMessage);
		}

		/* 返回请求方法 */
		String method = request.getMethod();// get post put ...

		/* getPathInfo */
		String pathInfo = request.getPathInfo();// 返回与客户端发出此请求时发送的 URL
												// 相关联的额外路径信息。
												// 额外路径信息位于 servlet
												// 路径之后但在查询字符串之前，并且将以 "/" 字符开头。
												// 如果没有额外路径信息，则此方法返回 null。

		/* Translated */
		String translated = request.getPathTranslated();

		/* 获取查询的的字符串 URL ？之后的的查询字符串 */
		request.getQueryString();

		/* 获取发送请求端的信息 ,这些方法都是继承自ServletRequest */
		request.getRemoteAddr();// 获取发送请求的Ip地址
		request.getRemoteHost();
		request.getRemotePort();// 发送请求的端口
		request.getRemoteUser();

		/* 获取request中的值 */

		String sessionId = request.getRequestedSessionId();// 获取session ID
		StringBuffer url = request.getRequestURL();// 返回：http://localhost:8080/yves/httpServletRequestMethodTest
		String uri = request.getRequestURI();// 返回：/yves/httpServletRequestMethodTest

		/* 获取session */
		request.getSession();// 没有session会自动创建并返回HttpSsession
		request.getSession(false);// 参数为false时候 没有session时不自动创建HttpSession返回Null

		/* getUserPrincipal */
		Principal principal = request.getUserPrincipal();// 返回包含当前已经过验证的用户的名称的
															// java.security.Principal
															// 对象。如果用户没有经过验证，则该方法返回
															// null。?
		/* isRequestedSessionIdFromCookie */
		boolean bool = request.isRequestedSessionIdFromCookie();

		/* 判断session来源 */
		request.isRequestedSessionIdFromCookie();// 检查请求的会话 ID 是否是作为 cookie 进入的。
		request.isRequestedSessionIdFromUrl();// 已过时，被isRequestedSessionIdFromURL取代了
		request.isRequestedSessionIdFromURL();// 检查请求的会话 ID是否是作为请求
												// URL的一部分进入的。还是自己再Servlet中创建的。

		request.isRequestedSessionIdValid();// 检查请求中session ID是否仍然有效。
		request.isUserInRole("role");// 指示指定的逻辑“角色”中是否包含经过验证的用户。角色和角色成员关系可使用部署描述符定义。如果用户没有经过验证，则该方法返回
										// false。

		/* 获取请求的个媒体类型 */
		request.getContentType();

		/* HttpServletRequest接口 extend接口ServletRequest中的方法 */
		request.isSecure();// 是否安全？
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teset");// 继承自ServletRequest的方法
		// 获取转发并设置要转发的路？

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
