/**
 * ID: HttpConn.java
 * Copyright (c) 2002-2013 Luther Inc.
 * http://xluther.com
 * All rights reserved.
 */
package com.cheng.utils.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cheng.utils.exception.RopException;

/**
 * Http连接工具集
 *
 * @author			Kama Luther
 * @version			0.1
 * @since           0.1
 * @.createdate     2014-1-23 下午04:47:57
 * @.modifydate     2014-1-23 下午04:47:57
 * <DT><B>修改历史记录</B>
 * <DD>
 * 
 * </DD>
 * </DT>
 */
public class HttpConn
{

	
	//------------------------------------------------------------------
	
	private static final Logger logger = LoggerFactory.getLogger(HttpConn.class); 
	
	//
	public static final String SCHEME_HTTP 		= "http";
	public static final String SCHEME_HTTPS 	= "https";
	
	//
	public static final String METHOD_HEAD 		= "HEAD";
	public static final String METHOD_TRACE 	= "TRACE";
	public static final String METHOD_OPTION 	= "OPTION";
	public static final String METHOD_DELETE	= "DELETE";
	public static final String METHOD_GET 		= "GET";
	public static final String METHOD_POST 		= "POST";
	
	public static final int SC_CONTINUE = 100;
	public static final int SC_OK = HttpURLConnection.HTTP_OK;
	public static final int SC_PARTIAL = HttpURLConnection.HTTP_PARTIAL;
	public static final int SC_MOVED_PERM = HttpURLConnection.HTTP_MOVED_PERM;
	public static final int SC_MOVED_TEMP = HttpURLConnection.HTTP_MOVED_TEMP;
	public static final int SC_SEE_OTHER = HttpURLConnection.HTTP_SEE_OTHER;
	public static final int SC_TEMP_REDIRECT = 307;
	public static final int SC_BAD_REQUEST = HttpURLConnection.HTTP_BAD_REQUEST;
	public static final int SC_UNAUTHORIZED = HttpURLConnection.HTTP_UNAUTHORIZED;
	public static final int SC_FORBIDDEN = HttpURLConnection.HTTP_FORBIDDEN;
	public static final int SC_NOT_FOUND = HttpURLConnection.HTTP_NOT_FOUND;
	public static final int SC_RANGE_NOT_SATISFIABLE = 416;
	public static final int SC_INTERNAL_ERROR = HttpURLConnection.HTTP_INTERNAL_ERROR;
	
	public static final int CONN_TIMEOUT = 10 * 1000;  // 10秒  连接一个url的连接等待时间
	public static final int RESP_TIMEOUT = 30 * 1000;  // 30秒  连接上一个url，获取response的返回等待时间
	
	//------------------------------------------------------------------
	
	private String 	m_host;
	private int 	m_port;
	private String 	m_schm;
	
	private boolean m_debug_mode;
	private int 	m_conn_timeout;
	private int 	m_read_timeout;
	
	private long				m_beginpos;
	private long				m_finishpos;
	private boolean				m_usegzipenc;  //使用gzip压缩
	private String 				m_referer;
	private String				m_session;
	
	private String				m_instr_encoding;
	private String				m_outstr;
	private String				m_outstr_encoding;	 //输出字符串编码
	
	private String				m_redirect_location;
	private long				m_content_length;
	private String				m_content_type;
	private String				m_content_encoding;
	private String				m_content_disposition;
	private String				m_content_range;
	private String				m_accept_ranges;
	
	//------------------------------------------------------------------
	
	public HttpConn()
	{
		m_debug_mode	= false;
		m_usegzipenc	= false;
		m_conn_timeout 	= CONN_TIMEOUT;
		m_read_timeout 	= RESP_TIMEOUT;
		m_beginpos		= -1;
		m_finishpos		= -1;
	}
	
	//------------------------------------------------------------------
	
	public String getResultString()
	{
		return m_outstr;
	}
	
	//------------------------------------------------------------------
	
	public void setGzipMode(boolean mode)
	{
		m_usegzipenc = mode;
	}
	
