package com.wideka.club.weixin.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.wideka.club.api.weixin.IAuthorizeService;
import com.wideka.club.api.weixin.IReceiveService;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;

/**
 * 
 * @author JiakunXu
 * 
 */
public class WeixinAction extends BaseAction {

	private static final long serialVersionUID = -2743999983035647869L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(WeixinAction.class);

	private IReceiveService receiveService;

	private IAuthorizeService authorizeService;

	private String msg_signature;

	private String timestamp;

	private String nonce;

	/**
	 * 通过成员授权获取到的code，每次成员授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期.
	 */
	private String code;

	public String receive() {
		StringBuilder data = new StringBuilder();

		InputStreamReader in = null;
		BufferedReader reader = null;
		try {
			in = new InputStreamReader(this.getServletRequest().getInputStream(), "UTF-8");
			reader = new BufferedReader(in);

			String temp = reader.readLine();
			while (temp != null) {
				data.append(temp);
				temp = reader.readLine();
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

		BooleanResult result = receiveService.receive(msg_signature, timestamp, nonce, data.toString());

		this.setResourceResult(String.valueOf(result.getResult()));

		return RESOURCE_RESULT;

	}

	public String authorize() {
		this.setResourceResult(authorizeService.authorize(code));
		return RESOURCE_RESULT;
	}

	public IReceiveService getReceiveService() {
		return receiveService;
	}

	public void setReceiveService(IReceiveService receiveService) {
		this.receiveService = receiveService;
	}

	public IAuthorizeService getAuthorizeService() {
		return authorizeService;
	}

	public void setAuthorizeService(IAuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}

	public String getMsg_signature() {
		return msg_signature;
	}

	public void setMsg_signature(String msg_signature) {
		this.msg_signature = msg_signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
