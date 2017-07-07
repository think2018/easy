/**
 * ID: DateUtils.java
 * Copyright (c) 2002-2013 Luther Inc.
 * http://xluther.com
 * All rights reserved.
 */
package com.cheng.utils.date;

import java.util.Calendar;

/**
 * 日期工具集
 *
 * @author			Kama Luther
 * @version			0.1
 * @since           0.1
 * @.createdate     2014-9-3 下午01:38:01
 * @.modifydate     2014-9-3 下午01:38:01
 * <DT><B>修改历史记录</B>
 * <DD>
 * 
 * </DD>
 * </DT>
 */
public class DateUtils
{
	
	public static String getFormatDate(int offset)
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, offset);
		
		String f = "%1$4d%2$02d%3$02d";
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		return String.format(f, year, month, day);
	}

}
