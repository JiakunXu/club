package com.wideka.club.api.weixin;

import com.wideka.club.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IReceiveService {

	/**
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	BooleanResult verify(String signature, String timestamp, String nonce, String echostr);

	/**
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param data
	 * @return
	 */
	BooleanResult callback(String signature, String timestamp, String nonce, String data);

	/**
	 * 
	 */
	void getCallbackIP();

}
