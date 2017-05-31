package com.cheng.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtils {
	
	public static Map<String, String>  getKeyValue(String path){
		
			ClassLoader classLoader = PropertiesUtils.class.getClassLoader();
			InputStream in = classLoader.getResourceAsStream(path);
			Properties properties = new Properties();

			try
			{
				properties.load(in);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				System.exit(-1);
			}
			
			Map<String, String> map = new HashMap<String, String>();
			Set<Object> set = properties.keySet();
			for (Object object : set) {
				map.put(object.toString(), properties.getProperty(object.toString()));
			}
			
			try
			{
				if (in != null)
					in.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			in = null;
			return map;
		}
	
	public static void main(String[] args) {
		  
		Map<String, String> map = PropertiesUtils.getKeyValue("appKey.properties");
		System.out.println(map);
	}
}
