package com.wideka.club.monitor.task;

import java.util.List;

import com.wideka.club.api.cache.IMemcachedCacheService;
import com.wideka.club.api.monitor.ILogMonitorService;
import com.wideka.club.api.monitor.bo.LogMonitor;
import com.wideka.club.framework.annotation.Profiler;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;

/**
 * 
 * @author xujiakun
 * 
 */
public class LogMonitorTask {

	private Logger4jExtend logger = Logger4jCollection.getLogger(LogMonitorTask.class);

	private IMemcachedCacheService memcachedCacheService;

	private ILogMonitorService logMonitorService;

	@SuppressWarnings("unchecked")
	@Profiler
	public void logMonitor() {
		List<LogMonitor> list = null;
		try {
			list = (List<LogMonitor>) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_LOG_MONITOR);
		} catch (Exception e) {
			logger.error(e);
		}

		if (list != null && list.size() != 0 && logMonitorService.createLogMonitor(list)) {
			try {
				memcachedCacheService.remove(IMemcachedCacheService.CACHE_KEY_LOG_MONITOR);
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public ILogMonitorService getLogMonitorService() {
		return logMonitorService;
	}

	public void setLogMonitorService(ILogMonitorService logMonitorService) {
		this.logMonitorService = logMonitorService;
	}

}
