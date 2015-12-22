package com.wideka.club.weixin.dao;

import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IEventPicDao {

	/**
	 * 
	 * @param content
	 * @return
	 */
	Long createEventPic(Content content);

}
