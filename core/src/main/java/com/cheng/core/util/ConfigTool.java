package com.cheng.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class ConfigTool {
	private static final Logger log = Logger.getLogger(ConfigTool.class);
	
	private	Properties properties = new Properties();
	
	public ConfigTool(String url) {
		ClassLoader classLoader = this.getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(url);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  
		
		try {
			properties.load(bufferedReader);
		} catch (IOException e) {
			log.error("加载配置文件:" + url + "失败! \n"
					+ ExceptionTools.getStackTrace(e));
			System.exit(-1);
		}

		try {
			bufferedReader.close();
			inputStream.close();
		} catch (IOException e) {
			log.error("关闭配置文件流:" + url + "失败 \n"
					+ ExceptionTools.getStackTrace(e));
			System.exit(-1);
		}
		bufferedReader = null;
		inputStream = null;
	}
	
	public String getValue(String key) {
		return properties.getProperty(key);
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public static void main(String[] agre) {
		ConfigTool mongoConfigTool = new ConfigTool("mongo.properties");
		ConfigTool emailConfigTool = new ConfigTool("email.properties");
		
		Dumper.dump(mongoConfigTool.toString());
		
		Dumper.dump(mongoConfigTool.getValue("mongo.host_1"));
		Dumper.dump(mongoConfigTool.getValue("mongo.host_2"));
		Dumper.dump(mongoConfigTool.getValue("mongo.host_3"));
		
		Dumper.dump(emailConfigTool.getValue("email.host"));
		Dumper.dump(emailConfigTool.getValue("email.username"));
		Dumper.dump(emailConfigTool.getValue("email.password"));
		
	}
}