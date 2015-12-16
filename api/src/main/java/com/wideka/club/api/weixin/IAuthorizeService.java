package com.wideka.club.api.weixin;

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
	 */
	String authorize(String code);

}
