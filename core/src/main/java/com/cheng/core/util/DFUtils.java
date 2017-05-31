package com.cheng.core.util;

public class DFUtils implements IServiceConfig
{
	public static String  dF(long moeny)
	{
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.00");  
		return df.format((double)moeny/EXCHANGE_RATE);
	}
}
