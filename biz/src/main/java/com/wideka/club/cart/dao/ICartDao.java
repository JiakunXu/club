package com.wideka.club.cart.dao;

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

}
