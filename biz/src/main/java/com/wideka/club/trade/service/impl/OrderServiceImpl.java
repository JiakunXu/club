package com.wideka.club.trade.service.impl;

import java.util.List;

import com.wideka.club.api.trade.IOrderService;
import com.wideka.club.api.trade.bo.Order;
import com.wideka.club.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public class OrderServiceImpl implements IOrderService {

	@Override
	public BooleanResult createOrder(String userId, Long shopId, Long tradeId, String itemId) {
		BooleanResult result = new BooleanResult();
		result.setResult(true);
		// TODO Auto-generated method stub

		return result;
	}

	@Override
	public BooleanResult createOrder(String userId, Long shopId, Long tradeId, String[] cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrderList(String userId, Long shopId, String tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
