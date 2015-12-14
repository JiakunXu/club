package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IReceiveService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.weixin.api.suite.ITicketService;

/**
 * 
 * @author JiakunXu
 * 
 */
public class ReceiveServiceImpl implements IReceiveService {

	private ITicketService ticketService;

	@Override
	public BooleanResult receive(String signature, String timestamp, String nonce, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	public ITicketService getTicketService() {
		return ticketService;
	}

	public void setTicketService(ITicketService ticketService) {
		this.ticketService = ticketService;
	}

}
