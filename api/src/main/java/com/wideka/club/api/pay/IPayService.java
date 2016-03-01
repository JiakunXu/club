package com.wideka.club.api.pay;

import com.wideka.club.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IPayService {

	String PAY_TYPE_ALIPAY = "alipay";

	String PAY_TYPE_WXPAY = "wxpay";

	/**
	 * 支付.
	 * 
	 * @param userId
	 * @param openId
	 * @param tradeNo
	 * @param payType
	 * @param ip
	 * @return
	 */
	BooleanResult pay(Long userId, String openId, String tradeNo, String payType, String ip);

	// >>>>>>>>>>以下是第三方交易平台<<<<<<<<<<

	/**
	 * 支付通知.
	 * 
	 * @param wxNotify
	 * @return
	 */
	BooleanResult notify(String wxNotify);

}
