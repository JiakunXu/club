package com.wideka.club.api.cart;

import java.util.List;

import com.wideka.club.api.cart.bo.Cart;
import com.wideka.club.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface ICartService {

	String STATE_COMPLETE = "E";

	String STATE_REMOVE = "D";

	/**
	 * 
	 * @param userId
	 * @param shopId
	 * @param itemId
	 * @param skuId
	 * @param quantity
	 * @return
	 */
	BooleanResult createCart(String userId, Long shopId, String itemId, String skuId, String quantity);

	/**
	 * 获取用户购物车.
	 * 
	 * @param userId
	 * @param shopId
	 * @return
	 */
	List<Cart> getCartList(String userId, Long shopId);

}
