package com.wideka.club.auth.service.impl;

import org.apache.commons.lang.StringUtils;

import com.wideka.club.api.auth.IAuthService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.exception.ServiceException;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.weixin.api.auth.IOAuth2Service;
import com.wideka.weixin.api.auth.bo.AccessToken;

/**
 * 
 * @author JiakunXu
 * 
 */
public class AuthServiceImpl implements IAuthService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(AuthServiceImpl.class);

	private IOAuth2Service oauth2Service;

	private String appId;

	private String appSecret;

	@Override
	public BooleanResult authorize(String redirectUrl) {
		return authorize(redirectUrl, null);
	}

	@Override
	public BooleanResult authorize(String redirectUrl, String state) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (StringUtils.isBlank(redirectUrl)) {
			result.setCode("redirectUrl 不能为空.");
			return result;
		}

		try {
			result.setCode(oauth2Service.authorize(appId, redirectUrl, "snsapi_base", state));
			result.setResult(true);
		} catch (ServiceException e) {
			logger.error(e);

			result.setCode(e.getMessage());
		}

		return result;
	}

	@Override
	public String getOpenId(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}

		AccessToken accessToken = null;

		try {
			accessToken = oauth2Service.accessToken(appId, appSecret, code);
		} catch (ServiceException e) {
			logger.error(e);
		}

		if (accessToken == null) {
			return null;
		}

		return accessToken.getOpenId();
	}

	public IOAuth2Service getOauth2Service() {
		return oauth2Service;
	}

	public void setOauth2Service(IOAuth2Service oauth2Service) {
		this.oauth2Service = oauth2Service;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

}
