package com.wideka.club.framework.aop;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StopWatch;

import com.wideka.club.api.cache.IMemcachedCacheService;
import com.wideka.club.api.monitor.IMethodMonitorService;
import com.wideka.club.api.monitor.bo.MethodMonitor;
import com.wideka.club.framework.annotation.Profiler;
import com.wideka.club.framework.exception.ServiceException;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;

/**
 * method profiler for count method execute time.
 * 
 * @author xujiakun
 * 
 */
public class MethodProfiler {

	private Logger4jExtend logger = Logger4jCollection.getLogger(MethodProfiler.class);

	private IMemcachedCacheService memcachedCacheService;

	private IMethodMonitorService methodMonitorService;

	public Object profile(ProceedingJoinPoint call) throws Throwable {
		MethodSignature o = (MethodSignature) call.getSignature();
		Method method = o.getMethod();

		Profiler profiler = method.getAnnotation(Profiler.class);
		if (profiler == null) {
			return call.proceed();
		}

		StopWatch clock = new StopWatch();
		String key = method.getName() + "@" + method.getDeclaringClass().getName();

		try {
			// incr
			incr(key);

			clock.start(call.toShortString());
			return call.proceed();
		} finally {
			clock.stop();

			// decr
			decr(key);

			final MethodMonitor methodMonitor = new MethodMonitor();
			methodMonitor.setClassName(method.getDeclaringClass().getName());
			methodMonitor.setMethodName(method.getName());
			methodMonitor.setCost(clock.getTotalTimeMillis());

			ExecutorService service = null;
			try {
				service = Executors.newFixedThreadPool(1);
			} catch (IllegalArgumentException e) {
				logger.error(e);
			}

			if (service != null) {
				try {
					service.execute(new Runnable() {
						public void run() {
							methodMonitorService.createMethodMonitor(methodMonitor);
						}
					});
				} catch (RejectedExecutionException e) {
					logger.error(e);
				} catch (Exception e) {
					logger.error(e);
				}

				try {
					service.shutdown();
				} catch (SecurityException e) {
					logger.error(e);
				} catch (Exception e) {
					logger.error(e);
				}
			}

			// clock.prettyPrint()
		}
	}

	private void incr(String key) {
		try {
			memcachedCacheService.incr(key, 1);
		} catch (ServiceException e) {
			logger.error(e);
		}
	}

	private void decr(String key) {
		try {
			memcachedCacheService.decr(key, 1);
		} catch (ServiceException e) {
			logger.error(e);
		}
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IMethodMonitorService getMethodMonitorService() {
		return methodMonitorService;
	}

	public void setMethodMonitorService(IMethodMonitorService methodMonitorService) {
		this.methodMonitorService = methodMonitorService;
	}

}
