package com.wideka.club.trade.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wideka.club.api.trade.IOrderService;
import com.wideka.club.api.trade.bo.Order;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.club.trade.dao.IOrderDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class OrderServiceImpl implements IOrderService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(OrderServiceImpl.class);

	private IOrderDao orderDao;

	@Override
	public BooleanResult createOrder(Long shopId, Long tradeId, String itemId, String skuId, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Order order = new Order();

		if (tradeId == null) {
			result.setCode("交易信息不能为空！");
			return result;
		}
		order.setTradeId(tradeId);

		if (shopId == null) {
			result.setCode("店铺信息不能为空！");
			return result;
		}
		order.setShopId(shopId);

		if (StringUtils.isBlank(itemId)) {
			result.setCode("商品信息不能为空！");
			return result;
		}
		try {
			order.setItemId(Long.valueOf(itemId));
		} catch (NumberFormatException e) {
			logger.error(itemId, e);

			result.setCode("商品信息不正确！");
			return result;
		}

		if (StringUtils.isBlank(skuId)) {
			result.setCode("SKU信息不能为空！");
			return result;
		}
		try {
			order.setSkuId(Long.valueOf(skuId));
		} catch (NumberFormatException e) {
			logger.error(itemId, e);

			result.setCode("SKU信息不正确！");
			return result;
		}

		if (StringUtils.isBlank(modifyUser)) {
			result.setCode("操作人信息不能为空！");
			return result;
		}
		order.setModifyUser(modifyUser.trim());

		try {
			orderDao.createOrder4Item(order);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(order), e);

			result.setCode("创建订单失败！");
		}

		return result;
	}

	@Override
	public BooleanResult createOrder(Long shopId, Long tradeId, String[] cartId, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Order order = new Order();

		if (tradeId == null) {
			result.setCode("交易信息不能为空！");
			return result;
		}
		order.setTradeId(tradeId);

		if (shopId == null) {
			result.setCode("店铺信息不能为空！");
			return result;
		}
		order.setShopId(shopId);

		if (cartId == null || cartId.length == 0) {
			result.setCode("购物车不能为空！");
			return result;
		}
		order.setCodes(cartId);

		if (StringUtils.isBlank(modifyUser)) {
			result.setCode("操作人信息不能为空！");
			return result;
		}

		order.setModifyUser(modifyUser.trim());

		try {
			orderDao.createOrder4Cart(order);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(order), e);

			result.setCode("创建订单失败！");
		}

		return result;
	}

	@Override
	public List<Order> getOrderList(String userId, Long shopId, Long tradeId) {
		if (StringUtils.isBlank(userId) || shopId == null || tradeId == null) {
			return null;
		}

		Order order = new Order();
		order.setShopId(shopId);
		order.setTradeId(tradeId);

		try {
			return orderDao.getOrderList(order);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(order), e);
		}

		return null;
	}

	@Override
	public Order getOrder(String userId, Long shopId, Long tradeId, Long orderId) {
		if (StringUtils.isBlank(userId) || shopId == null || tradeId == null) {
			return null;
		}

		Order order = new Order();
		order.setShopId(shopId);
		order.setTradeId(tradeId);
		order.setOrderId(orderId);

		try {
			return orderDao.getOrder(order);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(order), e);
		}

		return null;
	}

	public IOrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

}
