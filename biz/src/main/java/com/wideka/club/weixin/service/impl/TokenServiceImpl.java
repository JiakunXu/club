package com.wideka.club.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.wideka.club.api.cache.IMemcachedCacheService;
import com.wideka.club.api.weixin.ITokenService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.exception.ServiceException;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.weixin.api.auth.IAccessTokenService;
import com.wideka.weixin.api.auth.bo.AccessToken;

/**
 * 
 * @author JiakunXu
 * 
 */
public class TokenServiceImpl implements ITokenService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(TokenServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	private IAccessTokenService accessTokenService;

	@Override
	public BooleanResult getToken(String corpId, String corpSecret) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (StringUtils.isBlank(corpId)) {
			result.setCode("corpId 不能为空.");
			return result;
		}

		if (StringUtils.isBlank(corpSecret)) {
			result.setCode("corpSecret 不能为空.");
			return result;
		}

		String token = null;
		String key = corpId.trim() + "&" + corpSecret.trim();

		try {
			token = (String) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_WX_TOKEN + key);
		} catch (ServiceException e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_WX_TOKEN + key, e);
		}

		if (StringUtils.isNotBlank(token)) {
			result.setCode(token);
			result.setResult(true);
			return result;
		}

		AccessToken accessToken = null;

		try {
			accessToken = accessTokenService.getToken(corpId, corpSecret);
		} catch (RuntimeException e) {
			logger.error(e);

			result.setCode(e.getMessage());
			return result;
		}

		token = accessToken.getAccessToken();

		try {
			memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_WX_TOKEN + key, token,
				accessToken.getExpiresIn());
		} catch (ServiceException e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_WX_TOKEN + key, e);
		}

		result.setCode(token);
		result.setResult(true);
		return result;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IAccessTokenService getAccessTokenService() {
		return accessTokenService;
	}

	public void setAccessTokenService(IAccessTokenService accessTokenService) {
		this.accessTokenService = accessTokenService;
	}

}
