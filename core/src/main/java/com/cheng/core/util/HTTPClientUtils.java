package com.cheng.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class HTTPClientUtils {

	private static final Log logger = LogFactory.getLog(HTTPClientUtils.class);
	
	private static final String APPLICATION_JSON = "application/json";

	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

	/**
	 * post方法提交
	 * @param url
	 * @param data
	 * @return
	 */
	public static String post(String url, Object data) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost();
		try {
//			System.out.println("url:"+url);
			httpPost.setURI(new URI(url));
			RequestConfig config = RequestConfig.custom().setConnectTimeout(20000).setConnectionRequestTimeout(20000)
					.build();
			httpPost.setConfig(config);

			httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
			httpPost.addHeader("custId", "8kl87wgttq");
			httpPost.addHeader("token", "JCCWmIr91490341211445");
			if (null != data) {
				String json = createJson(data);
//				System.out.println("data json:"+json);
				StringEntity se = new StringEntity(json);
				se.setContentType(CONTENT_TYPE_TEXT_JSON);
				se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
				httpPost.setEntity(new StringEntity(json, "UTF-8"));
			}

			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity == null)
				return null;
			BufferedReader reader = new BufferedReader((new InputStreamReader(entity.getContent(), "utf-8")));
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = reader.readLine()) != null) {
				buffer.append(str);
			}
			response.close();
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * get方法提交
	 * @param url
	 * @return
	 */
	public static String get(String url) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet();
		try {
			httpGet.setURI(new URI(url));
			RequestConfig config = RequestConfig.custom().setConnectTimeout(20000).setConnectionRequestTimeout(20000)
					.build();
			httpGet.setConfig(config);

			httpGet.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
			httpGet.addHeader("custId", "8kl87wgttq");
			httpGet.addHeader("token", "JCCWmIr91490341211445");
			CloseableHttpResponse response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity == null)
				return null;
			BufferedReader reader = new BufferedReader((new InputStreamReader(entity.getContent(), "utf-8")));
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = reader.readLine()) != null) {
				buffer.append(str);
			}
			response.close();
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * get方法提交
	 * @param url
	 * @return
	 */
	public static String delete(String url) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpDelete httpGet = new HttpDelete();
//		HttpGet httpGet = new HttpGet();
		try {
			httpGet.setURI(new URI(url));
			RequestConfig config = RequestConfig.custom().setConnectTimeout(20000).setConnectionRequestTimeout(20000)
					.build();
			httpGet.setConfig(config);

			httpGet.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
			httpGet.addHeader("custId", "8kl87wgttq");
			httpGet.addHeader("token", "JCCWmIr91490341211445");
			CloseableHttpResponse response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity == null)
				return null;
			BufferedReader reader = new BufferedReader((new InputStreamReader(entity.getContent(), "utf-8")));
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = reader.readLine()) != null) {
				buffer.append(str);
			}
			response.close();
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * put方法提交
	 * @param url
	 * @param data
	 * @return
	 */
	public static String put(String url, Object data) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut();
		try {
			httpPut.setURI(new URI(url));
			RequestConfig config = RequestConfig.custom().setConnectTimeout(20000).setConnectionRequestTimeout(20000)
					.build();
			httpPut.setConfig(config);
			httpPut.addHeader("custId", "8kl87wgttq");
			httpPut.addHeader("token", "JCCWmIr91490341211445");
			httpPut.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
			if (null != data) {
				String json = createJson(data);
				StringEntity se = new StringEntity(json);
				se.setContentType(CONTENT_TYPE_TEXT_JSON);
				se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
				httpPut.setEntity(new StringEntity(json, "UTF-8"));
			}

			CloseableHttpResponse response = client.execute(httpPut);
			HttpEntity entity = response.getEntity();
			if (entity == null)
				return null;
			BufferedReader reader = new BufferedReader((new InputStreamReader(entity.getContent(), "utf-8")));
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = reader.readLine()) != null) {
				buffer.append(str);
			}
			response.close();
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * obj change json
	 * @param data
	 * @return
	 */
	public static String createJson(Object data) {
		JsonConfig jsonConfig = new JsonConfig();
		PropertyFilter filter = new PropertyFilter() {
			public boolean apply(Object object, String fieldName, Object fieldValue) {
				if (fieldValue instanceof List) {
					@SuppressWarnings("unchecked")
					List<Object> list = (List<Object>) fieldValue;
					if (list.size() == 0) {
						return true;
					}
				}
				return null == fieldValue || "".equals(fieldValue);
			}
		};
		jsonConfig.setJsonPropertyFilter(filter);
		JSONObject jsonObject = new JSONObject();
		@SuppressWarnings("static-access")
		String json = jsonObject.fromObject(data, jsonConfig).toString();
		return json;
	}
	
	/**
	 * 调用post请求，得到结果集
	 * @param url
	 * @param object
	 * @param o
	 * @return
	 */
	public static <T> T getPostResponseJsonToMap(String url, Object object, Class<T> o) {
		String line = "";
		T obj = null;
		try {
			line = HTTPClientUtils.post(url, object);
			obj = JsonUtils.fromJson(line, o);
		} catch (Exception e) {
			logger.error("调用接口失败�?" + url + "******" + e);
		}
		return obj;
	}
	
	/**
	 * 调用put请求，得到结果集
	 * @param url
	 * @param object
	 * @param o
	 * @return
	 */
	public static <T> T getPutResponseJsonToMap(String url, Object object, Class<T> o) {
		String line = "";
		T obj = null;
		try {
			line = HTTPClientUtils.put(url, object);
			obj = JsonUtils.fromJson(line, o);
		} catch (Exception e) {
			logger.error("调用接口失败�?" + url + "******" + e);
		}
		return obj;
	}
	
	/**
	 * 调用get请求，得到结果集
	 * @param url
	 * @param o
	 * @return
	 */
	public static <T> T getResponseJsonToMap(String url, Class<T> o) {
		String line = "";
		T obj = null;
		try {
			line = HTTPClientUtils.get(url);
			obj = JsonUtils.fromJson(line, o);
		} catch (Exception e) {
			logger.error("调用接口失败�?" + url + "******" + e);
		}
		return obj;
	}
}
