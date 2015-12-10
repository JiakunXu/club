package com.wideka.club.trade.dao.impl;

import java.util.List;

import com.wideka.club.api.trade.bo.Trade;
import com.wideka.club.framework.dao.impl.BaseDaoImpl;
import com.wideka.club.trade.dao.ITradeDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class TradeDaoImpl extends BaseDaoImpl implements ITradeDao {

	@Override
	public int getTradeCount(Trade trade) {
		return (Integer) getSqlMapClientTemplate().queryForObject("trade.getTradeCount", trade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trade> getTradeList(Trade trade) {
		return (List<Trade>) getSqlMapClientTemplate().queryForList("trade.getTradeList", trade);
	}

	@Override
	public int updateTrade(Trade trade) {
		return getSqlMapClientTemplate().update("trade.updateTrade", trade);
	}

	@Override
	public Trade getTrade(Trade trade) {
		return (Trade) getSqlMapClientTemplate().queryForObject("trade.getTrade", trade);
	}

	@Override
	public Long createTrade(Trade trade) {
		return (Long) getSqlMapClientTemplate().insert("trade.createTrade", trade);
	}

}
