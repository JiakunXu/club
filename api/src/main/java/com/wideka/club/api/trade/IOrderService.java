package com.wideka.club.api.trade;

import java.util.List;

import com.wideka.club.api.trade.bo.Order;
import com.wideka.club.framework.bo.BooleanResult;

/**
 * 订单详细信息接口.
 * 
 * @author JiakunXu
 * 
 */
public interface IOrderService {

	/**
	 * 创建订单明细信息.
	 * 
	 * @param userId
	 * @param shopId
	 * @param tradeId
	 * @param itemId
	 * @return
	 */
	BooleanResult createOrder(String userId, Long shopId, Long tradeId, String itemId);

	/**
	 * 根据购物车批量创建订单明细信息.
	 * 
	 * @param userId
	 * @param shopId
	 * @param tradeId
	 * @param cartId
	 * @return
	 */
	BooleanResult createOrder(String userId, Long shopId, Long tradeId, String[] cartId);

	/**
	 * 买家查询(首先调用 ITradeService.getTrade).
	 * 
	 * @param userId
	 * @param shopId
	 * @param tradeId
	 * @return
	 */
	List<Order> getOrderList(String userId, Long shopId, String tradeId);

}
