package com.wideka.club.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author
 * 
 */
public final class LogUtil {

	private static final Logger logger = Logger.getLogger(LogUtil.class);

	private static final String TAG = "\":";

	private LogUtil() {
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String parserBean(Object o) {
		StringBuilder sb = new StringBuilder();
		try {
			Object obj = o;

			if (obj == null) {
				return "null";
			} else if (obj instanceof String || (obj instanceof StringBuilder)) {
				obj = obj.toString();
				String v = ((String) obj).replaceAll("/\\{0}\"{1}/", "\\\"");
				v = v.replaceAll("/\r/", "\\n");
				sb.append('"').append("params:").append('"').append(obj).append('"');
			} else if (obj instanceof Object[]) {
				Object[] os = (Object[]) obj;
				sb.append("[");
				for (int i = 0; i < os.length; i++) {
					Object obj0 = os[i];
					sb.append(parserBean(obj0)).append(',');
				}
				if (sb.charAt(sb.length() - 1) == ',') {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append("]");
			} else if ((obj instanceof Number) || (obj instanceof Boolean)) {
				sb.append(obj.toString());
			} else if (obj instanceof Collection) {
				Collection col = (Collection) obj;
				Iterator iter = col.iterator();
				sb.append("[");
				while (iter.hasNext()) {
					Object obj0 = iter.next();
					sb.append(parserBean(obj0)).append(',').append("\r\n");
				}
				if (sb.charAt(sb.length() - 1) == ',') {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append("]");
			} else if (obj instanceof Map) {
				sb.append('{');
				Map<Object, Object> map = (Map) obj;

				for (Map.Entry<Object, Object> entry : map.entrySet()) {
					Object key = entry.getKey();
					Object v = entry.getValue();
					sb.append('"').append(key.toString()).append(TAG).append(parserBean(v)).append(',');
				}

				if (sb.charAt(sb.length() - 1) == ',') {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append('}');
			} else if (obj instanceof java.util.Date) {
				sb.append('"').append(DateUtil.datetime((Date) obj, DateUtil.DEFAULT_DATETIME_FORMAT)).append('"');
			} else if (obj instanceof Exception) {
				Exception e = (Exception) obj;
				StringBuilder t = new StringBuilder();
				t.append(e.getClass().getName()).append(':').append(e.getMessage());
				sb.append(parserBean(t));
			} else if (obj instanceof Calendar) {
				Calendar c = (Calendar) obj;
				sb.append('"').append(DateUtil.datetime((Date) c.getTime(), DateUtil.DEFAULT_DATETIME_FORMAT))
					.append('"');
			} else {
				sb.append(obj.getClass().getName()).append(":");
				sb.append("{");
				Map<String, Object> map = PropertyUtils.describe(obj);

				for (Map.Entry<String, Object> entry : map.entrySet()) {
					String element = entry.getKey();
					Object value = entry.getValue();
					if (value == null) {
						sb.append("\"" + element + TAG).append('"').append("null").append('"').append(',');
					} else if (value instanceof String || (value instanceof StringBuilder)) {
						sb.append("\"" + element + TAG);
						value = value.toString();
						String v = ((String) value).replaceAll("/\\{0}\"{1}/", "\\\"");
						v = v.replaceAll("/\r/", "\\n");
						sb.append('"').append(v).append('"').append(',');
					} else if (value instanceof java.util.Date) {
						sb.append("\"" + element + TAG);
						sb.append('"').append(DateUtil.datetime((Date) value, DateUtil.DEFAULT_DATETIME_FORMAT))
							.append('"').append(',');

					} else if (value instanceof Calendar) {
						sb.append("\"" + element + TAG);
						Calendar c = (Calendar) value;
						sb.append('"').append(DateUtil.datetime((Date) c.getTime(), DateUtil.DEFAULT_DATETIME_FORMAT))
							.append('"').append(',');
					} else if (value instanceof java.lang.Number || (value instanceof Boolean)) {
						sb.append("\"" + element + TAG).append(":\"" + value + "\"").append(',');
					}
				}

				if (sb.charAt(sb.length() - 1) == ',') {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append('}');
			}
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException:", e);
		} catch (InvocationTargetException e) {
			logger.error("InvocationTargetException:", e);
		} catch (NoSuchMethodException e) {
			logger.error("NoSuchMethodException：", e);
		} catch (Exception e) {
			logger.error("Exception:", e);
		}
		return sb.toString();
	}

}
