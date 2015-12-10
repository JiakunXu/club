package com.wideka.club.framework.util;

import java.util.UUID;

/**
 * 唯一标识符发生器(20位).
 * 
 * @author
 * 
 */
public final class UUIDUtil {
	// 随机种子
	private static char[] x36s = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

	// 随机数长度
	private static final short LEN = 20;

	private UUIDUtil() {

	}

	public static String generate() {
		char[] chs = new char[LEN];

		// 生成前8位随机字符(以系统时间为随机池, 以36位数字+英文字母为随机种子)
		long v = (System.currentTimeMillis() - 936748800000L) >> 1;

		for (int i = 7; i > 0; i--) {
			chs[i] = x36s[(int) (v % 36)];
			v = v / 36;
		}
		// 确保第一个随机字符是"字母", 以符合一般编程的标识符定义
		chs[0] = x36s[(int) (v % 26) + 10];

		// 生成后12位随机字符(以UUID为随机池, 以36位数字+英文字母为随机种子)
		UUID u = UUID.randomUUID();
		v = u.getLeastSignificantBits() ^ u.getMostSignificantBits();
		if (v < 0) {
			v = -v;
		}

		for (int i = 8; i < LEN; i++) {
			chs[i] = x36s[(int) (v % 36)];
			v = v / 36;
		}

		return new String(chs);
	}

}
