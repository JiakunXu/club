package com.wideka.club.pay.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.wideka.club.api.pay.IPayService;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.ClientUtil;

/**
 * 
 * @author JiakunXu
 * 
 */
public class PayAction extends BaseAction {

	private static final long serialVersionUID = 3200362561478926494L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(PayAction.class);

	private IPayService payService;

	/**
	 * 交易支付.
	 */
	private String tradeNo;

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {
		return SUCCESS;
	}

	/**
	 * 支付.
	 * 
	 * @return
	 */
	public String pay() {
		BooleanResult result =
			payService.pay(this.getOpenId(), 0L, tradeNo, "wxpay", ClientUtil.getIpAddr(this.getServletRequest()),
				this.getOpenId());

		if (result.getResult()) {
			this.setResourceResult(result.getCode());
		} else {
			this.getServletResponse().setStatus(599);
			this.setResourceResult(result.getCode());
		}

		return RESOURCE_RESULT;
	}

	/**
	 * 退款.
	 * 
	 * @return
	 */
	public String refund() {
		BooleanResult result = payService.refund(this.getOpenId(), 0L, tradeNo);

		if (result.getResult()) {
			this.setResourceResult(result.getCode());
		} else {
			this.getServletResponse().setStatus(599);
			this.setResourceResult(result.getCode());
		}

		return RESOURCE_RESULT;
	}

	/**
	 * 回调.
	 * 
	 * @return
	 */
	public String wxNotify() {
		StringBuilder fileContent = new StringBuilder();

		InputStreamReader in = null;
		BufferedReader reader = null;
		try {
			in = new InputStreamReader(this.getServletRequest().getInputStream(), "UTF-8");
			reader = new BufferedReader(in);

			String tempStr = reader.readLine();
			while (tempStr != null) {
				fileContent.append(tempStr);
				tempStr = reader.readLine();
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}

		BooleanResult result = payService.notify(fileContent.toString());
		this.setResourceResult(result.getCode());

		return RESOURCE_RESULT;
	}

	public IPayService getPayService() {
		return payService;
	}

	public void setPayService(IPayService payService) {
		this.payService = payService;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

}
