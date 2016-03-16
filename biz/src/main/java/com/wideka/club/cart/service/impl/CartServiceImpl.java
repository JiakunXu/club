package com.wideka.club.cart.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wideka.club.api.cart.ICartService;
import com.wideka.club.api.cart.bo.Cart;
import com.wideka.club.api.item.bo.ItemFile;
import com.wideka.club.cart.dao.ICartDao;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.LogUtil;

/**
 * 
 * @author JiakunXu
 * 
 */
public class CartServiceImpl implements ICartService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(CartServiceImpl.class);

	private ICartDao cartDao;

	@Override
	public BooleanResult createCart(String userId, Long shopId, String itemId, String skuId, String quantity) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Cart cart = new Cart();

		if (StringUtils.isBlank(userId)) {
			result.setCode("用户信息不能为空。");
			return result;
		}
		cart.setUserId(userId.trim());

		if (shopId == null) {
			result.setCode("店铺信息不能为空。");
			return result;
		}
		cart.setShopId(shopId);

		if (StringUtils.isBlank(itemId)) {
			result.setCode("商品信息不能为空。");
			return result;
		}
		try {
			cart.setItemId(Long.valueOf(itemId));
		} catch (NumberFormatException e) {
			logger.error(itemId, e);

			result.setCode("商品信息错误。");
			return result;
		}

		if (StringUtils.isNotBlank(skuId)) {
			try {
				cart.setSkuId(Long.valueOf(skuId));
			} catch (NumberFormatException e) {
				logger.error(itemId, e);

				result.setCode("SKU信息错误。");
				return result;
			}
		}

		// TODO
		cart.setPointsId(0L);

		if (StringUtils.isBlank(quantity)) {
			result.setCode("购买商品数量不能为空。");
			return result;
		}

		int q;

		try {
			q = Integer.parseInt(quantity);
		} catch (Exception e) {
			logger.error(quantity, e);
			result.setCode("购买商品数量非数字类型。");
			return result;
		}

		if (q == 0 || q < 1) {
			result.setCode("数量不能为0或负。");
			return result;
		}

		cart.setQuantity(q);

		try {
			cartDao.createCart(cart);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cart), e);

			result.setCode("添加购物车失败，请稍后再试。");
		}

		if (result.getResult()) {
			result.setCode("添加成功。");
		}
		return result;
	}

	@Override
	public List<Cart> getCartList(String userId, Long shopId) {
		// userId 必填
		if (StringUtils.isBlank(userId) || shopId == null) {
			return null;
		}

		Cart cart = new Cart();
		cart.setUserId(userId.trim());
		cart.setShopId(shopId);

		List<Cart> cartList = null;

		try {
			cartList = cartDao.getCartList(cart);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cart), e);
		}

		if (cartList == null || cartList.size() == 0) {
			return null;
		}

		String[] itemId = new String[cartList.size()];
		int i = 0;
		for (Cart ca : cartList) {
			itemId[i++] = ca.getItemId().toString();
		}

		// 2. 获取商品文件信息
		Map<String, List<ItemFile>> map = null;// itemFileService.getItemFileList(shopId,
												// itemId);

		// 不存在商品文件 直接返回
		if (map == null || map.isEmpty()) {
			return cartList;
		}

		for (Cart ca : cartList) {
			ca.setItemFileList(map.get(ca.getItemId()));
		}

		return cartList;
	}

	@Override
	public BooleanResult removeCart(String userId, Long shopId, String[] cartId) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Cart cart = new Cart();

		if (StringUtils.isBlank(userId)) {
			result.setCode("用户信息不能为空！");
			return result;
		}
		cart.setUserId(userId.trim());
		cart.setModifyUser(userId);

		if (shopId == null) {
			result.setCode("店铺信息不能为空！");
			return result;
		}
		cart.setShopId(shopId);

		if (cartId == null || cartId.length == 0) {
			result.setCode("购物车商品信息不能为空！");
			return result;
		}
		cart.setCodes(cartId);

		cart.setState(ICartService.STATE_REMOVE);

		int n = updateCart(cart);
		if (n == -1) {
			result.setCode("购物车更新失败！");
			return result;
		}

		result.setCode("删除成功。");
		result.setResult(true);

		return result;
	}

	/**
	 * 
	 * @param cart
	 * @return
	 */
	private int updateCart(Cart cart) {
		try {
			return cartDao.updateCart(cart);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cart), e);
		}

		return -1;
	}

	@Override
	public BooleanResult updateQuantity(String userId, Long shopId, String cartId, String quantity) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Cart cart = new Cart();

		if (StringUtils.isBlank(userId)) {
			result.setCode("用户信息不能为空。");
			return result;
		}
		cart.setUserId(userId.trim());
		cart.setModifyUser(userId);

		if (shopId == null) {
			result.setCode("店铺信息不能为空。");
			return result;
		}
		cart.setShopId(shopId);

		if (StringUtils.isBlank(cartId)) {
			result.setCode("购物车信息不能为空。");
			return result;
		}
		try {
			cart.setCartId(Long.valueOf(cartId));
		} catch (NumberFormatException e) {
			logger.error(cartId, e);

			result.setCode("购物车信息错误。");
			return result;
		}

		if (StringUtils.isBlank(quantity)) {
			result.setCode("购买商品数量不能为空。");
			return result;
		}

		int q;

		try {
			q = Integer.parseInt(quantity);
		} catch (Exception e) {
			logger.error(quantity, e);
			result.setCode("购买商品数量非数字类型。");
			return result;
		}

		if (q == 0 || q < 1) {
			result.setCode("数量不能为0或负。");
			return result;
		}

		cart.setQuantity(q);

		int n = updateQuantity(cart);
		if (n != 1) {
			result.setCode(String.valueOf(q - 1));
			return result;
		}

		result.setCode(String.valueOf(q));
		result.setResult(true);

		return result;
	}

	/**
	 * 
	 * @param cart
	 * @return
	 */
	private int updateQuantity(Cart cart) {
		try {
			return cartDao.updateQuantity(cart);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cart), e);
		}

		return -1;
	}

	public ICartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(ICartDao cartDao) {
		this.cartDao = cartDao;
	}

}
