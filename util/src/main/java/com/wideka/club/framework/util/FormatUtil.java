package com.wideka.club.framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author
 * 
 */
public final class FormatUtil {

	public static boolean isEmpty(String str) {
		if ((str == null) || (str.length() <= 0)) {
			return true;
		}

		return false;
	}

	public static String formatDecimal(Double decimal, String pattern) {
		if (decimal == null) {
			return null;
		}
		DecimalFormat df = new DecimalFormat();
		if (isEmpty(pattern)) {
			df.applyPattern("#,###.##");
		} else {
			df.applyPattern(pattern);
		}
		return df.format(decimal);

	}

	public static String formatDecimal(Float decimal, String pattern) {
		if (decimal == null) {
			return null;
		}
		DecimalFormat df = new DecimalFormat();
		if (isEmpty(pattern)) {
			df.applyPattern("#,###.##");
		} else {
			df.applyPattern(pattern);
		}
		return df.format(decimal);

	}

	public static String formatDecimal(Double decimal) {
		return formatDecimal(decimal, "");
	}

	public static String formatDecimal(Float decimal) {
		return formatDecimal(decimal, "");
	}

	public static String formatDecimal(BigDecimal decimal, String pattern) {
		if (decimal == null) {
			return null;
		}
		DecimalFormat df = new DecimalFormat();
		if (isEmpty(pattern)) {
			df.applyPattern("#,###.##");
		} else {
			df.applyPattern(pattern);
		}
		return df.format(decimal);
	}

	/**
	 * 根据数据得到数据金额字符串格式（如果为负依然返回负值）.
	 * 
	 * @param amount
	 * @return
	 */
	public static String getAmountFormat(BigDecimal amount) {
		if (amount == null || amount.signum() == 0) {
			return "0.00";
		}
		return new DecimalFormat("###,##0.00").format(amount);
	}

	/**
	 * 根据字符串得到数据金额字符串格式（如果为负依然返回负值）.
	 * 
	 * @param amount
	 * @return
	 */
	public static String getAmountFormat(String amount) {
		return getAmountFormat(new BigDecimal(amount));
	}

	/**
	 * 根据数据得到金额字符串格式（如果为负,返回０.００）.
	 * 
	 * @param amount
	 * @return if(amount==null||amount<=0):0.00;else 220.00
	 */
	public static String getAmountFormatStr(BigDecimal amount) {
		if (amount == null || amount.signum() != 1) {
			return "0.00";
		}
		return new DecimalFormat("###,##0.00").format(amount);
	}

	/**
	 * 字符串大小写转换.
	 */
	public static String lowerToUpper(String s) {
		if (StringUtils.isBlank(s)) {
			return null;
		}

		StringBuilder t = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			t.append(s.substring(i, i + 1).toUpperCase());
		}

		return t.toString();
	}

}
