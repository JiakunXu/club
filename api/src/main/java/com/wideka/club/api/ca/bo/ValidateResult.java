package com.wideka.club.api.ca.bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.wideka.club.api.user.bo.User;

/**
 * ValidateResult.
 * 
 * @author xujiakun
 * 
 */
@XmlRootElement
public class ValidateResult implements Serializable {

	private static final long serialVersionUID = 807590090225810140L;

	/**
	 * 返回结果.
	 */
	private String resultCode;

	/**
	 * 信息.
	 */
	private String message;

	/**
	 * 登陆用户信息.
	 */
	private User user;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