	public void setInStrEncoding(String enc)
	{
		m_instr_encoding = enc;
	}
	public void setOutStrEncoding(String enc)
	{
		m_outstr_encoding = enc;
	}
	
	public void setDebugMode(boolean mode)
	{
		m_debug_mode = mode;
	}
	public void setConnTimeout(int timeout)
	{
		m_conn_timeout = timeout;
	}
	public void setReadTimeout(int timeout)
	{
		m_read_timeout = timeout;
	}
	
	//下标从0开始
	public void setBeginPos(long pos)
	{
		m_beginpos = pos;
	}
	public void setFinishPos(long pos)
	{
		m_finishpos = pos;
	}

	//------------------------------------------------------------------
	
	public String getRedirectLocation()
	{
		return m_redirect_location;
	}
	public long getContentLength()
	{
		return m_content_length;
	}
	public String getContentType()
	{
		return m_content_type;
	}
	public String getContentEncoding()
	{
		return m_content_encoding;
	}
	public String getContentDisposition()
	{
		return m_content_disposition;
	}
	public String getContentRange()
	{
		return m_content_range;
	}
	public String getAcceptRanges()
	{
		return m_accept_ranges;
	}
	public boolean isSupportRanges()
	{
		return !((m_accept_ranges!=null) && "none".equalsIgnoreCase(m_accept_ranges));  //  none, bytes
	}
	
	//------------------------------------------------------------------
	
	public void setHost(String host, int port)
	{
		setHost(host,port,SCHEME_HTTP);
	}
	public void setHost(String host, int port, String scheme)
	{
		m_host = host;
		m_port = port;
		m_schm = scheme;
		m_referer = m_schm + "://" + m_host + ":" + m_port;
	}
	public void setReferer(String referer)
	{
		m_referer = referer;
	}
	
	//------------------------------------------------------------------
	
