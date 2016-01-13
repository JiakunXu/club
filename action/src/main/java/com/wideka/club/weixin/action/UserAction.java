package com.wideka.club.weixin.action;

import java.util.List;

import com.wideka.club.api.weixin.IWeixinService;
import com.wideka.club.framework.action.BaseAction;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.weixin.api.user.bo.User;

/**
 * 
 * @author JiakunXu
 * 
 */
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 6328740646132798068L;

	private IWeixinService weixinService;

	/**
	 * 操作.
	 */
	private String op;

	private User user;

	private List<User> userList;

	public String user() {
		if ("create".equals(op)) {
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

		if ("list".equals(op)) {
			userList = weixinService.getSimpleUserList("1", "1", "0");
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
