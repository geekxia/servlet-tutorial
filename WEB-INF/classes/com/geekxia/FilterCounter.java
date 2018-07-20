package com.geekxia;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import java.io.IOException;
import javax.servlet.ServletException;

public class FilterCounter implements Filter {
	private int counter;
	
	// 在 init() 方法中初始化这个计数器
	public void init(FilterConfig config) throws ServletException {
		counter = 0;
	}
	// 在 doFilter() 方法中，累加这个计数器
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// 访问量累加
		counter++;
		System.out.print("网站访问量："+counter);
		// 把请求和响应，回传至过滤器链
		chain.doFilter(req, res);
	}
	public void destroy() {
		// 在这里，把计数器的值，写入数据库
	}
}
