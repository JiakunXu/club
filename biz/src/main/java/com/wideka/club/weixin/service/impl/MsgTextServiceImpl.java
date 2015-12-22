package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IMsgTextService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.club.weixin.dao.IMsgTextDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class MsgTextServiceImpl implements IMsgTextService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(MsgTextServiceImpl.class);

	private IMsgTextDao msgTextDao;

	@Override
	public BooleanResult createMsgText(Content content) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (content == null) {
			result.setCode("content 信息不能为空。");
			return result;
		}

		try {
			Long id = msgTextDao.createMsgText(content);
			result.setCode(id.toString());
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(content), e);

			result.setCode("写入 weixin_tb_msg_text 表失败。");
		}

		return result;
	}

	public IMsgTextDao getMsgTextDao() {
		return msgTextDao;
	}

	public void setMsgTextDao(IMsgTextDao msgTextDao) {
		this.msgTextDao = msgTextDao;
	}

}
