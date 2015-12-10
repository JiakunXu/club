package com.wideka.club.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

/**
 * 
 * @author
 * 
 */
public final class EncryptUtil {

	private static final Logger logger = Logger.getLogger(EncryptUtil.class);

	private static final String CHARSET_UTF8 = "UTF-8";

	private static final String SECRET_KEY = "papa2-inc";

	private EncryptUtil() {

	}

	public static String encryptMD5(String data) throws IOException {
		byte[] bytes = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			bytes = md.digest(data.getBytes(CHARSET_UTF8));
		} catch (GeneralSecurityException gse) {
			String msg = getStringFromException(gse);
			throw new IOException(msg, gse);
		}

		return byte2hex(bytes);
	}

	public static String encryptSHA(String data) throws IOException {
		byte[] bytes = null;

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			bytes = md.digest(data.getBytes(CHARSET_UTF8));
		} catch (GeneralSecurityException gse) {
			String msg = getStringFromException(gse);
			throw new IOException(msg, gse);
		}

		return byte2hex(bytes);
	}

	public static String encryptHMAC(String data) throws IOException {
		return encryptHMAC(data, SECRET_KEY);
	}

	public static String encryptHMAC(String data, String secret) throws IOException {
		byte[] bytes = null;

		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes(CHARSET_UTF8), "HmacSHA1");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes(CHARSET_UTF8));
		} catch (GeneralSecurityException gse) {
			String msg = getStringFromException(gse);
			throw new IOException(msg, gse);
		}

		return byte2hex(bytes);
	}

	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();

		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex);
		}

		return sign.toString();
	}

	private static String getStringFromException(Throwable e) {
		String result = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			PrintStream ps = new PrintStream(bos, false, CHARSET_UTF8);
			e.printStackTrace(ps);
			result = bos.toString(CHARSET_UTF8);
		} catch (UnsupportedEncodingException ee) {
			logger.error(ee);
		}

		return result;
	}

}
