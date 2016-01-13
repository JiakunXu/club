package com.wideka.club.weixin.action;

import java.util.List;

import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.bo.BooleanResult;
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

	private Department department;

	private List<Department> departmentList;

	public String department() {
		if ("create".equals(op)) {
			return "create";
		}

		if ("department/create".equals(op)) {
			BooleanResult result = weixinService.createDepartment(department);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

}
