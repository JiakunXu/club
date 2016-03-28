package com.wideka.club.trade.dao.impl;

import com.wideka.club.api.trade.bo.OrderRefund;
import com.wideka.club.framework.dao.impl.BaseDaoImpl;
import com.wideka.club.trade.dao.IOrderRefundDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class OrderRefundDaoImpl extends BaseDaoImpl implements IOrderRefundDao {

	@Override
	public Long createOrderRefund(OrderRefund orderRefund) {
		return (Long) getSqlMapClientTemplate().insert("trade.order.refund.createOrderRefund", orderRefund);
	}

}
