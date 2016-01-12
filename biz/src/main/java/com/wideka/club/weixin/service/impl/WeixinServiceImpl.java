package com.wideka.club.weixin.service.impl;

import java.util.List;

import com.wideka.club.api.weixin.ITokenService;
import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.weixin.api.department.IDepartmentService;
import com.wideka.weixin.api.department.bo.Department;
import com.wideka.weixin.api.tag.ITagService;
import com.wideka.weixin.api.tag.bo.Tag;

/**
 * 
 * @author JiakunXu
 * 
 */
public class WeixinServiceImpl implements IWeixinService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(WeixinServiceImpl.class);

	private ITokenService tokenService;

	private IDepartmentService departmentService;

	private ITagService tagService;

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
	public List<Tag> getTagList() {
		BooleanResult result = tokenService.getToken(corpId, corpSecret);

		if (!result.getResult()) {
			return null;
		}

		try {
			return tagService.getTagList(result.getCode()).getTagList();
		} catch (RuntimeException e) {
			logger.error(e);
		}

		return null;
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

	public ITagService getTagService() {
		return tagService;
	}

	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
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
