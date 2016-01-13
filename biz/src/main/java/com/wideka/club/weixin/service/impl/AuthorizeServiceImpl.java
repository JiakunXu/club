package com.wideka.club.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.wideka.club.api.weixin.IAuthorizeService;
import com.wideka.club.api.weixin.ITokenService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.exception.ServiceException;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.weixin.api.user.IUserService;
import com.wideka.weixin.api.user.bo.UserInfo;

/**
 * 
 * @author JiakunXu
 * 
 */
public class AuthorizeServiceImpl implements IAuthorizeService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(AuthorizeServiceImpl.class);

	private ITokenService tokenService;

	private IUserService userService;

	private String corpId;

	private String corpSecret;

	@Override
	public UserInfo getUserInfo(String code) throws ServiceException {
		if (StringUtils.isBlank(code)) {
			throw new ServiceException("code cannot be null.");
		}

		BooleanResult result = tokenService.getToken(corpId, corpSecret);

		if (!result.getResult()) {
			throw new ServiceException(result.getCode());
		}

		try {
			return userService.getUserInfo(result.getCode(), code.trim());
		} catch (RuntimeException e) {
			logger.error(e);

			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public BooleanResult authSucc(String code) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		UserInfo user = null;
		try {
			user = getUserInfo(code);
		} catch (ServiceException e) {
			result.setCode(e.getMessage());
			return result;
		}

		if (user == null) {
			result.setCode("user is null.");
			return result;
		}

		BooleanResult res = tokenService.getToken(corpId, corpSecret);

		if (!res.getResult()) {
			throw new ServiceException(result.getCode());
		}

		try {
			userService.authSucc(res.getCode(), user.getUserId());
		} catch (RuntimeException e) {
			result.setCode(e.getMessage());
			return result;
		}

		result.setCode(JSON.toJSONString(user));
		result.setResult(true);
		return result;
	}

	public ITokenService getTokenService() {
		return tokenService;
	}

	public void setTokenService(ITokenService tokenService) {
		this.tokenService = tokenService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getCorpSecret() {
		return corpSecret;
	}

	public void setCorpSecret(String corpSecret) {
		this.corpSecret = corpSecret;
	}

}
