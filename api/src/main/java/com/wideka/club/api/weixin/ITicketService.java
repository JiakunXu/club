package com.wideka.club.api.weixin;

import com.wideka.club.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface ITicketService {

	/**
	 * 
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	BooleanResult getTicket(String appId, String appSecret);

	/**
	 * 
	 * @param corpId
	 * @param corpSecret
	 * @return
	 */
	BooleanResult getTicket4Corp(String corpId, String corpSecret);

}
