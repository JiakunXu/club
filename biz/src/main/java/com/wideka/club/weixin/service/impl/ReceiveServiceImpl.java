package com.wideka.club.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.wideka.club.api.weixin.IReceiveService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.weixin.api.suite.ICallbackService;

/**
 * 
 * @author JiakunXu
 * 
 */
public class ReceiveServiceImpl implements IReceiveService {

	private ICallbackService callbackService;

	private String token;

	private String encodingAesKey;

	private String corpId;

	@Override
	public BooleanResult receive(String signature, String timestamp, String nonce, String echostr, String data) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		System.out.println("signature:" + signature);
		System.out.println("timestamp:" + timestamp);
		System.out.println("nonce:" + nonce);
		System.out.println("echostr:" + echostr);
		System.out.println("data:" + data);

		if (StringUtils.isNotBlank(echostr)) {
			try {
				result.setCode(callbackService.verify(token, encodingAesKey, corpId, signature, timestamp, nonce,
					echostr));
				result.setResult(true);
			} catch (RuntimeException e) {
				result.setCode(e.getMessage());
				return result;
			}
		}

		result.setResult(true);
		return result;
	}

	public ICallbackService getCallbackService() {
		return callbackService;
	}

	public void setCallbackService(ICallbackService callbackService) {
		this.callbackService = callbackService;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEncodingAesKey() {
		return encodingAesKey;
	}

	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

}
