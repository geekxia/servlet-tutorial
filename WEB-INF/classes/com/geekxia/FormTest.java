package com.geekxia;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.*;

public class FormTest extends HttpServlet {
	public FormTest() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		// 读取HTTP头信息
		Enumeration headerNames = req.getHeaderNames();
		while(headerNames.hasMoreElements()) {			
			String key = (String)headerNames.nextElement();
			String value = req.getHeader(key);
			out.println("<div>");
			out.println("<span>" + key + "</span>" );			
			out.println("<span>" + value + "</span>");
			out.println("</div>");
		}
		
		
		String title = "GeekXia";		
		// 处理GET请求，请求参数为 name 和 url
		String name = new String(req.getParameter("name").getBytes("ISO8859-1"), "UTF-8");
		String url = req.getParameter("url");
		
		out.println("<!DOCTYPE html><html><head><title>" + title + "</title></head>\n" +
			"<body><h1>" + name + "</h1>" + "<h1>" + url + "</h1></body></html>");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setIntHeader("Refresh", 5);
		res.setContentType("text/html;charset=UTF-8");
		Calendar cale = Calendar.getInstance();
		Date tasktime = cale.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(tasktime);
		PrintWriter out = res.getWriter();
		out.println("<h1>"+ nowTime +"</h1>");
		
//		doGet(req, res);
	}
}
