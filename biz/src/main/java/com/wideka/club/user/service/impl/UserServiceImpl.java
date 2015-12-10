package com.wideka.club.user.service.impl;

import org.apache.commons.lang.StringUtils;

import com.wideka.club.api.cache.IMemcachedCacheService;
import com.wideka.club.api.user.IUserService;
import com.wideka.club.api.user.bo.User;
import com.wideka.club.framework.exception.ServiceException;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.user.dao.IUserDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class UserServiceImpl implements IUserService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(UserServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	private IUserDao userDao;

	@Override
	public User getUserByPassport(String passport) {
		if (StringUtils.isBlank(passport)) {
			return null;
		}

		String key = passport.trim().toUpperCase();

		User user = null;

		try {
			user = (User) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_PASSPORT + key);
		} catch (ServiceException e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_PASSPORT + key, e);
		}

		if (user != null) {
			return user;
		}

		user = getUser4Validate(passport);

		// not null then set to cache
		if (user != null) {
			try {
				memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_PASSPORT + key, user);
			} catch (ServiceException e) {
				logger.error(IMemcachedCacheService.CACHE_KEY_PASSPORT + key, e);
			}
		}

		return user;
	}

	@Override
	public User getUser4Validate(String passport) {
		if (StringUtils.isBlank(passport)) {
			return null;
		}

		try {
			return userDao.getUserByPassport(passport.trim());
		} catch (Exception e) {
			logger.error(passport, e);
		}

		return null;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}
