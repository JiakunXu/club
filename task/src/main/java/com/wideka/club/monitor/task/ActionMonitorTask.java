package com.wideka.club.monitor.task;

import java.util.List;

import com.wideka.club.api.cache.IMemcachedCacheService;
import com.wideka.club.api.monitor.IActionMonitorService;
import com.wideka.club.api.monitor.bo.ActionMonitor;
import com.wideka.club.framework.annotation.Profiler;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;

/**
 * 
 * @author xujiakun
 * 
 */
public class ActionMonitorTask {

	private Logger4jExtend logger = Logger4jCollection.getLogger(ActionMonitorTask.class);

	private IMemcachedCacheService memcachedCacheService;

	private IActionMonitorService actionMonitorService;

	@SuppressWarnings("unchecked")
	@Profiler
	public void actionMonitor() {
		List<ActionMonitor> list = null;
		try {
			list = (List<ActionMonitor>) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_ACTION_LOG);
		} catch (Exception e) {
			logger.error(e);
		}

		if (list != null && list.size() != 0 && actionMonitorService.createActionMonitor(list)) {
			try {
				memcachedCacheService.remove(IMemcachedCacheService.CACHE_KEY_ACTION_LOG);
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

	public IActionMonitorService getActionMonitorService() {
		return actionMonitorService;
	}

	public void setActionMonitorService(IActionMonitorService actionMonitorService) {
		this.actionMonitorService = actionMonitorService;
	}

}
