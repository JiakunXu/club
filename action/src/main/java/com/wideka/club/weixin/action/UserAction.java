package com.wideka.club.weixin.action;

import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.action.BaseAction;

/**
 * 
 * @author JiakunXu
 * 
 */
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 6328740646132798068L;

	private IWeixinService weixinService;

	/**
	 * 操作.
	 */
	private String op;

	public String user() {
		if ("list".equals(op)) {
			return "list";
		}

		return SUCCESS;
	}

	public IWeixinService getWeixinService() {
		return weixinService;
	}

	public void setWeixinService(IWeixinService weixinService) {
		this.weixinService = weixinService;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

}
