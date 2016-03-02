package com.wideka.club.trade.action;

import com.wideka.club.api.trade.ITradeService;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public class TradeAction extends BaseAction {

	private static final long serialVersionUID = -912767004509511731L;

	private ITradeService tradeService;

	/**
	 * 交易编号(第三方支付).
	 */
	private String tradeNo;

	public String create() {
		BooleanResult result = tradeService.createTrade(Long.valueOf(this.getOpenId()), 0l, "1");

		if (result.getResult()) {
			tradeNo = result.getCode();
		} else {

		}

		return SUCCESS;
	}

	public ITradeService getTradeService() {
		return tradeService;
	}

	public void setTradeService(ITradeService tradeService) {
		this.tradeService = tradeService;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

}
