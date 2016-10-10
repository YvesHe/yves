package cn.com.yves.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadingFile extends HttpServlet {

	// 判断是否是是文件上传的文件。
	private boolean isMultipart;
	// 保存文件的路径
	private String filePath;
	// 上传文件最大的大小
	private int maxFileSize = 50 * 1024;
	// 内存中最大的文件？
	private int maxMemSize = 4 * 1024;

	private File file;

	@Override
	public void init() throws ServletException {
		// 获取文件将别存储的位置。
		filePath = getServletContext().getInitParameter("file-upload");
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

		throw new ServletException("GET method used with "
				+ getClass().getName() + ": POST method required.");
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

		// 思考： 文件上传为什么不能用get方式
		isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			response.getWriter().print("It isn't  file upload  !");
		}

		// 配置factory
		DiskFileItemFactory dItemFactory = new DiskFileItemFactory();
		dItemFactory.setSizeThreshold(maxMemSize); // 文件大小的最大值将被存储在内存中
		dItemFactory.setRepository(new File(
				"E:\\git\\yves\\WebRoot\\uploadingFile\\temp"));

		// 创建一个新的文件上传处理程序，以及配置。
		ServletFileUpload upload = new ServletFileUpload(dItemFactory);
		upload.setSizeMax(maxFileSize);// 允许上传的文件大小的最大值

		// 解析请求，获取文件项
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			// 处理上传的文件项
			Iterator<FileItem> i = fileItems.iterator();
			while (i.hasNext()) {
				FileItem fi = i.next();
				if (!fi.isFormField()) {
					// 获取上传文件的参数
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					// 写入文件
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(
								filePath
										+ fileName.substring(fileName
												.lastIndexOf("\\")));
					} else {
						file = new File(
								filePath
										+ fileName.substring(fileName
												.lastIndexOf("\\") + 1));
					}
					fi.write(file);
					response.getWriter().println(
							"Uploaded Filename: " + fileName + "<br>");
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
