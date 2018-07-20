package com.geekxia;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Locale;
import java.util.Date;
import java.text.DateFormat;
import java.text.NumberFormat;

public class GetLocale extends HttpServlet {
	public GetLocale() {
		super();
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 获取客户端的区域对象
		Locale loc = req.getLocale();
		
		// 获取当前区域的语言、国家
		String language = loc.getLanguage();
		String country = loc.getCountry();
		
		// 对当前区域的时间进行格式化
		String date = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, loc).format(new Date());
		
		// 对当前区域的货币格式化、百分比格式化
		NumberFormat nft = NumberFormat.getCurrencyInstance(loc);
		String formattedCurr = nft.format(1000000);
		String formattedPerc = nft.format(0.51);
		
		
		// 设置语言
		res.setHeader("Content-Language", "es");
		// 设置响应内容类型
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		out.println("<h1>"+ language +"</h1>");
		out.println("<h1>"+ country +"</h1>");
		out.println("<h1>"+ date +"</h1>");
		out.println("<h1>"+ formattedCurr +"</h1>");
		out.println("<h1>"+ formattedPerc +"</h1>");
	}
}
