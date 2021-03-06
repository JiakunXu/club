package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IEventBatchJobService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.club.weixin.dao.IEventBatchJobDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class EventBatchJobServiceImpl implements IEventBatchJobService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(EventBatchJobServiceImpl.class);

	private IEventBatchJobDao eventBatchJobDao;

	@Override
	public BooleanResult createEventBatchJob(Content content) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (content == null) {
			result.setCode("content 信息不能为空。");
			return result;
		}

		try {
			Long id = eventBatchJobDao.createEventBatchJob(content);
			result.setCode(id.toString());
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(content), e);

			result.setCode("写入 weixin_tb_event_batch_job 表失败。");
		}

		return result;
	}

	public IEventBatchJobDao getEventBatchJobDao() {
		return eventBatchJobDao;
	}

	public void setEventBatchJobDao(IEventBatchJobDao eventBatchJobDao) {
		this.eventBatchJobDao = eventBatchJobDao;
	}

}
