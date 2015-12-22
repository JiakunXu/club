package com.wideka.club.weixin.dao;

import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IEventBatchJobDao {

	/**
	 * 
	 * @param content
	 * @return
	 */
	Long createEventBatchJob(Content content);

}
