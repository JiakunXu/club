package com.wideka.club.weixin.action;

import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.action.BaseAction;

/**
 * 
 * @author JiakunXu
 * 
 */
public class AuthAction extends BaseAction {

	private static final long serialVersionUID = 5334199804808618041L;

	private IWeixinService weixinService;

	/**
	 * 操作.
	 */
	private String op;

	public String agent() {
		if ("oauth2".equals(op)) {
			return "oauth2";
		}

		if ("convert".equals(op)) {
			return "convert";
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
