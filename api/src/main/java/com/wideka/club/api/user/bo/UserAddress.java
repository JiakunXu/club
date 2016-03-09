package com.wideka.club.api.user.bo;

/**
 * 收货地址.
 * 
 * @author JiakunXu
 * 
 */
public class UserAddress {

	/**
	 * 主键.
	 */
	private Long addId;

	/**
	 * 用户id.
	 */
	private String userId;

	/**
	 * 收货人.
	 */
	private String contactName;

	/**
	 * 省.
	 */
	private String province;

	/**
	 * 市.
	 */
	private String city;

	/**
	 * 区.
	 */
	private String area;

	/**
	 * 区 对应id.
	 */
	private String backCode;

	/**
	 * 联系地址.
	 */
	private String address;

	/**
	 * 邮政编码.
	 */
	private String postalCode;

	/**
	 * 联系电话.
	 */
	private String tel;

	/**
	 * 是否默认　(Y是/N否).
	 */
	private String defaults;

	public Long getAddId() {
		return addId;
	}

	public void setAddId(Long addId) {
		this.addId = addId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBackCode() {
		return backCode;
	}

	public void setBackCode(String backCode) {
		this.backCode = backCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDefaults() {
		return defaults;
	}

	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}

}
