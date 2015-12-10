package com.wideka.club.framework.bo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author xujiakun
 * 
 */
@XmlRootElement
public class BooleanResult extends BaseResult {

	private static final long serialVersionUID = 4115289089294330499L;

	private boolean result;

	/**
	 * @return the result
	 */
	public boolean getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

}
