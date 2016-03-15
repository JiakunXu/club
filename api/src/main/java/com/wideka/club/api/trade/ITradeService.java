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

	/**
	 * 临时.
	 */
	String CHECK = "check";

	/**
	 * 待付款.
	 */
	String TO_PAY = "topay";

	/**
	 * 已付款.
	 */
	String TO_SEND = "tosend";

	/**
	 * 已发货.
	 */
	String SEND = "send";

	/**
	 * 已签收.
	 */
	String SIGN = "sign";

	String FEEDBACK = "feedback";

	String FEEDBACKED = "feedbacked";

	/**
	 * 申请退款.
	 */
	String TO_REFUND = "torefund";

	/**
	 * 已退款.
	 */
	String REFUND = "refund";

	String CANCEL = "cancel";

	/**
	 * 立即购买.
	 * 
	 * @param userId
	 * @param shopId
	 * @param itemId
	 * @param skuId
	 * @param quantity
	 * @return
	 */
	BooleanResult createTrade(String userId, Long shopId, String itemId, String skuId, String quantity);

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
	BooleanResult createTrade(String userId, Long shopId, String[] cartId);

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
	int getTradeCount(String userId, Long shopId, String[] type);

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
	List<Trade> getTradeList(String userId, Long shopId, String[] type);

	/**
	 * 买家查看订单.
	 * 
	 * @param userId
	 * @param shopId
	 * @param tradeNo
	 * @return
	 */
	Trade getTrade(String userId, Long shopId, String tradeNo);

	/**
	 * 
	 * @param userId
	 * @param shopId
	 * @param tradeNo
	 * @param trade
	 * @return
	 */
	BooleanResult updateReceiver(String userId, Long shopId, String tradeNo, Trade trade);

	/**
	 * 取消订单.
	 * 
	 * @param userId
	 * @param shopId
	 * @param tradeNo
	 * @return
	 */
	BooleanResult cancelTrade(String userId, Long shopId, String tradeNo);

	/**
	 * 临时订单 -> 待付款订单.
	 * 
	 * @param userId
	 * @param shopId
	 * @param tradeNo
	 * @return
	 */
	BooleanResult topayTrade(String userId, Long shopId, String tradeNo);

	// >>>>>>>>>>以下是第三方交易平台<<<<<<<<<<

	/**
	 * 
	 * @param tradeNo
	 * @return
	 */
	Trade getTrade(String tradeNo);

	/**
	 * 
	 * @param tradeNo
	 * @param payType
	 * @param payDate
	 * @return
	 */
	BooleanResult payTrade(String tradeNo, String payType, String payDate);

}
