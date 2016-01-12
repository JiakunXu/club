package com.wideka.club.weixin.service.impl;

import java.util.List;

import com.wideka.club.api.weixin.ITokenService;
import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.weixin.api.agent.IAgentService;
import com.wideka.weixin.api.agent.bo.Agent;
import com.wideka.weixin.api.department.IDepartmentService;
import com.wideka.weixin.api.department.bo.Department;
import com.wideka.weixin.api.message.IMessageService;
import com.wideka.weixin.api.message.bo.Text;
import com.wideka.weixin.api.tag.ITagService;
import com.wideka.weixin.api.tag.bo.Tag;
import com.wideka.weixin.api.user.IUserService;
import com.wideka.weixin.api.user.bo.User;

/**
 * 
 * @author JiakunXu
 * 
 */
public class WeixinServiceImpl implements IWeixinService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(WeixinServiceImpl.class);

	private ITokenService tokenService;

	private IDepartmentService departmentService;

	private IUserService userService;

	private ITagService tagService;

	private IAgentService agentService;

	private IMessageService messageService;

	private String corpId;

	private String corpSecret;

	@Override
	public List<Department> getDepartmentList(String id) {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return null;
		}

		try {
			return departmentService.getDepartmentList(result.getCode(), id).getDepartmentList();
		} catch (RuntimeException e) {
			logger.error(e);
		}

		return null;
	}

	@Override
	public List<User> getSimpleUserList(String departmentId, String fetchChild, String status) {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return null;
		}

		try {
			return userService.getSimpleUserList(result.getCode(), departmentId, fetchChild, status);
		} catch (RuntimeException e) {
			logger.error(e);
		}

		return null;
	}

	@Override
	public List<Tag> getTagList() {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return null;
		}

		try {
			return tagService.getTagList(result.getCode());
		} catch (RuntimeException e) {
			logger.error(e);
		}

		return null;
	}

	@Override
	public List<Agent> getAgentList() {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return null;
		}

		try {
			return agentService.getAgentList(result.getCode());
		} catch (RuntimeException e) {
			logger.error(e);
		}

		return null;
	}

	@Override
	public BooleanResult send(String toUser, String toParty, String toTag, String agentId, String content, String safe) {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return null;
		}

		Text text = new Text();
		text.setContent(content);

		try {
			messageService.send(result.getCode(), toUser, toParty, toTag, Integer.parseInt(agentId), text, safe);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(e);

			result.setCode(e.getMessage());
			result.setResult(false);
		}

		return result;
	}

	public ITokenService getTokenService() {
		return tokenService;
	}

	public void setTokenService(ITokenService tokenService) {
		this.tokenService = tokenService;
	}

	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public ITagService getTagService() {
		return tagService;
	}

	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}

	public IAgentService getAgentService() {
		return agentService;
	}

	public void setAgentService(IAgentService agentService) {
		this.agentService = agentService;
	}

	public IMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getCorpSecret() {
		return corpSecret;
	}

	public void setCorpSecret(String corpSecret) {
		this.corpSecret = corpSecret;
	}

}
