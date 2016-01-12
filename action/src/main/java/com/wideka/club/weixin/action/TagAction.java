package com.wideka.club.weixin.action;

import java.util.List;

import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.weixin.api.tag.bo.Tag;

/**
 * 
 * @author JiakunXu
 * 
 */
public class TagAction extends BaseAction {

	private static final long serialVersionUID = 1175178780054186617L;

	private IWeixinService weixinService;

	/**
	 * 操作.
	 */
	private String op;

	private List<Tag> tagList;

	public String tag() {
		if ("list".equals(op)) {
			tagList = weixinService.getTagList();
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

	public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

}
