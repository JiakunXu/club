package com.wideka.club.framework.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author
 * 
 */
public final class MapUtil {

	private static final Object[] EMPTY_ARRAY = {};

	private static final int INDEX_3 = 3;

	private static final int BEGIN_INDEX_4 = 4;

	private MapUtil() {

	}

	/**
	 * 将一个bean转换成map.
	 * 
	 * @param map
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Map populateMap(Map map, Object bean) throws Exception {
		return populateMap(map, bean, null);
	}

	/**
	 * 假设prefix=detail.，bean带有一个属性name，则map中将有一个项：
	 * key=detail.name，value为bean的name属性.
	 * 
	 * @param map
	 * @param bean
	 * @param prefix
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map populateMap(Map map, Object bean, String prefix) throws Exception {
		boolean withoutPrefix = StringUtils.isBlank(prefix);

		try {
			Method[] methods = bean.getClass().getMethods();
			for (int i = 0; i < methods.length; i++) {
				String methodName = methods[i].getName();
				Class[] pts = methods[i].getParameterTypes();
				Class rt = methods[i].getReturnType();

				if (methodName.startsWith("get") && pts.length == 0 && !Void.class.equals(rt)) {
					String propName =
						Character.toLowerCase(methodName.charAt(INDEX_3)) + methodName.substring(BEGIN_INDEX_4);
					if ("class".equals(propName)) {
						continue;
					}

					String key = withoutPrefix ? propName : prefix + propName;

					Object value = methods[i].invoke(bean, EMPTY_ARRAY);
					if (value != null) {
						map.put(key, value);
					}
				}
			}

			return map;
		} catch (Exception e) {
			throw new Exception("populateMap: ", e);
		}
	}

	/**
	 * 将map转换成bean.
	 * 
	 * @param bean
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Object populateBean(Object bean, Map map) throws Exception {
		try {
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new Exception("populateBean: ", e);
		}
	}

	/**
	 * 假设prefix=detail.，若map中带有一个key为detail.name，
	 * 则bean.setName被调用，参数值为该key对应的value.
	 * 
	 * @param bean
	 * @param map
	 * @param prefix
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object populateBean(Object bean, Map map, String prefix) throws Exception {
		boolean withoutPrefix = StringUtils.isBlank(prefix);
		Map m = new HashMap();

		try {
			Method[] methods = bean.getClass().getMethods();
			for (int i = 0; i < methods.length; i++) {
				String methodName = methods[i].getName();

				if (methodName.startsWith("set")) {
					String propName =
						Character.toLowerCase(methodName.charAt(INDEX_3)) + methodName.substring(BEGIN_INDEX_4);
					String key = withoutPrefix ? propName : prefix + propName;

					Object value = map.get(key);
					if (value != null) {
						Class[] pts = methods[i].getParameterTypes();
						if (pts.length == 1) {
							m.put(propName, value);
						}
					}
				}
			}

			BeanUtils.populate(bean, m);
			return bean;
		} catch (Exception e) {
			throw new Exception("populateBean: ", e);
		}
	}

}
