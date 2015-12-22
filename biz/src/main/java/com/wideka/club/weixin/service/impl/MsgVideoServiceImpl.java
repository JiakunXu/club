package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IMsgVideoService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.club.weixin.dao.IMsgVideoDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class MsgVideoServiceImpl implements IMsgVideoService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(MsgVideoServiceImpl.class);

	private IMsgVideoDao msgVideoDao;

	@Override
	public BooleanResult createMsgVideo(Content content) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (content == null) {
			result.setCode("content 信息不能为空。");
			return result;
		}

		try {
			Long id = msgVideoDao.createMsgVideo(content);
			result.setCode(id.toString());
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(content), e);

			result.setCode("写入 weixin_tb_msg_video 表失败。");
		}

		return result;
	}

	public IMsgVideoDao getMsgVideoDao() {
		return msgVideoDao;
	}

	public void setMsgVideoDao(IMsgVideoDao msgVideoDao) {
		this.msgVideoDao = msgVideoDao;
	}

}
