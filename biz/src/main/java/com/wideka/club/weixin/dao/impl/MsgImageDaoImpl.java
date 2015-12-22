package com.wideka.club.weixin.dao.impl;

import com.wideka.club.framework.dao.impl.BaseDaoImpl;
import com.wideka.club.weixin.dao.IMsgImageDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class MsgImageDaoImpl extends BaseDaoImpl implements IMsgImageDao {

	@Override
	public Long createMsgImage(Content content) {
		return (Long) getSqlMapClientTemplate().insert("weixin.msg.image.createMsgImage", content);
	}

}
