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
import com.wideka.weixin.api.material.IMaterialService;
import com.wideka.weixin.api.material.bo.MaterialList;
import com.wideka.weixin.api.menu.IMenuService;
import com.wideka.weixin.api.menu.bo.Menu;
import com.wideka.weixin.api.message.IMessageService;
import com.wideka.weixin.api.message.bo.Image;
import com.wideka.weixin.api.message.bo.Text;
import com.wideka.weixin.api.tag.ITagService;
import com.wideka.weixin.api.tag.bo.Tag;
import com.wideka.weixin.api.user.IUserService;
import com.wideka.weixin.api.user.bo.User;
import com.wideka.weixin.api.user.bo.UserResult;

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

	private IMaterialService materialService;

	private IAgentService agentService;

	private IMessageService messageService;

	private IMenuService menuService;

	private String corpId;

	private String corpSecret;

	@Override
	public BooleanResult createDepartment(Department department) {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return result;
		}

		try {
			Department d = departmentService.createDepartment(result.getCode(), department);
			result.setCode(String.valueOf(d.getId()));
		} catch (RuntimeException e) {
			logger.error(e);

			result.setCode(e.getMessage());
			result.setResult(false);
		}

		return result;
	}

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
	public BooleanResult createUser(User user) {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return result;
		}

		try {
			userService.createUser(result.getCode(), user);
			result.setCode(null);
		} catch (RuntimeException e) {
			logger.error(e);

			result.setCode(e.getMessage());
			result.setResult(false);
		}

		return result;
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
	public BooleanResult inviteUser(String userId) {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return result;
		}

		try {
			UserResult res = userService.inviteUser(result.getCode(), userId);
			result.setCode(res.getType());
		} catch (RuntimeException e) {
			logger.error(e);

			result.setCode(e.getMessage());
			result.setResult(false);
		}

		return result;
	}

	@Override
	public BooleanResult createTag(Tag tag) {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return result;
		}

		try {
			Tag t = tagService.createTag(result.getCode(), tag);
			result.setCode(String.valueOf(t.getTagId()));
		} catch (RuntimeException e) {
			logger.error(e);

			result.setCode(e.getMessage());
			result.setResult(false);
		}

		return result;
	}

	@Override
	public BooleanResult updateTag(Tag tag) {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return result;
		}

		try {
			tagService.updateTag(result.getCode(), tag);
			result.setCode(null);
		} catch (RuntimeException e) {
			logger.error(e);

			result.setCode(e.getMessage());
			result.setResult(false);
		}

		return result;
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
	public MaterialList batchGet(String type, int agentId, int offset, int count) {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return null;
		}

		try {
			return materialService.batchGet(result.getCode(), type, agentId, offset, count);
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
	public BooleanResult send(String toUser, String toParty, String toTag, int agentId, Text text, String safe) {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return null;
		}

		try {
			messageService.send(result.getCode(), toUser, toParty, toTag, agentId, text, safe);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(e);

			result.setCode(e.getMessage());
			result.setResult(false);
		}

		return result;
	}

	@Override
	public BooleanResult send(String toUser, String toParty, String toTag, int agentId, Image image, String safe) {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return null;
		}

		try {
			messageService.send(result.getCode(), toUser, toParty, toTag, agentId, image, safe);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(e);

			result.setCode(e.getMessage());
			result.setResult(false);
		}

		return result;
	}

	@Override
	public Menu getMenu(String agentId) {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return null;
		}

		try {
			return menuService.getMenu(result.getCode(), agentId);
		} catch (RuntimeException e) {
			logger.error(e);
		}

		return null;
	}

	@Override
	public BooleanResult convertToOpenId(String userId, Integer agentId) {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return result;
		}

		try {
			User user = userService.convertToOpenId(result.getCode(), userId, agentId);
			result.setCode(user.getOpenId() + "|" + user.getAppId());
		} catch (RuntimeException e) {
			logger.error(e);

			result.setCode(e.getMessage());
			result.setResult(false);
		}

		return result;
	}

	@Override
	public BooleanResult convertToUserId(String openId) {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);
		if (!result.getResult()) {
			return result;
		}

		try {
			User user = userService.convertToUserId(result.getCode(), openId);
			result.setCode(user.getUserId());
		} catch (RuntimeException e) {
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

	public IMaterialService getMaterialService() {
		return materialService;
	}

	public void setMaterialService(IMaterialService materialService) {
		this.materialService = materialService;
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

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
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
