package com.wideka.club.api.user.bo;

import com.wideka.club.framework.bo.SearchInfo;

/**
 * 
 * @author JiakunXu
 * 
 */
public class User extends SearchInfo {

	private static final long serialVersionUID = 5804990636185340805L;

	private String userId;

	/**
	 * 用户名.
	 */
	private String userName;

	/**
	 * 登录帐号.
	 */
	private String passport;

	/**
	 * 登录密码.
	 */
	private String password;

	private String state;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
