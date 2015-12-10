package com.wideka.club.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.JavaScriptUtils;

/**
 * 
 * @author jacky.chenb
 * 
 */
public final class EncodeUtil {

	public static final String APP_ENCODING = "GBK";

	public static final String DB_ENCODING = "GBK";

	/**
	 * 预定义图片格式.
	 */
	static final String[] SUFFIXS = { ".gif", ".jpg", ".jpeg", ".png", ".ico" };

	/**
	 * 根据数据库的字符集计算出该字符串的存储字节数.
	 */
	public static int getDBLength(String str) {
		if (str == null) {
			return 0;
		}

		try {
			return str.getBytes(DB_ENCODING).length;
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * 根据应用的字符集将字符串转化成字节数组.
	 */
	public static byte[] toBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes(APP_ENCODING);
			} catch (UnsupportedEncodingException e) {
				throw new IllegalStateException(e);
			}
		} else {
			return new byte[0];
		}
	}

	/**
	 * 根据应用的字符集将字节数组转化成字符串.
	 */
	public static String toString(byte[] bytes) {
		if (bytes != null) {
			try {
				return new String(bytes, APP_ENCODING);
			} catch (UnsupportedEncodingException e) {
				throw new IllegalStateException(e);
			}
		} else {
			return null;
		}
	}

	/**
	 * 根据应用的字符集对字符串进行URL编码.
	 */
	public static String url(Object obj) {
		if (obj == null) {
			return "";
		}

		try {
			return URLEncoder.encode(obj.toString(), APP_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * 根据应用的字符集对字符串URL编码转化中文.
	 * 
	 * @param obj
	 * @return
	 */
	public static String decode(Object obj) {
		if (obj == null) {
			return "";
		}

		try {
			return URLDecoder.decode(obj.toString(), APP_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * 对单、双引号中的特殊字符进行预处理，防止javascript解析出错。 当输出脚本变量时，必须预处理。例如： <script> var s =
	 * "<%= EncodeUtil.script(value) %>"; <\script>.
	 */
	public static String javascript(Object obj) {
		if (obj == null) {
			return "";
		}

		return JavaScriptUtils.javaScriptEscape(obj.toString());
	}

	/**
	 * 对特殊字符<>"&等进行预处理，防止HTML解析出错。 当输出input、area等元素的value属性时，必须预处理。例如： <input
	 * name="foo" value="<%= EncodeUtil.html(value) %>">.
	 */
	public static String html(Object obj) {
		if (obj == null) {
			return "";
		}

		return StringEscapeUtils.escapeHtml(obj.toString());
	}

	/**
	 * 对特殊字符<>"&等进行预处理，防止XML解析出错。 当输出XML的属性值时，必须预处理。例如： <node
	 * attribute="<%= EncodeUtil.xml(value) %>" />.
	 */
	public static String xml(Object obj) {
		if (obj == null) {
			return "";
		}

		return StringEscapeUtils.escapeXml(obj.toString());
	}

	/**
	 * 对用户文本按常规显示习惯显示在html中:br元素、回车和空格作特殊处理,其余转义输出。例如： "11\r\n\r\n22<br>
	 * <br>
	 * ss<br />
	 * d t"--->"11<br/>
	 * <br/>
	 * 22<br/>
	 * <br/>
	 * ss<br/>
	 * d&nbsp;&nbsp;&nbsp;t".
	 * 
	 * @added by hao.lvh
	 */
	public static String htmlText(Object obj) {
		if (obj == null) {
			return "";
		}

		String objStr = obj.toString();
		objStr = StringEscapeUtils.escapeHtml(objStr);
		objStr = objStr.replaceAll("\\n|\\r\\n", "<br/>");
		objStr = objStr.replaceAll(" ", "&nbsp;");

		return objStr;
	}

	/**
	 * 截取子字符串.
	 * 
	 * @param str
	 *            ָ指定的字符串
	 * @param iLen
	 *            截取长度
	 * @return substr
	 */
	public static String substr(String str, int iLen) {
		return substr(str, iLen, false);
	}

	/**
	 * 1. 截取子字符串 2. 子字符串 + ... + title提示.
	 * 
	 * @param s
	 *            指定的字符串
	 * @param iLen
	 *            截取长度
	 * @param showTitle
	 *            是否添加title提示。true：显示、false：不显示，默认false
	 * @return substr
	 */
	public static String substr(String s, int iLen, boolean showTitle) {
		String str = s;

		if (str == null || "".equals(str)) {
			return "";
		}

		try {
			str = html(str);

			StringBuilder rtStr = new StringBuilder();
			String tmp = null;
			int cLen = 0;

			for (int i = 0; i < iLen; i++) {
				tmp = str.substring(i, i + 1);
				rtStr.append(tmp);

				cLen += tmp.getBytes(APP_ENCODING).length == 2 ? 2 : 1;

				if (cLen >= iLen) {
					break;
				}
			}

			if (iLen < getByteLen(str) && showTitle) {
				return "<span title=\"" + str + "\">" + rtStr.toString() + "...</span>";
			}

			return rtStr.toString();

		} catch (Exception e) {
			return str;
		}
	}

	/**
	 * 获取字符串的字节长度，汉字为2个字节，字母数字为1个字节.
	 * 
	 * @param s
	 * @return
	 */
	private static int getByteLen(String s) {
		if (StringUtils.isBlank(s)) {
			return 0;
		}

		int len = 0;

		try {
			for (int i = 0; i < s.length(); i++) {
				String tmp = s.substring(i, i + 1);
				len += tmp.getBytes(APP_ENCODING).length == 2 ? 2 : 1;
			}
		} catch (Exception e) {
			return len;
		}

		return len;
	}

	public static Map<String, String> getUserIdAppInstanceId(String sipUsername) {
		if (StringUtils.isBlank(sipUsername)) {
			return null;
		}
		String[] attrs = sipUsername.split("@");
		if (attrs.length != 2) {
			return null;
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("userId", attrs[0]);
		result.put("appInstanceId", attrs[1]);
		return result;
	}

	/**
	 * 校验字符串是否是合法的图片地址.
	 * 
	 * @param iconUrl
	 * @return
	 */
	public static boolean isIconUrl(String iconUrl) {
		if (StringUtils.isBlank(iconUrl)) {
			return false;
		}

		for (String suf : SUFFIXS) {
			if (iconUrl.toLowerCase().endsWith(suf)) {
				return true;
			}
		}
		return false;
	}

}
