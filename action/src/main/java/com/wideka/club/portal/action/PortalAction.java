package com.wideka.club.portal.action;

import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.api.weixin.bo.Ticket;
import com.wideka.club.framework.action.BaseAction;

/**
 * 
 * @author JiakunXu
 * 
 */
public class PortalAction extends BaseAction {

	private static final long serialVersionUID = 2191525146456353749L;

	private IWeixinService weixinService;

	private Ticket ticket;

	/**
	 * 登录首页.
	 * 
	 * @return
	 */
	public String index() {
		return SUCCESS;
	}

	public String home() {
		return SUCCESS;
	}

	/**
	 * 移动商场.
	 * 
	 * @return
	 */
	public String homepage() {
		ticket = weixinService.getTicket(env.getProperty("appUrl") + "/homepage.htm");

		return SUCCESS;
	}

	public IWeixinService getWeixinService() {
		return weixinService;
	}

	public void setWeixinService(IWeixinService weixinService) {
		this.weixinService = weixinService;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
