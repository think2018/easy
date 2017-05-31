/**
 * ID: FileIoUtils.java
 * Copyright (c) 2002-2013 Luther Inc.
 * http://xluther.com
 * All rights reserved.
 */
package com.cheng.core.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cheng.exception.ZuvException;

/**
 * 文件操作工具集
 *
 * @author			Kama Luther
 * @version			0.1
 * @since           0.1
 * @.createdate     2014-5-7 下午05:42:43
 * @.modifydate     2014-5-7 下午05:42:43
 * <DT><B>修改历史记录</B>
 * <DD>
 * 
 * </DD>
 * </DT>
 */
public class FileIoUtils
{
	
	//-------------------------------------------------------------------------------------
	
	private static final Logger logger = LoggerFactory.getLogger(FileIoUtils.class); 
	
	//-------------------------------------------------------------------------------------
	
	public final static String MD5             = "MD5";
	public final static int    MD5_LEN         = 32;	
	public final static String SHA1            = "SHA1";
	public final static int    SHA1_LEN        = 40;	
	public final static String SHA_256         = "SHA-256";
	public final static int    SHA_256_LEN     = 64;	
	public final static String SHA_384         = "SHA-384";
	public final static int    SHA_384_LEN     = 96;	
	public final static String SHA_512         = "SHA-512";
	
	//-------------------------------------------------------------------------------------
	
	public static final void main(String[] args)
	{
		removeFileOrFolder("D:\\test");
	}
	
	//-------------------------------------------------------------------------------------
	
	public static boolean createFolder(String path)
	{
		File file = new File(path);
		if (!file.exists())
		{
			return file.mkdirs();
		}
		else
		{
			return false;
		}
	}
	
	public static boolean removeFileOrFolder(String path)
	{
		File file = new File(path);
		
		if(file.isDirectory() && file.list().length > 0)
		{
			return false;
		}
		else
		{
			return file.delete();
		}
	}
	
	public static List<File> listFolder(String path)
	{
		File file = new File(path);
		if(file.exists())
		{
			return Arrays.asList(file.listFiles());
		}
		return null;
	}
	
	//-------------------------------------------------------------------------------------
	
	/**
	 * 文件签名
	 * @param file
	 */
	public static byte[] FileDigest(File file)
	{
		return FileDigest(MD5, file);
	}
	
	
	/**
	 * 二进制转十六进制字符串
	 * 
	 * @param bytes
	 */
	public static String getHexString(byte[] bytes)
	{
		StringBuilder ret = new StringBuilder(bytes.length << 1);
		for (int i = 0; i < bytes.length; i++)
		{
			ret.append(Character.forDigit((bytes[i] >> 4) & 0xf, 16));
			ret.append(Character.forDigit(bytes[i] & 0xf, 16));
		}
		return ret.toString();
	}
	
	/**
	 * 文件签名
	 * @param type
	 * @param file
	 */
	public static byte[] FileDigest(String type, File file)
	{
		FileInputStream in = null;
		try
		{
			in = new FileInputStream(file);
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			
			MessageDigest md5 = MessageDigest.getInstance(type);
			md5.update(byteBuffer);
			return md5.digest();
		}
		catch (FileNotFoundException e)
		{
			logger.error("文件路径错误",e);
			return null;
		}
		catch (IOException e)
		{
			logger.error("文件读取错误",e);
			return null;
		}
		catch (NoSuchAlgorithmException e)
		{
			logger.error("没有对应算法",e);
			return null;
		}

		finally
		{
			if (null != in)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
				}
			}
		}
	}
	
	//-------------------------------------------------------------------
	
	private static final int UNIT_BUFF_SIZE = 1024;
	
	public static boolean toFile(byte[] data, String path)
	{
		FileOutputStream fos;
		try
		{
			fos = new FileOutputStream(path);
			fos.write(data);
			fos.flush();
			fos.close();
			fos = null;
			return true;
		}
		catch (FileNotFoundException e)
		{
			logger.error("路径错误", e);
			return false;
		}
		catch (IOException e)
		{
			logger.error("读写错误", e);
			return false;
		}
	}

	public static byte[] toBytes(InputStream instream)
		throws ZuvException
	{
		ByteArrayOutputStream baos = null;
		try
		{
			baos = new ByteArrayOutputStream();
			byte[] b = new byte[UNIT_BUFF_SIZE];
			int len = 0;
			while ((len = instream.read(b, 0, UNIT_BUFF_SIZE)) != -1)
			{
				baos.write(b, 0, len);
				baos.flush();
			}
			return baos.toByteArray();
		}
		catch (IOException e)
		{
			logger.error("读写错误", e);
			throw new ZuvException("读写错误", e);
		}
		finally
		{
			try
			{
				if(baos != null) baos.close();
			}
			catch (IOException e)
			{
				logger.error("close reader error", e);
			}
		}
	}
	
	//-------------------------------------------------------------------

}
