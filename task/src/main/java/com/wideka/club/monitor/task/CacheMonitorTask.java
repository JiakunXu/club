package com.wideka.club.monitor.task;

import com.wideka.club.api.cache.IMemcachedCacheService;
import com.wideka.club.api.monitor.ICacheMonitorService;
import com.wideka.club.framework.annotation.Profiler;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;

/**
 * 
 * @author xujiakun
 * 
 */
public class CacheMonitorTask {

	private Logger4jExtend logger = Logger4jCollection.getLogger(CacheMonitorTask.class);

	private IMemcachedCacheService memcachedCacheService;

	private ICacheMonitorService cacheMonitorService;

	@Profiler
	public void cacheMonitor() {
		try {
			cacheMonitorService.createCacheMonitor(memcachedCacheService.getStats());
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public ICacheMonitorService getCacheMonitorService() {
		return cacheMonitorService;
	}

	public void setCacheMonitorService(ICacheMonitorService cacheMonitorService) {
		this.cacheMonitorService = cacheMonitorService;
	}

}
