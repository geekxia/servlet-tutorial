package com.geekxia;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Email extends HttpServlet {
	public Email() {
		super();
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 收件人
		String to = "108508992@qq.com";
		// 发件人
		String from = "448914712@qq.com";
		// 发送邮件的主机地址
		String host = "localhost";
		// 获取系统属性
		Properties props = System.getProperties();
		// 设置邮件服务器，并向邮件服务器提供用户验证
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.user", "myuser");
		props.setProperty("mail.password", "mypwd");
		// 获取默认的 session 对象
		Session session = Session.getDefaultInstance(props);
		// 设置响应内容类型
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		try {
			// 创建一个默认的 MimeMessage 对象
			MimeMessage msg = new MimeMessage(session);
			// 设置 from
			msg.setFrom(new InternetAddress(from));
			// 设置 to
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// 设置 subject
			msg.setSubject("这是首行");
			// 创建消息体部分
			BodyPart msgBody = new MimeBodyPart();
			// 添加邮件内容
			msgBody.setText("这是邮件内容");
			// 创建一个多部分消息
			Multipart multi = new MimeMultipart();
			// 设置文本消息部分
			multi.addBodyPart(msgBody);
			// 附件
			msgBody = new MimeBodyPart();
			String filename = "file.txt";
			DataSource source = new FileDataSource(filename);
			msgBody.setDataHandler(new DataHandler(source));
			msgBody.setFileName(filename);
			multi.addBodyPart(msgBody);
			
			// 发送邮件
			msg.setContent(multi);
			Transport.send(msg);
			
			out.println("<h1>发送电子邮件成功</h1>");
		} catch(MessagingException e) {
			e.printStackTrace();
		}
	}
}
