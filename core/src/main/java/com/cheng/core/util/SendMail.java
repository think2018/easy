package com.cheng.core.util;

import java.util.Properties;

import javax.mail.Message;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	private String host = ""; // smtp服务器
	private String user = ""; // 用户名
	private String pwd = ""; // 密码
	private String from = ""; // 发件人地址
	private String subject = ""; // 邮件标题

	public SendMail() {
		ConfigTool emailConfigTool = new ConfigTool("email.properties");
		host = emailConfigTool.getValue("email.host");
		user = emailConfigTool.getValue("email.username");
		pwd = emailConfigTool.getValue("email.password");
		from = emailConfigTool.getValue("email.from");
		subject = emailConfigTool.getValue("email.subject");
	}

	public void send(String toEmail, String content) {
		Properties props = new Properties();
		// 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
		props.put("mail.smtp.host", host);
		// 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
		props.put("mail.smtp.auth", "true");
		// 用刚刚设置好的props对象构建一个session
		Session session = Session.getDefaultInstance(props);
		// 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
		// 用（你可以在控制台（console)上看到发送邮件的过程）
		session.setDebug(true);
		// 用session为参数定义消息对象
		MimeMessage message = new MimeMessage(session);
		try {
			// 加载发件人地址
			message.setFrom(new InternetAddress(from));
			// 加载收件人地址
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			// 加载标题
			System.out.println(subject);
			message.setSubject(subject);

			// 设置邮件的文本内容
			message.setContent(content, "text/html;charset=gb2312");
			// 保存邮件
			message.saveChanges();
			// 发送邮件
			Transport transport = session.getTransport("smtp");
			// 连接服务器的邮箱
			transport.connect(host, user, pwd);
			// 把邮件发送出去
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		DateC dateC = new DateC();
		SendMail sendMail = new SendMail();
		// 设置收件人地址,邮件内容

		StringBuffer theMessage = new StringBuffer();
		theMessage.append("<h2><font color=red>这倒霉孩子</font></h2>");
		theMessage.append("<hr>");
		theMessage.append("<i>年年失望年年望</i>");

		// sendMail.send("tkgg_qq@126.com", theMessage.toString());
		sendMail.send("tkgg_qq@126.com", "hi");
		// sendMail.send("tkgg_qq@126.com", dateC.getCurrentFullDateTime());

	}
}