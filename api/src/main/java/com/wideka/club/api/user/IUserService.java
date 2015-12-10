package com.wideka.club.api.user;

import com.wideka.club.api.user.bo.User;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IUserService {

	String SUCCESS = "success";

	String ERROR = "error";

	String ERROR_MESSAGE = "操作失败！";

	String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	String ERROR_NULL_MESSAGE = "操作失败，不存在！";

	String ERROR_EXIST_MESSAGE = "操作失败，已存在！";

	/**
	 * 根据登陆帐号获取用户信息(存在缓存).
	 * 
	 * @param passport
	 * @return
	 */
	User getUserByPassport(String passport);

	/**
	 * 根据登陆帐号获取用户信息(不存在缓存).
	 * 
	 * @param passport
	 * @return
	 */
	User getUser4Validate(String passport);

}
