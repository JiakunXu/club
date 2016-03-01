package com.wideka.club.trade.action;

import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.util.DateUtil;
import com.wideka.club.framework.util.UUIDUtil;

/**
 * 
 * @author JiakunXu
 * 
 */
public class TradeAction extends BaseAction {

	private static final long serialVersionUID = -912767004509511731L;

	/**
	 * 交易编号(第三方支付).
	 */
	private String tradeNo;

	public String create() {
		tradeNo = DateUtil.getNowDatetimeStr(DateUtil.DEFAULT_DATEFULLDATE_FORMAT) + UUIDUtil.generate();

		return SUCCESS;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

}
