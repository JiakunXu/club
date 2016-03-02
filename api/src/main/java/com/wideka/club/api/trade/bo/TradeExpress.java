package com.wideka.club.api.trade.bo;

/***
 * 交易物流.
 * 
 * @author JiakunXu
 * 
 */
public class TradeExpress {

	private Long id;

	/**
	 * 交易id.
	 */
	private Long tradeId;

	/**
	 * 物流公司id.
	 */
	private Long epsId;

	/**
	 * 物流单号.
	 */
	private String epsNo;

	private String modifyUser;

	// >>>>>>>>>>以下是辅助属性<<<<<<<<<<

	/**
	 * 物流公司.
	 */
	private String epsName;

	private String api;

	private String mobileApi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Long getEpsId() {
		return epsId;
	}

	public void setEpsId(Long epsId) {
		this.epsId = epsId;
	}

	public String getEpsNo() {
		return epsNo;
	}

	public void setEpsNo(String epsNo) {
		this.epsNo = epsNo;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getEpsName() {
		return epsName;
	}

	public void setEpsName(String epsName) {
		this.epsName = epsName;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getMobileApi() {
		return mobileApi;
	}

	public void setMobileApi(String mobileApi) {
		this.mobileApi = mobileApi;
	}

}
