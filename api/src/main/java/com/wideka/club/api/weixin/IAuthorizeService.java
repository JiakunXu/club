package com.wideka.club.api.weixin;

import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.exception.ServiceException;
import com.wideka.weixin.api.user.bo.UserInfo;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IAuthorizeService {

	/**
	 * 
	 * @param code
	 *            通过成员授权获取到的code，每次成员授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期.
	 * @return
	 * @throws ServiceException
	 */
	UserInfo getUserInfo(String code) throws ServiceException;

	/**
	 * 
	 * @param code
	 * @return
	 */
	BooleanResult authSucc(String code);

}
