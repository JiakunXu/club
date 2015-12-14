package com.wideka.club.weixin.service.impl;

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

	@Override
	public BooleanResult receive(String signature, String timestamp, String nonce, String data) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		System.out.println("signature: " + signature);
		System.out.println("nonce: " + nonce);
		System.out.println("data: " + data);

		result.setResult(true);
		return result;
	}

	public ICallbackService getCallbackService() {
		return callbackService;
	}

	public void setCallbackService(ICallbackService callbackService) {
		this.callbackService = callbackService;
	}

}
