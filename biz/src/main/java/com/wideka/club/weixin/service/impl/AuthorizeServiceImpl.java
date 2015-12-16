package com.wideka.club.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.wideka.club.api.weixin.IAuthorizeService;
import com.wideka.club.api.weixin.ITokenService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.weixin.api.user.IUserInfoService;
import com.wideka.weixin.api.user.bo.UserInfo;

/**
 * 
 * @author JiakunXu
 * 
 */
public class AuthorizeServiceImpl implements IAuthorizeService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(AuthorizeServiceImpl.class);

	private ITokenService tokenService;

	private IUserInfoService userInfoService;

	private String corpId;

	private String corpSecret;

	@Override
	public String authorize(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}

		BooleanResult result = tokenService.getToken(corpId, corpSecret);

		if (!result.getResult()) {
			return result.getCode();
		}

		try {
			UserInfo userInfo = userInfoService.getUserInfo(result.getCode(), code.trim());

			return JSON.toJSONString(userInfo);
		} catch (RuntimeException e) {
			logger.error(e);

			return e.getMessage();
		}
	}

	public ITokenService getTokenService() {
		return tokenService;
	}

	public void setTokenService(ITokenService tokenService) {
		this.tokenService = tokenService;
	}

	public IUserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(IUserInfoService userInfoService) {
		this.userInfoService = userInfoService;
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
