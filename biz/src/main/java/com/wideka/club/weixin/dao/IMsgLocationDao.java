package com.wideka.club.weixin.dao;

import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IMsgLocationDao {

	/**
	 * 
	 * @param content
	 * @return
	 */
	Long createMsgLocation(Content content);

}
