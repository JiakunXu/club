package com.wideka.club.trade.dao;

import com.wideka.club.api.trade.bo.OrderRefund;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IOrderRefundDao {

	/**
	 * 
	 * @param orderRefund
	 * @return
	 */
	Long createOrderRefund(OrderRefund orderRefund);

}
