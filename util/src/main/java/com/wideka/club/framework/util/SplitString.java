package com.wideka.club.framework.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author
 * 
 */
public final class SplitString {

	private SplitString() {

	}

	/**
	 * 
	 * @param ids
	 * @return
	 */
	public static String[] getRoleIds(String ids) {
		if (ids == null) {
			return new String[0];
		}
		return ids.split(",");
	}

	/**
	 * 
	 * @param roleIds
	 * @return
	 */
	public static String joinIds(String[] roleIds) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < roleIds.length; i++) {
			if (i == 0) {
				sb.append(roleIds[i]);
			} else {
				sb.append(",");
				sb.append(roleIds[i]);
			}
		}
		return sb.toString();
	}

	public static String buildBooksiteIdCacheKey(String id) {
		StringBuilder sb = new StringBuilder();
		sb.append("booksiteId_").append(id);
		return sb.toString();
	}

	public static String parseCacheKey(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		int index = key.lastIndexOf('_');
		return key.substring(index + 1);
	}

	/**
	 * 
	 * @param ctrl
	 * @return
	 */
	public static Map<String, String> parseCtrlParams(String ctrl) {
		Map<String, String> paraMap = new HashMap<String, String>();
		if (ctrl != null) {
			String[] params = ctrl.split("&");
			for (Object p : params) {
				String[] keyValues = p.toString().split("=");
				if (keyValues.length > 2 || keyValues.length < 2) {
					continue;
				}
				paraMap.put(keyValues[0], keyValues[1]);
			}
		}
		return paraMap;
	}

}
