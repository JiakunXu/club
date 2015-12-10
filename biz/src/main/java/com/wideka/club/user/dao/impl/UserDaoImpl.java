package com.wideka.club.user.dao.impl;

import com.wideka.club.api.user.bo.User;
import com.wideka.club.framework.dao.impl.BaseDaoImpl;
import com.wideka.club.user.dao.IUserDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class UserDaoImpl extends BaseDaoImpl implements IUserDao {

	@Override
	public User getUserByPassport(String passport) {
		return (User) getSqlMapClientTemplate().queryForObject("user.getUserByPassport", passport);
	}

}
