package com.wideka.club.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.wideka.club.api.cache.IMemcachedCacheService;
import com.wideka.club.api.weixin.ITicketService;
import com.wideka.club.api.weixin.ITokenService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.exception.ServiceException;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.weixin.api.auth.IJSAPITicketService;
import com.wideka.weixin.api.auth.bo.Ticket;

/**
 * 
 * @author JiakunXu
 * 
 */
public class TicketServiceImpl implements ITicketService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(TicketServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	private ITokenService tokenService;

	private IJSAPITicketService jsapiTicketService;

	@Override
	public BooleanResult getTicket(String corpId, String corpSecret) {
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

		String t = null;
		String key = corpId.trim() + "&" + corpSecret.trim();

		try {
			t = (String) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_WX_TICKET + key);
		} catch (ServiceException e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_WX_TICKET + key, e);
		}

		if (StringUtils.isNotBlank(t)) {
			result.setCode(t);
			result.setResult(true);
			return result;
		}

		result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return result;
		}

		Ticket ticket = null;

		try {
			ticket = jsapiTicketService.getTicket(result.getCode());
		} catch (RuntimeException e) {
			logger.error(e);

			result.setCode(e.getMessage());
			result.setResult(false);
			return result;
		}

		t = ticket.getTicket();

		try {
			memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_WX_TICKET + key, t, ticket.getExpiresIn());
		} catch (ServiceException e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_WX_TICKET + key, e);
		}

		result.setCode(t);
		return result;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public ITokenService getTokenService() {
		return tokenService;
	}

	public void setTokenService(ITokenService tokenService) {
		this.tokenService = tokenService;
	}

	public IJSAPITicketService getJsapiTicketService() {
		return jsapiTicketService;
	}

	public void setJsapiTicketService(IJSAPITicketService jsapiTicketService) {
		this.jsapiTicketService = jsapiTicketService;
	}

}
