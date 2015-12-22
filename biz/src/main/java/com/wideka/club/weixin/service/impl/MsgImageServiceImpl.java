package com.wideka.club.weixin.service.impl;

import com.wideka.club.api.weixin.IMsgImageService;
import com.wideka.club.framework.bo.BooleanResult;
import com.wideka.club.framework.log.Logger4jCollection;
import com.wideka.club.framework.log.Logger4jExtend;
import com.wideka.club.framework.util.LogUtil;
import com.wideka.club.weixin.dao.IMsgImageDao;
import com.wideka.weixin.api.callback.bo.Content;

/**
 * 
 * @author JiakunXu
 * 
 */
public class MsgImageServiceImpl implements IMsgImageService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(MsgImageServiceImpl.class);

	private IMsgImageDao msgImageDao;

	@Override
	public BooleanResult createMsgImage(Content content) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (content == null) {
			result.setCode("content 信息不能为空。");
			return result;
		}

		try {
			Long id = msgImageDao.createMsgImage(content);
			result.setCode(id.toString());
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(content), e);

			result.setCode("写入 weixin_tb_msg_image 表失败。");
		}

		return result;
	}

	public IMsgImageDao getMsgImageDao() {
		return msgImageDao;
	}

	public void setMsgImageDao(IMsgImageDao msgImageDao) {
		this.msgImageDao = msgImageDao;
	}

}
