package com.wideka.club.trade.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.wideka.club.api.trade.IOrderService;
import com.wideka.club.api.trade.ITradeService;
import com.wideka.club.api.trade.bo.Trade;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.DateUtil;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.club.framework.util.UUIDUtil;
import com.wideka.club.trade.dao.ITradeDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class TradeServiceImpl implements ITradeService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(TradeServiceImpl.class);

	private TransactionTemplate transactionTemplate;

	private IOrderService orderService;

	private ITradeDao tradeDao;

	@Override
	public BooleanResult createTrade(final String userId, final Long shopId, final String itemId) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (userId == null) {
			result.setCode("用户信息不能为空！");
			return result;
		}

		if (shopId == null) {
			result.setCode("店铺信息不能为空！");
			return result;
		}

		if (StringUtils.isBlank(itemId)) {
			result.setCode("购物车不能为空！");
			return result;
		}

		BooleanResult res = transactionTemplate.execute(new TransactionCallback<BooleanResult>() {
			public BooleanResult doInTransaction(TransactionStatus ts) {
				BooleanResult result = new BooleanResult();
				result.setResult(false);

				// 1. 创建交易
				// create trade
				Long tradeId;

				Trade trade = new Trade();
				trade.setUserId(userId);
				trade.setShopId(shopId);
				// 交易价格
				trade.setTradePrice(BigDecimal.ZERO);
				// 积分兑换
				trade.setTradePoints(BigDecimal.ZERO);
				trade.setCouponPrice(BigDecimal.ZERO);
				trade.setPostage(BigDecimal.ZERO);
				trade.setChange(BigDecimal.ZERO);
				// 买家结算
				trade.setType(ITradeService.CHECK);

				// 收货地址
				trade.setReceiverName(null);
				trade.setReceiverProvince(null);
				trade.setReceiverCity(null);
				trade.setReceiverArea(null);
				trade.setReceiverBackCode(null);
				trade.setReceiverAddress(null);
				trade.setReceiverZip(null);
				trade.setReceiverTel(null);

				// 14位日期 ＋ 11位随机数
				trade.setTradeNo(DateUtil.getNowDateminStr() + UUIDUtil.generate().substring(9));

				try {
					tradeId = tradeDao.createTrade(trade);
				} catch (Exception e) {
					logger.error(LogUtil.parserBean(trade), e);
					ts.setRollbackOnly();

					result.setCode("创建交易失败！");
					return result;
				}

				// 2. 创建订单
				result = orderService.createOrder(userId, shopId, tradeId, itemId);
				if (!result.getResult()) {
					ts.setRollbackOnly();

					return result;
				}

				result.setCode(trade.getTradeNo());
				return result;
			}
		});

		return res;
	}

	@Override
	public BooleanResult createTrade(String userId, Long shopId, String[] cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTradeCount(String userId, Long shopId, String[] type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Trade> getTradeList(String userId, Long shopId, String[] type) {
		if (StringUtils.isBlank(userId) || shopId == null || type == null || type.length == 0) {
			return null;
		}

		Trade trade = new Trade();
		trade.setUserId(userId.trim());
		trade.setShopId(shopId);
		trade.setCodes(type);

		// 暂不分页
		trade.setLimit(10);
		trade.setOffset(0);
		trade.setSort("CREATE_DATE");
		trade.setOrder("DESC");

		return getTradeList(trade);
	}

	/**
	 * 
	 * @param trade
	 * @return
	 */
	private List<Trade> getTradeList(Trade trade) {
		try {
			return tradeDao.getTradeList(trade);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(trade), e);
		}

		return null;
	}

	@Override
	public Trade getTrade(String userId, Long shopId, String tradeNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BooleanResult cancelTrade(String userId, Long shopId, String tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	public ITradeDao getTradeDao() {
		return tradeDao;
	}

	public void setTradeDao(ITradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}

}
