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

	// 下载文件的配置在web.xml中的相应servlet中配置了,当然也是可以直接在这里配置的,分别配置了媒体类型,编码格式,和下载文件来源的路径Dir
	@Override
	public void init(ServletConfig config) throws ServletException {
		contentType = config.getInitParameter("contentType");
		encoding = config.getInitParameter("encoding");
		downPath = config.getInitParameter("downPath");
	}

	/**
	 * 页面和后台都保持UTF-8下载文本文件，无中文乱码
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// get方式提交时,该设置无效,可要可不要

		// 下面两句的配置相当于:
		// response.setContentType("application/x-msdownload;charset=UTF-8");
		response.setContentType(contentType);
		response.setContentType("charset=UTF-8");

		// 先获取要下载的文件名，本例子默认在files/downloadFile下找文件下载
		String fileName = new String(request.getParameter("fileName").getBytes(
				"ISO-8859-1"), "UTF-8");// get方式要处理乱码

		String fullName = downPath + File.separator + fileName;

		// 配置response
		response.reset();// 清空buffer,设置页面不缓存

		String resultName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");// 处理下载中文文件名时下载对话框中不显示名字
		response.addHeader("Content-Disposition", "attachment; filename=\""
				+ resultName + "\"");// Content-Disposition MIME媒体处理类型
										// attachment 附件形式
										// 客户使用目标另存为对话框保存指定文件写文件到客户端，
										// 如果没有这句话，则浏览器会自动打开文档，不会弹出下载对话框。

		// 虽然doGet（）方法已经向外抛出了异常，但是我还是习惯在里面捕获异常处理
		File file = new File(fullName);
		if (file.exists() && file.isFile()) {
			InputStream is = null;
			OutputStream os = null;

			try {
				is = new FileInputStream(file);
				os = response.getOutputStream();
				byte[] buff = new byte[1024];
				int len = -1;
				while ((len = is.read(buff)) != -1) {
					os.write(buff, 0, len);
				}
				os.flush();
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
