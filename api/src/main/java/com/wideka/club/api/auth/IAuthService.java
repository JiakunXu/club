package com.wideka.club.api.auth;

import com.wideka.club.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IAuthService {

	/**
	 * 
	 * @param redirectUrl
	 * @return
	 */
	BooleanResult authorize(String redirectUrl);

	/**
	 * 
	 * @param redirectUrl
	 * @param state
	 * @return
	 */
	BooleanResult authorize(String redirectUrl, String state);

	/**
	 * 
	 * @param code
	 * @return
	 */
	String getOpenId(String code);

}
