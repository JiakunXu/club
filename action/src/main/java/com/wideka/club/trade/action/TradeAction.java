package com.wideka.club.trade.action;

import java.util.List;

import com.wideka.club.api.trade.ITradeService;
import com.wideka.club.api.trade.bo.Trade;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.struts.annotations.JsonResult;
import com.wideka.club.framework.util.ClientUtil;

/**
 * 
 * @author JiakunXu
 * 
 */
public class TradeAction extends BaseAction {

	private static final long serialVersionUID = -3583761070404384603L;

	private ITradeService tradeService;

	private int total;

	private List<Trade> tradeList;

	private String tradeId;

	private String tradeNo;

	private String tradeDate;

	private String result;

	/**
	 * 二维码.
	 */
	private String tradeCode;

	private Trade trade;

	@JsonResult(field = "tradeList", include = { "tradeId", "tradeCode", "tradeNo", "tradeDate", "like", "likeDate",
		"itemName", "sellerName" }, total = "total")
	public String getTradeJsonList() {
		Trade t = new Trade();
		t = getSearchInfo(t);

		total = tradeService.getTradeCount(t);

		if (total > 0) {
			tradeList = tradeService.getTradeList(t);
		}

		return JSON_RESULT;
	}

	@JsonResult(field = "result")
	public String updateTradeNo() {
		BooleanResult res = tradeService.updateTradeNo(tradeId, tradeNo, getUser().getUserId().toString());

		this.setResult(res.getCode());
		if (!res.getResult()) {
			this.getServletResponse().setStatus(500);
		}

		return JSON_RESULT;
	}

	@JsonResult(field = "result")
	public String updateTradeDate() {
		BooleanResult res = tradeService.updateTradeDate(tradeId, tradeDate, getUser().getUserId().toString());

		this.setResult(res.getCode());
		if (!res.getResult()) {
			this.getServletResponse().setStatus(500);
		}

		return JSON_RESULT;
	}

	public String detail() {
		trade = tradeService.getTrade(tradeCode);

		return SUCCESS;
	}

	public String like() {
		BooleanResult res = tradeService.like(tradeCode, ClientUtil.getIpAddr(this.getServletRequest()));

		if (res.getResult()) {
			this.setSuccessMessage(res.getCode());
		} else {
			this.setFailMessage(res.getCode());
		}

		return RESULT_MESSAGE;
	}

	public String unlike() {
		BooleanResult res = tradeService.unlike(tradeCode, ClientUtil.getIpAddr(this.getServletRequest()));

		if (res.getResult()) {
			this.setSuccessMessage(res.getCode());
		} else {
			this.setFailMessage(res.getCode());
		}

		return RESULT_MESSAGE;
	}

	public String create() {
		int c = 0;
		Trade t = new Trade();
		t.setItemId("1");
		for (int i = 0; i < 10; i++) {
			BooleanResult res = tradeService.createTrade(t, getUser().getUserId().toString());
			if (res.getResult()) {
				c++;
			}
		}

		this.setSuccessMessage("成功创建 " + c + " 条交易数据。");

		return RESULT_MESSAGE;
	}

	public ITradeService getTradeService() {
		return tradeService;
	}

	public void setTradeService(ITradeService tradeService) {
		this.tradeService = tradeService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Trade> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<Trade> tradeList) {
		this.tradeList = tradeList;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

}
