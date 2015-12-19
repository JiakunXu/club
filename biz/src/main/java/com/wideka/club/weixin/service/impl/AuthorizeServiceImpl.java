package com.wideka.club.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.wideka.club.api.weixin.IAuthorizeService;
import com.wideka.club.api.weixin.ITokenService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.weixin.api.message.IMessageService;
import com.wideka.weixin.api.message.bo.File;
import com.wideka.weixin.api.message.bo.Image;
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
	public String authorize(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}

		BooleanResult result = tokenService.getToken(corpId, corpSecret);

		if (!result.getResult()) {
			return result.getCode();
		}

		try {
			User user = userInfoService.getUserInfo(result.getCode(), code.trim());

			return JSON.toJSONString(user);
		} catch (RuntimeException e) {
			logger.error(e);

			try {
				Text text = new Text();
				text.setContent(e.getMessage());
				// messageService.send(result.getCode(), "Jiakun.Xu", null, null, 19, text,
				// "1");
			} catch (RuntimeException re) {
				logger.error(re);
			}

			try {
				Image file = new Image();
				file.setMediaId("1_4QdjNOxBUhYefcQXEMkql6Y0oj0tGCS_JukaziakGDOdLE4pwojcE5QdbOJSos5kDZ-tjQN-MfgZaMzjiMOpA");
				messageService.send(result.getCode(), "Jiakun.Xu", null, null, 19, file, "1");
			} catch (RuntimeException re) {
				logger.error(re);
			}

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
