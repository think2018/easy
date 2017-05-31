/**
 * ID: IDCardUtils.java
 * Copyright (c) 2002-2013 Luther Inc.
 * http://xluther.com
 * All rights reserved.
 */
package com.cheng.core.util;

/**
 * 身份证工具
 * 
公民身份号码是特征组合码,由十七位数字本体码和一位数字校验码组成.
排列顺序依次为:六位数字地址码,八位数字出生日期码,三位数字顺序码和一位数字校验码。 
　 
1、地址码 (6位)
表示编码对象常住户口所在县（市、旗、区）的行政区划代码，按 GB/T 2260 的规定执行。 

2、出生日期码 (8位)
表示编码对象出生的年、月、日，按 GB/T 7408 的规定执行。年、月、日代码之间不用分隔符。 
例：某人出生日期为 1966年10月26日，其出生日期码为 19661026。 

3、顺序码 (3位)
表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配给女性。 

4 、校验码 (1位)
校验码采用ISO 7064：1983，MOD 11-2 校验码系统。 

　 （1）十七位数字本体码加权求和公式 
　　　 S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和 
　　　 Ai:表示第i位置上的身份证号码数字值 
　　　 Wi:表示第i位置上的加权因子 
　　　 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 

　 （2）计算模 
　　　 Y = mod(S, 11) 

　 （3）通过模得到对应的校验码 
　　　　Y:　　 0 1 2 3 4 5 6 7 8 9 10 
　　　　校验码: 1 0 X 9 8 7 6 5 4 3 2 

旧身份证包括  区位码(6位)+出生日期码(6位)+顺序码(3位)=15位
 *
 * @author			Kama Luther
 * @version			0.1
 * @since           0.1
 * @.createdate     2014-4-4 下午04:19:28
 * @.modifydate     2014-4-4 下午04:19:28
 * <DT><B>修改历史记录</B>
 * <DD>
 * 
 * </DD>
 * </DT>
 */
public class IDCardUtils
{
	
	// 对应权值
	final static int[]  wi = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1}; 
	// 对应校验码
	final static char[] vi = {'1','0','X','9','8','7','6','5','4','3','2'}; 
	
	//校验身份证是否正确
	public static boolean validate(String idcard)
	{
		// 空,非18位或15位
		if(idcard==null || (idcard.length() !=18 && idcard.length() != 15))
		{
			return false;
		}
		
		//18位
		if(idcard.length() ==18)
		{
			String substring = idcard.substring(0, 17);
			char v = genVerifyDigit(substring);
			char l = idcard.charAt(17);
			return v == l;
		}
		
		return true;
	}
	
	//通过前17位算出第18位的校验码
	public static char genVerifyDigit(String idcard)
	{
		int remaining = 0; 
		int sum = 0;
		if(idcard.length()==17)
		{
			for(int i=0;i<17;i++)
			{
				String s = idcard.substring(i,i+1);
				sum = sum + Integer.parseInt(s)*wi[i];
			}
			remaining = sum % 11; 
		}
		return vi[remaining];
	}
	
	//通过给定的本体码算出完整的身份证号;支持自动补齐旧版身份证号码
	public static String genIDCode(String idcard)
	{
		if(idcard.length()==15)
		{
			idcard = idcard.substring(0,6)+"19"+idcard.substring(6);
		}
		if(idcard.length()==17)
		{
			return idcard+String.valueOf(genVerifyDigit(idcard));
		}
		else
		{
			return idcard;
		}
	}
	
	public static String getRegionCode(String idcode)
	{
		if(idcode.length() > 6)
		{
			return idcode.substring(0, 6);
		}
		return idcode;
	}
	
	public static String getBirthCode(String idcode)
	{
		if(idcode.length() == 15)
		{
			return idcode.substring(6, 12);
		}
		else if(idcode.length() == 18)
		{
			return idcode.substring(6, 14);
		}
		return idcode;
	}
	
	public static String getSexCode(String idcode)
	{
		if(idcode.length() == 15)
		{
			return idcode.substring(14,15);
		}
		else if(idcode.length() == 18)
		{
			return idcode.substring(16,17);
		}
		return idcode;
	}

}
