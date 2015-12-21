package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IReceiveService;
import com.wideka.club.api.weixin.ITokenService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.weixin.api.callback.ICallbackService;

/**
 * 
 * @author JiakunXu
 * 
 */
public class ReceiveServiceImpl implements IReceiveService {

	private ICallbackService callbackService;

	private ITokenService tokenService;

	private String token;

	private String encodingAesKey;

	private String corpId;

	private String corpSecret;

	@Override
	public BooleanResult verify(String signature, String timestamp, String nonce, String echostr) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			result.setCode(callbackService.verify(token, encodingAesKey, corpId, signature, timestamp, nonce, echostr));
			result.setResult(true);
		} catch (RuntimeException e) {
			result.setCode(e.getMessage());
		}

		return result;
	}

	@Override
	public BooleanResult callback(String signature, String timestamp, String nonce, String data) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		System.out.println("token:" + token);
		System.out.println("encodingAesKey:" + encodingAesKey);
		System.out.println("corpId:" + corpId);
		System.out.println("signature:" + signature);
		System.out.println("timestamp:" + timestamp);
		System.out.println("nonce:" + nonce);
		System.out.println("data:" + data);

		try {
			System.out.println("========================================");
			System.out.println(LogUtil.parserBean(callbackService.callback(token, encodingAesKey, corpId, signature,
				timestamp, nonce, data)));
			System.out.println("========================================");
			result.setResult(true);
		} catch (RuntimeException e) {
			result.setCode(e.getMessage());
		}

		return result;
	}

	@Override
	public void getCallbackIP() {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (result.getResult()) {
			try {
				System.out.println(LogUtil.parserBean(callbackService.getCallbackIP(result.getCode())));
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

	public ICallbackService getCallbackService() {
		return callbackService;
	}

	public void setCallbackService(ICallbackService callbackService) {
		this.callbackService = callbackService;
	}

	public ITokenService getTokenService() {
		return tokenService;
	}

	public void setTokenService(ITokenService tokenService) {
		this.tokenService = tokenService;
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

	public String getCorpSecret() {
		return corpSecret;
	}

	public void setCorpSecret(String corpSecret) {
		this.corpSecret = corpSecret;
	}

}
