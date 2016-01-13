package com.wideka.club.weixin.action;

import java.util.List;

import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.weixin.api.department.bo.Department;

/**
 * 
 * @author JiakunXu
 * 
 */
public class DepartmentAction extends BaseAction {

	private static final long serialVersionUID = 2018723548858951991L;

	private IWeixinService weixinService;

	/**
	 * 操作.
	 */
	private String op;

	private List<Department> departmentList;

	public String department() {
		if ("create".equals(op)) {
			return "create";
		}

		if ("list".equals(op)) {
			departmentList = weixinService.getDepartmentList("1");
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

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

}
