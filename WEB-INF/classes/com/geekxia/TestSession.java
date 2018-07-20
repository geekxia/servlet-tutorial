package com.geekxia;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.*;


public class TestSession extends HttpServlet {
	public TestSession() {
		super();
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 如果不存在session会话，就创建一个session对象
		HttpSession session = req.getSession(true);
		// 使用session对象
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<h1>"+ session.getId() +"</h1>");
	}
}
