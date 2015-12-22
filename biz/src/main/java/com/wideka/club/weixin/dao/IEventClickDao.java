package com.wideka.club.weixin.dao;

import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IEventClickDao {

	/**
	 * 
	 * @param content
	 * @return
	 */
	Long createEventClick(Content content);

}
