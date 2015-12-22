package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IMsgLocationService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.club.weixin.dao.IMsgLocationDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class MsgLocationServiceImpl implements IMsgLocationService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(MsgLocationServiceImpl.class);

	private IMsgLocationDao msgLocationDao;

	@Override
	public BooleanResult createMsgLocation(Content content) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (content == null) {
			result.setCode("content 信息不能为空。");
			return result;
		}

		try {
			Long id = msgLocationDao.createMsgLocation(content);
			result.setCode(id.toString());
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(content), e);

			result.setCode("写入 weixin_tb_msg_location 表失败。");
		}

		return result;
	}

	public IMsgLocationDao getMsgLocationDao() {
		return msgLocationDao;
	}

	public void setMsgLocationDao(IMsgLocationDao msgLocationDao) {
		this.msgLocationDao = msgLocationDao;
	}

}
