package com.wideka.club.trade.service.impl;

import org.apache.commons.lang.StringUtils;

import com.wideka.club.api.trade.IOrderRefundService;
import com.wideka.club.api.trade.bo.OrderRefund;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.club.trade.dao.IOrderRefundDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class OrderRefundServiceImpl implements IOrderRefundService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(OrderRefundServiceImpl.class);

	private IOrderRefundDao orderRefundDao;

	@Override
	public BooleanResult createOrderRefund(Long shopId, String tradeNo, String refundNo, Long orderId,
		OrderRefund orderRefund, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (orderRefund == null) {
			result.setCode("退款订单信息不能为空！");
			return result;
		}

		if (orderId == null) {
			result.setCode("订单信息不能为空！");
			return result;
		}
		orderRefund.setOrderId(orderId);

		if (StringUtils.isBlank(tradeNo)) {
			result.setCode("交易编号信息不能为空！");
			return result;
		}
		orderRefund.setTradeNo(tradeNo.trim());

		if (StringUtils.isBlank(refundNo)) {
			result.setCode("退款订单编号信息不能为空！");
			return result;
		}
		orderRefund.setRefundNo(refundNo.trim());

		if (StringUtils.isBlank(modifyUser)) {
			result.setCode("操作人信息不能为空！");
			return result;
		}
		orderRefund.setModifyUser(modifyUser.trim());

		try {
			orderRefundDao.createOrderRefund(orderRefund);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(orderRefund), e);

			result.setCode("创建退款订单失败！");
		}

		return result;
	}

	public IOrderRefundDao getOrderRefundDao() {
		return orderRefundDao;
	}

	public void setOrderRefundDao(IOrderRefundDao orderRefundDao) {
		this.orderRefundDao = orderRefundDao;
	}

}
