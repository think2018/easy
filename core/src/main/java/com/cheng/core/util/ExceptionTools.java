package com.cheng.core.util;

public class ExceptionTools
{
	public static String getStackTrace(Exception e)
	{
		StackTraceElement[] arr=e.getStackTrace();
		StringBuilder sb=new StringBuilder();
		sb.append(e.toString());
		sb.append("\n");
		for(StackTraceElement ele:arr)
		{
			sb.append("\tat ");
			sb.append(ele.toString());
			sb.append("\n");
		}
			
		return sb.toString();
	}
}
