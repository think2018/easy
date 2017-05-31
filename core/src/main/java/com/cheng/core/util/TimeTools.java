package com.cheng.core.util;

import java.util.Calendar;

public class TimeTools
{
   public static long getTodayBegin()
   {
	   Calendar calendar= Calendar.getInstance();
	  
	   calendar.set(Calendar.HOUR_OF_DAY, 0);
	   calendar.set(Calendar.MINUTE, 0);
	   calendar.set(Calendar.SECOND, 0);
	   calendar.set(Calendar.MILLISECOND, 0);
	   return calendar.getTimeInMillis();
   }
   
   public static long getTodayEnd()
   {
	   Calendar calendar= Calendar.getInstance();
	
	   calendar.set(Calendar.HOUR_OF_DAY, 23);
	   calendar.set(Calendar.MINUTE, 59);
	   calendar.set(Calendar.SECOND, 59);
	   calendar.set(Calendar.MILLISECOND,999);
	   return calendar.getTimeInMillis();
   }
   
   public static boolean isInToday(long time)
   {
	   long todayBegin=TimeTools.getTodayBegin();
       long todayEnd=TimeTools.getTodayEnd();
       return (time>=todayBegin&&time<=todayEnd)?true:false;
   }
   
   public static void main(String[] agre)
   {
	   System.out.println(TimeTools.isInToday(System.currentTimeMillis()));
   }
}
