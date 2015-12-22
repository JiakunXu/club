package com.wideka.club.weixin.dao;

import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IMsgImageDao {

	/**
	 * 
	 * @param content
	 * @return
	 */
	Long createMsgImage(Content content);

}
