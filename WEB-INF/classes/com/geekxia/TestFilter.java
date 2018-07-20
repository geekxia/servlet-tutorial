package com.geekxia;

import javax.servlet.*;
import java.util.*;

public class TestFilter implements Filter {
	// 过滤器初始化
	public void init(FilterConfig config) throws ServletException {
		String site = config.getInitParameter("Site");
		System.out.println("网站名称：" + site);
	}
	// 过滤器执行阶段
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws java.io.IOException, ServletException {
		System.out.println("geekxia");
		chain.doFilter(req, res);
	}
	// 过滤器销毁阶段
	public void destroy() {}
}
