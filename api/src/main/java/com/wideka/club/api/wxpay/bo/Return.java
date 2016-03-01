package com.wideka.club.api.wxpay.bo;

import org.dom4j.Element;
import org.dom4j.VisitorSupport;

/**
 * 
 * @author JiakunXu
 * 
 */
public class Return extends VisitorSupport {

	/**
	 * SUCCESS/FAIL
	 * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断.
	 */
	private String returnCode;

	private String returnMsg;

	public void visit(Element node) {
		String name = node.getName();
		String text = node.getText();

		if ("return_code".equals(name)) {
			returnCode = text;
		} else if ("return_msg".equals(name)) {
			returnMsg = text;
		}
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

}
