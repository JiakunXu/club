package com.wideka.club.weixin.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.weixin.api.department.bo.Department;

/**
 * 
 * @author JiakunXu
 * 
 */
public class DepartmentAction extends BaseAction {

	private static final long serialVersionUID = 2018723548858951991L;

	private static final String CHARSET_UTF8 = "UTF-8";

	private static final String CHARSET_ISO8859 = "ISO8859-1";

	private Logger4jExtend logger = Logger4jCollection.getLogger(DepartmentAction.class);

	private IWeixinService weixinService;

	/**
	 * 操作.
	 */
	private String op;

	private Department department;

	private String id;

	private List<Department> departmentList;

	private String name;

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

		if ("department/update".equals(op)) {
			BooleanResult result = weixinService.updateDepartment(department);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		if ("department/delete".equals(op)) {
			BooleanResult result = weixinService.deleteDepartment(id);

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

		if ("detail".equals(op)) {
			if (StringUtils.isNotBlank(name)) {
				try {
					name = new String(name.trim().getBytes(CHARSET_ISO8859), CHARSET_UTF8);
				} catch (UnsupportedEncodingException e) {
					logger.error(name, e);
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
