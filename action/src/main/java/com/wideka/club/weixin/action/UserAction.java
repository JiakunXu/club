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
import com.wideka.weixin.api.user.bo.User;

/**
 * 
 * @author JiakunXu
 * 
 */
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 6328740646132798068L;

	private static final String CHARSET_UTF8 = "UTF-8";

	private static final String CHARSET_ISO8859 = "ISO8859-1";

	private Logger4jExtend logger = Logger4jCollection.getLogger(UserAction.class);

	private IWeixinService weixinService;

	/**
	 * 操作.
	 */
	private String op;

	private List<Department> departmentList;

	private User user;

	private String userId;

	private List<User> userList;

	private String name;

	public String user() {
		if ("create".equals(op)) {
			departmentList = weixinService.getDepartmentList("1");
			return "create";
		}
		if ("user/create".equals(op)) {
			BooleanResult result = weixinService.createUser(user);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		if ("user/update".equals(op)) {
			BooleanResult result = weixinService.updateUser(user);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		if ("user/delete".equals(op)) {
			BooleanResult result = weixinService.deleteUser(userId);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
		}

		if ("simplelist".equals(op)) {
			userList = weixinService.getSimpleUserList("1", "1", "0");
			return "simpleList";
		}

		if ("list".equals(op)) {
			userList = weixinService.getUserList("1", "1", "0");
			return "list";
		}

		if ("invite".equals(op)) {
			return "invite";
		}
		if ("user/invite".equals(op)) {
			BooleanResult result = weixinService.inviteUser(userId);

			if (result.getResult()) {
				this.setSuccessMessage("成功！");
			} else {
				this.setFailMessage(result.getCode());
			}

			return RESULT_MESSAGE;
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

	public String user4Framework7() {
		if ("create".equals(op)) {
			departmentList = weixinService.getDepartmentList("1");
			return "create";
		}
		if ("user/create".equals(op)) {
			BooleanResult result = weixinService.createUser(user);

			if (result.getResult()) {
				this.setResourceResult("成功！");
			} else {
				this.getServletResponse().setStatus(500);
				this.setResourceResult(result.getCode());
			}

			return RESOURCE_RESULT;
		}

		if ("user/update".equals(op)) {
			BooleanResult result = weixinService.updateUser(user);

			if (result.getResult()) {
				this.setResourceResult("成功！");
			} else {
				this.getServletResponse().setStatus(500);
				this.setResourceResult(result.getCode());
			}

			return RESOURCE_RESULT;
		}

		if ("user/delete".equals(op)) {
			BooleanResult result = weixinService.deleteUser(userId);

			if (result.getResult()) {
				this.setResourceResult("成功！");
			} else {
				this.getServletResponse().setStatus(500);
				this.setResourceResult(result.getCode());
			}

			return RESOURCE_RESULT;
		}

		if ("simplelist".equals(op)) {
			userList = weixinService.getSimpleUserList("1", "1", "0");
			return "simpleList";
		}

		if ("list".equals(op)) {
			userList = weixinService.getUserList("1", "1", "0");
			return "list";
		}

		if ("invite".equals(op)) {
			return "invite";
		}
		if ("user/invite".equals(op)) {
			BooleanResult result = weixinService.inviteUser(userId);

			if (result.getResult()) {
				this.setResourceResult("成功！");
			} else {
				this.getServletResponse().setStatus(500);
				this.setResourceResult(result.getCode());
			}

			return RESOURCE_RESULT;
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

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
