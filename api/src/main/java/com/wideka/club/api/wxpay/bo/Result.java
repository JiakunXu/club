package com.wideka.club.api.wxpay.bo;

import org.dom4j.Element;

/**
 * 业务结果.
 * 
 * @author JiakunXu
 * 
 */
public class Result extends Return {

	/**
	 * 业务结果 SUCCESS/FAIL.
	 */
	private String resultCode;

	/**
	 * 错误代码.
	 */
	private String errCode;

	/**
	 * 错误代码描述.
	 */
	private String errCodeDes;

	public void visit(Element node) {
		super.visit(node);

		String name = node.getName();
		String text = node.getText();

		if ("result_code".equals(name)) {
			resultCode = text;
		} else if ("err_code".equals(name)) {
			errCode = text;
		} else if ("err_code_des".equals(name)) {
			errCodeDes = text;
		}
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}

}
