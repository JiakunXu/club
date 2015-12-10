package com.wideka.club.api.trade;

import java.util.List;

import com.wideka.club.api.trade.bo.Trade;
import com.wideka.club.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface ITradeService {

	/**
	 * 
	 * @param trade
	 * @return
	 */
	int getTradeCount(Trade trade);

	/**
	 * 
	 * @param trade
	 * @return
	 */
	List<Trade> getTradeList(Trade trade);

	/**
	 * 
	 * @param tradeId
	 * @param tradeNo
	 * @param modifyUser
	 * @return
	 */
	BooleanResult updateTradeNo(String tradeId, String tradeNo, String modifyUser);

	/**
	 * 
	 * @param tradeId
	 * @param tradeDate
	 * @param modifyUser
	 * @return
	 */
	BooleanResult updateTradeDate(String tradeId, String tradeDate, String modifyUser);

	/**
	 * 
	 * @param tradeCode
	 * @return
	 */
	Trade getTrade(String tradeCode);

	/**
	 * 
	 * @param tradeCode
	 * @param modifyUser
	 * @return
	 */
	BooleanResult like(String tradeCode, String modifyUser);

	/**
	 * 
	 * @param tradeCode
	 * @param modifyUser
	 * @return
	 */
	BooleanResult unlike(String tradeCode, String modifyUser);

	/**
	 * 
	 * @param trade
	 * @param modifyUser
	 * @return
	 */
	BooleanResult createTrade(Trade trade, String modifyUser);

}
