package com.wideka.club.trade.action;

import java.util.List;

import com.wideka.club.api.trade.ITradeService;
import com.wideka.club.api.trade.bo.Trade;
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

	/**
	 * 订单类型.
	 */
	private String type;

	private List<Trade> tradeList;

	/**
	 * 创建临时订单.
	 * 
	 * @return
	 */
	public String create() {
		BooleanResult result = tradeService.createTrade(this.getOpenId(), 0L, "1");

		if (result.getResult()) {
			tradeNo = result.getCode();
		} else {

		}

		return SUCCESS;
	}

	/**
	 * 订单列表.
	 * 
	 * @return
	 */
	public String list() {
		String userId = this.getOpenId();
		Long shopId = 0L;

		// 待付款
		if ("pay".equals(type)) {
			tradeList = tradeService.getTradeList(userId, shopId, new String[] { "check", "topay" });
		} else if ("tosend".equals(type)) {
			tradeList = tradeService.getTradeList(userId, shopId, new String[] { "tosend" });
		} else if ("send".equals(type)) {
			tradeList = tradeService.getTradeList(userId, shopId, new String[] { "send" });
		} else {
			tradeList = tradeService.getTradeList(userId, shopId, null);
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Trade> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<Trade> tradeList) {
		this.tradeList = tradeList;
	}

}
