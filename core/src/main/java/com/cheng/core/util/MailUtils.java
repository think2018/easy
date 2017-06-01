///**
// * ID: MailUtils.java
// * Copyright (c) 2002-2013 Luther Inc.
// * http://xluther.com
// * All rights reserved.
// */
//package com.cheng.core.util;
//
//import java.io.IOException;
//import java.util.Map;
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//
///**
// * 邮件工具集
// *
// * @author			Kama Luther
// * @version			0.1
// * @since           0.1
// * @.createdate     2014-6-13 下午02:35:25
// * @.modifydate     2014-6-13 下午02:35:25
// * <DT><B>修改历史记录</B>
// * <DD>
// * 
// * </DD>
// * </DT>
// */
//public class MailUtils
//{
//	
//	private static final Logger logger = LoggerFactory.getLogger(MailUtils.class); 
//	
//	//-----------------------------------------------------------
//	
//	private JavaMailSender 			sender;
//	private FreeMarkerConfigurer 	config;
//	private String 					from;
//	
//	public void setSender(JavaMailSender sender)
//	{
//		this.sender = sender;
//	}
//	public void setConfig(FreeMarkerConfigurer config)
//	{
//		this.config = config;
//	}
//	public void setFrom(String from)
//	{
//		this.from = from;
//	}
//	
//	//-----------------------------------------------------------
//	
//	public boolean sendText(String to, String subject, String text)
//	{
//		try
//		{
//			SimpleMailMessage message = new SimpleMailMessage();
//			message.setTo(to);
//			message.setFrom(from);
//			message.setSubject(subject);
//			message.setText(text);
//			sender.send(message);
//			return true;
//		}
//		catch(MailException e)
//		{
//			logger.error(e.getMessage());
//			return false;
//		}
//	}
//	
//	public boolean sendHtml(String to, String subject, String text)
//	{
//		try
//		{
//			MimeMessage message = sender.createMimeMessage();
//			MimeMessageHelper messageHelper = new MimeMessageHelper(message, false, "UTF-8");
//			messageHelper.setTo(to);
//			messageHelper.setFrom(from);
//			messageHelper.setSubject(subject);
//			messageHelper.setText(text, true);
//			sender.send(message);
//			return true;
//		}
//		catch(MailException e)
//		{
//			logger.error(e.getMessage());
//			return false;
//		}
//		catch (MessagingException e)
//		{
//			logger.error("Merge Mail Error : " + e.getMessage());
//			return false;
//		}
//	}
//	
//	//-----------------------------------------------------------
//	
//	public String getTemplateText(String template, Map<String, Object> map)
//	{
//		try
//		{
//			Template tpl= config.getConfiguration().getTemplate(template);
//			return FreeMarkerTemplateUtils.processTemplateIntoString(tpl, map);
//		}
//		catch (IOException e)
//		{
//			logger.error("Template File Read Error : " + e.getMessage());
//			return null;
//		}
//		catch (TemplateException e)
//		{
//			logger.error("Merge Template Error : " + e.getMessage());
//			return null;
//		} 
//	}
//	
//	public boolean sendTextWithTemplate(String to, String subject, String template, Map<String, Object> map)
//	{
//		String text = getTemplateText(template, map);
//		return (text!=null) && sendText(to, subject, text);
//	}
//	
//	public boolean sendHtmlWithTemplate(String to, String subject, String template, Map<String, Object> map)
//	{
//		String text = getTemplateText(template, map);
//		return (text!=null) && sendHtml(to, subject, text);
//	}
//	
//	//-----------------------------------------------------------
//	
//	/**
//	 * 发送普通带一张图片的Html邮件
//	 */
//	public boolean sendHtmlWithImage(String to, String subject, String text, String imagepath)
//	{
//		try
//		{
//			MimeMessage message = sender.createMimeMessage();
//			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//			messageHelper.setTo(to);
//			messageHelper.setFrom(from);
//			messageHelper.setSubject(subject);
//			messageHelper.setText(text, true);
//			
//			//Content="<html><head></head><body><img src=\"cid:image\"/></body></html>";
//			//图片：<img src='cid:image'/>
//			FileSystemResource file = new FileSystemResource(imagepath);
//			messageHelper.addInline("img-" + file.getFile().length(), file);
//			
//			sender.send(message);
//			return true;
//		}
//		catch(MailException e)
//		{
//			logger.error(e.getMessage());
//			return false;
//		}
//		catch (MessagingException e)
//		{
//			logger.error("Merge Mail Error : " + e.getMessage());
//			return false;
//		}
//	}
//	
//	/**
//	 * 发送普通带一个附件的Html邮件
//	 */
//	public boolean sendHtmlWithAttach(String to, String subject, String text, String filepath)
//	{
//		try
//		{
//			MimeMessage message = sender.createMimeMessage();
//			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//			messageHelper.setTo(to);
//			messageHelper.setFrom(from);
//			messageHelper.setSubject(subject);
//			messageHelper.setText(text, true);
//			
//			FileSystemResource file = new FileSystemResource(filepath);
//			messageHelper.addAttachment(file.getFilename(), file);
//			
//			sender.send(message);
//			return false;
//		}
//		catch(MailException e)
//		{
//			logger.error(e.getMessage());
//			return false;
//		}
//		catch (MessagingException e)
//		{
//			logger.error("Merge Mail Error : " + e.getMessage());
//			return false;
//		}
//	}
//	
//	//-----------------------------------------------------------
//
//}
