package com.wideka.club.user.action;

import com.wideka.club.api.user.IUserAddressService;
import com.wideka.club.api.user.bo.UserAddress;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public class UserAddressAction extends BaseAction {

	private static final long serialVersionUID = -8769317353845217884L;

	private IUserAddressService userAddressService;

	private UserAddress userAddress;

	/**
	 * 创建收货地址并修改订单.
	 */
	private String tradeNo;

	public String index() {
		return SUCCESS;
	}

	public String create() {
		BooleanResult result = userAddressService.createUserAddress(this.getOpenId(), userAddress, 0L, tradeNo);

		if (result.getResult()) {
			this.setResourceResult(result.getCode());
		} else {
			this.getServletResponse().setStatus(599);
			this.setResourceResult(result.getCode());
		}

		return RESOURCE_RESULT;
	}

	public IUserAddressService getUserAddressService() {
		return userAddressService;
	}

	public void setUserAddressService(IUserAddressService userAddressService) {
		this.userAddressService = userAddressService;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

}
