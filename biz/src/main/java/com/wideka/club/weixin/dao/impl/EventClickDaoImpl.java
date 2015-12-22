package com.wideka.club.weixin.dao.impl;

import com.wideka.club.framework.dao.impl.BaseDaoImpl;
import com.wideka.club.weixin.dao.IEventClickDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class EventClickDaoImpl extends BaseDaoImpl implements IEventClickDao {

	@Override
	public Long createEventClick(Content content) {
		return (Long) getSqlMapClientTemplate().insert("weixin.event.click.createEventClick", content);
	}

}
