package com.wideka.club.framework.log;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author xujiakun
 * 
 */
public final class Logger4jCollection {

	private static Map<String, Logger4jExtend> map = new HashMap<String, Logger4jExtend>();

	private Logger4jCollection() {

	}

	@SuppressWarnings("rawtypes")
	public static Logger4jExtend getLogger(Class clazz) {
		if (!map.containsKey(clazz.getName())) {
			map.put(clazz.getName(), new Logger4jExtend(clazz.getName()));
		}

		return map.get(clazz.getName());
	}

}
