package com.wideka.club.cart.dao;

import java.util.List;

import com.wideka.club.api.cart.bo.Cart;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface ICartDao {

	/**
	 * 
	 * @param cart
	 * @return
	 */
	Long createCart(Cart cart);

	/**
	 * 
	 * @param cart
	 * @return
	 */
	List<Cart> getCartList(Cart cart);

	/**
	 * 
	 * @param cart
	 * @return
	 */
	int updateCart(Cart cart);

	/**
	 * 
	 * @param cart
	 * @return
	 */
	int updateQuantity(Cart cart);

	/**
	 * 
	 * @param cart
	 * @return
	 */
	Cart getCartStats(Cart cart);

}
