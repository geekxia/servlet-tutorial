package com.geekxia;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Counter extends HttpServlet {
	public Counter() {
		super();
	}
	
	private int counter;
	// 在 init()方法中初始化这个计数器，或者从数据表中查询这个计数器并初始化
	public void init() {
		counter = 0;
	}
	// 在 doGet() 和 doPost() 中累加这个计数器
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		// 累加计数器
		counter++;
		PrintWriter out = res.getWriter();
		out.println("<p>"+ counter +"</p>");
	}
	public void destroy() {
		// 把这个计数器存储到数据表中
	}
}
