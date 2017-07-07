/**
 * ID: SmsUtils.java
 * Copyright (c) 2002-2013 Luther Inc.
 * http://xluther.com
 * All rights reserved.
 */
package com.cheng.utils.sms;

import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cheng.utils.exception.ExceptionTools;
import com.cheng.utils.http.HttpConn;

/**
 * 
 * 发送短信
 * 
 * @author yuesiwen
 * @version 0.1
 * @since 0.1
 * @.createdate 2015-4-23 下午2:30:28
 * @.modifydate 2015-4-23 下午2:30:28 <DT><B>修改历史记录</B>
 *              <DD>
 * 
 *              </DD></DT>
 */
public class SmsUtils
{

	private static final Logger logger = LoggerFactory
			.getLogger(SmsUtils.class);

	private String url;

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public boolean sendSms(String mobile, String content, SMSTypeEnum type)
	{
		Map<String, String> map = new TreeMap<String, String>();
		map.put("btype", String.valueOf(type.value()));
		map.put("phone", mobile);
		map.put("content", content);
		HttpConn conn = new HttpConn();
		conn.setInStrEncoding("UTF-8");
		conn.setOutStrEncoding("UTF-8");
		int state = conn.execute(url, map, HttpConn.METHOD_GET);
		logger.info("sent sms state : " + state);
		if (state == HttpConn.SC_OK)
		{
			String data = conn.getResultString();
			logger.info("sent sms data : " + data);
			try
			{
				JSONArray jsonArr = new JSONArray(data);
				boolean result = jsonArr.getJSONObject(0).getBoolean(
						"sendResult");
				if (!result)
				{
                   logger.warn("sent sms fail! result=" + result);
				}
				return result;

			} catch (Exception e)
			{
				logger.error("sent sms error! data : " + data + " ERROR:"
						+ ExceptionTools.getStackTrace(e));
				return false;
			}

		} else
		{
			logger.warn("sent sms fail! state=" + state);
			return false;
		}

	}
	
	
	public boolean sendSms(String mobile, String content)
	{
		return sendSms( mobile, content, SMSTypeEnum.UNKNOW);
	}

	// -----------------------------------------------------------

	public static void main(String[] agre)
	{
		SmsUtils sms=new SmsUtils();
		sms.setUrl("http://120.24.63.116:8141/smsServer/api/sms/send");//正式
		//sms.setUrl("http://117.135.194.102:7007/smsServer/api/sms/send");//测试
		sms.sendSms("13545893514","你好",SMSTypeEnum.FRIEND_RECOMMEND_INVITE);
	}

}
