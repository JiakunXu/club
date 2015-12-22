package com.wideka.club.weixin.dao.impl;

import com.wideka.club.framework.dao.impl.BaseDaoImpl;
import com.wideka.club.weixin.dao.IMsgTextDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class MsgTextDaoImpl extends BaseDaoImpl implements IMsgTextDao {

	@Override
	public Long createMsgText(Content content) {
		return (Long) getSqlMapClientTemplate().insert("weixin.msg.text.createMsgText", content);
	}

}
