package com.wideka.club.framework.struts.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.wideka.club.api.cache.IMemcachedCacheService;
import com.wideka.club.api.monitor.bo.ActionMonitor;
import com.wideka.club.api.user.bo.User;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.ClientUtil;
import com.wideka.club.framework.util.DateUtil;

/**
 * ActionMonitorInterceptor.
 * 
 * @author xujiakun
 * 
 */
public class ActionMonitorInterceptor implements Interceptor {

	private static final long serialVersionUID = -57833731348869514L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(ActionMonitorInterceptor.class);

	private IMemcachedCacheService memcachedCacheService;

	public void init() {
	}

	public void destroy() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {

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

		com.wideka.club.framework.annotation.ActionMonitor actionLog =
			method.getAnnotation(com.wideka.club.framework.annotation.ActionMonitor.class);
		if (actionLog != null) {
			String actionName = actionLog.actionName();
			// actionName 不为空时，记录操作日志
			if (StringUtils.isEmpty(actionName)) {
				return invocation.invoke();
			}

			@SuppressWarnings("rawtypes")
			Map session = invocation.getInvocationContext().getSession();
			User user = (User) session.get("ACEGI_SECURITY_LAST_LOGINUSER");
			if (user == null) {
				return invocation.invoke();
			}

			ActionMonitor monitor = new ActionMonitor();
			monitor.setUserId(user.getUserId());
			monitor.setActionName(actionName);

			ActionContext ctx = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
			monitor.setIp(ClientUtil.getIpAddr(request));
			monitor.setCreateDate(DateUtil.getNowDatetimeStr());

			try {
				@SuppressWarnings("unchecked")
				List<ActionMonitor> monitors =
					(List<ActionMonitor>) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_ACTION_LOG);

				if (monitors == null || monitors.size() == 0) {
					monitors = new ArrayList<ActionMonitor>();
				}

				monitors.add(monitor);

				memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_ACTION_LOG, monitors,
					IMemcachedCacheService.CACHE_KEY_ACTION_LOG_DEFAULT_EXP);
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return invocation.invoke();
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

}
