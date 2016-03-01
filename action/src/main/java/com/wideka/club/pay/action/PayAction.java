package com.wideka.club.pay.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

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

	private String redirectUrl;

	/**
	 * 用户唯一标识.
	 */
	private String openId;

	/**
	 * 微信支付.
	 * 
	 * @return
	 */
	public String authorize() {
		BooleanResult result = null;

		try {
			result =
				payService.authorize(
					URLEncoder.encode(env.getProperty("appUrl") + "/pay/index.htm?tradeNo=" + tradeNo, "UTF-8"),
					tradeNo);
		} catch (Exception e) {
			logger.error(e);
			return ERROR;
		}

		if (result.getResult()) {
			redirectUrl = result.getCode();

			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {
		openId = payService.getOpenId(this.getCode());

		if (StringUtils.isEmpty(openId)) {
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 支付.
	 * 
	 * @return
	 */
	public String pay() {
		BooleanResult result =
			payService.pay(0l, openId, tradeNo, "wxpay", ClientUtil.getIpAddr(this.getServletRequest()));

		if (result.getResult()) {
			this.setSuccessMessage(result.getCode());
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
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

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
