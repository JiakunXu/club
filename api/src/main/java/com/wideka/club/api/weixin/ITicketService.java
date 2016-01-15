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
	 * @param corpId
	 * @param corpSecret
	 * @return
	 */
	BooleanResult getTicket(String corpId, String corpSecret);

}
