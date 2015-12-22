package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IEventPicService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.club.weixin.dao.IEventPicDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class EventPicServiceImpl implements IEventPicService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(EventPicServiceImpl.class);

	private IEventPicDao eventPicDao;

	@Override
	public BooleanResult createEventPic(Content content) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (content == null) {
			result.setCode("content 信息不能为空。");
			return result;
		}

		try {
			Long id = eventPicDao.createEventPic(content);
			result.setCode(id.toString());
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(content), e);

			result.setCode("写入 weixin_tb_event_pic 表失败。");
		}

		return result;
	}

	public IEventPicDao getEventPicDao() {
		return eventPicDao;
	}

	public void setEventPicDao(IEventPicDao eventPicDao) {
		this.eventPicDao = eventPicDao;
	}

}
