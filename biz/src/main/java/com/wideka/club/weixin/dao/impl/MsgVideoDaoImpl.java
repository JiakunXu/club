package com.wideka.club.weixin.dao.impl;

import com.wideka.club.framework.dao.impl.BaseDaoImpl;
import com.wideka.club.weixin.dao.IMsgVideoDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class MsgVideoDaoImpl extends BaseDaoImpl implements IMsgVideoDao {

	@Override
	public Long createMsgVideo(Content content) {
		return (Long) getSqlMapClientTemplate().insert("weixin.msg.video.createMsgVideo", content);
	}

}
