package com.wideka.club.cart.action;

import java.util.Arrays;
import java.util.List;

import com.wideka.club.api.cart.ICartService;
import com.wideka.club.api.cart.bo.Cart;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public class CartAction extends BaseAction {

	private static final long serialVersionUID = -4392828383974468915L;

	private ICartService cartService;

	private List<Cart> cartList;

	/**
	 * 删除购物车.
	 */
	private String[] cartId;

	/**
	 * 
	 * @return
	 */
	public String index() {
		cartList = cartService.getCartList(this.getOpenId(), 0L);

		return SUCCESS;
	}

	/**
	 * 添加购物车.
	 * 
	 * @return
	 */
	public String add() {
		BooleanResult result = cartService.createCart(this.getOpenId(), 0L, "1", "0", "1");

		if (result.getResult()) {
			this.setResourceResult(result.getCode());
		} else {
			this.getServletResponse().setStatus(599);
			this.setResourceResult(result.getCode());
		}

		return RESOURCE_RESULT;
	}

	/**
	 * 移除购物车.
	 * 
	 * @return
	 */
	public String remove() {
		BooleanResult result = cartService.removeCart(this.getOpenId(), 0L, cartId);

		if (result.getResult()) {
			this.setResourceResult(result.getCode());
		} else {
			this.getServletResponse().setStatus(599);
			this.setResourceResult(result.getCode());
		}

		return RESOURCE_RESULT;
	}

	public ICartService getCartService() {
		return cartService;
	}

	public void setCartService(ICartService cartService) {
		this.cartService = cartService;
	}

	public List<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

	public String[] getCartId() {
		return cartId != null ? Arrays.copyOf(cartId, cartId.length) : null;
	}

	public void setCartId(String[] cartId) {
		if (cartId != null) {
			this.cartId = Arrays.copyOf(cartId, cartId.length);
		}
	}

}
