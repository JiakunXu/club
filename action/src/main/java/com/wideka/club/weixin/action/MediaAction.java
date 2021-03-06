package com.wideka.club.weixin.action;

import com.alibaba.fastjson.JSON;
import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.weixin.api.material.bo.MaterialList;

/**
 * 
 * @author JiakunXu
 * 
 */
public class MediaAction extends BaseAction {

	private static final long serialVersionUID = 1349533398486466538L;

	private IWeixinService weixinService;

	/**
	 * 操作.
	 */
	private String op;

	private String materialCount;

	private MaterialList materialList;

	public String media() {
		if ("material/get_count".equals(op)) {
			materialCount = JSON.toJSONString(weixinService.getCount("19"));
			return "material/getCount";
		}

		if ("material/batchget".equals(op)) {
			materialList = weixinService.batchGet("image", 19, 0, 50);
			return "material/batchGet";
		}

		return SUCCESS;
	}

	public String media4Framework7() {
		if ("material/get_count".equals(op)) {
			materialCount = JSON.toJSONString(weixinService.getCount("19"));
			return "material/getCount";
		}

		if ("material/batchget".equals(op)) {
			materialList = weixinService.batchGet("image", 19, 0, 50);
			return "material/batchGet";
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

	public String getMaterialCount() {
		return materialCount;
	}

	public void setMaterialCount(String materialCount) {
		this.materialCount = materialCount;
	}

	public MaterialList getMaterialList() {
		return materialList;
	}

	public void setMaterialList(MaterialList materialList) {
		this.materialList = materialList;
	}

}
