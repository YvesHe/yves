package cn.com.yves.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadFile extends HttpServlet {
	private String contentType;
	private String encoding;
	private String downPath;

	@Override
	public void init(ServletConfig config) throws ServletException {
		contentType = config.getInitParameter("contentType");
		encoding = config.getInitParameter("encoding");
		downPath = config.getInitParameter("downPath");
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
		// 先获取要下载的文件名，本例子默认在files/downloadFile下找文件下载
		String fileName = request.getParameter("fileName");
		String fullName = downPath + File.separator + fileName;

		// 发送到客户端吗？
		response.reset();
		response.setContentType(contentType);
		response.addHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");

		// 写文件到客户端，虽然doGet（）方法已经向外抛出了异常，但是我还是习惯在里面捕获异常处理
		File file = new File(fullName);
		if (file.exists() && file.isFile()) {
			InputStream is = null;
			OutputStream os = null;

			try {
				is = new FileInputStream(file);
				os = response.getOutputStream();
				byte[] buff = new byte[1024];
				int len = -1;
				while ((len = is.read(buff, 0, len)) != -1) {
					os.write(buff, 0, len);
				}
			} catch (IOException e) {
			} finally {
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}
			}

		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}
}
