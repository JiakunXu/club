package com.wideka.club.framework.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.wideka.club.framework.dao.IBaseDao;

/**
 * base dao.
 * 
 * @author xujiakun
 * 
 */
public class BaseDaoImpl extends SqlMapClientDaoSupport implements IBaseDao {

	private static final String PFIX = ",";

	/**
	 * namespace.
	 */
	protected String namespace;

	/**
	 * listToString.
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private String listToString(List list) {
		StringBuilder sb = new StringBuilder();
		if (list != null) {
			int i = 0;
			for (Object id : list) {
				if (i > 0) {
					sb.append(PFIX + id);
				} else {
					sb.append(id);
				}
				i++;
			}
		}
		return sb.toString();
	}

}
