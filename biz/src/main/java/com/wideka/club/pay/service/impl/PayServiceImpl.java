package com.wideka.club.pay.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.wideka.club.api.cache.IMemcachedCacheService;
import com.wideka.club.api.pay.IPayService;
import com.wideka.club.api.trade.ITradeService;
import com.wideka.club.api.trade.bo.OrderRefund;
import com.wideka.club.api.trade.bo.Trade;
import com.wideka.club.api.wxpay.IWxpayService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.exception.ServiceException;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.DateUtil;
import com.wideka.club.framework.util.UUIDUtil;
import com.wideka.weixin.api.pay.bo.Refund;
import com.wideka.weixin.api.pay.bo.WxNotify;
import com.wideka.weixin.framework.util.XmlUtil;

/**
 * 
 * @author JiakunXu
 * 
 */
public class PayServiceImpl implements IPayService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(PayServiceImpl.class);

	private TransactionTemplate transactionTemplate;

	private IMemcachedCacheService memcachedCacheService;

	private IWxpayService wxpayService;

	private ITradeService tradeService;

	@Override
	public BooleanResult pay(final String userId, final Long shopId, final String tradeNo, final String remark,
		String payType, String ip, String openId) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (StringUtils.isBlank(userId)) {
			result.setCode("用户信息不能为空.");
			return result;
		}

		if (shopId == null) {
			result.setCode("店铺信息不能为空.");
			return result;
		}

		if (StringUtils.isBlank(tradeNo)) {
			result.setCode("交易信息不能为空.");
			return result;
		}

		// 验证支付方式
		if (StringUtils.isNotBlank(payType) && !IPayService.PAY_TYPE_ALIPAY.equals(payType)
			&& !IPayService.PAY_TYPE_WXPAY.equals(payType)) {
			result.setCode("请重新选择支付方式！");
			return result;
		}

		// 锁定订单
		String key = tradeNo.trim();

		try {
			memcachedCacheService.add(IMemcachedCacheService.CACHE_KEY_TRADE_NO + key, key, 30);
		} catch (ServiceException e) {
			result.setCode("当前订单已被锁定，请稍后再试。");
			return result;
		}

		// 0. 查询交易订单
		Trade trade = tradeService.getTrade(userId, shopId, tradeNo);
		if (trade == null) {
			result.setCode("当前订单不存在！");
			return result;
		}

		// 1. 判断是否属于未付款交易订单
		String type = trade.getType();
		if (!ITradeService.CHECK.equals(type) && !ITradeService.TO_PAY.equals(type)) {
			result.setCode("当前订单已完成支付！");
			return result;
		}

		// 2. 临时订单
		if (ITradeService.CHECK.equals(type)) {
			// 3. 判断库存

			// 4. 占用库存
			BooleanResult res1 = transactionTemplate.execute(new TransactionCallback<BooleanResult>() {
				public BooleanResult doInTransaction(TransactionStatus ts) {
					BooleanResult res0 = new BooleanResult();

					// 4.1 占用库存

					// 4.2 修改交易状态 -> topay
					res0 = tradeService.topayTrade(userId, shopId, tradeNo, remark);
					if (!res0.getResult()) {
						ts.setRollbackOnly();

						return res0;
					}

					return res0;
				}
			});

			if (!res1.getResult()) {
				return res1;
			}
		}

		// topay 状态 去 付款 ＝＝ 已经占用库存 需要检查 当前订单 是否超过了付款有效时间
		if (ITradeService.TO_PAY.equals(type)) {

		}

		if (IPayService.PAY_TYPE_ALIPAY.equals(payType)) {
			return result;
		}

		if (IPayService.PAY_TYPE_WXPAY.equals(payType)) {
			BigDecimal price = trade.getPrice().multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP);
			String timeStart = DateUtil.getNowDateminStr();
			String timeExpire =
				DateUtil.datetime(DateUtil.addMinutes(new Date(), 15), DateUtil.DEFAULT_DATEFULLTIME_FORMAT);

			try {
				result.setCode(wxpayService.getBrandWCPayRequest(tradeNo, "订单号：" + tradeNo, null, null,
					Integer.parseInt(price.toString()), ip, timeStart, timeExpire, openId));
				result.setResult(true);
			} catch (ServiceException e) {
				logger.error(e);

				result.setCode(e.getMessage());
			}

			return result;
		}

		result.setCode("支付类型.");
		return result;
	}

	@Override
	public BooleanResult refund(final String userId, final Long shopId, final String tradeNo, String orderId,
		final OrderRefund orderRefund) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (StringUtils.isBlank(userId)) {
			result.setCode("用户信息不能为空.");
			return result;
		}

		if (shopId == null) {
			result.setCode("店铺信息不能为空.");
			return result;
		}

		if (StringUtils.isBlank(tradeNo)) {
			result.setCode("交易信息不能为空.");
			return result;
		}

		if (StringUtils.isBlank(orderId)) {
			result.setCode("订单信息不能为空.");
			return result;
		}

		Long id = null;
		try {
			id = Long.valueOf(orderId);
		} catch (NumberFormatException e) {
			logger.error(e);

			result.setCode("订单信息不正确.");
			return result;
		}
		final Long ordreId = id;

		// 锁定订单
		String key = tradeNo.trim();

		try {
			memcachedCacheService.add(IMemcachedCacheService.CACHE_KEY_TRADE_NO + key, key, 30);
		} catch (ServiceException e) {
			result.setCode("当前订单已被锁定，请稍后再试。");
			return result;
		}

		// 0. 查询交易订单
		final Trade trade = tradeService.getTrade(userId, shopId, tradeNo);
		if (trade == null) {
			result.setCode("当前订单不存在！");
			return result;
		}

		// 1. 判断是否属于未付款交易订单
		String type = trade.getType();
		if (!ITradeService.TO_SEND.equals(type)) {
			result.setCode("当前订单尚未付款或已发货！");
			return result;
		}

		// 2. 退款订单编号
		final String refundNo = UUIDUtil.generate().substring(4);

		String payType = trade.getPayType();

		if (IPayService.PAY_TYPE_ALIPAY.equals(payType)) {
			return result;
		}

		if (IPayService.PAY_TYPE_WXPAY.equals(payType)) {
			result = transactionTemplate.execute(new TransactionCallback<BooleanResult>() {
				public BooleanResult doInTransaction(TransactionStatus ts) {
					BooleanResult res0 =
						tradeService.createOrderRefund(shopId, trade.getTradeNo(), refundNo, ordreId, orderRefund,
							userId.toString());

					if (!res0.getResult()) {
						ts.setRollbackOnly();

						return res0;
					}

					BigDecimal price1 =
						trade.getPrice().multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP);
					int fee = Integer.parseInt(price1.toString());
					BigDecimal price2 =
						orderRefund.getRefundFee().multiply(new BigDecimal("100"))
							.setScale(0, BigDecimal.ROUND_HALF_UP);
					int refundFee = Integer.parseInt(price2.toString());

					try {
						Refund refund =
							wxpayService.refund(null, null, trade.getTradeNo(), refundNo, fee, refundFee, null);

						res0.setCode("申请退款成功。");
					} catch (ServiceException e) {
						ts.setRollbackOnly();

						res0.setResult(false);
						res0.setCode(e.getMessage());
						return res0;
					}

					return res0;
				}
			});

			return result;
		}

		result.setCode("支付类型.");
		return result;
	}

	// >>>>>>>>>>以下是第三方交易平台<<<<<<<<<<

	@Override
	public BooleanResult notify(String wxNotify) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		result.setCode(IWxpayService.RETURN_CODE_FAIL);

		if (StringUtils.isBlank(wxNotify)) {
			return result;
		}

		final WxNotify notify = (WxNotify) XmlUtil.parse(wxNotify, new WxNotify());

		// 1. 判断回调信息
		BooleanResult res = wxpayService.validateWxNotify(notify);
		if (!res.getResult()) {
			return result;
		}

		// 锁定订单
		String key = notify.getOutTradeNo();

		try {
			memcachedCacheService.add(IMemcachedCacheService.CACHE_KEY_TRADE_NO + key, key,
				IMemcachedCacheService.CACHE_KEY_TRADE_NO_DEFAULT_EXP);
		} catch (ServiceException e) {
			return result;
		}

		// 2. 判断订单状态
		final Trade trade = tradeService.getTrade(notify.getOutTradeNo());

		if (trade == null) {
			return result;
		}

		// 已付款.
		if (!ITradeService.TO_PAY.equals(trade.getType())) {
			result.setResult(true);
			result.setCode(IWxpayService.RETURN_CODE_SUCCESS);
			return result;
		}

		res = transactionTemplate.execute(new TransactionCallback<BooleanResult>() {
			public BooleanResult doInTransaction(TransactionStatus ts) {
				// 3. 修改交易状态 topay -> tosend.
				BooleanResult result =
					tradeService.payTrade(trade.getTradeNo(), IPayService.PAY_TYPE_WXPAY, notify.getTimeEnd());
				if (!result.getResult()) {
					ts.setRollbackOnly();

					return result;
				}

				// 4. 记录回调信息.
				result = wxpayService.createWxNotify(notify);
				if (!result.getResult()) {
					ts.setRollbackOnly();

					return result;
				}

				return result;
			}
		});

		if (res.getResult()) {
			result.setResult(true);
			result.setCode(IWxpayService.RETURN_CODE_SUCCESS);
		}

		return result;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IWxpayService getWxpayService() {
		return wxpayService;
	}

	public void setWxpayService(IWxpayService wxpayService) {
		this.wxpayService = wxpayService;
	}

	public ITradeService getTradeService() {
		return tradeService;
	}

	public void setTradeService(ITradeService tradeService) {
		this.tradeService = tradeService;
	}

}
