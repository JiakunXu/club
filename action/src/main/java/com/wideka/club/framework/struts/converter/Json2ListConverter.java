package com.wideka.club.framework.struts.converter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ognl.OgnlOps;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.util.StrutsTypeConverter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;

/**
 * 客户端将从表数据以JSON数组的方式提交到服务端 此类就是实现JSON与List<E>对象之间的互换.
 * 
 * @author tingjia.chentj
 * 
 */
public class Json2ListConverter extends StrutsTypeConverter {

	// modify by xujiakun yyyy-MM-dd'T'HH:mm:ss'Z' to yyyy-MM-dd'T'HH:mm:ss
	private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

	private Logger4jExtend log = Logger4jCollection.getLogger(Json2ListConverter.class);

	/**
	 * 集合中的实际类型.
	 */
	@SuppressWarnings("rawtypes")
	private Class genericType;

	@SuppressWarnings("rawtypes")
	@Override
	public Object convertValue(Map context, Object target, Member member, String propertyName, Object value,
		Class toType) {
		// 获取集合中的实际类型
		if (member instanceof Method) {
			ParameterizedType type = (ParameterizedType) ((Method) member).getGenericParameterTypes()[0];
			Type actualType = type.getActualTypeArguments()[0];
			genericType = (Class) actualType;
		}
		return super.convertValue(context, target, member, propertyName, value, toType);
	}

	/**
	 * 将JSON格式转换成List<E>.
	 * 
	 * @param context
	 * @param values
	 *            字符串数组长度为1，values[0]为JSON格式：[{...},{...},...{...}]
	 * @param toClass
	 * 
	 * @see com.opensymphony.webwork.util.WebWorkTypeConverter#convertFromString(java.util.Map,
	 *      java.lang.String[], java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (genericType == null) {
			return null;
		}
		// XWorkConverter.setInstance(new XWorkConverter() {});
		ArrayList<Object> list = new ArrayList<Object>();
		try {
			JSONArray array = new JSONArray(values[0]);
			for (int i = 0; i < array.length(); i++) {
				Object obj = genericType.newInstance();
				Map map = toMap(array.getJSONObject(i));
				PropertyDescriptor[] props = PropertyUtils.getPropertyDescriptors(obj);
				for (PropertyDescriptor desc : props) {
					if (map.containsKey(desc.getName())) {
						Object value = null;
						// 处理日期型
						// "yyyy-MM-dd'T'HH:mm:ss'Z'"此格式是由json2.js转换后的Date格式
						if (java.util.Date.class.isAssignableFrom(desc.getPropertyType())) {
							try {
								value = new SimpleDateFormat(DATE_PATTERN).parse((String) map.get(desc.getName()));
							} catch (ParseException e) {
								log.error(e);
							}
						} else if (java.sql.Date.class.isAssignableFrom(desc.getPropertyType())) {
							try {
								value =
									new java.sql.Date(new SimpleDateFormat(DATE_PATTERN).parse(
										(String) map.get(desc.getName())).getTime());
							} catch (ParseException e) {
								log.error(e);
							}
						} else {
							value = OgnlOps.convertValue(map.get(desc.getName()), desc.getPropertyType());

						}
						desc.getWriteMethod().invoke(obj, new Object[] { value });
					}
				}
				list.add(obj);
			}
		} catch (Exception e) {
			log.error("JSON 数据转换错误.", e);
		}
		return list;
	}

	/**
	 * 将List<E>转换成JSON格式.
	 * 
	 * @param context
	 * @param o
	 * 
	 * @see com.opensymphony.webwork.util.WebWorkTypeConverter#convertToString(java.util.Map,
	 *      java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object o) {
		String json = null;
		if (o instanceof List) {
			json = new JSONArray((List) o).toString();
		}
		return json;
	}

	// 将JSONObject转换成Map
	@SuppressWarnings("rawtypes")
	private Map toMap(JSONObject object) throws JSONException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Iterator iter = object.keys();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			Object val = object.get(key);
			map.put(key, "".equals(val.toString()) ? null : val);
		}
		return map;
	}

}
