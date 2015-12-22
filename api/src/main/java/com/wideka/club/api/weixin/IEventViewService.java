package com.wideka.club.api.weixin;

import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IEventViewService {

	/**
	 * 
	 * @param content
	 * @return
	 */
	BooleanResult createEventView(Content content);

}