	public int execute(String url)
		throws RopException
	{
		return execute(url, null, METHOD_GET);
	}
	public int execute(String url, Map<String, String> data)
		throws RopException
	{
		return execute(url, data, METHOD_GET);
	}
	public int execute(String url, Map<String, String> data, String method)
		throws RopException
	{
		int statecode = SC_CONTINUE;
		
		//1. 处理地址
		if(IsNull(url))
		{
			logger.error("地址为空");
			throw new RopException("地址为空");
		}
		
		//非完整路径,拼接上完整路径
		if( !url.toLowerCase().startsWith(SCHEME_HTTP + "://")
				&& !url.toLowerCase().startsWith(SCHEME_HTTPS + "://"))
		{
			url = m_schm + "://" + m_host + ":" + m_port + url;
		}
		//完整路径,拆分各部件
		else
		{
			try
			{
				URI uri = new URI(url);
				m_schm = uri.getScheme();
				m_host = uri.getHost();
				m_port = uri.getPort();
				m_port = (m_port==-1)?80:m_port;
			}
			catch (URISyntaxException e)
			{
				logger.error("非法地址:"+url, e);
				throw new RopException("非法地址:"+url);
			}
		}
		
		//2. 参数处理: Head和Get方法是拼接参数
		if( (METHOD_HEAD.equalsIgnoreCase(method) || METHOD_GET.equalsIgnoreCase(method))
				&& (data!=null) )
		{
			url = url + "?" + BuildData(data);
		}
		
		//3.
		try
		{
			URLConnection urlconnection = new URL(url).openConnection();
			HttpURLConnection m_connect = (HttpURLConnection) urlconnection;
			
			//设置请求头
			m_connect.setRequestMethod(method);
			m_connect.setConnectTimeout(m_conn_timeout);  
			m_connect.setReadTimeout(m_read_timeout);  
			
			if(METHOD_HEAD.equalsIgnoreCase(method))
			{
				m_connect.setDoOutput(false);  //是否向连接输出  默认false
				m_connect.setDoInput(true);  //是否从连接读入 默认true
			}
			else if(METHOD_GET.equalsIgnoreCase(method))
			{
				m_connect.setDoOutput(false);  //是否向连接输出  默认false
				m_connect.setDoInput(true);  //是否从连接读入 默认true
			}
			else if(METHOD_POST.equalsIgnoreCase(method))
			{
				m_connect.setDoOutput(true);  //post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false
				m_connect.setDoInput(true);  //是否从连接读入 默认true
				m_connect.setUseCaches(false); //post请求不使用缓存
				m_connect.setInstanceFollowRedirects(false);   //禁止自动重定向
				
				m_connect.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			}
			
			m_connect.setRequestProperty("Accept", "*/*");  
			m_connect.setRequestProperty("Connection", "keep-alive");
			m_connect.setRequestProperty("Host", m_host + ":" + m_port); 
			m_connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:23.0) Gecko/20100101 Firefox/23.0");  
			m_connect.setRequestProperty("Content-Charset", "UTF-8");
			
			if(m_usegzipenc)
			{
				m_connect.setRequestProperty("Accept-Encoding", "gzip");
			}
			if(m_session != null)
			{
				m_connect.setRequestProperty("Cookie", m_session); 
			}
			if(m_referer != null)
			{
				m_connect.setRequestProperty("Referer", m_referer); 
			}
			
			//请求位置
			if(m_beginpos>-1 || m_finishpos>-1)
			{
				StringBuffer rangebf = new StringBuffer();
				if(m_beginpos>-1)
				{
					rangebf.append(m_beginpos);
				}
				rangebf.append("-");
				if(m_finishpos>-1)
				{
					rangebf.append(m_finishpos);
				}
				m_connect.setRequestProperty("Range", "bytes=" + rangebf.toString()); 
			}

			m_connect.connect();
			
			//参数处理: Post方法是写入输出流
			if(METHOD_POST.equalsIgnoreCase(method))
			{
				ProcOutput(m_connect.getOutputStream(), data);
			}
			
			//
			m_content_length 		= m_connect.getContentLength();
			m_content_type 			= m_connect.getContentType();// .getHeaderField("Content-Type");
			m_content_disposition 	= m_connect.getHeaderField("Content-Disposition");
			m_content_encoding		= m_connect.getHeaderField("Content-Encoding");
			m_content_range			= m_connect.getHeaderField("Content-Range");
			m_accept_ranges			= m_connect.getHeaderField("Accept-Ranges");
			m_redirect_location 	= m_connect.getHeaderField("Location");
			
			if(m_content_length == -1)
			{
				m_content_length = m_connect.getHeaderFieldInt("Accept-Length", 0);
			}
			
			// Content-Disposition = attachment; filename=test.pdf
			if(NotNull(m_content_disposition) && (m_content_disposition.lastIndexOf('=') != -1))
			{
				m_content_disposition = m_content_disposition.substring(m_content_disposition.lastIndexOf('=')+1);
			}
			
			//保持Session会话
			String session				= m_connect.getHeaderField("Set-Cookie");
			if(session != null)
			{
				if(session.indexOf(';') != -1) session=session.substring(0, session.indexOf(';'));
				m_session = session;
			}
			
			if(m_debug_mode)
			{
				DebugHeader(m_connect);
			}
			
			//自动解析网页编码
			String encoding = "UTF-8";
			if(m_content_type != null && m_content_type.toLowerCase().lastIndexOf('=') != -1)
			{
				encoding = m_content_type.substring(m_content_type.lastIndexOf('=') + 1);
			}
			
			//处理输入流
			String param = IsNull(m_outstr_encoding)?encoding:m_outstr_encoding;
			
			//处理输入流
			if(NotNull(m_content_encoding) && m_content_encoding.toLowerCase().startsWith("gzip"))  //gzip压缩
			{
				m_outstr = Stream2String(new GZIPInputStream(m_connect.getInputStream()), param);
			}
			else
			{
				m_outstr = Stream2String(m_connect.getInputStream(), param);
			}
				
			m_connect.disconnect();
			
			//
			statecode 	= m_connect.getResponseCode();
			logger.debug(statecode + "," + m_content_length + "," + m_content_type + "," + m_content_encoding + "," + m_content_disposition + "," + m_session + "," + m_referer);
			
			m_referer				= url;
		}
		catch (MalformedURLException e)
		{
			logger.error("地址错误", e);
			throw new RopException("初始化时地址错误:"+url, e);
		}
		catch (ConnectException e)
		{
			logger.error("连接错误", e);
			throw new RopException("初始化时连接错误:"+url, e);
		}
		catch (IOException e)
		{
			logger.error("读写错误", e);
			throw new RopException("初始化时读写错误", e);
		}
		catch (RopException e)
		{
			throw e;
		}

		
		return statecode;
	}
	
	
	
	//------------------------------------------------------------------
	
	/**
	 * 处理输出
	 * @param outputStream
	 */
	private void ProcOutput(OutputStream out, Map<String, String> data)
	{
		String content = BuildData(data);
		DataOutputStream dos = new DataOutputStream(out); 
		try
		{
			dos.writeBytes(content); //dos.write(content.getBytes());
			dos.flush(); 
		}
		catch (IOException e)
		{
			logger.error("读写错误", e);
			throw new RopException("处理输出时读写错误", e);
		}
		finally
		{
			try
			{
				dos.close();
			}
			catch (IOException e)
			{
				logger.error("关闭错误", e);
			}
		}
	}
	
	/**
	 * 构建输入参数
	 * @param data
	 */
	public String BuildData(Map<String, String> data)
	{
		return BuildData(data, (m_instr_encoding==null)?"UTF-8":m_instr_encoding);
	}

	/**
	 * 构建输入参数
	 * @param data
	 */
	public String BuildData(Map<String, String> data, String encode)
	{
		StringBuffer params = new StringBuffer();
		if (data != null)
		{
			for (Iterator<String> iter = data.keySet().iterator(); iter.hasNext();)
			{
				String name = (String) iter.next();
				String value = (String) data.get(name);
				params.append(name).append("=");

				if(value!=null && value.length()>0)
				{
					try
					{
						params.append(URLEncoder.encode(value, encode));
					}
					catch (UnsupportedEncodingException e)
					{
						throw new RopException("构造数据时编码错误: "+value, e);
					}
				}

				if (iter.hasNext()) params.append("&");
			}
		}
		return params.toString();
	}
	
	/**
	 * 调试头信息
	 * @param conn 
	 */
	public void DebugHeader(HttpURLConnection conn)
	{
		Map<String, List<String>> headmap = conn.getHeaderFields();
		for(Iterator<String> headiter = headmap.keySet().iterator(); headiter.hasNext();)
		{
			String key = headiter.next();
			String out = (key == null)?"":(key + " = ");
			logger.info(out + conn.getHeaderField(key));
//			System.out.println(out + conn.getHeaderField(key));
		}
	}
	
	/**
	 * 判断是否重定向
	 * @param state
	 */
	public boolean IsRedirectState(int state)
	{
		return  ( (state == SC_MOVED_TEMP) 
				||  (state == SC_MOVED_PERM) 
				||  (state == SC_SEE_OTHER) 
				||  (state == SC_TEMP_REDIRECT) );
	}
	
	//------------------------------------------------------------------
	
	
	public static final String LINE_SEP = System.getProperty("line.separator");
	
	public static final String ENC_UTF8 = "UTF-8";
	public static final String ENC_GBK  = "GBK";
	
	public static String Stream2String(InputStream instream, String charset)
	{
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		String line = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(instream, (charset==null)?ENC_UTF8:charset));
			while ( (line = reader.readLine()) != null)
			{
				sb.append(line + LINE_SEP);
			}
		}
		catch (IOException e)
		{
			logger.error("读写错误", e);
			throw new RopException("读写错误: ", e);
		}
		finally
		{
			try
			{
				if(reader != null) reader.close();
			}
			catch (IOException e)
			{
				logger.error("close reader error", e);
			}
		}
		return sb.toString();
	}
	
	//------------------------------------------------------------------
	
    public static boolean IsNull(String str)
	{
		return (str == null) || (str.trim().length() == 0);
	}
	
    public static boolean NotNull(String str)
	{
		return (str != null) && (str.trim().length() > 0);
	}
    
    //------------------------------------------------------------------


}
