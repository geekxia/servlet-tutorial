package com.geekxia;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;


public class DatabaseAccess extends HttpServlet {
	public DatabaseAccess() {
		super();
	}
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/GEEKXIA";
	private static final String USER = "root";
	private static final String PASS = "123456";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		try {
			// 注册JDBC驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 创建一个数据库连接
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// 执行SQL语句
			stmt = conn.createStatement();
			String sql = "SELECT id, name, url FROM geekxia";
			ResultSet rs = stmt.executeQuery(sql);
			
			// 从结果集中读取数据
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String url = rs.getString("url");
				
				out.println("<p>"+ id +"</p>" + "<p>"+ name +"</p>" + "<p>"+ url +"</p>");
			}
			// 完成后关闭
			rs.close();
			stmt.close();
			conn.close();
		} catch(SQLException e) {
			// 处理JDBC错误
			e.printStackTrace();
		} catch(Exception e) {
			// 处理Class.forName错误
			e.printStackTrace();
		} finally {
			// 关闭资源 
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
 }
