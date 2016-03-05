package com.wideka.club.framework.struts.interceptor;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 
 * @author JiakunXu
 * 
 */
public class OAuth2Interceptor implements Interceptor {

	private static final long serialVersionUID = 2100766542685755228L;

	private static final String OAUTH = "oauth2";

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		@SuppressWarnings("rawtypes")
		Map session = invocation.getInvocationContext().getSession();
		String openId = (String) session.get("ACEGI_SECURITY_LAST_OPEN_ID");

		if (StringUtils.isEmpty(openId)) {
			return OAUTH;
		}

		return invocation.invoke();
	}

}
