/**
 * ID: DigestCoder.java
 * Copyright (c) 2002-2013 Luther Inc.
 * http://xluther.com
 * All rights reserved.
 */
package com.cheng.utils.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cheng.utils.exception.RopException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密工具集
 * 
 * MD5、SHA-1为数字签名 BASE64、3DES为可逆加密方法
 *
 * @author Kama Luther
 * @version 0.1
 * @since 0.1
 * @.createdate 2013-11-11 下午01:32:00
 * @.modifydate 2013-11-11 下午01:32:00
 *              <DT><B>修改历史记录</B>
 *              <DD>
 * 
 *              </DD></DT>
 */
public class DigestCoder {

	private static final Logger logger = LoggerFactory.getLogger(DigestCoder.class);
	private static final String UTF_8 = "UTF-8";

	public final static String MD5 = "MD5";
	public final static int MD5_LEN = 32;
	public final static String SHA1 = "SHA1";
	public final static int SHA1_LEN = 40;
	public final static String SHA_256 = "SHA-256";
	public final static int SHA_256_LEN = 64;
	public final static String SHA_384 = "SHA-384";
	public final static int SHA_384_LEN = 96;
	public final static String SHA_512 = "SHA-512";
	public final static int SHA_512_LEN = 128;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println(getHexString(FileDigest(MD5, new
		// File("F:\\leisure\\Someone_Like_You.mp4"))));
		System.out.println(getHexString(Digest(SHA1, "12345678")));

		System.out.println(getDigestStr(MD5, "10032-100000000008"));

		// String base64Encoder = base64Encoder("test");
		// System.out.println(base64Encoder);
		// String base64Decoder = base64Decoder(base64Encoder);
		// System.out.println(base64Decoder);

		// byte[] keys = getKey("123456");
		// String desedeEncoder = desedeEncoder("test",keys);
		// System.out.println(desedeEncoder);
		// String desedeDecoder = desedeDecoder(desedeEncoder,keys);
		// System.out.println(desedeDecoder);
	}

	// ----------------------------------------------------------------------------

	public static String getDigestStr(String type, String data) {
		return getHexString(Digest(type, data));
	}

	// ----------------------------------------------------------------------------

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
			logger.error("文件路径错误", e);
			throw new RopException("文件路径错误", e);
		} catch (IOException e) {
			logger.error("文件读取错误", e);
			throw new RopException("文件读取错误", e);
		} catch (NoSuchAlgorithmException e) {
			logger.error("没有对应算法", e);
			throw new RopException("没有对应算法", e);
		}

		finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("关闭文件错误", e);
				}
			}
		}
	}

	/**
	 * 数字签名
	 * 
	 * @param type
	 * @param data
	 */
	public static byte[] Digest(String type, byte[] data) {
		try {
			MessageDigest md5 = MessageDigest.getInstance(type);
			md5.update(data, 0, data.length);
			return md5.digest();
		} catch (NoSuchAlgorithmException e) {
			logger.error("没有对应算法", e);
			throw new RopException("没有对应算法", e);
		}
	}

	/**
	 * 数字签名
	 * 
	 * @param type
	 * @param data
	 */
	public static byte[] Digest(String type, String data) {
		try {
			MessageDigest md = MessageDigest.getInstance(type);
			return md.digest(data.getBytes(UTF_8));
		} catch (NoSuchAlgorithmException e) {
			logger.error("没有对应算法", e);
			throw new RopException("没有对应算法", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("编码不支持", e);
			throw new RopException("编码不支持", e);
		}
	}

	/**
	 * 二进制转十六进制字符串
	 * 
	 * @param bytes
	 */
	public static String getHexString(byte[] bytes) {
		// logger.info("Len:" + (bytes.length << 1));
		StringBuilder ret = new StringBuilder(bytes.length << 1);
		for (int i = 0; i < bytes.length; i++) {
			ret.append(Character.forDigit((bytes[i] >> 4) & 0xf, 16));
			ret.append(Character.forDigit(bytes[i] & 0xf, 16));
		}
		return ret.toString();
	}

	///////////////////////////////////////////////////////////////////////////////

	public static String base64Encoder(byte[] data) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	/**
	 * BASE64 加密
	 * 
	 * @param src
	 */
	public static String base64Encoder(String src) {
		try {
			return base64Encoder(src.getBytes(UTF_8));
		} catch (UnsupportedEncodingException e) {
			logger.error("编码不支持", e);
			throw new RopException("编码不支持", e);
		}
	}

	public static byte[] base64Decoderbytes(String dest) {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			return decoder.decodeBuffer(dest);
		} catch (IOException e) {
			logger.error("字符编码错误", e);
			throw new RopException("字符编码错误", e);
		}
	}

	/**
	 * BASE64解密
	 * 
	 * @param dest
	 */
	public static String base64Decoder(String dest) {
		try {
			return new String(base64Decoderbytes(dest), UTF_8);
		} catch (UnsupportedEncodingException e) {
			logger.error("编码不支持", e);
			throw new RopException("编码不支持", e);
		} catch (RopException e) {
			throw e;
		}
	}

	///////////////////////////////////////////////////////////////////////////////

	private static final String ALGORITHM_DESEDE = "DES"; // 定义 加密算法,可用
															// DES,DESede,Blowfish,CBC/PKCS5Padding

	public static byte[] getKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance(ALGORITHM_DESEDE);
			_generator.init(new SecureRandom(strKey.getBytes()));
			return _generator.generateKey().getEncoded();
		} catch (NoSuchAlgorithmException e) {
			logger.error("算法不支持", e);
			throw new RopException("算法不支持", e);
		}
	}

	/**
	 * 3DES加密
	 * 
	 * @param src
	 * @param key
	 */
	public static String desedeEncoder(String src, byte[] key) {

		try {
			SecretKey secretKey = new SecretKeySpec(key, ALGORITHM_DESEDE);
			Cipher cipher = Cipher.getInstance(ALGORITHM_DESEDE);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] b = cipher.doFinal(src.getBytes(UTF_8));

			return getHexString(b);
		} catch (NoSuchAlgorithmException e) {
			logger.error("算法不支持", e);
			throw new RopException("算法不支持", e);
		} catch (InvalidKeyException e) {
			logger.error("密钥错误", e);
			throw new RopException("密钥错误", e);
		} catch (IllegalBlockSizeException e) {
			logger.error("格式错误", e);
			throw new RopException("格式错误", e);
		} catch (NoSuchPaddingException e) {
			logger.error("格式错误", e);
			throw new RopException("格式错误", e);
		} catch (BadPaddingException e) {
			logger.error("格式错误", e);
			throw new RopException("格式错误", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("编码不支持", e);
			throw new RopException("编码不支持", e);
		}
	}

	/**
	 * 3DES解密
	 * 
	 * @param dest
	 * @param key
	 */
	public static String desedeDecoder(String dest, byte[] key) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, ALGORITHM_DESEDE);
			Cipher cipher = Cipher.getInstance(ALGORITHM_DESEDE);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] b = cipher.doFinal(str2ByteArray(dest));

			return new String(b, UTF_8);
		} catch (NoSuchAlgorithmException e) {
			logger.error("算法不支持", e);
			throw new RopException("算法不支持", e);
		} catch (InvalidKeyException e) {
			logger.error("密钥错误", e);
			throw new RopException("密钥错误", e);
		} catch (IllegalBlockSizeException e) {
			logger.error("格式错误", e);
			throw new RopException("格式错误", e);
		} catch (NoSuchPaddingException e) {
			logger.error("格式错误", e);
			throw new RopException("格式错误", e);
		} catch (BadPaddingException e) {
			logger.error("格式错误", e);
			throw new RopException("格式错误", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("编码不支持", e);
			throw new RopException("编码不支持", e);
		}
	}

	/**
	 * 字符串转字节数组
	 * 
	 * @param s
	 */
	private static byte[] str2ByteArray(String s) {
		int byteArrayLength = s.length() / 2;
		byte[] b = new byte[byteArrayLength];
		for (int i = 0; i < byteArrayLength; i++) {
			byte b0 = (byte) Integer.valueOf(s.substring(i * 2, i * 2 + 2), 16).intValue();
			b[i] = b0;
		}
		return b;
	}

}
