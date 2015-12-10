package com.wideka.club.framework.bo;

import java.io.Serializable;

/**
 * BaseResult.
 * 
 * @author xujiakun
 * 
 */
public class BaseResult implements Serializable {

	private static final long serialVersionUID = -4398337558212309016L;

	private String code;

	/**
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
