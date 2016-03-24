package com.wideka.club.trade.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.wideka.club.api.cache.IMemcachedCacheService;
import com.wideka.club.api.cart.ICartService;
import com.wideka.club.api.cart.bo.Cart;
import com.wideka.club.api.item.bo.Item;
import com.wideka.club.api.item.bo.ItemSku;
import com.wideka.club.api.trade.IOrderService;
import com.wideka.club.api.trade.ITradeService;
import com.wideka.club.api.trade.bo.Order;
import com.wideka.club.api.trade.bo.Trade;
import com.wideka.club.api.user.IUserAddressService;
import com.wideka.club.api.user.bo.UserAddress;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.exception.ServiceException;
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

	private IMemcachedCacheService memcachedCacheService;

	private IUserAddressService userAddressService;

	private IOrderService orderService;

	private ICartService cartService;

	private ITradeDao tradeDao;

	@Override
	public BooleanResult createTrade(final String userId, final Long shopId, final String itemId, final String skuId,
		String quantity) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (userId == null) {
			result.setCode("用户信息不能为空。");
			return result;
		}

		if (shopId == null) {
			result.setCode("店铺信息不能为空。");
			return result;
		}

		if (StringUtils.isBlank(itemId)) {
			result.setCode("商品信息不能为空。");
			return result;
		}

		// 获取默认收货地址
		final UserAddress userAddress = userAddressService.getDefaultUserAddress(userId.trim());

		BooleanResult res = transactionTemplate.execute(new TransactionCallback<BooleanResult>() {
			public BooleanResult doInTransaction(TransactionStatus ts) {
				BooleanResult result = new BooleanResult();
				result.setResult(false);

				// 1. 创建交易
				// create trade
				Long tradeId = null;

				Trade trade = new Trade();
				trade.setUserId(userId);
				trade.setShopId(shopId);
				// 交易价格
				trade.setTradePrice(BigDecimal.ONE);
				// 积分兑换
				trade.setTradePoints(BigDecimal.ZERO);
				trade.setCouponPrice(BigDecimal.ZERO);
				trade.setPostage(BigDecimal.ZERO);
				trade.setChange(BigDecimal.ZERO);
				// 买家结算
				trade.setType(ITradeService.CHECK);

				// 收货地址
				if (userAddress != null) {
					trade.setReceiverName(userAddress.getContactName());
					trade.setReceiverProvince(userAddress.getProvince());
					trade.setReceiverCity(userAddress.getCity());
					trade.setReceiverArea(userAddress.getArea());
					trade.setReceiverBackCode(userAddress.getBackCode());
					trade.setReceiverAddress(userAddress.getAddress());
					trade.setReceiverZip(userAddress.getPostalCode());
					trade.setReceiverTel(userAddress.getTel());
				}

				// 14位日期 ＋ 11位随机数
				trade.setTradeNo(DateUtil.getNowDateminStr() + UUIDUtil.generate().substring(9));

				try {
					tradeId = tradeDao.createTrade(trade);
				} catch (Exception e) {
					logger.error(LogUtil.parserBean(trade), e);
					ts.setRollbackOnly();

					result.setCode("创建交易失败。");
					return result;
				}

				// 2. 创建订单
				result = orderService.createOrder(shopId, tradeId, itemId, skuId, userId);
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
	public BooleanResult createTrade(final String userId, final Long shopId, final String[] cartId) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (StringUtils.isBlank(userId)) {
			result.setCode("用户信息不能为空！");
			return result;
		}

		if (shopId == null) {
			result.setCode("店铺信息不能为空！");
			return result;
		}

		if (cartId == null || cartId.length == 0) {
			result.setCode("购物车不能为空！");
			return result;
		}

		// 获取选中商品总计价格，兑换积分，运费
		final Cart cart = cartService.getCartStats(userId.trim(), shopId, cartId);

		// 合计价格 合计积分 最小运费
		if (cart.getPrice() == null || cart.getPoints() == null || cart.getPostage() == null) {
			result.setCode("购物车商品总价为空！");
			return result;
		}

		// 获取默认收货地址
		final UserAddress userAddress = userAddressService.getDefaultUserAddress(userId.trim());

		BooleanResult res = transactionTemplate.execute(new TransactionCallback<BooleanResult>() {
			public BooleanResult doInTransaction(TransactionStatus ts) {
				BooleanResult result = new BooleanResult();
				result.setResult(false);

				// 1. 创建交易
				// create trade
				Long tradeId = null;

				Trade trade = new Trade();
				trade.setUserId(userId.trim());
				trade.setShopId(shopId);
				// 交易价格
				trade.setTradePrice(cart.getPrice());
				// 积分兑换
				trade.setTradePoints(BigDecimal.ZERO);
				trade.setCouponPrice(BigDecimal.ZERO);
				trade.setPostage(cart.getPostage());
				trade.setChange(BigDecimal.ZERO);
				// 买家结算
				trade.setType(ITradeService.CHECK);

				// 收货地址
				if (userAddress != null) {
					trade.setReceiverName(userAddress.getContactName());
					trade.setReceiverProvince(userAddress.getProvince());
					trade.setReceiverCity(userAddress.getCity());
					trade.setReceiverArea(userAddress.getArea());
					trade.setReceiverBackCode(userAddress.getBackCode());
					trade.setReceiverAddress(userAddress.getAddress());
					trade.setReceiverZip(userAddress.getPostalCode());
					trade.setReceiverTel(userAddress.getTel());
				}

				// 14位日期 ＋ 11位随机数
				trade.setTradeNo(DateUtil.getNowDateminStr() + UUIDUtil.generate().substring(9));

				// 交易订单 关联 购物车
				StringBuilder sb = new StringBuilder();
				for (String id : cartId) {
					if (sb.length() > 0) {
						sb.append(",");
					}
					sb.append(id);
				}
				trade.setCartId(sb.toString());

				try {
					tradeId = tradeDao.createTrade(trade);
				} catch (Exception e) {
					logger.error(LogUtil.parserBean(trade), e);
					ts.setRollbackOnly();

					result.setCode("创建交易失败！");
					return result;
				}

				// 2. 创建订单
				result = orderService.createOrder(shopId, tradeId, cartId, userId.trim());
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
	public int getTradeCount(String userId, Long shopId, String[] type) {
		if (StringUtils.isBlank(userId) || shopId == null) {
			return 0;
		}

		Trade trade = new Trade();
		trade.setUserId(userId.trim());
		trade.setShopId(shopId);
		trade.setCodes(type);

		return getTradeCount(trade);
	}

	/**
	 * 
	 * @param trade
	 * @return
	 */
	private int getTradeCount(Trade trade) {
		try {
			return tradeDao.getTradeCount(trade);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(trade), e);
		}

		return 0;
	}

	@Override
	public List<Trade> getTradeList(String userId, Long shopId, String[] type) {
		if (StringUtils.isBlank(userId) || shopId == null) {
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
		if (StringUtils.isBlank(userId) || shopId == null || StringUtils.isBlank(tradeNo)) {
			return null;
		}

		Trade t = new Trade();
		t.setUserId(userId.trim());
		t.setShopId(shopId);
		t.setTradeNo(tradeNo.trim());

		Trade trade = getTrade(t);

		if (trade == null) {
			return null;
		}

		List<Order> orderList = orderService.getOrderList(userId, shopId, trade.getTradeId());

		if (orderList != null && orderList.size() > 0) {
			trade.setOrderList(orderList);
		}

		return trade;
	}

	@Override
	public BooleanResult updateReceiver(String userId, Long shopId, String tradeNo, Trade trade) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (StringUtils.isBlank(userId)) {
			result.setCode("用户信息不能为空。");
			return result;
		}

		if (shopId == null) {
			result.setCode("店铺信息不能为空。");
			return result;
		}

		if (StringUtils.isBlank(tradeNo)) {
			result.setCode("订单信息不能为空。");
			return result;
		}

		if (trade == null) {
			result.setCode("联系人信息不能为空。");
			return result;
		}

		trade.setUserId(userId.trim());
		trade.setModifyUser(userId.trim());
		trade.setShopId(shopId);
		trade.setTradeNo(tradeNo.trim());

		return updateTrade(trade);
	}

	@Override
	public BooleanResult cancelTrade(String userId, Long shopId, String tradeNo) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		final Trade trade = new Trade();

		if (StringUtils.isBlank(userId)) {
			result.setCode("用户信息不能为空。");
			return result;
		}
		trade.setUserId(userId.trim());
		trade.setModifyUser(userId);

		if (shopId == null) {
			result.setCode("店铺信息不能为空。");
			return result;
		}
		trade.setShopId(shopId);

		if (StringUtils.isBlank(tradeNo)) {
			result.setCode("订单信息不能为空。");
			return result;
		}
		trade.setTradeNo(tradeNo.trim());

		// 锁定当前订单
		try {
			memcachedCacheService.add(IMemcachedCacheService.CACHE_KEY_TRADE_NO + tradeNo.trim(), tradeNo,
				IMemcachedCacheService.CACHE_KEY_TRADE_NO_DEFAULT_EXP);
		} catch (ServiceException e) {
			result.setCode("当前订单已被锁定，请稍后再试。");
			return result;
		}

		// 0. 查询 未付款交易订单
		Trade t = getTrade(trade);
		if (t == null) {
			result.setCode("当前订单不存在。");
			return result;
		}

		if (!ITradeService.CHECK.equals(t.getType()) && !ITradeService.TO_PAY.equals(t.getType())) {
			result.setCode("当前订单已付款或取消。");
			return result;
		}

		// 处理 订单明细数据 需要用到
		trade.setTradeId(t.getTradeId());

		return cancelTrade(trade, t.getType(), t.getUserId());
	}

	@Override
	public BooleanResult topayTrade(String userId, Long shopId, String tradeNo) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Trade trade = new Trade();
		// 待付款
		trade.setType(ITradeService.TO_PAY);

		if (StringUtils.isBlank(userId)) {
			result.setCode("用户信息不能为空。");
			return result;
		}
		trade.setUserId(userId.trim());

		if (shopId == null) {
			result.setCode("店铺信息不能为空。");
			return result;
		}
		trade.setShopId(shopId);

		if (StringUtils.isBlank(tradeNo)) {
			result.setCode("交易订单不能为空。");
			return result;
		}
		trade.setTradeNo(tradeNo.trim());

		trade.setModifyUser(userId);

		return updateTrade(trade);
	}

	// >>>>>>>>>>以下是第三方交易平台<<<<<<<<<<

	@Override
	public Trade getTrade(String tradeNo) {
		if (StringUtils.isBlank(tradeNo)) {
			return null;
		}

		Trade trade = new Trade();
		trade.setTradeNo(tradeNo.trim());

		return getTrade(trade);
	}

	@Override
	public BooleanResult payTrade(String tradeNo, String payType, String payDate) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Trade trade = new Trade();
		// 待发货
		trade.setType(ITradeService.TO_SEND);

		if (StringUtils.isBlank(tradeNo)) {
			result.setCode("交易订单不能为空。");
			return result;
		}
		trade.setTradeNo(tradeNo.trim());

		if (StringUtils.isBlank(payType)) {
			result.setCode("支付类型不能为空。");
			return result;
		}
		trade.setPayType(payType.trim());

		if (StringUtils.isBlank(payDate)) {
			result.setCode("支付时间不能为空。");
			return result;
		}
		trade.setPayDate(payDate);

		trade.setModifyUser(payType);

		return updateTrade(trade);
	}

	/**
	 * 
	 * @param trade
	 * @return
	 */
	private Trade getTrade(Trade trade) {
		try {
			return tradeDao.getTrade(trade);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(trade), e);
		}

		return null;
	}

	private BooleanResult updateTrade(Trade trade) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			int c = tradeDao.updateTrade(trade);
			if (c == 1) {
				result.setResult(true);
			} else {
				result.setCode("更新交易失败。");
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(trade), e);
			result.setCode("更新交易表失败。");
		}

		return result;
	}

	/**
	 * 
	 * @param trade
	 * @param type
	 * @param modifyUser
	 * @return
	 */
	private BooleanResult cancelTrade(final Trade trade, String type, String modifyUser) {
		// 是否需要释放库存
		boolean f = false;
		// item sku 表库存
		List<ItemSku> skus = null;
		// item 表库存 即不存在 sku
		List<Item> items = null;
		// 用于统计 还有 sku 的商品的合计库存数
		String[] itemIds = null;

		// 根据 type 判断 是否需要 释放已占库存
		if (ITradeService.TO_PAY.equals(type)) {
			// 需要释放库存
			f = true;

			// 1. 判断库存
			List<Order> orderList = orderService.getOrderList(trade.getUserId(), trade.getShopId(), trade.getTradeId());
			if (orderList == null || orderList.size() == 0) {
				BooleanResult result = new BooleanResult();
				result.setResult(false);
				result.setCode("当前订单明细不存在。");

				return result;
			}

			// 存放各个商品库存数量 存在 购物相同商品 的情况
			Map<String, Integer> map = new HashMap<String, Integer>();

			for (Order order : orderList) {
				String key = order.getItemId() + "&" + order.getSkuId();
				if (!map.containsKey(key)) {
					map.put(key, order.getStock());
				}

				int quantity = order.getQuantity();
				int stock = map.get(key);

				map.put(key, stock + quantity);
			}

			// 根据 map 组装 skuList
			skus = new ArrayList<ItemSku>();
			items = new ArrayList<Item>();
			itemIds = new String[orderList.size()];
			int i = 0;

			for (Map.Entry<String, Integer> m : map.entrySet()) {
				String[] key = m.getKey().split("&");

				// if skuId is null or 0 then 商品没有规格
				if (StringUtils.isBlank(key[1]) || "0".equals(key[1])) {
					Item item = new Item();
					item.setItemId(Long.valueOf(key[0]));
					item.setStock(m.getValue());

					items.add(item);

					continue;
				}

				ItemSku sku = new ItemSku();
				sku.setItemId(key[0]);
				sku.setSkuId(key[1]);
				sku.setStock(m.getValue());

				skus.add(sku);

				itemIds[i++] = key[0];
			}
		}

		// type 取消
		trade.setType(ITradeService.CANCEL);
		// 是否需要释放库存
		final boolean flag = f;
		// item sku 表库存
		final List<ItemSku> skuList = skus;
		// item 表库存 即不存在 sku
		final List<Item> itemList = items;
		// 用于统计 还有 sku 的商品的合计库存数
		final String[] itemId = itemIds;

		BooleanResult res = transactionTemplate.execute(new TransactionCallback<BooleanResult>() {
			public BooleanResult doInTransaction(TransactionStatus ts) {
				// 1. 订单状态取消
				BooleanResult result = updateTrade(trade);
				if (!result.getResult()) {
					ts.setRollbackOnly();

					return result;
				}

				return result;
			}
		});

		if (res.getResult()) {
			res.setCode("取消成功。");
		}
		return res;
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

	public IUserAddressService getUserAddressService() {
		return userAddressService;
	}

	public void setUserAddressService(IUserAddressService userAddressService) {
		this.userAddressService = userAddressService;
	}

	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	public ICartService getCartService() {
		return cartService;
	}

	public void setCartService(ICartService cartService) {
		this.cartService = cartService;
	}

	public ITradeDao getTradeDao() {
		return tradeDao;
	}

	public void setTradeDao(ITradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}

}
