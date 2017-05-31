package com.cheng.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MyThreadPool
{
	private static MyThreadPool myPool = null;
	private ThreadPoolExecutor threadPool = null;
	private ScheduledThreadPoolExecutor scheduledPool=null;

	public ThreadPoolExecutor getThreadPool()
	{
		return threadPool;
	}

	public ScheduledThreadPoolExecutor getScheduledPool()
	{
		return scheduledPool;
	}

	private int corePoolSize = 10;// 池中所保存的线程数，包括空闲线程。
	private int maximumPoolSize = 50;// 池中允许的最大线程数。
	private long keepAliveTime = 3;// 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
	private int scheduledPoolSize=10;
	
	private static synchronized void create()
	{
		if(myPool==null)
			myPool=new MyThreadPool();
	}

	public static MyThreadPool getInstance()
	{
		if (myPool == null)
			create();
		return myPool;
	}

	private MyThreadPool()
	{

		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("pool.properties");
		if (in != null)
		{
			Properties property = new Properties();
			try
			{
				property.load(in);
			} catch (IOException e)
			{
				System.out.println(e);
			}
			this.corePoolSize = Integer.parseInt(property
					.getProperty("corePoolSize"));
			this.maximumPoolSize = Integer.parseInt(property
					.getProperty("maximumPoolSize"));
			this.keepAliveTime = Long.parseLong(property
					.getProperty("keepAliveTime"));
			this.scheduledPoolSize=Integer.parseInt(property
					.getProperty("scheduledPoolSize"));
		} else
		{
			System.out.println("文件pool.properties没找到！");
			return;
		}
		threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
				keepAliveTime, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(100),
				new ThreadPoolExecutor.DiscardPolicy());
		this.scheduledPool=new ScheduledThreadPoolExecutor(scheduledPoolSize);
	}
}
