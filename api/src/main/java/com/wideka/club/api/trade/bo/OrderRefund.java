package com.wideka.club.api.trade.bo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单退款.
 * 
 * @author JiakunXu
 * 
 */
public class OrderRefund implements Serializable {

	private static final long serialVersionUID = 619027445184579870L;

	private Long refundId;

	private Long orderId;

	private String tradeNo;

	private String refundNo;

	/**
	 * 退款金额.
	 */
	private BigDecimal refundFee;

	/**
	 * 操作人ID.
	 */
	private String modifyUser;

	public Long getRefundId() {
		return refundId;
	}

	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

}
