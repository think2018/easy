/**
 * ID: KeyGenUtils.java
 * Copyright (c) 2002-2013 Luther Inc.
 * http://xluther.com
 * All rights reserved.
 */
package com.cheng.core.util;

/**
 * 键值生成器
 * 
 * @author Kama Luther
 * @version 0.1
 * @since 0.1
 * @.createdate 2014-5-4 上午11:46:02
 * @.modifydate 2014-5-4 上午11:46:02 <DT><B>修改历史记录</B>
 *              <DD>
 * 
 *              </DD></DT>
 */
public class KeyGenUtils
{

	public static final void main(String[] args)
	{
		System.out.println(generate(6, "1", 2)); // app
		System.out.println(generate(12, "41", 2)); // user
		System.out.println(generate(10, "41", 2)); // company

		System.out.println(generate(6, "1", 123456));
		System.out.println(generate(12, "41", 123456123456L));
		System.out.println(generate(10, "41", 1234567890));

		System.out.println(generate(6, 2));
		System.out.println(generate(12, 2));
		System.out.println(generate(10, 2));

		System.out.println(generate(6, 1123456));
		System.out.println(generate(12, 1123456123456L));
		System.out.println(generate(10, 11234567890L));
	}

	/**
	 * 键值生成
	 * 
	 * @param len
	 *            预期总长度
	 * @param prefix
	 *            前导字符串
	 * @param suffix
	 *            后面补进数字
	 * @return
	 */
	public static String generate(int len, String prefix, long suffix)
	{
		if (prefix == null)
			return null;

		String s_suffix = String.valueOf(suffix);

		int suflen = s_suffix.length();
		int prelen = prefix.length();
		if (len < suflen)
		{
			return s_suffix.substring(0, len);
		} else if (len < suflen + prelen)
		{
			return prefix.substring(0, len - suflen) + s_suffix;
		} else
		{
			return String.format("%s%0" + (len - prelen) + "d", prefix, suffix);
		}
	}

	public static String generate(int len, long suffix)
	{
		String s_suffix = String.valueOf(suffix);
		int suflen = s_suffix.length();
		if (len < suflen)
		{
			return s_suffix.substring(0, len);
		} else
		{
			return String.format("%0" + len + "d", suffix);
		}
	}

}
