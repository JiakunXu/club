package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IEventSubscribeService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.club.weixin.dao.IEventSubscribeDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class EventSubscribeServiceImpl implements IEventSubscribeService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(EventSubscribeServiceImpl.class);

	private IEventSubscribeDao eventSubscribeDao;

	@Override
	public BooleanResult createEventSubscribe(Content content) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (content == null) {
			result.setCode("content 信息不能为空。");
			return result;
		}

		try {
			Long id = eventSubscribeDao.createEventSubscribe(content);
			result.setCode(id.toString());
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(content), e);

			result.setCode("写入 weixin_tb_event_subscribe 表失败。");
		}

		return result;
	}

	public IEventSubscribeDao getEventSubscribeDao() {
		return eventSubscribeDao;
	}

	public void setEventSubscribeDao(IEventSubscribeDao eventSubscribeDao) {
		this.eventSubscribeDao = eventSubscribeDao;
	}

}
