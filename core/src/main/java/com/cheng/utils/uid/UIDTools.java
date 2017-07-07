package com.cheng.utils.uid;

import java.util.UUID;

public class UIDTools
{
   public static String getUid_32()
   {
	   return UUID.randomUUID().toString().replace("-", "");
   }
   
   public static String getUid_36()
   {
	   return UUID.randomUUID().toString();
   }
   
   public static void main(String[] agre)
   {
	   System.out.println("getUid_32()："+getUid_32());
	   System.out.println("getUid_36()："+getUid_36());
   }
}
