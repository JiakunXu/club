package com.wideka.club.trade.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

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

	private ITradeDao tradeDao;

	@Override
	public int getTradeCount(Trade trade) {
		if (trade == null) {
			return 0;
		}

		try {
			return tradeDao.getTradeCount(trade);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(trade), e);
		}

		return 0;
	}

	@Override
	public List<Trade> getTradeList(Trade trade) {
		if (trade == null) {
			return null;
		}

		try {
			return tradeDao.getTradeList(trade);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(trade), e);
		}

		return null;
	}

	@Override
	public BooleanResult updateTradeNo(String tradeId, String tradeNo, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Trade trade = new Trade();

		if (StringUtils.isBlank(tradeId)) {
			result.setCode("交易编号不能为空。");
			return result;
		}

		try {
			trade.setTradeId(Long.valueOf(tradeId));
		} catch (NumberFormatException e) {
			logger.error(e);

			result.setCode("交易编号格式不匹配。");
			return result;
		}

		if (StringUtils.isNotBlank(tradeNo)) {
			trade.setTradeNo(tradeNo.trim());
		} else {
			trade.setTradeNo("");
		}

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}

		trade.setModifyUser(modifyUser);

		return updateTrade(trade);
	}

	@Override
	public BooleanResult updateTradeDate(String tradeId, String tradeDate, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Trade trade = new Trade();

		if (StringUtils.isBlank(tradeId)) {
			result.setCode("交易编号不能为空。");
			return result;
		}

		try {
			trade.setTradeId(Long.valueOf(tradeId));
		} catch (NumberFormatException e) {
			logger.error(e);

			result.setCode("交易编号格式不匹配。");
			return result;
		}

		if (StringUtils.isNotBlank(tradeDate)) {
			try {
				DateUtil.datetime(tradeDate.trim(), DateUtil.DEFAULT_DATE_FORMAT);
			} catch (Exception e) {

				result.setCode("交易时间格式不匹配。");
				return result;
			}

			trade.setTradeDate(tradeDate.trim());
		} else {
			trade.setTradeDate("0000-00-00");
		}

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}

		trade.setModifyUser(modifyUser);

		return updateTrade(trade);
	}

	private BooleanResult updateTrade(Trade trade) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			int c = tradeDao.updateTrade(trade);
			if (c == 1) {
				result.setCode("修改交易数据成功！");
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(trade), e);

			result.setCode("更新交易数据表失败！");
		}

		return result;
	}

	@Override
	public Trade getTrade(String tradeCode) {
		if (StringUtils.isBlank(tradeCode)) {
			return null;
		}

		Trade trade = new Trade();
		trade.setTradeCode(tradeCode.trim());

		try {
			return tradeDao.getTrade(trade);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(trade), e);
		}

		return null;
	}

	@Override
	public BooleanResult like(String tradeCode, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Trade trade = new Trade();
		trade.setLike("Y");

		if (StringUtils.isBlank(tradeCode)) {
			result.setCode("交易编号不能为空。");
			return result;
		}
		trade.setTradeCode(tradeCode.trim());

		if (StringUtils.isBlank(modifyUser)) {
			result.setCode("点赞信息不能为空。");
			return result;
		}
		trade.setModifyUser(modifyUser.trim());

		return updateTrade(trade);
	}

	@Override
	public BooleanResult unlike(String tradeCode, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Trade trade = new Trade();
		trade.setLike("N");

		if (StringUtils.isBlank(tradeCode)) {
			result.setCode("交易编号不能为空。");
			return result;
		}
		trade.setTradeCode(tradeCode.trim());

		if (StringUtils.isBlank(modifyUser)) {
			result.setCode("差评信息不能为空。");
			return result;
		}
		trade.setModifyUser(modifyUser.trim());

		return updateTrade(trade);
	}

	@Override
	public BooleanResult createTrade(Trade trade, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (trade == null) {
			result.setCode("交易信息不能为空。");
			return result;
		}

		trade.setTradeCode(UUIDUtil.generate());

		if (StringUtils.isBlank(trade.getItemId())) {
			result.setCode("商品信息不能为空。");
			return result;
		}

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}

		trade.setModifyUser(modifyUser);

		try {
			tradeDao.createTrade(trade);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(trade), e);

			result.setCode("写入交易数据表失败！");
		}

		return result;
	}

	public ITradeDao getTradeDao() {
		return tradeDao;
	}

	public void setTradeDao(ITradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}

}
