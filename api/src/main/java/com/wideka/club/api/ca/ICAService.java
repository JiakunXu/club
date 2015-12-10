package com.wideka.club.api.ca;

import com.wideka.club.api.ca.bo.ValidateResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ICAService {

	String RESULT_SUCCESS = "0";

	String RESULT_FAILED = "1";

	String RESULT_ERROR = "2";

	String INCORRECT_NULL = "用户名或密码不能为空！";

	String INCORRECT_LOGINID = "该用户在系统中不存在！";

	String INCORRECT_LOGIN = "用户名或密码输入不正确！";

	String INCORRECT_TOKEN = "token验证失败！";

	String INCORRECT_LOCKED = "账号锁定，请联系管理员！";

	String INCORRECT_DISABLED = "账号禁用，请联系管理员！";

	/**
	 * validateUser.
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	ValidateResult validateUser(String passport, String password);

}
