package com.wideka.club.weixin.dao;

import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IEventScanCodeDao {

	/**
	 * 
	 * @param content
	 * @return
	 */
	Long createEventScanCode(Content content);

}
