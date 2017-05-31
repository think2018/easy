/**
 * ID: ImageUtils.java
 * Copyright (c) 2002-2013 Luther Inc.
 * http://xluther.com
 * All rights reserved.
 */
package com.cheng.core.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import com.sun.imageio.plugins.jpeg.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;



/**
 * 图片处理工具集
 *
 * @author			Kama Luther
 * @version			0.1
 * @since           0.1
 * @.createdate     2014-6-23 上午09:46:10
 * @.modifydate     2014-6-23 上午09:46:10
 * <DT><B>修改历史记录</B>
 * <DD>
 * 
 * </DD>
 * </DT>
 */
public class ImageUtils
{

	private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class); 

	//--------------------------------------------------------------------------------

	public static void main(String[] args) throws IOException
	{
		JPGScale("d:\\51416bdc6d533.jpg", 200, "-s");
	}

	//--------------------------------------------------------------------------------

	public static final String 	JPG_EXT 	= ".jpg";
	public static final String 	JPEG_EXT 	= ".jpeg";
	public static final String 	PNG_EXT 	= ".png";
	public static final String 	GIF_EXT 	= ".gif";

	//--------------------------------------------------------------------------------

	public static boolean JPGScale(String srcfilepath, int maxsize, String nameext)
	{
		//输入参数校验
		if(srcfilepath==null || 
				(!srcfilepath.toLowerCase().endsWith(JPG_EXT) 
						&& !srcfilepath.toLowerCase().endsWith(JPEG_EXT)
						&& !srcfilepath.toLowerCase().endsWith(PNG_EXT)
						&& !srcfilepath.toLowerCase().endsWith(GIF_EXT)))
		{
			return false;
		}

		//文件是否存在
		File file = new File(srcfilepath);
		if(!file.exists())
		{
			return false;
		}

		//缩略图文件
		String ext = srcfilepath.substring(srcfilepath.lastIndexOf("."));
		String dstfilepath = srcfilepath.substring(0, srcfilepath.length()-ext.length());
		dstfilepath += nameext;
		dstfilepath += ext;

		FileOutputStream fos = null;
		try
		{
			//读取图片
			Image srcImage = ImageIO.read(file);
			int srcWidth = srcImage.getWidth(null);//原图片宽度
			int srcHeight = srcImage.getHeight(null);//原图片高度
			int dstWidth = srcWidth;//缩略图宽度
			int dstHeight = srcHeight;//缩略图高度
			float scale = 0;

			//计算缩略图的宽和高
			if(srcWidth>maxsize)
			{
				dstWidth = maxsize;
				scale = (float)srcWidth/(float)maxsize;
				dstHeight = Math.round((float)srcHeight/scale);
			}
			srcHeight = dstHeight;
			if(srcHeight>maxsize)
			{
				dstHeight = maxsize;
				scale = (float)srcHeight/(float)maxsize;
				dstWidth = Math.round((float)dstWidth/scale);
			}

			//生成缩略图
			BufferedImage dstImage = new BufferedImage(dstWidth,dstHeight,BufferedImage.TYPE_INT_RGB);
			dstImage.getGraphics().drawImage(srcImage, 0, 0, dstWidth, dstHeight, null);
			fos = new FileOutputStream(dstfilepath);

			//新压缩方法
			saveAsJPEG(300, dstImage, 0.8f, fos);

			/*JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
			encoder.encode(dstImage);*/

			fos.close();
			fos = null;

			dstImage = null;
			srcImage = null;
			return true;
		}
		catch (IOException e)
		{
			logger.error("IO Error", e);
			return false;
		}
		finally
		{
			if (fos != null)
			{
				try
				{
					fos.close();
				}
				catch (IOException e) { }
				fos = null;
			}
		}
	}

	//--------------------------------------------------------------------------------	
	/**
	 * 
	 * @param dpi    分辨率
	 * @param image_to_save
	 * @param JPEGcompression   压缩质量 百分比
	 * @param fos
	 * @throws IOException
	 */
	public static void saveAsJPEG(Integer dpi ,BufferedImage image_to_save, float JPEGcompression, FileOutputStream fos) throws IOException {

		JPEGImageWriter imageWriter  =  (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpg").next();
		ImageOutputStream ios  =  ImageIO.createImageOutputStream(fos);
		imageWriter.setOutput(ios);

		IIOMetadata imageMetaData  =  imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(image_to_save), null);
		if(dpi !=  null && !dpi.equals("")){
			Element tree  =  (Element) imageMetaData.getAsTree("javax_imageio_jpeg_image_1.0");
			Element jfif  =  (Element)tree.getElementsByTagName("app0JFIF").item(0);
			jfif.setAttribute("Xdensity", Integer.toString(dpi) );
			jfif.setAttribute("Ydensity", Integer.toString(dpi));
		}

		if(JPEGcompression >= 0 && JPEGcompression <= 1f){
			
			JPEGImageWriteParam jpegParams  =  (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
			jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
			jpegParams.setCompressionQuality(JPEGcompression);
			
		}

		imageWriter.write(imageMetaData, new IIOImage(image_to_save, null, null), null);
		ios.close();
		imageWriter.dispose();

	}



}
