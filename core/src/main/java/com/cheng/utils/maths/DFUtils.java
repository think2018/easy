package com.cheng.utils.maths;


public class DFUtils
{
	
	//汇率
	public static final long EXCHANGE_RATE = 10000;
	
	public static String  dF(long moeny)
	{
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.00");  
		return df.format((double)moeny/EXCHANGE_RATE);
	}
}
