package com.wideka.club.cart.action;

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
	 * 
	 * @return
	 */
	public String index() {
		cartList = cartService.getCartList(this.getOpenId(), 0L);

		return SUCCESS;
	}

	public String add() {
		BooleanResult result = cartService.createCart(this.getOpenId(), 0L, "0", "0", "1");

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

}
