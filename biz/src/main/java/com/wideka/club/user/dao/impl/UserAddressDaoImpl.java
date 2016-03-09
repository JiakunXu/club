package com.wideka.club.user.dao.impl;

import com.wideka.club.api.user.bo.UserAddress;
import com.wideka.club.framework.dao.impl.BaseDaoImpl;
import com.wideka.club.user.dao.IUserAddressDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class UserAddressDaoImpl extends BaseDaoImpl implements IUserAddressDao {

	@Override
	public UserAddress getUserAddress(UserAddress userAddress) {
		return (UserAddress) getSqlMapClientTemplate().queryForObject("user.address.getUserAddress", userAddress);
	}

	@Override
	public Long createUserAddress(UserAddress userAddress) {
		return (Long) getSqlMapClientTemplate().insert("user.address.createUserAddress", userAddress);
	}

	@Override
	public int removeDefaultUserAddress(UserAddress userAddress) {
		return getSqlMapClientTemplate().update("user.address.removeDefaultUserAddress", userAddress);
	}

}
