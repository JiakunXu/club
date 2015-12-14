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
	 * @param data
	 * @return
	 */
	BooleanResult receive(String signature, String timestamp, String nonce, String data);

}
