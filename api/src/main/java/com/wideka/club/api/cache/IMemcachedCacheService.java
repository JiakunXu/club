package com.wideka.club.api.cache;

import java.net.InetSocketAddress;
import java.util.List;

import com.wideka.club.api.cache.bo.CacheStats;
import com.wideka.club.framework.exception.ServiceException;
import com.wideka.club.framework.exception.SystemException;

/**
 * MemcachedCache.
 * 
 * @author JiakunXu
 * 
 */
public interface IMemcachedCacheService extends ICacheService<String, Object> {

	/**
	 * default_exp_time.
	 */
	int DEFAULT_EXP = 24 * 60 * 60;

	/**
	 * session.
	 */
	int CACHE_KEY_SESSION_DEFAULT_EXP = 168 * 60 * 60;

	/**
	 * session.
	 */
	int CACHE_KEY_SESSION_EXP = 3 * 60;

	// >>>>>>>>>>以下是权限相关<<<<<<<<<<

	/**
	 * sso token.
	 */
	int CACHE_KEY_SSO_TOKEN_DEFAULT_EXP = 60;

	/**
	 * user id.
	 */
	String CACHE_KEY_USER_ID = "key_user_id_";

	int CACHE_KEY_LOCKED_USER_ID_DEFAULT_EXP = 1 * 3;

	/**
	 * passport.
	 */
	String CACHE_KEY_PASSPORT = "key_passport_";

	// >>>>>>>>>>以下是监控相关<<<<<<<<<<

	/**
	 * log monitor.
	 */
	String CACHE_KEY_LOG_MONITOR = "key_log_monitor";

	int CACHE_KEY_LOG_MONITOR_DEFAULT_EXP = 0;

	/**
	 * action log.
	 */
	String CACHE_KEY_ACTION_LOG = "key_action_log";

	int CACHE_KEY_ACTION_LOG_DEFAULT_EXP = 0;

	// >>>>>>>>>>以下是微信相关<<<<<<<<<<

	/**
	 * token.
	 */
	String CACHE_KEY_WX_TOKEN = "key_wx_token_";

	String CACHE_KEY_WX_TICKET = "key_wx_ticket_";

	// >>>>>>>>>>以下是交易相关<<<<<<<<<<

	String CACHE_KEY_TRADE_NO = "key_trade_no_";

	int CACHE_KEY_TRADE_NO_DEFAULT_EXP = 3;

	// >>>>>>>>>>end<<<<<<<<<<

	/**
	 * incr.
	 * 
	 * @param key
	 * @param inc
	 * @return
	 * @throws ServiceException
	 */
	long incr(String key, int inc) throws ServiceException;

	/**
	 * incr.
	 * 
	 * @param key
	 * @param inc
	 * @return
	 * @throws ServiceException
	 */
	long incr(String key, long inc) throws ServiceException;

	/**
	 * decr.
	 * 
	 * @param key
	 * @param decr
	 * @return
	 * @throws ServiceException
	 */
	long decr(String key, int decr) throws ServiceException;

	/**
	 * decr.
	 * 
	 * @param key
	 * @param decr
	 * @return
	 * @throws ServiceException
	 */
	long decr(String key, long decr) throws ServiceException;

	/**
	 * flushAll.
	 * 
	 * @param address
	 * @throws SystemException
	 */
	void flushAll(InetSocketAddress address) throws SystemException;

	/**
	 * cache stats.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	List<CacheStats> getStats() throws ServiceException;

}
