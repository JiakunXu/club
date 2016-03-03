package com.wideka.club.wxpay.dao.impl;

import com.wideka.club.framework.dao.impl.BaseDaoImpl;
import com.wideka.club.wxpay.dao.IWxpayDao;
import com.wideka.weixin.api.pay.bo.WxNotify;

/**
 * 
 * @author JiakunXu
 * 
 */
public class WxpayDaoImpl extends BaseDaoImpl implements IWxpayDao {

	@Override
	public void createWxNotify(WxNotify wxNotify) {
		getSqlMapClientTemplate().insert("wxpay.createWxNotify", wxNotify);
	}

}
