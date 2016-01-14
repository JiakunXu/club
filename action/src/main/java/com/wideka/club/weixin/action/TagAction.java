package com.wideka.club.weixin.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.weixin.api.tag.bo.Tag;
import com.wideka.weixin.api.tag.bo.TagResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public class TagAction extends BaseAction {

	private static final long serialVersionUID = 1175178780054186617L;

	private static final String CHARSET_UTF8 = "UTF-8";

	private static final String CHARSET_ISO8859 = "ISO8859-1";

	private Logger4jExtend logger = Logger4jCollection.getLogger(TagAction.class);

	private IWeixinService weixinService;

	/**
	 * 操作.
	 */
	private String op;

	private Tag tag;

	private String tagId;

	private TagResult tagResult;

	private List<Tag> tagList;

	private String tagName;

	public String tag() {
		if ("create".equals(op)) {
			return "create";
		}

		if ("tag/create".equals(op)) {
			BooleanResult result = weixinService.createTag(tag);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		if ("tag/update".equals(op)) {
			BooleanResult result = weixinService.updateTag(tag);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		if ("tag/delete".equals(op)) {
			BooleanResult result = weixinService.deleteTag(tagId);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		if ("get".equals(op)) {
			tagResult = weixinService.getTag(tagId);
			return "get";
		}

		if ("list".equals(op)) {
			tagList = weixinService.getTagList();
			return "list";
		}

		if ("detail".equals(op)) {
			if (StringUtils.isNotBlank(tagName)) {
				try {
					tagName = new String(tagName.trim().getBytes(CHARSET_ISO8859), CHARSET_UTF8);
				} catch (UnsupportedEncodingException e) {
					logger.error(tagName, e);
				}
			}

			return "detail";
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

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public TagResult getTagResult() {
		return tagResult;
	}

	public void setTagResult(TagResult tagResult) {
		this.tagResult = tagResult;
	}

	public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
