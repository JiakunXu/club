package com.wideka.club.weixin.action;

import com.alibaba.fastjson.JSON;
import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.action.BaseAction;

/**
 * 
 * @author JiakunXu
 * 
 */
public class MenuAction extends BaseAction {

	private static final long serialVersionUID = -7366916512411572869L;

	private IWeixinService weixinService;

	/**
	 * 操作.
	 */
	private String op;

	private String menu;

	public String menu() {
		if ("get".equals(op)) {
			menu = JSON.toJSONString(weixinService.getMenu("19"));
			return "get";
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

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

}
