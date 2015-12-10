package com.wideka.club.framework.struts.interceptor;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.wideka.club.framework.annotation.Decode;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;

import ognl.ObjectPropertyAccessor;
import ognl.OgnlException;
import ognl.OgnlRuntime;
import ognl.PropertyAccessor;

/**
 * 此类在webwork配置文件中配置时须加在params之前 用于处理AJAX请求时的乱码问题.
 * 
 * @author tingjia.chentj
 */
public class DecodeParametersInterceptor extends AroundInterceptor {

	private static final long serialVersionUID = 6484320257843908148L;

	private static Logger4jExtend log = Logger4jCollection.getLogger(DecodeParametersInterceptor.class);

	private static final ThreadLocal<Boolean> ENCODED = new ThreadLocal<Boolean>();

	public static boolean isEncoded() {
		return ENCODED.get() == null ? false : ENCODED.get();
	}

	public static void setEncoded(boolean value) {
		ENCODED.set(value);
	}

	static {
		OgnlRuntime.setPropertyAccessor(Object.class, getObjectAccessor());
	}

	protected void after(ActionInvocation invocation, String result) throws Exception {
		ENCODED.set(null);
		ENCODED.remove();
	}

	protected void before(ActionInvocation invocation) throws Exception {

	}

	private static PropertyAccessor getObjectAccessor() {
		// 在设置前根据需要先进行URLDecode
		return new ObjectPropertyAccessor() {
			@SuppressWarnings("rawtypes")
			@Override
			public void setProperty(Map context, Object target, Object oname, Object value) throws OgnlException {
				setEncoded("XMLHttpRequest".equalsIgnoreCase(ServletActionContext.getRequest().getHeader(
					"x-requested-with")));
				if (DecodeParametersInterceptor.isEncoded()) {
					try {
						boolean decode = target.getClass().isAnnotationPresent(Decode.class);
						decode =
							decode
								|| getDeclaredField(target.getClass(), (String) oname)
									.isAnnotationPresent(Decode.class);
						if (decode) {
							String[] tmp = value == null ? new String[0] : (String[]) value;
							for (int i = 0, len = tmp.length; i < len; i++) {
								if (tmp[i] != null) {
									tmp[i] = URLDecoder.decode(tmp[i], "UTF-8");
								}
							}
						}
					} catch (Exception e) {
						// getDeclaredField可能会返回空值（找不到相应的field时）
						log.error(e);
					}
				}
				super.setProperty(context, target, oname, value);
			}

			@SuppressWarnings("rawtypes")
			private Field getDeclaredField(Class target, String name) {
				if (target == null) {
					return null;
				}
				try {
					return target.getDeclaredField(name);
				} catch (Exception e) {
					return getDeclaredField(target.getSuperclass(), name);
				}
			}
		};
	}

}
