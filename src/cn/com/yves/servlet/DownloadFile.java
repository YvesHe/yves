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
	 * 页面和后台都保持UTF-8下载文本文件，无中文乱码
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// response.setContentType("application/x-msdownload;charset=UTF-8");
		response.setContentType(contentType);
		response.setContentType("charset=UTF-8");

		// 先获取要下载的文件名，本例子默认在files/downloadFile下找文件下载
		String fileName = request.getParameter("fileName");
		String fullName = downPath + File.separator + fileName;

		// 配置response
		response.reset();// 清空buffer,设置页面不缓存

		response.addHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");// Content-Disposition MIME媒体处理类型
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
