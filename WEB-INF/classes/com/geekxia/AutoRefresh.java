package com.geekxia;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.*;


public class AutoRefresh extends HttpServlet {
	public AutoRefresh() {
		super();
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 设置每 5 秒自动刷新一次页面
		res.setIntHeader("Refresh", 5);
		
		res.setContentType("text/html;charset=UTF-8");
		// 获取当前时间
		Calendar cal = new GregorianCalendar();
		String am_pm;
		int hour = cal.get(Calendar.HOUR);
		int min = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		if (cal.get(Calendar.AM_PM) == 0) {
			am_pm = "AM";
		} else {
			am_pm = "PM";
		}
		String CT = hour + ":" + min + ":" + second + " " + am_pm;
		
		PrintWriter out = res.getWriter();
		out.println("<h1>"+ CT +"</h1>");
	}
}
