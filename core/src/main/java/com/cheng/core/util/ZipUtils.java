/**
 * ID: ZipUtils.java
 * Copyright (c) 2002-2013 Luther Inc.
 * http://xluther.com
 * All rights reserved.
 */
package com.cheng.core.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.UUID;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 压缩工具
 *
 * @author			Kama Luther
 * @version			0.1
 * @since           0.1
 * @.createdate     2014-4-4 下午10:44:43
 * @.modifydate     2014-4-4 下午10:44:43
 * <DT><B>修改历史记录</B>
 * <DD>
 * 
 * </DD>
 * </DT>
 */
public class ZipUtils
{
	
	private static final Logger logger = LoggerFactory.getLogger(ZipUtils.class); 
	
	public static void main(String[] args) throws IOException
	{
		String filepath = "F:\\test2.zip";
		unzip(filepath, "GBK", "F:/test2");
	}
	
	//--------------------------------------------------------------------------------
	
	private static final int BUFF_SIZE = 4096;
	
	public static boolean unzip(String file, String path)
	{
		return unzip(file, "GBK", path); 
	}
	
	public static boolean unzip(String file, String encode, String path)
	{
		ZipFile zipfile = null;
		FileOutputStream fos = null;
		InputStream fis = null;
		try
		{
			File savefolder = new File(path);
			if(!savefolder.exists()) savefolder.mkdirs();
			
			zipfile = new ZipFile(file, encode);
			Enumeration<?> entries = zipfile.getEntries();
			while(entries.hasMoreElements())
			{
				ZipEntry entry = (ZipEntry)entries.nextElement();
				if(!entry.isDirectory())
				{
					String filename = entry.getName();
					int lindex = filename.lastIndexOf(".");
					String fileext = (lindex<0)?"":filename.substring(lindex);
					String savefilename = UUID.randomUUID().toString().replaceAll("\\-", "") + fileext;
					long filesize = entry.getSize();
//					logger.info(filename + "("+filesize+") : " + savefilename);
					
					if(filesize>0)
					{
						fis = zipfile.getInputStream(entry);
						File savefile = new File(path, savefilename).getAbsoluteFile();
						fos = new FileOutputStream(savefile);
						
						byte[] buffer = new byte[BUFF_SIZE];
						int readLen = 0;
						while ((readLen = fis.read(buffer, 0, BUFF_SIZE)) >= 0)
						{
							fos.write(buffer, 0, readLen);
						}

						fos.flush();
						fos.close();
					
						fis.close();
					}
				}
			}
			return true;
		}
		catch (FileNotFoundException e)
		{
			logger.error("Path Error", e);
			return false;
		}
		catch (IOException e)
		{
			logger.error("IO Error", e);
			return false;
		}
		finally
		{
			try
			{
				if (fos != null)
				{
					fos.close();
				}
				
				if (fis != null)
				{
					fis.close();
				}
				
				if (zipfile != null)
				{
					zipfile.close();
				}
			}
			catch (IOException e){}
		}
	}
	
	//--------------------------------------------------------------------------------

}
