package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IEventEnterAgentService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.club.weixin.dao.IEventEnterAgentDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class EventEnterAgentServiceImpl implements IEventEnterAgentService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(EventEnterAgentServiceImpl.class);

	private IEventEnterAgentDao eventEnterAgentDao;

	@Override
	public BooleanResult createEventEnterAgent(Content content) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (content == null) {
			result.setCode("content 信息不能为空。");
			return result;
		}

		try {
			Long id = eventEnterAgentDao.createEventEnterAgent(content);
			result.setCode(id.toString());
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(content), e);

			result.setCode("写入 weixin_tb_event_enter_agent 表失败。");
		}

		return result;
	}

	public IEventEnterAgentDao getEventEnterAgentDao() {
		return eventEnterAgentDao;
	}

	public void setEventEnterAgentDao(IEventEnterAgentDao eventEnterAgentDao) {
		this.eventEnterAgentDao = eventEnterAgentDao;
	}

}
