package com.wideka.club.framework.struts.result;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.ognl.OgnlUtil;
import com.wideka.club.framework.struts.annotations.JsonResult;

/**
 * JSON Result
 * 
 * Two json format: 1. [ {...}, {...} ] or 2. { values: [ {...}, {...} ], total
 * : n }
 * 
 * @author tingjia.chentj
 * 
 */
public class JSONResult implements Result, StrutsStatics {

	private static final long serialVersionUID = -4293657447642850227L;

	private static final String APPLICATION_JSON = "application/json";

	/**
	 * Charset.
	 */
	protected String charset;

	/**
	 * 
	 * @param charset
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * 
	 * @see com.opensymphony.xwork.Result#execute(com.opensymphony.xwork.ActionInvocation)
	 */
	public void execute(ActionInvocation invocation) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		// 设置ContentType
		StringBuffer contentType = new StringBuffer();
		contentType.append(APPLICATION_JSON);
		contentType.append(isLegalCharSet() ? ("; charset=" + charset) : "; charset=GBK");
		response.setContentType(contentType.toString());

		Object action = invocation.getAction();
		Method method = null;
		try {
			method = action.getClass().getDeclaredMethod(invocation.getProxy().getMethod(), new Class[0]);
		} catch (Exception e) {
			method =
				action.getClass().getDeclaredMethod(
					"do" + invocation.getProxy().getMethod().substring(0, 1).toUpperCase()
						+ invocation.getProxy().getMethod().substring(1), new Class[0]);
		}
		StringBuffer json = new StringBuffer();
		JsonResult result = method.getAnnotation(JsonResult.class);
		if (result != null) {
			Field field = action.getClass().getDeclaredField(result.field());
			field.setAccessible(true);

			Object value = field.get(action);

			OgnlUtil ognlUtil = ActionContext.getContext().getContainer().getInstance(OgnlUtil.class);

			Map<String, Object> map = null;

			if (List.class.isAssignableFrom(field.getType())) {
				List<?> list = ((List<?>) value);
				if (list != null && list.size() > 0) {
					map = ognlUtil.getBeanMap(list.get(0));
				} else {
					json.append("[]");
				}
			} else {
				map = ognlUtil.getBeanMap(value);
			}

			if (map != null) {
				if (result != null) {
					String[] include = result.include();
					String[] exclude = result.exclude();
					// 处理包含的属性
					if (include.length > 0) {
						Map<String, Object> elements = new HashMap<String, Object>();
						for (int i = 0; i < include.length; i++) {
							elements.put(include[i], true);
						}
						map = elements;
					} else if (exclude.length > 0) {
						// 处理排除的属性
						for (int i = 0; i < exclude.length; i++) {
							map.remove(exclude[i]);
						}
					}
				}

				final Map<String, Object> m = map;
				PropertyFilter filter = new PropertyFilter() {
					public boolean apply(Object source, String name, Object value) {
						if (m.containsKey(name)) {
							return true;
						}

						return false;
					}
				};

				json.append(JSON.toJSONString(value, filter));
			}

			String fieldName = result.total();

			if (!"".equals(fieldName)) {
				Field total = action.getClass().getDeclaredField(result.total());
				total.setAccessible(true);
				json.insert(0, "{\"rows\":");
				json.append(",\"total\":");
				json.append(total.get(action));
				json.append("}");
			}
		}

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

	/**
	 * 验证字符集的有效性.
	 * 
	 * @return
	 */
	protected boolean isLegalCharSet() {
		Charset tmp = null;
		if (charset != null) {
			if (Charset.isSupported(charset)) {
				tmp = Charset.forName(charset);
			} else {
				tmp = null;
			}
		}
		return tmp != null;
	}

}
