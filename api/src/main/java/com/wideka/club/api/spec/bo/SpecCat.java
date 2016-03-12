package com.wideka.club.api.spec.bo;

import com.wideka.club.framework.bo.SearchInfo;

/**
 * 规格类目.
 * 
 * @author JiakunXu
 * 
 */
public class SpecCat extends SearchInfo {

	private static final long serialVersionUID = -6180808931784315891L;

	private Long specCId;

	/**
	 * 店铺ID.
	 */
	private Long shopId;

	private String specCName;

	private String state;

	private String modifyUser;

	public Long getSpecCId() {
		return specCId;
	}

	public void setSpecCId(Long specCId) {
		this.specCId = specCId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getSpecCName() {
		return specCName;
	}

	public void setSpecCName(String specCName) {
		this.specCName = specCName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

}
