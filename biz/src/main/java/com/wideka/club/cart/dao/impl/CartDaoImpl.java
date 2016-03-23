package com.wideka.club.cart.dao.impl;

import java.util.List;

import com.wideka.club.api.cart.bo.Cart;
import com.wideka.club.cart.dao.ICartDao;
import com.wideka.club.framework.dao.impl.BaseDaoImpl;

/**
 * 
 * @author JiakunXu
 * 
 */
public class CartDaoImpl extends BaseDaoImpl implements ICartDao {

	@Override
	public Long createCart(Cart cart) {
		return (Long) getSqlMapClientTemplate().insert("cart.createCart", cart);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cart> getCartList(Cart cart) {
		return (List<Cart>) getSqlMapClientTemplate().queryForList("cart.getCartList", cart);
	}

	@Override
	public int updateCart(Cart cart) {
		return getSqlMapClientTemplate().update("cart.updateCart", cart);
	}

	@Override
	public int updateQuantity(Cart cart) {
		return getSqlMapClientTemplate().update("cart.updateQuantity", cart);
	}

	@Override
	public Cart getCartStats(Cart cart) {
		return (Cart) getSqlMapClientTemplate().queryForObject("cart.getCartStats", cart);
	}

}
