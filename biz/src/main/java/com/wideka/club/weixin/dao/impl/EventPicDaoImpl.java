package com.wideka.club.weixin.dao.impl;

import com.wideka.club.framework.dao.impl.BaseDaoImpl;
import com.wideka.club.weixin.dao.IEventPicDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class EventPicDaoImpl extends BaseDaoImpl implements IEventPicDao {

	@Override
	public Long createEventPic(Content content) {
		return (Long) getSqlMapClientTemplate().insert("weixin.event.pic.createEventPic", content);
	}

}
