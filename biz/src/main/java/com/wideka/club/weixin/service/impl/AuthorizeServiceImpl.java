package com.wideka.club.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.wideka.club.api.weixin.IAuthorizeService;
import com.wideka.club.api.weixin.ITokenService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.exception.ServiceException;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.weixin.api.message.IMessageService;
import com.wideka.weixin.api.message.bo.Text;
import com.wideka.weixin.api.user.IUserInfoService;
import com.wideka.weixin.api.user.bo.User;

/**
 * 
 * @author JiakunXu
 * 
 */
public class AuthorizeServiceImpl implements IAuthorizeService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(AuthorizeServiceImpl.class);

	private ITokenService tokenService;

	private IUserInfoService userInfoService;

	private IMessageService messageService;

	private String corpId;

	private String corpSecret;

	@Override
	public User getUserInfo(String code) throws ServiceException {
		if (StringUtils.isBlank(code)) {
			throw new ServiceException("code cannot be null.");
		}

		BooleanResult result = tokenService.getToken(corpId, corpSecret);

		if (!result.getResult()) {
			throw new ServiceException(result.getCode());
		}

		try {
			return userInfoService.getUserInfo(result.getCode(), code.trim());
		} catch (RuntimeException e) {
			logger.error(e);

			try {
				Text text = new Text();
				text.setContent(e.getMessage());
				messageService.send(result.getCode(), "Jiakun.Xu", null, null, 19, text, "1");
			} catch (RuntimeException re) {
				logger.error(re);
			}

			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public BooleanResult authSucc(String code) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		User user = null;
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
			userInfoService.authSucc(res.getCode(), user.getUserId());
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

	public IUserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(IUserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public IMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
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
