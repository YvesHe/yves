package cn.com.yves.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadXML extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		response.setContentType("text/xml;charset=UTF-8");
		// send the data
		PrintWriter pw = response.getWriter();
		pw.write("<?xml version='1.0' encoding='UTF-8'?>");
		pw.write("<xmlData>");
		pw.write("<name address='hunan'>heyu</name>");
		pw.write("<age>21</age>");
		pw.write("<sex>男</sex>");
		pw.write("</xmlData>");
	}
}
