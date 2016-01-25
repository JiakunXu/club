package com.wideka.club.weixin.action;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.weixin.api.agent.bo.Agent;

/**
 * 
 * @author JiakunXu
 * 
 */
public class AgentAction extends BaseAction {

	private static final long serialVersionUID = 6446418150793765788L;

	private IWeixinService weixinService;

	/**
	 * 操作.
	 */
	private String op;

	private String agent;

	private List<Agent> agentList;

	public String agent() {
		if ("get".equals(op)) {
			agent = JSON.toJSONString(weixinService.getAgent("19"));
			return "get";
		}

		if ("list".equals(op)) {
			agentList = weixinService.getAgentList();
			return "list";
		}

		return SUCCESS;
	}

	public String agent4Framework7() {
		if ("get".equals(op)) {
			agent = JSON.toJSONString(weixinService.getAgent("19"));
			return "get";
		}

		if ("list".equals(op)) {
			agentList = weixinService.getAgentList();
			return "list";
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

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public List<Agent> getAgentList() {
		return agentList;
	}

	public void setAgentList(List<Agent> agentList) {
		this.agentList = agentList;
	}

}
