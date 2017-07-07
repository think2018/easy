/**
 * ID: StringUtils.java
 * Copyright (c) 2002-2013 Luther Inc.
 * http://xluther.com
 * All rights reserved.
 */
package com.cheng.utils.string;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 字符串工具集
 * 
 * @author Kama Luther
 * @version 0.1
 * @since 0.1
 * @.createdate 2014-7-3 下午01:27:01
 * @.modifydate 2014-7-3 下午01:27:01 <DT><B>修改历史记录</B>
 *              <DD>
 * 
 *              </DD></DT>
 */
public class StringUtils
{

	public static boolean IsEmpty(String str)
	{
		return (str == null) || (str.trim().length() == 0);
	}

	public static boolean NotEmpty(String str)
	{
		return (str != null) && (str.trim().length() > 0);
	}

	public static boolean IsNull(String str)
	{
		return (str == null);
	}

	public static boolean NotNull(String str)
	{
		return (str != null);
	}

	public static Set<String> spillByComma(String str)
	{
		Set<String> result = new HashSet<String>();
		if (IsEmpty(str))
			return result;
		String[] tempArr = str.split(",");
		if (tempArr == null)
			return result;
		for (String temp : tempArr)
			result.add(temp);
		return result;
	}

	public static String toCommaSpaceStr(Set<String> set)
	{
		if (set == null || set.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder();
		for (String str : set)
		{
			sb.append(str);
			sb.append(",");
		}
		return sb.substring(0, sb.length() - 1);
	}

	public static boolean isInteger(String str)
	{
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

}
