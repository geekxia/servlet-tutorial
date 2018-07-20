package com.geekxia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFile extends HttpServlet {
	public UploadFile() {
		super();
	}
	// 指定上传目录
	private static final String UPLOAD_DIR = "upload";
	
	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024*1024*3;
	private static final int MAX_FILE_SIZE = 1024*1024*40;
	private static final int MAX_REQUEST_SIZE = 1024*1024*50;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		// 检测是不是多媒体上传
		if (!ServletFileUpload.isMultipartContent(req)) {			
			out.println("表单必须包含 enctype=multipart/form-data");
			out.flush();
			return;
		}
		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
		upload.setHeaderEncoding("UTF-8");
		
		String uploadPath = req.getServletContext().getRealPath("./")+File.separator + UPLOAD_DIR;
		
		// 如果目录不存在，就创建目录
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			List<FileItem> formItems = upload.parseRequest(req);
			if(formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item: formItems) {
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
//						out.println(filePath);
						
						// 保存文件至硬盘
						item.write(storeFile);
						req.setAttribute("message", "文件上传成功");
					}
				}
			}
		} catch (Exception e) {
			req.setAttribute("message", "错误信息："+e.getMessage());
		}
//		out.println("<p>上传成功</p>");
		// 跳转至新页面
		req.getServletContext().getRequestDispatcher("./success.html").forward(req, res);
	}
	
}
