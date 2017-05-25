package com.cheng.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author chengyunfei
 * @version 1.0
 * @date : 2017-05-08 17:41:57
 * @Description ch ...
 */
public class FileUtils {
	
	private static final Log LOGGER = LogFactory.getLog(FileUtils.class);
	
	public static String readTxtFile(String filePath) {
		String params = "";
		
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格�?
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
//					System.out.println(params);
					params += lineTxt.trim();
				}
				read.close();
			} else {
				LOGGER.debug("找不到指定的文件");
			}
		} catch (Exception e) {
			LOGGER.debug("读取文件内容出错");
			LOGGER.error(e);
		}

		return params;

	}

	public static void main(String[] args) {
		
		String url = FileUtils.class.getClassLoader().getResource("params.txt").getPath();
		
		System.out.println(url);
		
		System.out.println(FileUtils.readTxtFile(url));
	}

}
