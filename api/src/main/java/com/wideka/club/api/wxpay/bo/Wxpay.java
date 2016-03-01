package com.wideka.club.api.wxpay.bo;

import java.io.Serializable;

/**
 * 
 * @author JiakunXu
 * 
 */
public class Wxpay implements Serializable {

	private static final long serialVersionUID = -8637186720873751933L;

	/**
	 * 公众号id.
	 */
	private String appId;

	/**
	 * 时间戳.
	 */
	private String timeStamp;

	/**
	 * 随机字符串.
	 */
	private String nonceStr;

	/**
	 * 订单详情扩展字符串.
	 */
	private String packageValue;

	/**
	 * 签名方式.
	 */
	private String signType;

	/**
	 * 签名.
	 */
	private String paySign;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageValue() {
		return packageValue;
	}

	public void setPackageValue(String packageValue) {
		this.packageValue = packageValue;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

}
