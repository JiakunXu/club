package com.wideka.club.express.action;

import com.wideka.club.framework.action.BaseAction;

/**
 * 
 * @author JiakunXu
 * 
 */
public class ExpressAction extends BaseAction {

	private static final long serialVersionUID = -7830510972757206031L;

	/**
	 * 
	 * @return
	 */
	public String index() {
		return SUCCESS;
	}

	/**
	 * 寄件.
	 * 
	 * @return
	 */
	public String ship() {
		return SUCCESS;
	}

	public String waybill() {
		return SUCCESS;
	}

	public String rate() {
		return SUCCESS;
	}

	public String time() {
		return SUCCESS;
	}

	/**
	 * 服务网点.
	 * 
	 * @return
	 */
	public String store() {
		return SUCCESS;
	}

	public String range() {
		return SUCCESS;
	}

}
