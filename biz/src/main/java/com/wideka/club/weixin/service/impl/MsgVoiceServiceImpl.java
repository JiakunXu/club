package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IMsgVoiceService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.club.weixin.dao.IMsgVoiceDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class MsgVoiceServiceImpl implements IMsgVoiceService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(MsgVoiceServiceImpl.class);

	private IMsgVoiceDao msgVoiceDao;

	@Override
	public BooleanResult createMsgVoice(Content content) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (content == null) {
			result.setCode("content 信息不能为空。");
			return result;
		}

		try {
			Long id = msgVoiceDao.createMsgVoice(content);
			result.setCode(id.toString());
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(content), e);

			result.setCode("写入 weixin_tb_msg_voice 表失败。");
		}

		return result;
	}

	public IMsgVoiceDao getMsgVoiceDao() {
		return msgVoiceDao;
	}

	public void setMsgVoiceDao(IMsgVoiceDao msgVoiceDao) {
		this.msgVoiceDao = msgVoiceDao;
	}

}
