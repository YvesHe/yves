package cn.com.yves.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试URL: http://localhost:8080/yves/servletContextTest
 * 
 * @author Yves He
 * 
 */
public class ServletContextTest extends HttpServlet {

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

		// getServletContext()方法是 ServletConfig 接口 中定义的方法
		// GenericServlet 类实现了 接口 ServletConfig
		// HttpServlet 类 继承 GenericServlet

		ServletContext application = this.getServletContext();// servletContext一般命名为application(servlet上下文的意思)

		// 获取web.xml中配置的初始化参数
		String initParamString = application
				.getInitParameter("servletContextTestParameter");
		System.out.println(initParamString);// 获取web.xml中配置<context-param>节点name为servletContextTestParameter的值

		// 获取所有配置的初始化参数的名称,然后遍枚举
		System.out.println("********开始遍历所有***********");
		Enumeration initParameterNames = application.getInitParameterNames();
		while (initParameterNames.hasMoreElements()) {
			String initParameter = (String) initParameterNames.nextElement();
			System.out.print(initParameter + "\t");
			System.out.println(application.getInitParameter(initParameter));
		}
		System.out.println("********遍历所有结束***********");

		// servletContext下还有很多方法...待测试.
		application.getContextPath();
		application.getServletContextName();

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
