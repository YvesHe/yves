package cn.com.yves.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormServlet extends HttpServlet {

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

		String text1 = request.getParameter("text1");

		Map<String, Object> myMap = request.getParameterMap();
		// myMap.entrySet().iterator();
		Iterator<String> iterMap = myMap.keySet().iterator();
		while (iterMap.hasNext()) {
			Object obj = (Object) myMap.get(iterMap.next());
			System.out.println(obj);
		}

		Enumeration en = request.getAttributeNames();
		while (en.hasMoreElements()) {
			Object obj = (Object) en.nextElement();
			System.out.println(obj.toString());

			// System.out.println("obj.toString()==="+obj.toString());
			if (obj.toString().trim().equals("LastPage")) {
				System.out.println("LastPage \n");
			} else if (obj.toString().trim().equals("NextPage")) {
				System.out.println("NextPage");
			}
		}
		System.out.println(en);

		String[] files = request.getParameterValues("file");

		request.setAttribute("name", "heyu");

		response.sendRedirect("/yves/showUserServlet");
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
