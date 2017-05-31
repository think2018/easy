/**
 * ID: RopException.java
 * Copyright (c) 2002-2013 Luther Inc.
 * http://xluther.com
 * All rights reserved.
 */
package com.cheng.core.util;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 异常类
 *
 * @author			Kama Luther
 * @version			0.1
 * @since           0.1
 * @.createdate     2013-7-19 下午02:30:31
 * @.modifydate     2013-7-19 下午02:30:31
 * <DT><B>修改历史记录</B>
 * <DD>
 * 
 * </DD>
 * </DT>
 */
public class RopException extends RuntimeException
{

	private static final long serialVersionUID = -1206585806307452043L;
	
	private Throwable cause;
	
	public RopException(String msg)
	{
		super(msg);
	}
	
	public RopException(String msg, Throwable cause)
	{
		super(msg);
		this.cause = cause;
	}
	
	public Throwable getCause() 
	{
		return this.cause;
	}
	
	public String getMessage() 
	{
		String msg = super.getMessage();
		if (this.cause == null)
		{
			return msg;
		}
		else
		{
			return "\r\tat " + msg + "; "
					+ this.cause.getClass().getName() + ": "
					+ this.cause.getMessage();
		}
	}
	
	public String getCurrentMessage()
	{
		return super.getMessage();
	}
	
	public void printStackTrace(PrintStream ps) 
	{
		if (getCause() == null) 
		{
			super.printStackTrace(ps);
		}
		else 
		{
			ps.println(this);
			getCause().printStackTrace(ps);
		}
	}
	
	public void printStackTrace(PrintWriter pw)
	{
		if (getCause() == null)
		{
			super.printStackTrace(pw);
		}
		else
		{
			pw.println(this);
			getCause().printStackTrace(pw);
		}
	}

}
