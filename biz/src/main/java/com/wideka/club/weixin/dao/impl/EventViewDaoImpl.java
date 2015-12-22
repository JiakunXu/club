package com.wideka.club.weixin.dao.impl;

import com.wideka.club.framework.dao.impl.BaseDaoImpl;
import com.wideka.club.weixin.dao.IEventViewDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class EventViewDaoImpl extends BaseDaoImpl implements IEventViewDao {

	@Override
	public Long createEventView(Content content) {
		return (Long) getSqlMapClientTemplate().insert("weixin.event.view.createEventView", content);
	}

}
