package com.cheng.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 螺丝帽短信接口，可以负责发送电信、移动和联通短信
 * 
 * @author yuesiwen
 * @version 0.1
 * @since 0.1
 * @.createdate 2014-12-22 下午4:04:04
 * @.modifydate 2014-12-22 下午4:04:04 <DT><B>修改历史记录</B>
 *              <DD>
 * 
 *              </DD></DT>
 */
public class SmsLSMUtils
{
	private static final Logger logger = LoggerFactory
			.getLogger(SmsLSMUtils.class);

	private String key;
	private String url;
	private String sign = "【司机宝】";

	// -----------------------------------------------------------

	public String getKey()
	{
		return key;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	// -----------------------------------------------------------
	public boolean sendSms(String mobile, String content)
	{
		DefaultHttpClient client=null;
		try
		{
			client = httpClientTrustingAllSSLCerts();
			
		} catch (Exception e)
		{
			logger.error("sms for LSM error:"+ e);
			return false;
		}

		client.addRequestInterceptor(new HttpRequestInterceptor()
		{
			public void process(HttpRequest request, HttpContext context)
					throws HttpException, IOException
			{
				request.addHeader("Accept-Encoding", "gzip");
				request.addHeader(
						"Authorization",
						"Basic "
								+ new String(new Base64()
										.encode(("api:key-" + key)
												.getBytes("utf-8")), "utf-8"));
			}
		});

		client.getParams().setIntParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
		client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT,
				30000);

		HttpPost request = new HttpPost(url);

		ByteArrayOutputStream bos = null;
		InputStream bis = null;
		byte[] buf = new byte[10240];

		String result = null;
		try
		{
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("mobile", mobile));
			params.add(new BasicNameValuePair("message", content + sign));
			request.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				bis = response.getEntity().getContent();
				Header[] gzip = response.getHeaders("Content-Encoding");

				bos = new ByteArrayOutputStream();
				int count;
				while ((count = bis.read(buf)) != -1)
				{
					bos.write(buf, 0, count);
				}
				bis.close();

				if (gzip.length > 0
						&& gzip[0].getValue().equalsIgnoreCase("gzip"))
				{
					GZIPInputStream gzin = new GZIPInputStream(
							new ByteArrayInputStream(bos.toByteArray()));
					StringBuffer sb = new StringBuffer();
					int size;
					while ((size = gzin.read(buf)) != -1)
					{
						sb.append(new String(buf, 0, size, "utf-8"));
					}
					gzin.close();
					bos.close();

					result = sb.toString();
				} else
				{
					result = new String(bos.toByteArray(), "utf-8");
				}

				logger.info("sms for LSM success :" + result + " mobile:"
						+ mobile + ";" + " message:" + content);
			} else
			{
				logger.error("sms for LSM error code is "
						+ response.getStatusLine().getStatusCode());
				return false;
			}
			return true;

		} catch (Exception e)
		{
			logger.error("sms error " + e);
			return false;

		} finally
		{

			if (bis != null)
			{
				try
				{
					bis.close();// 最后要关闭BufferedReader
				} catch (Exception e)
				{
				}
			}
		}
	}

	// -----------------------------------------------------------
	public static boolean isDX(String mobile)
	{
		if (mobile == null)
			return false;
		mobile = mobile.trim();
		if (mobile.startsWith("133") || mobile.startsWith("1349")
				|| mobile.startsWith("153") || mobile.startsWith("180")
				|| mobile.startsWith("181") || mobile.startsWith("189")
				|| mobile.startsWith("177"))
			return true;
		return false;
	}
	
	private DefaultHttpClient httpClientTrustingAllSSLCerts() throws NoSuchAlgorithmException, KeyManagementException {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, getTrustingManager(), new java.security.SecureRandom());

        SSLSocketFactory socketFactory = new SSLSocketFactory(sc);
        Scheme sch = new Scheme("https", socketFactory, 443);
        httpclient.getConnectionManager().getSchemeRegistry().register(sch);
        return httpclient;
    }

    private TrustManager[] getTrustingManager() {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                // Do nothing
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                // Do nothing
            }

        } };
        return trustAllCerts;
    }

	public static void main(String[] agre)
	{
		System.out.println(isDX("18086047775"));
	}
}
