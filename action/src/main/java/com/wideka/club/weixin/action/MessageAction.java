package com.wideka.club.weixin.action;

import org.apache.commons.lang3.StringUtils;

import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public class MessageAction extends BaseAction {

	private static final long serialVersionUID = -783512905307414411L;

	private IWeixinService weixinService;

	/**
	 * 操作.
	 */
	private String op;

	private String toUser;

	private String toParty;

	private String toTag;

	private String agentId;

	private String content;

	private String safe;

	public String message() {
		if ("send/text".equals(op)) {
			BooleanResult result = weixinService.send(toUser, toParty, toTag, Integer.parseInt(agentId), content, safe);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		if (StringUtils.isNotBlank(op)) {
			return op.trim();
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

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getToParty() {
		return toParty;
	}

	public void setToParty(String toParty) {
		this.toParty = toParty;
	}

	public String getToTag() {
		return toTag;
	}

	public void setToTag(String toTag) {
		this.toTag = toTag;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSafe() {
		return safe;
	}

	public void setSafe(String safe) {
		this.safe = safe;
	}

}
