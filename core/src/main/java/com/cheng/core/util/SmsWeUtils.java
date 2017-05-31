/**
 * ID: SmsWeUtils.java
 * Copyright (c) 2002-2013 Luther Inc.
 * http://xluther.com
 * All rights reserved.
 */
package com.cheng.core.util;

import java.util.Map;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 短信通道2
 *
 * @author			Kama Luther
 * @version			0.1
 * @since           0.1
 * @.createdate     2014-7-8 上午09:03:21
 * @.modifydate     2014-7-8 上午09:03:21
 * <DT><B>修改历史记录</B>
 * <DD>
 * 
 * </DD>
 * </DT>
 */
public class SmsWeUtils
{
	
	private static final Logger logger = LoggerFactory.getLogger(SmsWeUtils.class); 
	
	//-----------------------------------------------------------
	
	private String url;
	private String tokenid;
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	public void setTokenid(String tokenid)
	{
		this.tokenid = tokenid;
	}
	
	//-----------------------------------------------------------


	public boolean sendSms(String mobile, String content)
	{
		boolean flag = false;
		Map<String, String> map = new TreeMap<String, String>();
		map.put("TokenID", tokenid);
		map.put("FormatID", "8");
		map.put("ScheduleDate", "2014-08-01");
		map.put("mobile", mobile);
		map.put("content", content);
		HttpConn conn = new HttpConn();
		conn.setInStrEncoding("UTF-8");
		conn.setOutStrEncoding("UTF-8");
		int state = conn.execute(url, map, HttpConn.METHOD_POST);
		logger.info("state : " + state);
		if(state == HttpConn.SC_OK)
		{
			String data = conn.getResultString();
			logger.info("data : " + data);
			if(data != null && data.indexOf("OK:")>0)
			{
				flag = true;
			}
		}
		return flag;
	}

	//-----------------------------------------------------------

}
