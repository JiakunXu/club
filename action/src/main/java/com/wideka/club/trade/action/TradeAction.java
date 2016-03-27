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
	 * 购物车.
	 */
	private String[] cartIds;

	/**
	 * 交易编号(第三方支付).
	 */
	private String tradeNo;

	/**
	 * 订单类型.
	 */
	private String type;

	private List<Trade> tradeList;

	private Trade trade;

	/**
	 * 订单明细.
	 */
	private String orderId;

	/**
	 * 创建临时订单.
	 * 
	 * @return
	 */
	public String create() {
		BooleanResult result = null;

		// 直接购买
		if (cartIds == null || cartIds.length == 0) {
			result = tradeService.createTrade(this.getOpenId(), 0L, "1", "1", "1");

			if (result.getResult()) {
				tradeNo = result.getCode();
			} else {

			}

			return SUCCESS;
		} else {
			result = tradeService.createTrade(this.getOpenId(), 0L, cartIds);

			if (result.getResult()) {
				this.setResourceResult(result.getCode());
			} else {
				this.getServletResponse().setStatus(599);
				this.setResourceResult(result.getCode());
			}

			return RESOURCE_RESULT;
		}
	}

	/**
	 * 订单数据统计.
	 * 
	 * @return
	 */
	public String stats() {
		StringBuilder sb = new StringBuilder();

		String userId = this.getOpenId();
		Long shopId = 0L;

		sb.append(tradeService.getTradeCount(userId, shopId, new String[] { "check", "topay" })).append("&");
		sb.append(tradeService.getTradeCount(userId, shopId, new String[] { "tosend" })).append("&");
		sb.append(tradeService.getTradeCount(userId, shopId, new String[] { "send" })).append("&");
		sb.append(tradeService.getTradeCount(userId, shopId, new String[] { "sign" }));

		this.setResourceResult(sb.toString());

		return RESOURCE_RESULT;
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
		if ("topay".equals(type)) {
			tradeList = tradeService.getTradeList(userId, shopId, new String[] { "check", "topay" });
		} else if ("tosend".equals(type)) {
			tradeList = tradeService.getTradeList(userId, shopId, new String[] { "tosend" });
		} else if ("send".equals(type)) {
			tradeList = tradeService.getTradeList(userId, shopId, new String[] { "send" });
		} else if ("sign".equals(type)) {
			tradeList = tradeService.getTradeList(userId, shopId, new String[] { "sign" });
		} else {
			tradeList = tradeService.getTradeList(userId, shopId, null);
		}

		return SUCCESS;
	}

	/**
	 * 取消尚未付款的订单.
	 * 
	 * @return
	 */
	public String cancel() {
		BooleanResult result = tradeService.cancelTrade(this.getOpenId(), 0L, tradeNo);

		if (result.getResult()) {
			this.setResourceResult(result.getCode());
		} else {
			this.getServletResponse().setStatus(599);
			this.setResourceResult(result.getCode());
		}

		return RESOURCE_RESULT;
	}

	/**
	 * 
	 * @return
	 */
	public String detail() {
		trade = tradeService.getTrade(this.getOpenId(), 0L, tradeNo);

		return SUCCESS;
	}

	/**
	 * 申请退款.
	 * 
	 * @return
	 */
	public String refund() {
		trade = tradeService.getOrder(this.getOpenId(), 0L, tradeNo, orderId);

		return SUCCESS;
	}

	public ITradeService getTradeService() {
		return tradeService;
	}

	public void setTradeService(ITradeService tradeService) {
		this.tradeService = tradeService;
	}

	public String[] getCartIds() {
		return cartIds;
	}

	public void setCartIds(String[] cartIds) {
		this.cartIds = cartIds;
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

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
