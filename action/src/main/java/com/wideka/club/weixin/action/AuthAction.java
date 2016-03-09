package com.wideka.club.weixin.action;

import org.apache.commons.lang.StringUtils;

import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public class AuthAction extends BaseAction {

	private static final long serialVersionUID = 5334199804808618041L;

	private IWeixinService weixinService;

	/**
	 * 操作.
	 */
	private String op;

	private String userId;

	private String agentId;

	private String openId;

	public String auth() {
		if ("oauth2".equals(op)) {
			return "oauth2";
		}

		if ("convert".equals(op)) {
			return "convert";
		}

		if ("auth/convertToOpenId".equals(op)) {
			BooleanResult result =
				weixinService
					.convertToOpenId(userId, StringUtils.isNotBlank(agentId) ? Integer.valueOf(agentId) : null);

			if (result.getResult()) {
				this.setSuccessMessage(result.getCode());
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		if ("auth/convertToUserId".equals(op)) {
			BooleanResult result = weixinService.convertToUserId(openId);

			if (result.getResult()) {
				this.setSuccessMessage(result.getCode());
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		return SUCCESS;
	}

	public String auth4Framework7() {
		if ("oauth2".equals(op)) {
			return "oauth2";
		}

		if ("convert".equals(op)) {
			return "convert";
		}

		if ("auth/convertToOpenId".equals(op)) {
			BooleanResult result =
				weixinService
					.convertToOpenId(userId, StringUtils.isNotBlank(agentId) ? Integer.valueOf(agentId) : null);

			if (!result.getResult()) {
				this.getServletResponse().setStatus(599);
			}

			this.setResourceResult(result.getCode());

			return RESOURCE_RESULT;
		}

		if ("auth/convertToUserId".equals(op)) {
			BooleanResult result = weixinService.convertToUserId(openId);

			if (!result.getResult()) {
				this.getServletResponse().setStatus(599);
			}

			this.setResourceResult(result.getCode());

			return RESOURCE_RESULT;
		}

		return SUCCESS;
	}

	public IWeixinService getWeixinService() {
		return weixinService;
	}

	public void setWeixinService(IWeixinService weixinService) {
		this.weixinService = weixinService;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
