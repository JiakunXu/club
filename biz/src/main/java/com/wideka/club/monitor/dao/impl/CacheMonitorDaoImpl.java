package com.wideka.club.monitor.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.wideka.club.api.cache.bo.CacheStats;
import com.wideka.club.framework.dao.impl.BaseDaoImpl;
import com.wideka.club.monitor.dao.ICacheMonitorDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class CacheMonitorDaoImpl extends BaseDaoImpl implements ICacheMonitorDao {

	@Override
	public int getCacheMonitorCount(CacheStats cacheStats) {
		return (Integer) getSqlMapClientTemplate().queryForObject("monitor.cache.getCacheMonitorCount", cacheStats);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CacheStats> getCacheMonitorList(CacheStats cacheStats) {
		return (List<CacheStats>) getSqlMapClientTemplate().queryForList("monitor.cache.getCacheMonitorList",
			cacheStats);
	}

	@Override
	public String createCacheMonitor(final List<CacheStats> cacheStatsList) {
		return getSqlMapClientTemplate().execute(new SqlMapClientCallback<String>() {
			StringBuilder sb = new StringBuilder();

			public String doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();

				for (CacheStats s : cacheStatsList) {
					if (sb.length() != 0) {
						sb.append(",");
					}
					sb.append(executor.insert("monitor.cache.createCacheMonitor", s));
				}
				executor.executeBatch();

				return sb.toString();
			}
		});
	}

}
