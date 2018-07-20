package com.geekxia;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ErrorHandle extends HttpServlet {
	
	public ErrorHandle() {
		super();
	}	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 获取请求对象中的错误/异常信息
		Throwable throwable = (Throwable)req.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer)req.getAttribute("javax.servlet.error.status_code");
		String servletName = (String)req.getAttribute("javax.servlet.error.servlet_name");
		if (servletName == null) {
			servletName = "Unknown";
		}
		String reqUrl = (String)req.getAttribute("javax.servlet.error.request_uri");
		if (reqUrl == null) {
			reqUrl = "Unknown";
		}
		
		res.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = res.getWriter();
		out.println("<p>"+servletName+"</p>");
		out.println("<p>"+reqUrl+"</p>");
	}
}
