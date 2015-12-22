package com.wideka.club.weixin.dao.impl;

import com.wideka.club.framework.dao.impl.BaseDaoImpl;
import com.wideka.club.weixin.dao.IEventSubscribeDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class EventSubscribeDaoImpl extends BaseDaoImpl implements IEventSubscribeDao {

	@Override
	public Long createEventSubscribe(Content content) {
		return (Long) getSqlMapClientTemplate().insert("weixin.event.subscribe.createEventSubscribe", content);
	}

}
