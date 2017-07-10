package com.cheng.utils.file;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author chengyunfei
 * @version 1.0
 * @date : 2017-05-08 17:41:57
 * @Description 文件操作工具集
 */
public class FileUtils {

	private static final Log LOGGER = LogFactory.getLog(FileUtils.class);

	// -------------------------------------------------------------------------------------

	public final static String MD5 = "MD5";
	public final static int MD5_LEN = 32;
	public final static String SHA1 = "SHA1";
	public final static int SHA1_LEN = 40;
	public final static String SHA_256 = "SHA-256";
	public final static int SHA_256_LEN = 64;
	public final static String SHA_384 = "SHA-384";
	public final static int SHA_384_LEN = 96;
	public final static String SHA_512 = "SHA-512";
	
	private static final int UNIT_BUFF_SIZE = 1024;

	
	public static String readTxtFile(String filePath) {
		String params = "";

		try {
			// String encoding = "GBK";
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格�?
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// System.out.println(params);
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

	public static String streamToString(InputStream instream) {
		try {
			int inputLength = 1024;
			byte[] buffer = new byte[inputLength];
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			int offset = 0;
			while ((offset = instream.read(buffer, 0, inputLength)) != -1) {
				output.write(buffer, 0, offset);
			}
			String result = new String(output.toByteArray(), "UTF-8");
			output.flush();
			output.close();
			instream.close();
			return result;
		} catch (IOException e) {
			System.out.println(e);
			return null;
		}
	}

	public static String inputStreamToString(InputStream in) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		StringBuffer buffer = new StringBuffer();
		String line;
		while ((line = br.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}

	public static boolean createFolder(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return file.mkdirs();
		} else {
			return false;
		}
	}

	public static boolean removeFileOrFolder(String path) {
		File file = new File(path);

		if (file.isDirectory() && file.list().length > 0) {
			return false;
		} else {
			return file.delete();
		}
	}

	public static List<File> listFolder(String path) {
		File file = new File(path);
		if (file.exists()) {
			return Arrays.asList(file.listFiles());
		}
		return null;
	}

	// -------------------------------------------------------------------------------------

	/**
	 * 文件签名
	 * 
	 * @param file
	 */
	public static byte[] FileDigest(File file) {
		return FileDigest(MD5, file);
	}

	/**
	 * 二进制转十六进制字符串
	 * 
	 * @param bytes
	 */
	public static String getHexString(byte[] bytes) {
		StringBuilder ret = new StringBuilder(bytes.length << 1);
		for (int i = 0; i < bytes.length; i++) {
			ret.append(Character.forDigit((bytes[i] >> 4) & 0xf, 16));
			ret.append(Character.forDigit(bytes[i] & 0xf, 16));
		}
		return ret.toString();
	}

	/**
	 * 文件签名
	 * 
	 * @param type
	 * @param file
	 */
	public static byte[] FileDigest(String type, File file) {
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());

			MessageDigest md5 = MessageDigest.getInstance(type);
			md5.update(byteBuffer);
			return md5.digest();
		} catch (FileNotFoundException e) {
			LOGGER.error("文件路径错误", e);
			return null;
		} catch (IOException e) {
			LOGGER.error("文件读取错误", e);
			return null;
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("没有对应算法", e);
			return null;
		}

		finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}

	// -------------------------------------------------------------------

	public static boolean toFile(byte[] data, String path) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(path);
			fos.write(data);
			fos.flush();
			fos.close();
			fos = null;
			return true;
		} catch (FileNotFoundException e) {
			LOGGER.error("路径错误", e);
			return false;
		} catch (IOException e) {
			LOGGER.error("读写错误", e);
			return false;
		}
	}

	public static byte[] toBytes(InputStream instream) {
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			byte[] b = new byte[UNIT_BUFF_SIZE];
			int len = 0;
			while ((len = instream.read(b, 0, UNIT_BUFF_SIZE)) != -1) {
				baos.write(b, 0, len);
				baos.flush();
			}
			return baos.toByteArray();
		} catch (IOException e) {
			LOGGER.error("读写错误", e);
		} finally {
			try {
				if (baos != null)
					baos.close();
			} catch (IOException e) {
				LOGGER.error("close reader error", e);
			}
		}

		// ch
		return null;
	}

	// -------------------------------------------------------------------

	public static void main(String[] args) {

		String url = FileUtils.class.getClassLoader().getResource("params.txt").getPath();

		System.out.println(url);

		System.out.println(FileUtils.readTxtFile(url));

		removeFileOrFolder("D:\\test");
	}

}
