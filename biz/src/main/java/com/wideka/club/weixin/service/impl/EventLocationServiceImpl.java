package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IEventLocationService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.club.weixin.dao.IEventLocationDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class EventLocationServiceImpl implements IEventLocationService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(EventLocationServiceImpl.class);

	private IEventLocationDao eventLocationDao;

	@Override
	public BooleanResult createEventLocation(Content content) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (content == null) {
			result.setCode("content 信息不能为空。");
			return result;
		}

		try {
			Long id = eventLocationDao.createEventLocation(content);
			result.setCode(id.toString());
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(content), e);

			result.setCode("写入 weixin_tb_event_location 表失败。");
		}

		return result;
	}

	public IEventLocationDao getEventLocationDao() {
		return eventLocationDao;
	}

	public void setEventLocationDao(IEventLocationDao eventLocationDao) {
		this.eventLocationDao = eventLocationDao;
	}

}
