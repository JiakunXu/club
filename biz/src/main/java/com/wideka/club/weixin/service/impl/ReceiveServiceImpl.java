package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IMsgImageService;
import com.wideka.club.api.weixin.IMsgLocationService;
import com.wideka.club.api.weixin.IMsgTextService;
import com.wideka.club.api.weixin.IMsgVideoService;
import com.wideka.club.api.weixin.IMsgVoiceService;
import com.wideka.club.api.weixin.IReceiveService;
import com.wideka.club.api.weixin.ITokenService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.weixin.api.callback.ICallbackService;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class ReceiveServiceImpl implements IReceiveService {

	private ICallbackService callbackService;

	private IMsgTextService msgTextService;

	private IMsgImageService msgImageService;

	private IMsgVoiceService msgVoiceService;

	private IMsgVideoService msgVideoService;

	private IMsgLocationService msgLocationService;

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
			Content content =
				callbackService.callback(token, encodingAesKey, corpId, signature, timestamp, nonce, data);

			String msgType = content.getMsgType();

			if ("text".equals(msgType)) {
				result = msgTextService.createMsgText(content);
			} else if ("image".equals(msgType)) {
				result = msgImageService.createMsgImage(content);
			} else if ("voice".equals(msgType)) {
				result = msgVoiceService.createMsgVoice(content);
			} else if ("video".equals(msgType)) {
				result = msgVideoService.createMsgVideo(content);
			} else if ("shortvideo".equals(msgType)) {
				result = msgVideoService.createMsgVideo(content);
			} else if ("location".equals(msgType)) {
				result = msgLocationService.createMsgLocation(content);
			}
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

	public IMsgTextService getMsgTextService() {
		return msgTextService;
	}

	public void setMsgTextService(IMsgTextService msgTextService) {
		this.msgTextService = msgTextService;
	}

	public IMsgImageService getMsgImageService() {
		return msgImageService;
	}

	public void setMsgImageService(IMsgImageService msgImageService) {
		this.msgImageService = msgImageService;
	}

	public IMsgVoiceService getMsgVoiceService() {
		return msgVoiceService;
	}

	public void setMsgVoiceService(IMsgVoiceService msgVoiceService) {
		this.msgVoiceService = msgVoiceService;
	}

	public IMsgVideoService getMsgVideoService() {
		return msgVideoService;
	}

	public void setMsgVideoService(IMsgVideoService msgVideoService) {
		this.msgVideoService = msgVideoService;
	}

	public IMsgLocationService getMsgLocationService() {
		return msgLocationService;
	}

	public void setMsgLocationService(IMsgLocationService msgLocationService) {
		this.msgLocationService = msgLocationService;
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
