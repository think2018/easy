package com.cheng.client.email;

public class Main {
	public static void main(String[] args) {

		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.126.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("sztemp2017@126.com");
		mailInfo.setPassword("cyf2017");// 您的邮箱密码
		mailInfo.setFromAddress("sztemp2017@126.com");
//		mailInfo.setToAddress("sztemp2018@126.com");
		mailInfo.setToAddress("tkgg_qq@126.com");
		mailInfo.setSubject("hello");
		mailInfo.setContent("hello");
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// 发送文体格式
//		sms.sendHtmlMail(mailInfo);// 发送html格式
	}
}
