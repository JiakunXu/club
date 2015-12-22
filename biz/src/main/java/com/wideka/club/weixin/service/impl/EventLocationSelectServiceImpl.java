package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IEventLocationSelectService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.club.weixin.dao.IEventLocationSelectDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class EventLocationSelectServiceImpl implements IEventLocationSelectService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(EventLocationSelectServiceImpl.class);

	private IEventLocationSelectDao eventLocationSelectDao;

	@Override
	public BooleanResult createEventLocationSelect(Content content) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (content == null) {
			result.setCode("content 信息不能为空。");
			return result;
		}

		try {
			Long id = eventLocationSelectDao.createEventLocationSelect(content);
			result.setCode(id.toString());
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(content), e);

			result.setCode("写入 weixin_tb_event_location_select 表失败。");
		}

		return result;
	}

	public IEventLocationSelectDao getEventLocationSelectDao() {
		return eventLocationSelectDao;
	}

	public void setEventLocationSelectDao(IEventLocationSelectDao eventLocationSelectDao) {
		this.eventLocationSelectDao = eventLocationSelectDao;
	}

}
