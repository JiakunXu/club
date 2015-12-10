package com.wideka.club.user.dao;

import com.wideka.club.api.user.bo.User;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IUserDao {

	/**
	 * 
	 * @param passport
	 * @return
	 */
	User getUserByPassport(String passport);

}
