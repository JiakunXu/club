package com.wideka.club.api.trade;

import java.util.List;

import com.wideka.club.api.trade.bo.Trade;
import com.wideka.club.framework.bo.BooleanResult;

/**
 * 交易接口.
 * 
 * @author JiakunXu
 * 
 */
public interface ITradeService {

	String CREATE = "create";

	String CHECK = "check";

	String TO_PAY = "topay";

	String PAY = "pay";

	String TO_SEND = "tosend";

	String SEND = "send";

	String SIGN = "sign";

	String FEEDBACK = "feedback";

	String FEEDBACKED = "feedbacked";

	String CANCEL = "cancel";

	/**
	 * 买家下单交易.
	 * 
	 * @param userId
	 *            必填.
	 * @param shopId
	 *            必填.
	 * @param itemId
	 *            商品id.
	 * @return
	 */
	BooleanResult createTrade(Long userId, Long shopId, String itemId);

	/**
	 * 买家下单交易.
	 * 
	 * @param userId
	 *            必填.
	 * @param shopId
	 *            必填.
	 * @param cartId
	 *            购物车id.
	 * @return
	 */
	BooleanResult createTrade(Long userId, Long shopId, String[] cartId);

	/**
	 * 买家查询某店铺交易.
	 * 
	 * @param userId
	 *            必填.
	 * @param shopId
	 *            必填.
	 * @param type
	 * @return
	 */
	int getTradeCount(Long userId, Long shopId, String[] type);

	/**
	 * 买家查询某店铺交易.
	 * 
	 * @param userId
	 *            必填.
	 * @param shopId
	 *            必填.
	 * @param type
	 * @return
	 */
	List<Trade> getTradeList(Long userId, Long shopId, String[] type);

	/**
	 * 买家查看订单.
	 * 
	 * @param userId
	 * @param shopId
	 * @param tradeNo
	 * @return
	 */
	Trade getTrade(Long userId, Long shopId, String tradeNo);

	/**
	 * 取消订单.
	 * 
	 * @param userId
	 * @param shopId
	 * @param tradeId
	 * @return
	 */
	BooleanResult cancelTrade(Long userId, Long shopId, String tradeId);

}
